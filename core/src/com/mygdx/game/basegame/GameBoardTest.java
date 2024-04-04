package com.mygdx.game.basegame;

import com.mygdx.game.basegame.base.TeamInit;
import com.mygdx.game.basegame.units.common.BaseHero;
import com.mygdx.game.basegame.base.GameBoard;
import com.mygdx.game.basegame.units.common.Heroes;

import java.util.*;

public class GameBoardTest {
    private boolean endGame;
    List<BaseHero> list;
    GameBoard worldWar;

    public GameBoardTest() {
        this.list = new TeamInit().teamInitialized("Troya");
        list.addAll(new TeamInit().teamInitialized("MongoloTatarskoeIgo"));

        worldWar = new GameBoard(list);
    }

    public boolean start() {
//        while (!this.endGame) {
            try {
                List<BaseHero> heroes = new ArrayList<>(this.worldWar.getListHeroes());
                heroes.sort((o1, o2) -> Heroes.valueOf(o2.getClass().getSimpleName()).getSpeed()
                        - Heroes.valueOf(o1.getClass().getSimpleName()).getSpeed());
                for (BaseHero hero : heroes) {
                    hero.step(this.worldWar.getListHeroes());
                }
            } catch (RuntimeException e) {
                this.endGame = Boolean.TRUE;
                System.out.println("END_GAME");
            }
//        }
        return this.endGame;
    }
}
