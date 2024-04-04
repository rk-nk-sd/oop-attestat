package com.mygdx.game.basegame.units;

import com.mygdx.game.basegame.units.common.BaseHero;
import com.mygdx.game.basegame.units.common.ShooterAbstract;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Арбалетчик
 */
public class Arbalester extends ShooterAbstract {
    private static final String HERO_ARBALESTER_D = "Hero_Arbalester #%d";
    private int arrows;
    private final int maxArrows;
    private Arbalester(String name, int hp, Point point, int energy) {
        super(name, hp, point, energy);
        this.arrows = 10;
        this.maxArrows = this.arrows;
    }

    public Arbalester(String name, Point point) {
        this(name, Arbalester.r.nextInt(100), point, Arbalester.r.nextInt(50));
    }

    public Arbalester() {
        this(String.format(Arbalester.HERO_ARBALESTER_D, ++Arbalester.number), new Point());
    }

    /**
     * При наличии запаса стрел находит ближайшего противника и атакует
     * @param list - Принимает список команды противника
     */
    @Override
    public void step(List<BaseHero> list) {
        List<BaseHero> heroes = list.stream()
                .filter(hero -> hero.getTeam() != null
                        && (!hero.getTeam().equals(this.getTeam()) && !hero.getAllias().contains(this.getTeam()))
                        && !hero.isDie()
                        && !this.equals(hero))
                .collect(Collectors.toList());

        if (heroes.isEmpty()) {
            throw new RuntimeException("GameOver!");
        }

        BaseHero target = this.findTarget(heroes);
        if (!this.isDie() && this.arrows > 0 && target != null && !target.isDie()) {
            this.attack(target);
            this.arrows--;
            System.out.printf("%s %s -> %s %s hp:%s\n", this.getName(), this.getPoint(), target.getName(), target.getPoint(), target.getHp());
        } else if(!this.isDie()){
            System.out.printf("У арбалестера %s закончились стрелы, кол-во стрел: %d позиция: %s\n", this.getName(), this.getArrows(), this.getPoint());
        }

    }

    @Override
    public int getArrows() {
        return this.arrows;
    }

    @Override
    public int getMaxArrows() {
        return this.maxArrows;
    }

    @Override
    public void setArrows(int count) {
        if (this.arrows < this.maxArrows) this.arrows++;
    }
}
