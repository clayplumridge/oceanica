package com.oceanica.player;

import java.util.HashMap;

import com.oceanica.dream.DreamConstants;
import com.oceanica.dream.DreamState;

public class PlayerData {
    private HashMap<String, DreamState> dreamStates = new HashMap<String, DreamState>();

    public PlayerData() {
    }

    public DreamState getDreamState(String dreamId) {
        return dreamStates.computeIfAbsent(dreamId,
                (id) -> new DreamState(DreamConstants.getById(dreamId).eligibleByDefault));
    }
}