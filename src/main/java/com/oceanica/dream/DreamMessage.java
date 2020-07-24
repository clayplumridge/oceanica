package com.oceanica.dream;

import java.util.function.Supplier;

import com.oceanica.advancement.DreamTrigger;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent.Context;

public class DreamMessage {
    public DreamMessage() {
    }

    public DreamMessage(PacketBuffer buf) {
    }

    public void encode(PacketBuffer buf) {
    }

    public void receive(Supplier<Context> context) {
        if (context.get().getDirection().getReceptionSide().isServer()) {
            // Enqueue so we can access off the network thread
            context.get().enqueueWork(() -> {
                ServerPlayerEntity player = context.get().getSender();
                DreamTrigger.INSTANCE.trigger(player);
            });
        }

        context.get().setPacketHandled(true);
    }
}