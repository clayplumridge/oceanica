package com.oceanica.world;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.oceanica.OceanicaMain;
import com.oceanica.world.structure.TempleFeature;
import com.oceanica.world.structure.TempleFeatureConfig;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class WorldGen {
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES,
            OceanicaMain.MOD_ID);
    public static final Feature<TempleFeatureConfig> TEMPLE = new TempleFeature(TempleFeatureConfig::deserialize);

    public static final Set<BiomeDictionary.Type> OCEAN_LIST = ImmutableSet.of(BiomeDictionary.Type.OCEAN);

    public static void addRegister(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

    public static void addWorldGen() {
        for (Biome biome : ForgeRegistries.BIOMES) {
            if (BiomeDictionary.getTypes(biome).stream().anyMatch(OCEAN_LIST::contains)) {
                OceanicaMain.LOGGER.info("Found Ocean-type biome: " + biome.getDisplayName());

                // Adds the temple as an ocean structure
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES,
                        TEMPLE.withConfiguration(new TempleFeatureConfig())
                                .withPlacement(Placement.CHANCE_TOP_SOLID_HEIGHTMAP.configure(new ChanceConfig(20))));
            }
        }
    }
}