package com.oceanica.patchouli;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.patchouli.api.PatchouliAPI;

public class PatchouliUtils {
    public static ItemStack getVisionsItemStack() {
        return PatchouliAPI.instance.getBookStack(new ResourceLocation("oceanica:visions"));
    }
}