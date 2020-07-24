package com.oceanica.dream;

import com.oceanica.OceanicaMain;
import com.oceanica.event.EventListener;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class DreamEventListener implements EventListener<PlayerWakeUpEvent> {
    private static final String PROTOCOL_VERSION = "1";
    private static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(OceanicaMain.MOD_ID, "dream"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    public static void registerMessages() {
        CHANNEL.registerMessage(0, DreamMessage.class, DreamMessage::encode, DreamMessage::new, DreamMessage::receive);
    }

    @Override
    public void accept(final PlayerWakeUpEvent event) {
        DreamEventListener.CHANNEL.sendToServer(new DreamMessage());
    }
}