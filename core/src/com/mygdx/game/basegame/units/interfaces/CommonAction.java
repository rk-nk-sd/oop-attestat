package com.mygdx.game.basegame.units.interfaces;

import com.mygdx.game.basegame.units.common.BaseHero;
import java.util.List;

public interface CommonAction extends Allias {
    void step(List<BaseHero> list);
}
