package com.naumdeveloper.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.naumdeveloper.base.Sprite;
import com.naumdeveloper.math.Rect;

public class Chip extends Sprite {

    private final Vector2 v;

    private final Vector2 touch;

    // скорость движения объекта
    private static final float V_LEN = 0.01f;

    // размер объкта
    private static final float HEIGHT = 0.15f;


    public Chip(Texture texture) {
        super(new TextureRegion(texture));
        v = new Vector2();
        touch = new Vector2();
    }

    // позиционирование объекта
    @Override
    public void resize(Rect worldBounds) {
        // размер объкта
        setHeightProportion(HEIGHT);
    }

    // движение объекта
    @Override
    public void update(float delta) {
        if(touch.dst(pos) > V_LEN){
            pos.add(v);
        } else {
            pos.set(touch);
        }
    }

    // передача событий
    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        this.touch.set(touch);
        v.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return false;
    }
}