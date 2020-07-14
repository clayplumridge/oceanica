package com.oceanica;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import com.oceanica.advancement.UseItemTrigger;
import com.oceanica.event.EventRegistration;
import com.oceanica.item.ItemRegistration;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OceanicaMain.MOD_ID)
public class OceanicaMain {
    public static final String MOD_ID = "oceanica";

    public OceanicaMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register all Oceanica event handlers
        EventRegistration.RegisterAll();

        // Attach registries
        ItemRegistration.AddRegister(modEventBus);

        // Attach Advancement Triggers
        CriteriaTriggers.register(UseItemTrigger.INSTANCE);
    }
}
