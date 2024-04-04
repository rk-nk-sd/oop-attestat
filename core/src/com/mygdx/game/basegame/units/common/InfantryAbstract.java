package com.mygdx.game.basegame.units.common;

import com.mygdx.game.basegame.units.Point;
import com.mygdx.game.basegame.units.interfaces.other.Move;

public abstract class InfantryAbstract extends ManaAbstract implements Move {
    CalculeteFeaturePoint calculeteFeaturePoint;

    public InfantryAbstract(String name, int hp, Point point, int energy) {
        super(name, hp, point, energy);
        calculeteFeaturePoint = new CalculeteFeaturePoint(context, this);
    }

    @Override
    public void move(BaseHero target) {
        Point featurePoint = calculeteFeaturePoint.featurePosition(target);
        this.setPoint(featurePoint.getPointX(), featurePoint.getPointY());
    }
}
