package com.oceanica.event;

import java.util.function.Consumer;

import com.oceanica.dream.DreamState;
import com.oceanica.player.PlayerManagement;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

public class DreamEventListener implements EventListener<PlayerWakeUpEvent> {
    private final String dreamId;
    private final Consumer<PlayerEntity> handler;

    public DreamEventListener(String dreamId, Consumer<PlayerEntity> handler) {
        this.dreamId = dreamId;
        this.handler = handler;
    }

    @Override
    public void accept(final PlayerWakeUpEvent event) {
        PlayerEntity player = event.getPlayer();
        DreamState dreamState = PlayerManagement.get(player.getUniqueID()).getDreamState(this.dreamId);

        if (dreamState.eligible && !dreamState.dreamt) {
            handler.accept(player);
        }
    }
}