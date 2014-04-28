package ca.cognizantgames.game;

import ca.cognizantgames.assets.NithKeyboardHandler;
import ca.cognizantgames.assets.SpriteSheet;
import ca.cognizantgames.screens.SinglePlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Player {
    Vector2 playerPos;

    NithKeyboardHandler Key;

    public Rectangle playerBox, playerLeft, playerRight;

    Animation WalkLeft, WalkRight;
    TextureRegion textureRegion;

    SpriteSheet spriteSheet;
    SpriteSheet weapons;

    public Rectangle weaponBox;

    public static final int GRAVITY = 4;
    int downwardVelocity;
    int move;
    int weaponId = 1;
    public int health = 10;

    Sprite weapon;

    Sound jump;

    boolean canJump;
    boolean holding;
    boolean played;

    float stateTime;
    float jumpTime;

    public Player(NithKeyboardHandler Key){
        this.Key = Key;
        spriteSheet = new SpriteSheet("core/assets/spritesheets/character.png", 32, 48);
        weapons = new SpriteSheet("core/assets/spritesheets/weapons.png", 16, 16);
        weapon = weapons.getSprite(2, 0);
        WalkLeft = new Animation(0.075f, spriteSheet.getTexture(0, 0), spriteSheet.getTexture(1, 0), spriteSheet.getTexture(2, 0), spriteSheet.getTexture(3, 0));
        WalkRight = new Animation(0.075f, spriteSheet.getTexture(0, 1), spriteSheet.getTexture(1, 1), spriteSheet.getTexture(2, 1), spriteSheet.getTexture(3, 1));
        stateTime = 0f;
        playerPos = new Vector2(0, 0);
        playerBox = new Rectangle((int) playerPos.x - 32, (int) playerPos.y + 84, 32, 48);
        playerLeft = new Rectangle((int) playerPos.x - 34, (int) playerPos.y + 84, 2, 40);
        playerRight = new Rectangle((int) playerPos.x + 4, (int) playerPos.y + 84, 2, 40);
        weaponBox = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 10, 10);

        jump = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/jump.wav"));
    }

    public void checkMove(float delta, Rectangle[] floor, Chest[] chests){
        for (Chest chest : chests) {
            chest.checkPlayer(this);
        }
        stateTime += delta;
        jumpTime += delta;

        downwardVelocity = GRAVITY;

        if(Key.D){
            move = SinglePlayer.SPEED;
            textureRegion = WalkRight.getKeyFrame(stateTime, true);
        }else if(Key.A){
            move = -SinglePlayer.SPEED;
            textureRegion = WalkLeft.getKeyFrame(stateTime, true);
        }else{
            move = 0;
        }
        if(!Key.D && !Key.A) {
            textureRegion = spriteSheet.getTexture(0, 0);
        }else if(Key.A && Key.D){
            move = 0;
        }

        for (Rectangle aFloor : floor) {
            if(playerBox.intersects(aFloor)){
                downwardVelocity = 0;
                jumpTime = 0;
                canJump = true;
            }
            if(playerLeft.intersects(aFloor) && !Key.D){
                move = 0;
            }
            if(playerRight.intersects(aFloor) && !Key.A){
                move = 0;
            }
        }

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            switch (weaponId){
                case 1 : weapon = weapons.getSprite(3, 0);
                         weapon.setScale(2);
                    break;
                case 2 : weapon = weapons.getSprite(1, 0);
                         weapon.setScale(2);
                    break;
            }
        }else{
            switch (weaponId){
                case 1 : weapon = weapons.getSprite(2, 0);
                         weapon.setScale(2);
                    break;
                case 2 : weapon = weapons.getSprite(0, 0);
                         weapon.setScale(2);
                    break;
            }
        }

        if(playerPos.x <= 200 && !Key.D){
            move = 0;
        }else if(playerPos.x >= 6000 && !Key.A){
            move = 0;
        }

        if(Key.SPACE && canJump && !holding){
            downwardVelocity -= 11;
            jumpTime  += delta;
            if(!played) {
                jump.play(0.5f);
                played = true;
            }
            if(jumpTime > 0.4) {
                canJump = false;
                holding = true;
            }
        }else if(!Key.SPACE){
            holding = false;
            played = false;
        }

        playerPos.x += move;
        playerPos.y -= downwardVelocity;
        playerBox.setLocation((int) playerPos.x, (int) playerPos.y);
        playerLeft.setLocation((int) playerPos.x, (int) playerPos.y+8);
        playerRight.setLocation((int) playerPos.x + 32, (int) playerPos.y+8);
        if(Gdx.input.getX() > Gdx.graphics.getWidth()/2){
            weapon.setPosition(playerPos.x + 35, playerPos.y + 20);
            weapon.setFlip(false, false);
        }else if(Gdx.input.getX() < Gdx.graphics.getWidth()/2){
            weapon.setPosition(playerPos.x - 20, playerPos.y + 20);
            weapon.setFlip(true, false);
        }
        weaponBox.setLocation((int) weapon.getX(), (int) weapon.getY());
    }

    public void weaponItem(int id){
        // start off with weapon 1, rapier
        switch(id){
            case 1 : weapon = weapons.getSprite(2, 0);
                weaponId = id;
                break;
            case 2 : weapon = weapons.getSprite(0, 0);
                weaponId = id;
                break;
            case 3 : health = 10;
                break;
        }
    }

    public void drawWeapon(SpriteBatch batch){
        weapon.draw(batch);
        weapon.setScale(2);
    }

    public TextureRegion getCurrentFrame(){
        return textureRegion;
    }

    public void setPlayerPos(Vector2 playerPos){
        this.playerPos = playerPos;
    }

    public Vector2 getPlayerPos(){
        return this.playerPos;
    }
}
