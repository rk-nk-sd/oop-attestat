package com.mygdx.game.basegame.units.common;

import com.mygdx.game.basegame.units.Point;
import com.mygdx.game.basegame.units.interfaces.other.Arrows;


public abstract class ShooterAbstract extends ManaAbstract implements Arrows {

    public ShooterAbstract(String name, int hp, Point point, int energy) {
        super(name, hp, point, energy);
    }
}
