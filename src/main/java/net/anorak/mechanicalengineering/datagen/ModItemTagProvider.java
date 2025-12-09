package net.anorak.mechanicalengineering.datagen;

import net.anorak.mechanicalengineering.MechanicalEngineering;
import net.anorak.mechanicalengineering.block.ModBlocks;
import net.anorak.mechanicalengineering.item.ModItems;
import net.anorak.mechanicalengineering.util.ModTags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture,
                              CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, completableFuture, tagLookupCompletableFuture, MechanicalEngineering.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.Items.CROPS)
                .add(Items.POTATO)
                .add(Items.WHEAT)
                .add(Items.CARROT)
                .add(Items.SWEET_BERRIES)
                .add(Items.GLOW_BERRIES)
                .add(Items.PUMPKIN);

        tag(ModTags.Items.SEEDS)
                .add(Items.WHEAT_SEEDS)
                .add(Items.BEETROOT_SEEDS)
                .add(Items.MELON_SEEDS)
                .add(Items.PUMPKIN_SEEDS)
                .add(Items.TORCHFLOWER_SEEDS)
                .add(Items.PITCHER_POD);

        tag(ModTags.Items.BIOMASS_GENERATOR_BURNABLE)
                .add(Items.BEETROOT)
                .add(Items.MELON_SLICE)
                .add(Items.MELON)
                .add(ModItems.BIOMASS.get())
                .add(ModBlocks.BIOMASS_BLOCK.get().asItem())
                .addTag(ModTags.Items.CROPS)
                .addTag(ModTags.Items.SEEDS);
    }
}
