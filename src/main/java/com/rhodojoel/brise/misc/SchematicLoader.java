package com.rhodojoel.brise.misc;

import com.rhodojoel.brise.blocks.BlockGemDoorEnter;
import com.rhodojoel.brise.blocks.BlockGemDoorExit;
import com.rhodojoel.brise.init.BriseBlocks;
import com.rhodojoel.brise.init.BriseDimensions;
import mod.akrivus.kagic.init.KAGIC;
import mod.akrivus.kagic.init.ModBlocks;
import mod.akrivus.kagic.tileentity.TileEntityGalaxyPadCore;
import mod.akrivus.kagic.tileentity.TileEntityWarpPadCore;
import mod.heimrarnadalr.kagic.world.structure.Schematic;
import mod.heimrarnadalr.kagic.world.structure.StructureData;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SchematicLoader {

    public static StructureData loadSchematic(String schematic) {
        System.out.println("Schematic Load Begin");
        NBTTagCompound schematicData;
        try {
            schematicData = CompressedStreamTools.readCompressed(Schematic.class.getResourceAsStream(schematic));
            System.out.println("Schematic Decompressed");
        } catch (Exception e) {
            System.out.println("Schematic Decompression Failed");
            KAGIC.instance.chatInfoMessage("Failed to load schematic " + schematic + "; trying uncompressed read");
            try {
                File file = new File(Schematic.class.getResource(schematic).toExternalForm());
                schematicData = CompressedStreamTools.read(file);
            } catch (Exception e1) {
                KAGIC.instance.chatInfoMessage("Failed to load schematic " + schematic);
                return null;
            }
        }

        Map<BlockPos, IBlockState> structureBlocks = new HashMap<BlockPos, IBlockState>();

        boolean schematicaFormat = true;
        short length = schematicData.getShort("Length");
        short width = schematicData.getShort("Width");
        short height = schematicData.getShort("Height");
        byte[] blocks = schematicData.getByteArray("Blocks");
        byte[] additionalBlocks = null;
        HashMap<Short, String> additionalBlockNames = new HashMap<Short, String>();
        if(schematicData.hasKey("SchematicaMapping")) {
            System.out.println("Schematic Valid for Schematica");
        }
        if (schematicData.hasKey("AddBlocks") && schematicData.hasKey("SchematicaMapping")) {
            schematicaFormat = true;
            byte[] addBlocks = schematicData.getByteArray("AddBlocks");
            additionalBlocks = new byte[addBlocks.length * 2];
            for (int i = 0; i < addBlocks.length; ++i) {
                additionalBlocks[i * 2] = (byte) ((addBlocks[i] >> 4) & 0xF);
                additionalBlocks[i * 2 + 1] = (byte) (addBlocks[i] & 0xF);
            }
            System.out.println("Added Additional Blocks");
            NBTTagCompound blockNames = (NBTTagCompound) schematicData.getTag("SchematicaMapping");
            for (String name : blockNames.getKeySet()) {
                additionalBlockNames.put(blockNames.getShort(name), name);
            }
            System.out.println("Added Additional Block Names");
        }
        byte[] data = schematicData.getByteArray("Data");
        NBTTagList tileEntities = schematicData.getTagList("TileEntities", 10);
        NBTTagList entities = schematicData.getTagList("Entities", 10);

        //i = (y * Length + z) * Width + x
        for (short y = 0; y < height; ++y) {
            for (short z = 0; z < length; ++z) {
                for (short x = 0; x < width; ++x) {
                    try {
                        int index = (y * length + z) * width + x;
                        BlockPos pos = new BlockPos(x, y, z);
                        IBlockState blockState = null;
                        if (schematicaFormat && additionalBlocks != null) {
                            short blockID = (short) Byte.toUnsignedInt(blocks[index]);
                            blockID |= ((additionalBlocks[index] & 0xFF) << 8);
                            blockState = Block.getBlockFromName(additionalBlockNames.get(blockID)).getStateFromMeta(Byte.toUnsignedInt(data[index]));
                        } else {
                            blockState = Block.getBlockById(Byte.toUnsignedInt(blocks[index])).getStateFromMeta(Byte.toUnsignedInt(data[index]));
                        }
                        structureBlocks.put(pos, blockState);
                    } catch (Exception e) {
                        KAGIC.instance.chatInfoMessage("Unable to create block at " + x + ", " + y + ", " + z);
                        structureBlocks.put(new BlockPos(x, y, z), Blocks.HAY_BLOCK.getDefaultState());
                    }
                }
            }
        }
        System.out.println("Schematic Loaded");
        return new StructureData(width, height, length, structureBlocks, tileEntities, entities, schematicaFormat);
    }

    public static void teleportPlayer(EntityPlayerMP player, BlockPos pos){
        player.attemptTeleport(pos.getX(), pos.getY(), pos.getZ());
    }

    public static void transferToDimension(EntityPlayerMP player, BlockPos pos){
        player.mcServer.getPlayerList().transferPlayerToDimension(
                player, BriseDimensions.DIM_ROOMS_ID, new TeleporterRooms(player.mcServer.getWorld(BriseDimensions.DIM_ROOMS_ID)));
        player.attemptTeleport(pos.getX(), pos.getY(), pos.getZ());
    }

    public static BlockPos findExit(String schematic) {
        StructureData room = SchematicLoader.loadSchematic("/assets/brise/structures/" + schematic + ".schematic");
        BlockPos origin = new BlockPos(0, 0, 0);
        for (BlockPos offset : room.getStructureBlocks().keySet()) {
            if(room.getStructureBlocks().get(offset).getBlock() instanceof BlockGemDoorExit){
                BlockPos i = origin.add(offset);
                System.out.println("x: " + i.getX() + " y: " + i.getY() + " z: " + i.getZ());
            }
        }
        return origin;
    }

    public static void generateSchematic(String schematic, BlockPos position, EntityPlayerMP player, @Nullable BlockPos door, @Nullable World world, @Nullable Block pivot) {
        StructureData room = SchematicLoader.loadSchematic("/assets/brise/structures/" + schematic + ".schematic");
        BlockPos offsetto = new BlockPos(0, 0, 0);
        for(BlockPos offset : room.getStructureBlocks().keySet()){
            if(world.getBlockState(offset).getBlock() == pivot){
                BlockPos origin = new BlockPos(0, 0, 0);
                BlockPos i = origin.add(offset);
                offsetto = new BlockPos(offset.getX(), offset.getY(), offset.getZ());
                //.add(new BlockPos(i.getX(), i.getY(), i.getZ()));
            }
        }
        float xOffst = offsetto.getX();
        float yOffset = offsetto.getY();
        float zOffset = offsetto.getZ();
        world.setBlockState(new BlockPos(player.posX, player.posY - 1, player.posZ), BriseBlocks.CHOOSE_BLOCK.getDefaultState());
        BlockGemDoorExit ex = (BlockGemDoorExit)world.getBlockState(new BlockPos(player.posX, player.posY - 1, player.posZ)).getBlock();
        ex.gemm = schematic;
        System.out.println("Started Generation");
        BlockPos pPos = new BlockPos(position.getX() - xOffst, position.getY() - yOffset, position.getZ() - zOffset);
        if(true) {
            System.out.println("Loading complete, beginning spawn process");
            for (BlockPos offset : room.getStructureBlocks().keySet()) {
                if (room.getStructureBlocks().get(offset).getBlock() == Blocks.AIR ||
                    room.getStructureBlocks().get(offset).getBlock() == Blocks.STRUCTURE_VOID) {
                    continue;
                }
                if(room.getStructureBlocks().get(offset).getBlock() instanceof BlockGemDoorExit){
                    ((BlockGemDoorExit) room.getStructureBlocks().get(offset).getBlock()).doorPos = door;
                }
                world.setBlockState(pPos.add(offset), room.getStructureBlocks().get(offset));
            }
            System.out.println("Block spawn complete, beginning tile entity spawning process");
            for (NBTBase nbt : room.getTileEntities()) {
                TileEntity te = TileEntity.create(world, (NBTTagCompound) nbt);
                if (te != null) {
                    int x = ((NBTTagCompound) nbt).getInteger("x");
                    int y = ((NBTTagCompound) nbt).getInteger("y");
                    int z = ((NBTTagCompound) nbt).getInteger("z");
                    BlockPos tePos = new BlockPos(x, y, z);
                    if (te instanceof TileEntityGalaxyPadCore) {
                        world.setBlockState(pPos.add(tePos), ModBlocks.GALAXY_PAD_CORE.getDefaultState());
                    } else if (te instanceof TileEntityWarpPadCore) {
                        world.setBlockState(pPos.add(tePos), ModBlocks.WARP_PAD_CORE.getDefaultState());
                    } else if (te instanceof TileEntityLockableLoot) {
                        KAGIC.instance.chatInfoMessage("Found chest at unrotated pos " + x + ", " + y + ", " + z);
                        room.chests.add((TileEntityLockableLoot) te);
                    } else {
                        KAGIC.instance.chatInfoMessage("Found tile entity of type " + te.getClass().getName());
                    }
                    world.setTileEntity(pPos.add(tePos), te);
                }
            }
            System.out.println("Tile entity spawning complete, beginning entity spawning process");
            if (room.schematicaFormat) {
                KAGIC.instance.chatInfoMessage("Attempting to spawn entities from structure file");
                for (NBTBase nbt : room.getEntities()) {
                    NBTTagList nbtPos = ((NBTTagCompound) nbt).getTagList("Pos", 6);
                    double x = nbtPos.getDoubleAt(0);
                    double y = nbtPos.getDoubleAt(1);
                    double z = nbtPos.getDoubleAt(2);
                    BlockPos ePos = new BlockPos(x, y, z);
                    Entity e = EntityList.createEntityFromNBT((NBTTagCompound) nbt, world);
                    if (e != null) {
                        e.setLocationAndAngles(pPos.getX() + ePos.getX(), pPos.getY() + ePos.getY(), pPos.getZ() + ePos.getZ(), e.rotationYaw, e.rotationPitch);
                        world.spawnEntity(e);
                    }
                }
            }
            System.out.println("Generation complete");
        }
    }
}
