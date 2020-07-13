package com.oceanica.event;

import com.oceanica.dream.DreamConstants;
import com.oceanica.dream.DreamEventListener;
import com.oceanica.dream.DreamMessageUtil;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class EventRegistration {
    public static void RegisterAll() {
        RegisterOnBaseEventBus(new DreamEventListener(DreamConstants.FirstDream.id, (player) -> {
            DreamMessageUtil.ShowDreamMessage("What a strange dream I had...", player);
        }));
    }

    public static <T extends Event> void RegisterOnBaseEventBus(EventListener<T> listener) {
        MinecraftForge.EVENT_BUS.addListener(listener);
    }

    public static <T extends Event> void RegisterOnModEventBus(EventListener<T> listener) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(listener);
    }
}