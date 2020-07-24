package com.oceanica.advancement;

import java.util.*;

import javax.annotation.Nonnull;

import com.google.gson.*;
import com.oceanica.OceanicaMain;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;

public class DreamTrigger implements ICriterionTrigger<DreamTrigger.Instance> {
    public static final ResourceLocation ID = new ResourceLocation(OceanicaMain.MOD_ID, "dream");
    public static final DreamTrigger INSTANCE = new DreamTrigger();
    private final Map<PlayerAdvancements, PlayerTracker> playerTrackers = new HashMap<>();

    private DreamTrigger() {
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void addListener(@Nonnull PlayerAdvancements player,
            @Nonnull ICriterionTrigger.Listener<DreamTrigger.Instance> listener) {
        this.playerTrackers.computeIfAbsent(player, DreamTrigger.PlayerTracker::new).listeners.add(listener);
    }

    @Override
    public void removeListener(@Nonnull PlayerAdvancements player,
            @Nonnull ICriterionTrigger.Listener<DreamTrigger.Instance> listener) {
        DreamTrigger.PlayerTracker tracker = this.playerTrackers.get(player);

        if (tracker != null) {
            tracker.listeners.remove(listener);

            if (tracker.listeners.isEmpty()) {
                this.playerTrackers.remove(player);
            }
        }
    }

    @Override
    public void removeAllListeners(@Nonnull PlayerAdvancements player) {
        playerTrackers.remove(player);
    }

    @Nonnull
    @Override
    public DreamTrigger.Instance deserializeInstance(@Nonnull JsonObject json,
            @Nonnull JsonDeserializationContext context) {
        return new DreamTrigger.Instance();
    }

    public void trigger(ServerPlayerEntity player) {
        DreamTrigger.PlayerTracker tracker = playerTrackers.get(player.getAdvancements());

        if (tracker != null) {
            tracker.trigger();
        }
    }

    static class PlayerTracker {
        private final PlayerAdvancements playerAdvancements;
        final Set<Listener<Instance>> listeners = new HashSet<>();

        PlayerTracker(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public void trigger() {
            this.listeners.stream().filter((testListener) -> testListener.getCriterionInstance().test())
                    .forEach((listener) -> listener.grantCriterion(this.playerAdvancements));
        }
    }

    static class Instance implements ICriterionInstance {
        Instance() {
        }

        @Nonnull
        @Override
        public ResourceLocation getId() {
            return ID;
        }

        boolean test() {
            return true;
        }
    }
}