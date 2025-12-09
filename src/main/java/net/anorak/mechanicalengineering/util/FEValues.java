package net.anorak.mechanicalengineering.util;

import net.anorak.mechanicalengineering.block.ModBlocks;
import net.anorak.mechanicalengineering.item.ModItems;
import net.anorak.mechanicalengineering.util.ModTags.ModTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;


public class FEValues {
    public static int getFEValue(Item item) {

        //Mod items
        if (item == ModItems.BIOMASS.get()) return 1500;

        //Mod Blocks:
        if (item == ModBlocks.BIOMASS_BLOCK.get().asItem()) return 7500;

        //Tags
        if (new ItemStack(item).is(ModTags.Items.CROPS)) return 1000;
        if (new ItemStack(item).is(ModTags.Items.SEEDS)) return 200;

        return 0;
    }
}