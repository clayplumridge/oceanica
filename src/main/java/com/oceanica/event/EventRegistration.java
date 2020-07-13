package com.oceanica.event;

import com.oceanica.dream.DreamConstants;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;

public class EventRegistration {
    public static void RegisterAll() {
        EventRegistration.Register(new DreamEventListener(DreamConstants.FirstDream.id, (player) -> {
            DreamMessageUtil.ShowDreamMessage("What a strange dream I had...", player);
        }));
    }

    public static <T extends Event> void Register(EventListener<T> listener) {
        MinecraftForge.EVENT_BUS.addListener(listener);
    }
}