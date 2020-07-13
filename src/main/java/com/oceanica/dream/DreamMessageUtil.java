package com.oceanica.dream;

import com.mojang.brigadier.LiteralMessage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentUtils;

public class DreamMessageUtil {
    public static void ShowDreamMessage(String message, PlayerEntity player) {
        ITextComponent textComponent = TextComponentUtils.toTextComponent(new LiteralMessage(message));
        player.sendStatusMessage(textComponent, false);
    }
}