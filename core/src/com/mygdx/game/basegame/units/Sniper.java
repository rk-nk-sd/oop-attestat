package com.mygdx.game.basegame.units;

import com.mygdx.game.basegame.units.common.BaseHero;
import com.mygdx.game.basegame.units.common.ManaAbstract;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Снайпер
 */
public class Sniper extends ManaAbstract {
    private static final String HERO_SNIPER_D = "Hero_Sniper #%d";
    private Sniper(String name, int hp, Point point, int energy) {
        super(name, hp, point, energy);
    }

    public Sniper(String name, Point point) {
        this(name, Sniper.r.nextInt(100), point, Sniper.r.nextInt(50));
    }

    public Sniper() {
        this(String.format(Sniper.HERO_SNIPER_D, ++Sniper.number), new Point());
    }

    @Override
    public void step(List<BaseHero> list) {
        List<BaseHero> heroes = list.stream()
                .filter(hero -> hero.getTeam() != null
                        && (!hero.getTeam().equals(this.getTeam()) && !hero.getAllias().contains(this.getTeam())
                        && !hero.isDie()))
                .collect(Collectors.toList());

        BaseHero target = this.findTarget(heroes);
        if (target != null && !this.isDie()) {
            this.attack(target);
        }
    }
}
