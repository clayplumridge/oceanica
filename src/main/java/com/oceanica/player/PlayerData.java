package com.oceanica.player;

import com.oceanica.datastructure.HydratingHashMap;
import com.oceanica.dream.DreamConstants;
import com.oceanica.dream.DreamState;

public class PlayerData {
    public final HydratingHashMap<String, DreamState> dreamStates = new HydratingHashMap<String, DreamState>(
            (id) -> new DreamState(DreamConstants.getById(id).eligibleByDefault));

    public PlayerData() {
    }
}