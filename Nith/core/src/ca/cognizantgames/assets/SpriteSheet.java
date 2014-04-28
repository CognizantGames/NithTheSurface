package ca.cognizantgames.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet {
    Texture sheet;
    TextureRegion[][] sprite;

    public SpriteSheet(String location, int spriteWidth, int spriteHeight) {
        sheet = new Texture(Gdx.files.internal(location));
        sprite = TextureRegion.split(sheet, spriteWidth, spriteHeight);
    }

    public TextureRegion getTexture(int x, int y) {
        return sprite[y][x];
    }

    public Sprite getSprite(int x, int y) {
        return new Sprite(sprite[y][x]);
    }

    public void dispose() {
        sheet.dispose();
    }
}
