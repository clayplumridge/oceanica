package com.oceanica.dream;

public class DreamState {
    public boolean eligible = false;
    public boolean dreamt = false;

    public DreamState() {
    }

    public DreamState(boolean eligible) {
        this.eligible = eligible;
    }
}