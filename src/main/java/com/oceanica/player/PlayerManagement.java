package com.oceanica.player;

import java.util.*;

import com.oceanica.datastructure.HydratingHashMap;

public class PlayerManagement {
    public static final HydratingHashMap<UUID, PlayerData> map = new HydratingHashMap<UUID, PlayerData>(
            (playerId) -> new PlayerData());
}