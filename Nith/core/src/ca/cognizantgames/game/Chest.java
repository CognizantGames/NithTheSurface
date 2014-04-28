package ca.cognizantgames.game;

import ca.cognizantgames.assets.NithKeyboardHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;
import java.util.Random;

public class Chest {
    Vector2 chestPos;

    NithKeyboardHandler Key;

    Random random;

    Sprite chest;

    Rectangle chestBox;

    int id;

    boolean used;

    Sound openChest;

    public Chest(NithKeyboardHandler Key, Sprite[] platform){
        random = new Random();
        this.Key = Key;
        chestPos = new Vector2();
        chest = new Sprite(new Texture(Gdx.files.internal("core/assets/images/chest.png")));
        openChest = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/open_chest.wav"));
        chestBox = new Rectangle((int) chest.getX(), (int) chest.getY(), (int) chest.getWidth(), (int) chest.getHeight());
        genRandomCoords(platform);
    }

    private void genRandomCoords(Sprite[] platform){
        int chance;
        int i;
        chance = random.nextInt((10 - 1)  + 1) + 1;
        i = random.nextInt(((platform.length-1) - 1) + 1) + 1;
        if(chance == 5) {
            chestPos.x = platform[i].getX();
            chestPos.y = platform[i].getY() + platform[i].getHeight();
        }
        chest.setPosition(chestPos.x, chestPos.y);
        chestBox.setLocation((int) chestPos.x, (int) chestPos.y);
    }

    public void createContent(){
        id = random.nextInt((3 - 1) + 1) + 1;
    }

    public void checkPlayer(Player player){
        if(player.playerBox.intersects(chestBox)) {
            if (Key.E && !used) {
                chest = new Sprite(new Texture(Gdx.files.internal("core/assets/images/chest_open.png")));
                chest.setPosition(chestPos.x, chestPos.y);
                System.out.println("Given" + id);
                player.weaponItem(id);
                used = true;
                openChest.play();
            }
        }
    }

    public void drawChest(SpriteBatch batch){
        chest.draw(batch);
    }

    /*public Sprite getSprite(){
        return this.chest;
    }*/
}
