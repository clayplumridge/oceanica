package com.oceanica.world.structure;

import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.world.gen.feature.IFeatureConfig;

public class TempleFeatureConfig implements IFeatureConfig {
    @Override
    public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
        return new Dynamic<>(ops);
    }

    public static TempleFeatureConfig deserialize(Dynamic<?> dynamic) {
        return new TempleFeatureConfig();
    }
}