package ca.cognizantgames.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver implements Screen{
    Game game;

    SpriteBatch spriteBatch;
    Sprite gameOver;

    float stateTime;

    public GameOver(Game game){
        this.game = game;
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
            gameOver.draw(spriteBatch);
            gameOver.setPosition(0, 0);
        spriteBatch.end();

        tick(delta);
    }

    public void tick(float delta){
        stateTime += delta;
        if(stateTime >= 2.0f) {
            if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
                System.exit(-1);
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        gameOver = new Sprite(new Texture(Gdx.files.internal("core/assets/images/gameover.png")));
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
