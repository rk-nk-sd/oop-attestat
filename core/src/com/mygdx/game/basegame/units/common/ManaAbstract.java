package com.mygdx.game.basegame.units.common;

import com.mygdx.game.basegame.units.Point;

public abstract class ManaAbstract extends BaseHero {
    private int mana;
    private int maxMana;

    public ManaAbstract(String name, int hp, Point point, int energy) {
        super(name, hp, point);
        this.mana = energy;
        this.maxMana = energy;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public String getInfo() {
        return String.format("%s Energy: %d", super.getInfo(), this.mana);
    }
}
