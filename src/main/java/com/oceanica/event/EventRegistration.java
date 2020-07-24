package com.oceanica.event;

import com.oceanica.dream.DreamEventListener;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class EventRegistration {
    public static void RegisterAll() {
        // Fires whenever a player dreams
        RegisterOnBaseEventBus(new DreamEventListener());
    }

    public static <T extends Event> void RegisterOnBaseEventBus(EventListener<T> listener) {
        MinecraftForge.EVENT_BUS.addListener(listener);
    }

    public static <T extends Event> void RegisterOnModEventBus(EventListener<T> listener) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(listener);
    }
}