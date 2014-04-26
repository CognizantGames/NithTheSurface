package ca.cognizantgames.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

public class Menu implements Screen {
    Game game;

    public Menu(Game game){
        this.game = game;
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        game.setScreen(new SinglePlayer(game));
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
