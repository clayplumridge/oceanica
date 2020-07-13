package com.oceanica.player;

import java.util.*;

public class PlayerManagement {
    private static HashMap<UUID, PlayerData> playerData = new HashMap<UUID, PlayerData>();

    public static PlayerData get(UUID id) {
        return playerData.computeIfAbsent(id, (playerId) -> new PlayerData());
    }
}