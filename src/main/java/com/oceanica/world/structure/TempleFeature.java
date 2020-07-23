package com.oceanica.world.structure;

import java.util.Random;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.Dynamic;
import com.oceanica.OceanicaMain;
import com.oceanica.patchouli.PatchouliUtils;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.feature.Feature;

public class TempleFeature extends Feature<TempleFeatureConfig> {
    private static int INTERNAL_LENGTH = 7;
    private static int INTERNAL_WIDTH = 7;
    private static int INTERNAL_HEIGHT = 4;

    public TempleFeature(Function<Dynamic<?>, ? extends TempleFeatureConfig> configFactoryIn) {
        super(configFactoryIn);
    }

    @Override
    public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand,
            BlockPos corner, TempleFeatureConfig config) {
        OceanicaMain.LOGGER.info("Starting generation for a Temple at location " + corner.toString());

        BlockState air = Blocks.AIR.getDefaultState();
        BlockState waterSource = Blocks.WATER.getDefaultState();
        BlockState prismarineBrick = Blocks.PRISMARINE_BRICKS.getDefaultState();
        BlockState lantern = Blocks.LANTERN.getDefaultState();
        BlockState chest = Blocks.CHEST.getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, Direction.SOUTH);

        // Floor / Ceiling
        BlockPos floorCorner = corner.add(0, 2, 0);
        for (int xMod = 0; xMod < INTERNAL_LENGTH; xMod++) {
            for (int zMod = 0; zMod < INTERNAL_WIDTH; zMod++) {
                BlockPos floorPosition = floorCorner.add(xMod, 0, zMod);
                BlockPos ceilingPosition = floorPosition.add(0, INTERNAL_HEIGHT + 1, 0);

                boolean isCenterX = xMod >= 2 && xMod < INTERNAL_LENGTH - 2;
                boolean isCenterZ = zMod >= 2 && zMod < INTERNAL_WIDTH - 2;
                if (isCenterX && isCenterZ) {
                    worldIn.setBlockState(floorPosition, waterSource, 2);
                    worldIn.setBlockState(ceilingPosition, waterSource, 2);
                } else {
                    worldIn.setBlockState(floorPosition, prismarineBrick, 2);
                    worldIn.setBlockState(ceilingPosition, prismarineBrick, 2);
                }
            }
        }

        // Legs
        BlockPos legCorner = floorCorner.add(0, -1, 0);
        ImmutableList<BlockPos> startPositions = ImmutableList.of(legCorner, legCorner.add(INTERNAL_LENGTH - 1, 0, 0),
                legCorner.add(INTERNAL_LENGTH - 1, 0, INTERNAL_WIDTH - 1), legCorner.add(0, 0, INTERNAL_WIDTH - 1));

        for (BlockPos position : startPositions) {
            BlockState testBlock = worldIn.getBlockState(position);
            while (testBlock == Blocks.WATER.getDefaultState()) {
                worldIn.setBlockState(position, prismarineBrick, 2);
                position = position.add(0, -1, 0);
                testBlock = worldIn.getBlockState(position);
            }
        }

        // N/S Wall (5x4x0)
        BlockPos wallCorner = floorCorner.add(-1, 1, 0);
        for (int yMod = 0; yMod < INTERNAL_HEIGHT; yMod++) {
            for (int zMod = 0; zMod < INTERNAL_WIDTH; zMod++) {
                worldIn.setBlockState(wallCorner.add(0, yMod, zMod), prismarineBrick, 2);
                worldIn.setBlockState(wallCorner.add(INTERNAL_LENGTH + 1, yMod, zMod), prismarineBrick, 2);
            }
        }

        // E/W Wall (0x4x5)
        wallCorner = floorCorner.add(0, 1, -1);
        for (int yMod = 0; yMod < INTERNAL_HEIGHT; yMod++) {
            for (int xMod = 0; xMod < INTERNAL_LENGTH; xMod++) {
                worldIn.setBlockState(wallCorner.add(xMod, yMod, 0), prismarineBrick, 2);
                worldIn.setBlockState(wallCorner.add(xMod, yMod, INTERNAL_WIDTH + 1), prismarineBrick, 2);
            }
        }

        // Interior (6x4x6)
        BlockPos interiorCorner = floorCorner.add(0, 1, 0);
        for (int xMod = 0; xMod < INTERNAL_LENGTH; xMod++) {
            for (int yMod = 0; yMod < INTERNAL_HEIGHT; yMod++) {
                for (int zMod = 0; zMod < INTERNAL_WIDTH; zMod++) {
                    worldIn.setBlockState(interiorCorner.add(xMod, yMod, zMod), air, 2);
                }
            }
        }

        // Lantern decorations
        BlockPos lanternCorner = floorCorner.add(0, INTERNAL_HEIGHT, 0);
        ImmutableList
                .of(lanternCorner, lanternCorner.add(INTERNAL_LENGTH - 1, 0, 0),
                        lanternCorner.add(INTERNAL_LENGTH - 1, 0, INTERNAL_WIDTH - 1),
                        lanternCorner.add(0, 0, INTERNAL_WIDTH - 1))
                .forEach(pos -> worldIn.setBlockState(pos, lantern, 2));

        // Lectern decoration (using chest for now)
        BlockPos chestPos = interiorCorner.add(INTERNAL_LENGTH / 2, 0, 0);
        worldIn.setBlockState(chestPos, chest, 3);

        ChestTileEntity entity = ((ChestTileEntity) worldIn.getTileEntity(chestPos));
        entity.setInventorySlotContents(0, PatchouliUtils.getVisionsItemStack());

        return true;
    }
}