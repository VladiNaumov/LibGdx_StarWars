package com.naumdeveloper.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import com.naumdeveloper.base.BaseScreen;
import com.naumdeveloper.math.Rect;
import com.naumdeveloper.sprite.Background;
import com.naumdeveloper.sprite.ButtonExit;
import com.naumdeveloper.sprite.ButtonPlay;
import com.naumdeveloper.sprite.Star;

public class MenuScreen extends BaseScreen {

    private static final int STAR_COUNT = 256;

    private final Game game;

    private Texture bg;
    private Background background;

    private TextureAtlas atlas;
    private Star[] stars;

    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;


    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(bg);

        atlas = new TextureAtlas("textures/menuAtlas.tpack");



        stars = new Star[STAR_COUNT];
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }

        buttonExit = new ButtonExit(atlas);
        buttonPlay = new ButtonPlay(atlas, game);

    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : stars) {
            star.resize(worldBounds);
        }


        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
    }


    //действие когда мы нажимаем на клавишу
    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {

        buttonExit.touchDown(touch, pointer, button);
        buttonPlay.touchDown(touch, pointer, button);
        return false;
    }

    //действие когда мы отпускаем клавишу
    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {

        buttonExit.touchUp(touch, pointer, button);
        buttonPlay.touchUp(touch, pointer, button);
        return false;
    }

    private void update(float delta) {
        for (Star star : stars) {
            star.update(delta);
        }
    }

    // отрисовка объекта на сцене
    private void draw() {
        batch.begin();
//        batch.setColor(1f, 1f, 1f, 1f);
        background.draw(batch);
        for (Star star : stars) {
//            batch.setColor(Color.YELLOW);
            star.draw(batch);
//            batch.setColor(Color.CLEAR);
        }
        buttonExit.draw(batch);

        buttonPlay.draw(batch);

        batch.end();
    }
}