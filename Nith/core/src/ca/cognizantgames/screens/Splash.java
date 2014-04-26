package ca.cognizantgames.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Splash implements Screen {
    Game game;

    Texture splash;
    SpriteBatch spriteBatch;
    float fade = 1.0f;
    float time;

    public Splash(Game game){
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(Color.WHITE.getRed(), Color.WHITE.getGreen(), Color.WHITE.getBlue(), Color.WHITE.getAlpha());
        fade -= delta / 2;
        time += delta;
        spriteBatch.begin();
        spriteBatch.draw(splash, 0, 0);
        spriteBatch.setColor(1, 1, 1, fade);
        spriteBatch.end();
        if(time >= 2){
            game.setScreen(new Menu(game));
        }else if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
            game.setScreen(new Menu(game));
        }
    }

    @Override
    public void resize(int x, int y) {

    }

    @Override
    public void show() {
        spriteBatch = new SpriteBatch();
        splash = new Texture(Gdx.files.internal("core/assets/Images/splash.png"));
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
