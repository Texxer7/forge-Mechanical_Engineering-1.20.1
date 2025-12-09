package net.anorak.mechanicalengineering.util.ModTags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModTags {
    public static class Items {
        public static final TagKey<Item> CROPS = tag("crops");
        public static final TagKey<Item> SEEDS = tag("seeds");
        public static final TagKey<Item> BIOMASS_GENERATOR_BURNABLE = tag("biomass_generator_burnable");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(
                    Registries.ITEM,
                    ResourceLocation.fromNamespaceAndPath("mechanical_engineering", name)
            );
        }
    }
}