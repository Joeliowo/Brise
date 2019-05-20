package com.rhodojoel.brise.misc;

import mod.akrivus.kagic.items.ItemActiveGemBase;
import mod.akrivus.kagic.items.ItemGem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class GemDisGUI extends Container {
    public IInventory items;

    public GemDisGUI(InventoryPlayer inventory, IInventory items) {
        this.items = items;
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(items, i, 8 + i * 18, 18) {
                public boolean isItemValid(ItemStack stack) {
                    return stack.getItem() instanceof ItemGem;
                }
            });
        }
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int l = 0; l < 9; ++l) {
            this.addSlotToContainer(new Slot(inventory, l, 8 + l * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.items.isUsableByPlayer(player);
    }
}
