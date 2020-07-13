package com.oceanica.item;

import java.util.List;

import javax.annotation.Nullable;

import com.oceanica.OceanicaMain;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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

    private String getFlavor() {
        return this.getResourceString("flavor");
    }

    private String getResourceString(String suffix) {
        return "item." + OceanicaMain.MOD_ID + "." + this.id + "." + suffix;
    }
}