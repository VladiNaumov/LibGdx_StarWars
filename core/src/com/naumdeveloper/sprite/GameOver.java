package com.naumdeveloper.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import com.naumdeveloper.base.BaseSprite;
import com.naumdeveloper.math.Rect;

public class GameOver extends BaseSprite {
    private static final float HEIGHT = 0.07f;
    private static final float BOTTOM_MARGIN = 0.009f;


    public GameOver(TextureAtlas atlas) {
        super(atlas.findRegion("message_game_over"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(HEIGHT);
        setBottom(BOTTOM_MARGIN);
    }
}
