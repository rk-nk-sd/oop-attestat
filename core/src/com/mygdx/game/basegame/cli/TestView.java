package com.mygdx.game.basegame.cli;

import com.mygdx.game.basegame.base.BaseContext;
import com.mygdx.game.basegame.units.Point;
import com.mygdx.game.basegame.units.common.BaseHero;

import java.util.List;
import java.util.stream.Collectors;

public class TestView {
    private static int step = 1;
    BaseContext context;

    public TestView(BaseContext context) {
        this.context = context;
    }

    public void view() {

        System.out.println("\n\n" + context.getBoardSize() + "\n");
        System.out.println("Список героев: " + context.getListHeroes().stream().map(h -> {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(h.getClass().getSimpleName());
            stringBuilder.append(" ");
            stringBuilder.append(h.getPoint().toString());
            return stringBuilder;
        }).collect(Collectors.toList()) + "\n");
        BaseHero baseHero = context.getListHeroes().stream().findFirst().orElse(null);
        List<BaseHero> group1 = context.getListHeroes().stream().filter(h -> h.getTeam().equals(baseHero.getTeam()) || baseHero.getAllias().contains(h.getTeam())).collect(
                Collectors.toList());

        System.out.println("Группа союзников: " + AnsiColors.ANSI_GREEN + group1.stream().map(BaseHero::getTeam).distinct().collect(
                Collectors.toList()) + AnsiColors.ANSI_RESET);

        System.out.println("Группа противников: " + AnsiColors.ANSI_BLUE + context.getListHeroes().stream().filter(h -> !group1.stream().map(BaseHero::getTeam).collect(
                Collectors.toList()).contains(h.getTeam())).map(BaseHero::getTeam).distinct().collect(Collectors.toList()) + AnsiColors.ANSI_RESET);

        System.out.println();

        System.out.println("Step_" + step++ + "\n");
        System.out.print("|");
        for (int i = 0; i < 10; i++) {
            System.out.printf("-%d-|",i);
        }
        System.out.println();
        for (int i = 0; i < context.getBoardSize().getY() + 1; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < context.getBoardSize().getX() + 1; j++) {
                sb.append("| ");
                for (BaseHero hero : context.getListHeroes()) {
                    if (hero.getPoint().equals(new Point(j,i))) {
                        if (group1.contains(hero) && !hero.isDie()) {
                            sb.append(AnsiColors.ANSI_GREEN + hero.getClass().getSimpleName().charAt(0) + AnsiColors.ANSI_RESET);
                        } else if (!hero.isDie()){
                            sb.append(AnsiColors.ANSI_BLUE + hero.getClass().getSimpleName().charAt(0) + AnsiColors.ANSI_RESET);
                        } else {
                            sb.append(AnsiColors.ANSI_RED + hero.getClass().getSimpleName().charAt(0) + AnsiColors.ANSI_RESET);
                        }
                    }
                }
                sb.append(" ");
            }
            sb.append("|");
            System.out.println(sb.toString().replace("  ", "   "));
        }
    }
}
