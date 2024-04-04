package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.basegame.GameBoardTest;
import com.mygdx.game.basegame.base.BaseContext;
import com.mygdx.game.basegame.units.common.BaseHero;
import com.mygdx.game.fons.Fons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture fon;
	Music music;
	GameBoardTest gameBoardTest;
	BaseContext context;
	Texture FC_Arbalester, FC_Farmer, FC_Lancer, FC_Monk, SC_Arbalester, SC_Farmer, SC_Lancer, SC_Monk, Die;
	String fc;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fon = new Texture("fons/" + Fons.values()[new Random().nextInt(Fons.values().length)] + ".png");
		music = Gdx.audio.newMusic(Gdx.files.internal("music/Timati_Garik_Sukachjov_-_Vse_bylo_ne_zrya_50198222.mp3"));
		music.setVolume(0.125f);
		music.play();
		gameBoardTest = new GameBoardTest();
		context = BaseContext.getInstance();
		FC_Arbalester = new Texture("units/FC_Arbalester.png");
		FC_Farmer = new Texture("units/FC_Farmer.png");
		FC_Lancer = new Texture("units/FC_Lancer.png");
		FC_Monk = new Texture("units/FC_Monk.png");
		SC_Arbalester = new Texture("units/SC_Arbalester.png");
		SC_Farmer = new Texture("units/SC_Farmer.png");
		SC_Lancer = new Texture("units/SC_Lancer.png");
		SC_Monk = new Texture("units/SC_Monk.png");
		Die = new Texture("units/die.png");
		fc = context.getListHeroes().stream().map(h -> {
			return h.getTeam();
		}).findAny().orElse("");
		System.out.println(fc);
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(fon, 0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		for(BaseHero hero : context.getListHeroes()) {
			String command;
			if (hero.isDie()) {
				command = "die";
			} else if (hero.getTeam().equals(fc)) {
				command = "FC_" + hero.getClass().getSimpleName();
			} else {
				command = "SC_" + hero.getClass().getSimpleName();
			}

			float x = hero.getPoint().getPointX() * 64;
			float y = hero.getPoint().getPointY() * ((Gdx.graphics.getHeight() / 2.f) / 6.f);
			switch (command) {
				case "FC_Arbalester":
					batch.draw(FC_Arbalester, x,y);
					break;
				case "SC_Arbalester":
					batch.draw(SC_Arbalester,  x,y);
					break;
				case "FC_Farmer":
					batch.draw(FC_Farmer,  x,y);
					break;
				case "SC_Farmer":
					batch.draw(SC_Farmer,  x,y);
					break;
				case "FC_Lancer":
					batch.draw(FC_Lancer,  x,y);
					break;
				case "SC_Lancer":
					batch.draw(SC_Lancer,  x,y);
					break;
				case "Magician":
					batch.draw(fon,  x,y);
					break;
				case "FC_Monk":
					batch.draw(FC_Monk,  x,y);
					break;
				case "SC_Monk":
					batch.draw(SC_Monk,  x,y);
					break;
				case "Priest":
					batch.draw(fon,  x,y);
					break;
				case "Robber":
					batch.draw(fon,  x,y);
					break;
				case "Sniper":
					batch.draw(fon,  x,y);
					break;
				case "die":
					batch.draw(Die,x,y);
			}
		}

		batch.end();

		if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
			gameBoardTest.start();
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		fon.dispose();
		FC_Arbalester.dispose();
		FC_Farmer.dispose();
		FC_Lancer.dispose();
		FC_Monk.dispose();
		SC_Arbalester.dispose();
		SC_Farmer.dispose();
		SC_Lancer.dispose();
		SC_Monk.dispose();
		Die.dispose();
	}
}
