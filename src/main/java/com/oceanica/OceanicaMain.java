package com.oceanica;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.oceanica.advancement.UseItemTrigger;
import com.oceanica.event.EventRegistration;
import com.oceanica.item.ItemRegistration;
import com.oceanica.world.WorldGen;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(OceanicaMain.MOD_ID)
public class OceanicaMain {
    public static final Logger LOGGER = LogManager.getLogger(OceanicaMain.MOD_ID);

    public static final String MOD_ID = "oceanica";

    public OceanicaMain() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        // Register all Oceanica event handlers
        EventRegistration.RegisterAll();

        // Attach registries
        ItemRegistration.AddRegister(modEventBus);
        WorldGen.addRegister(modEventBus);

        // Attach Advancement Triggers
        CriteriaTriggers.register(UseItemTrigger.INSTANCE);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        // Updates biomes to include features
        WorldGen.addWorldGen();
    }
}
