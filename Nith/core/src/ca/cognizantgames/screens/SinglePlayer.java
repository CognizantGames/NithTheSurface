/**
 * This is the single player class the game drawing is done here!
 * @author Demetry Romanowski
 * demetryromanowski@gmail.com
 * 4262014
 */

package ca.cognizantgames.screens;

import ca.cognizantgames.assets.NithKeyboardHandler;
import ca.cognizantgames.game.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

public class SinglePlayer implements Screen {
    Game game;

    SpriteBatch batch;
    //Start declaring resources here!
    TerrainGen gen;
    Sprite[] backGround;
    Sprite[] platform;
    Sprite[] lava;
    Sprite[] heart;

    Player player;

    Enemy[] enemies;

    NithKeyboardHandler Key;

    OrthographicCamera camera;

    Vector3 playerPos;

    Sprite light;
    Sprite blackNess;

    BitmapFont font;

    Chest[] chests;

    Sound playerHurt, playerKill;

    Exitgate exit;

    public Rectangle[] platformBox;
    public Rectangle[] lavaBox;

    public static final int SPEED = 5;
    public static final int MAP_LENGTH = 10;
    public static final int OFFSET = 300;
    public static final int TOTAL_ENTITIES = 50;

    int score;

    boolean generated = false;
    boolean doOnce = false;

    public SinglePlayer(Game game){
        this.game = game;
        Key = new NithKeyboardHandler();
        Gdx.input.setInputProcessor(Key);

        player  = new Player(Key);
        gen = new TerrainGen(MAP_LENGTH * 15);
        batch = new SpriteBatch();
        backGround = new Sprite[MAP_LENGTH];
        platform = new Sprite[MAP_LENGTH * 15];
        lava = new Sprite[MAP_LENGTH * 40];
        heart = new Sprite[10];

        platformBox = new Rectangle[platform.length];
        lavaBox = new Rectangle[lava.length];

        chests = new Chest[150];

        camera = new OrthographicCamera();

        player.setPlayerPos(new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2));
        playerPos = new Vector3(player.getPlayerPos().x, player.getPlayerPos().y, 0);

        enemies = new Enemy[TOTAL_ENTITIES];
        font = new BitmapFont();

        exit = new Exitgate(new Vector2(5500, 300), game);
    }
    @Override
    public void render(float delta) {
        if(player.health == 0 && !doOnce){
            game.setScreen(new GameOver(game));
            doOnce = true;
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
            drawBackground();
            drawMap();

            for(int i = 0; i < player.health; i++){
                heart[i].draw(batch);
                heart[i].setScale(0.5f);
            }

            player.drawWeapon(batch);

            tick(delta);

            for(Chest chest : chests){
                chest.drawChest(batch);
            }
            batch.draw(player.getCurrentFrame(), camera.position.x, camera.position.y);

            setHearts();

            font.draw(batch, "Score: "+score, -140 + camera.position.x, 160 + camera.position.y);

            exit.drawGate(batch);
        batch.end();

        camera.position.set(playerPos);
        camera.zoom = 0.5f;
        camera.update();
    }

    public void setHearts(){
        if(heart[0] != null) {
            heart[0].setPosition((-120 + playerPos.x) + (0 * heart[0].getWidth() / 2), -160 + playerPos.y);
        }
        if(heart[1] != null) {
            heart[1].setPosition((-120 + playerPos.x) + (1 * heart[1].getWidth() / 2), -160 + playerPos.y);
        }
        if(heart[2] != null) {
            heart[2].setPosition((-120 + playerPos.x) + (2 * heart[2].getWidth() / 2), -160 + playerPos.y);
        }
        if(heart[3] != null){
            heart[3].setPosition((-120 + playerPos.x) + (3 * heart[3].getWidth()/2), -160 + playerPos.y);
        }
        if(heart[4] != null){
            heart[4].setPosition((-120 + playerPos.x) + (4 * heart[4].getWidth()/2), -160 + playerPos.y);
        }
        if(heart[5] != null) {
            heart[5].setPosition((-120 + playerPos.x) + (5 * heart[5].getWidth() / 2), -160 + playerPos.y);
        }
        if(heart[6] != null) {
            heart[6].setPosition((-120 + playerPos.x) + (6 * heart[6].getWidth() / 2), -160 + playerPos.y);
        }
        if(heart[7] != null) {
            heart[7].setPosition((-120 + playerPos.x) + (7 * heart[7].getWidth() / 2), -160 + playerPos.y);
        }
        if(heart[8] != null){
            heart[8].setPosition((-120 + playerPos.x) + (8 * heart[8].getWidth()/2), -160 + playerPos.y);
        }
        if(heart[9] != null){
            heart[9].setPosition((-120 + playerPos.x) + (9 * heart[9].getWidth()/2), -160 + playerPos.y);
        }
    }

    public void tick(float delta){
        player.checkMove(delta, platformBox, chests);
        playerPos = new Vector3(player.getPlayerPos().x, player.getPlayerPos().y, 0);

        for (int i = 0; i < enemies.length; i++) {
            if(enemies[i] != null) {
                enemies[i].checkMove(delta, new Vector2(playerPos.x, playerPos.y));
                enemies[i].getEnemy().draw(batch);
                if (enemies[i].enemyBox.intersects(player.playerBox)) {
                    enemies[i] = null;
                    player.health -= 1; //Commented out for debug purposes!d
                    playerHurt.play();
                }
            }
            if(enemies[i] != null) {
                if (enemies[i].enemyBox.intersects(player.weaponBox) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
                    enemies[i] = null;
                    score += 100;
                    playerKill.play();
                }
            }
        }
        exit.playerCheck(player);
    }

    public void drawBackground(){
        for(int i = 0; i < MAP_LENGTH; i++) {
            backGround[i].draw(batch);
            backGround[i].setPosition(i * (int) backGround[i].getWidth(), 0);
        }
    }

    public void drawMap(){
        for(int i = 0; i < MAP_LENGTH * 15; i++) {
            platform[i].draw(batch);
            platform[i].setPosition(i * 80, (gen.getyData()[i] * 20) + OFFSET);
            platformBox[i].setLocation((int) platform[i].getX(), (int) platform[i].getY());
        }
        for(int i = 0; i < lava.length; i++){
            for(int j = 0; j < 9; j++){
                lava[i].draw(batch);
                lava[i].setPosition(i * lava[i].getWidth(), j * lava[i].getHeight());
            }
        }
        if(!generated) {
            for (int i = 0; i < chests.length; i++) {
                chests[i] = new Chest(Key, platform);
                chests[i].createContent();
            }
            generated = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void show() {
        gen.Generate(3);
        for(int i = 0; i < backGround.length; i++) {
            backGround[i] = new Sprite(new Texture(Gdx.files.internal("core/assets/images/gameback.png")));

        }
        for(int i = 0; i < platform.length; i ++){
            platform[i] = new Sprite(new Texture(Gdx.files.internal("core/assets/images/floor2.png")));
            platformBox[i] = new Rectangle(0, 0, 80, 20);
        }

        for(int i = 0; i < lava.length; i++){
            lava[i] = new Sprite(new Texture(Gdx.files.internal("core/assets/images/lava.png")));
            lavaBox[i] = new Rectangle((int) lava[i].getX(), (int) lava[i].getY(), (int) lava[i].getWidth(), (int) lava[i].getWidth());
        }

        for(int i = 0; i < enemies.length; i++){
            enemies[i] = new Enemy(new Vector2(i * 140, 400));
        }

        for(int i = 0; i < heart.length; i++){
            heart[i] = new Sprite(new Texture(Gdx.files.internal("core/assets/images/heart.png")));
        }


        light = new Sprite(new Texture(Gdx.files.internal("core/assets/images/light.png")));
        blackNess = new Sprite(new Texture(Gdx.files.internal("core/assets/images/black.png")));
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        playerHurt = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/hurt.wav"));
        playerKill = Gdx.audio.newSound(Gdx.files.internal("core/assets/sounds/kill.wav"));
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
