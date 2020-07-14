package com.oceanica.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.oceanica.OceanicaMain;
import com.oceanica.advancement.UseItemTrigger;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.World;

public class DreamNotes extends Item {
    private final String id;

    public DreamNotes(String id) {
        super(new Properties().maxStackSize(1).group(ItemGroup.MISC));

        this.id = id;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip,
            ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent(this.getFlavor()).applyTextStyle(TextFormatting.GRAY));
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);

        if (playerIn instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) playerIn;
            UseItemTrigger.INSTANCE.trigger(player, stack, player.getServerWorld(), player.getPosX(), player.getPosY(),
                    player.getPosZ());
        }

        return new ActionResult<>(ActionResultType.SUCCESS, stack);
    }

    private String getFlavor() {
        return this.getResourceString("flavor");
    }

    private String getResourceString(String suffix) {
        return "item." + OceanicaMain.MOD_ID + "." + this.id + "." + suffix;
    }
}