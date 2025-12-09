package net.anorak.mechanicalengineering.datagen;

import net.anorak.mechanicalengineering.MechanicalEngineering;
import net.anorak.mechanicalengineering.block.ModBlocks;
import net.anorak.mechanicalengineering.item.ModItems;
import net.anorak.mechanicalengineering.util.ModTags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.RUBY_BLOCK.get())
                .pattern("RRR")
                .pattern("RRR")
                .pattern("RRR")
                .define('R', ModItems.RUBY.get())
                .unlockedBy(getHasName(ModItems.RUBY.get()), has(ModItems.RUBY.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.RUBY.get(), 9)
                .requires(ModBlocks.RUBY_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.RUBY_BLOCK.get()), has(ModBlocks.RUBY_BLOCK.get()))
                .save(pWriter);

//*************** Biomass stuff ****************************************************************************************//
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BIOMASS_BLOCK.get())
                .pattern("BB")
                .pattern("BB")
                .define('B', ModItems.BIOMASS.get())
                .unlockedBy(getHasName(ModItems.BIOMASS.get()), has(ModItems.BIOMASS.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BIOMASS.get(), 4)
                .requires(ModBlocks.BIOMASS_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.BIOMASS_BLOCK.get()), has(ModBlocks.BIOMASS_BLOCK.get()))
                .save(pWriter, MechanicalEngineering.MOD_ID + ":biomass_from_biomass_block");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BIOMASS.get(), 2)
                .requires(Ingredient.of(ModTags.Items.CROPS))
                .unlockedBy("has_crops", has(ModTags.Items.CROPS))
                .save(pWriter, MechanicalEngineering.MOD_ID + ":biomass_from_crops");

//*************** Other ****************************************************************************************//
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.PUMPKIN_SEEDS, 4)
                .pattern("PS")
                .define('P', Items.PUMPKIN)
                .define('S', Items.SHEARS)
                .unlockedBy(getHasName(Items.PUMPKIN), has(Items.PUMPKIN))
                .save(pWriter);

//*************** Block Entities****************************************************************************************//
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.GEM_POLISHING_STATION.get(), 1)
                .pattern("SSS")
                .pattern("CDC")
                .pattern("C C")
                .define('S', Items.SMOOTH_STONE_SLAB)
                .define('C', Items.CALCITE)
                .define('D', Items.DIAMOND)
                .unlockedBy(getHasName(Items.DIAMOND), has(Items.DIAMOND))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.BIOMASS_GENERATOR.get(), 1)
                .pattern("SSS")
                .pattern("SBS")
                .pattern("IRI")
                .define('S', Items.STONE)
                .define('B', ModItems.BIOMASS.get())
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE_BLOCK)
                .unlockedBy(getHasName(ModItems.BIOMASS.get()), has(ModItems.BIOMASS.get()))
                .save(pWriter);
    }
}