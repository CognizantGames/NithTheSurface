package ca.cognizantgames.game;

import ca.cognizantgames.screens.Win;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Exitgate {
    Vector2 position;

    Sprite image;

    Rectangle gateRect;

    Game game;

    public Exitgate(Vector2 position, Game game){
        this.game = game;
        this.position = position;
        image = new Sprite(new Texture(Gdx.files.internal("core/assets/images/exitgate.png")));
        image.setPosition(position.x, position.y);
        gateRect = new Rectangle((int) image.getX(), (int) image.getY(), (int) image.getWidth(), (int) image.getHeight());
    }
    public void playerCheck(Player player){
        if(player.playerBox.intersects(gateRect)){
            game.setScreen(new Win(game));
        }
    }
    public void drawGate(SpriteBatch batch){
        image.draw(batch);
    }
}
