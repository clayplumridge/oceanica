package com.oceanica.world.structure;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;
import com.oceanica.OceanicaMain;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;

public class TempleFeature extends Feature<TempleFeatureConfig> {
    public TempleFeature(Function<Dynamic<?>, ? extends TempleFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
            BlockPos pos, TempleFeatureConfig config) {
        OceanicaMain.LOGGER.info("Starting generation for a Temple at location " + pos.toString());

        BlockState cobble = Blocks.COBBLESTONE.getDefaultState();

        for (int i = 0; i < 5; i++) {
            BlockPos posAdjusted = new BlockPos(pos.getX(), pos.getY() + i, pos.getZ());
            worldIn.setBlockState(posAdjusted, cobble, 2);
        }

        return true;
    }

}