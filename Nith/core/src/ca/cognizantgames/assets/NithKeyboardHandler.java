package ca.cognizantgames.assets;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class NithKeyboardHandler implements InputProcessor{
    public boolean W, S, A, D, E, SPACE;
    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.W){
            W = true;
            return true;
        }
        if(keycode == Input.Keys.S){
            S = true;
            return true;
        }
        if(keycode == Input.Keys.A){
            A = true;
            return true;
        }
        if(keycode == Input.Keys.D){
            D = true;
            return true;
        }
        if(keycode == Input.Keys.SPACE){
            SPACE = true;
            return true;
        }
        if(keycode == Input.Keys.E){
            E = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.W){
            W = false;
            return true;
        }
        if(keycode == Input.Keys.S){
            S = false;
            return true;
        }
        if(keycode == Input.Keys.A){
            A = false;
            return true;
        }
        if(keycode == Input.Keys.D){
            D = false;
            return true;
        }
        if(keycode == Input.Keys.E){
            E = false;
            return true;
        }
        if(keycode == Input.Keys.SPACE){
            SPACE = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
