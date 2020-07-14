package com.oceanica.advancement;

import java.util.*;

import javax.annotation.Nonnull;

import com.google.gson.*;
import com.oceanica.OceanicaMain;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;

public class UseItemTrigger implements ICriterionTrigger<UseItemTrigger.Instance> {
    public static final ResourceLocation ID = new ResourceLocation(OceanicaMain.MOD_ID, "use_item");
    public static final UseItemTrigger INSTANCE = new UseItemTrigger();
    private final Map<PlayerAdvancements, PlayerTracker> playerTrackers = new HashMap<>();

    private UseItemTrigger() {
    }

    @Nonnull
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    @Override
    public void addListener(@Nonnull PlayerAdvancements player,
            @Nonnull ICriterionTrigger.Listener<UseItemTrigger.Instance> listener) {
        this.playerTrackers.computeIfAbsent(player, UseItemTrigger.PlayerTracker::new).listeners.add(listener);
    }

    @Override
    public void removeListener(@Nonnull PlayerAdvancements player,
            @Nonnull ICriterionTrigger.Listener<UseItemTrigger.Instance> listener) {
        UseItemTrigger.PlayerTracker tracker = this.playerTrackers.get(player);

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
    public UseItemTrigger.Instance deserializeInstance(@Nonnull JsonObject json,
            @Nonnull JsonDeserializationContext context) {
        return new UseItemTrigger.Instance(ItemPredicate.deserialize(json.get("item")),
                LocationPredicate.deserialize(json.get("location")));
    }

    public void trigger(ServerPlayerEntity player, ItemStack stack, ServerWorld world, double x, double y, double z) {
        UseItemTrigger.PlayerTracker tracker = playerTrackers.get(player.getAdvancements());

        if (tracker != null) {
            tracker.trigger(player, stack, world, x, y, z);
        }
    }

    static class PlayerTracker {
        private final PlayerAdvancements playerAdvancements;
        final Set<Listener<Instance>> listeners = new HashSet<>();

        PlayerTracker(PlayerAdvancements playerAdvancementsIn) {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public void trigger(ServerPlayerEntity player, ItemStack stack, ServerWorld world, double x, double y,
                double z) {
            this.listeners.stream()
                    .filter((testListener) -> testListener.getCriterionInstance().test(stack, world, x, y, z))
                    .forEach((listener) -> listener.grantCriterion(this.playerAdvancements));
        }
    }

    static class Instance implements ICriterionInstance {
        private final ItemPredicate item;
        private final LocationPredicate location;

        Instance(ItemPredicate item, LocationPredicate loc) {
            this.item = item;
            this.location = loc;
        }

        @Nonnull
        @Override
        public ResourceLocation getId() {
            return ID;
        }

        boolean test(ItemStack stack, ServerWorld world, double x, double y, double z) {
            return this.item.test(stack) && this.location.test(world, x, y, z);
        }
    }
}