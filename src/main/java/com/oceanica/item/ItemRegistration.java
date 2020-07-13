package com.oceanica.item;

import com.oceanica.OceanicaMain;

import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistration {
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS,
            OceanicaMain.MOD_ID);

    public static final String FIRST_DREAM_NOTES_ID = "first-dream-notes";
    public static final RegistryObject<Item> FIRST_DREAM_NOTES = ITEMS.register(FIRST_DREAM_NOTES_ID,
            () -> new DreamNotes(FIRST_DREAM_NOTES_ID));

    public static void AddRegister(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}