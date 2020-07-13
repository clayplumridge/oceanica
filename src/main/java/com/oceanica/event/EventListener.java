package com.oceanica.event;

import java.util.function.Consumer;

import net.minecraftforge.eventbus.api.Event;

public interface EventListener<T extends Event> extends Consumer<T> {
}