/**
 * This is the single player class the game drawing is done here!
 * @author Demetry Romanowski
 * demetryromanowski@gmail.com
 * 4262014
 */
//TODO: Add a player, and make sure that the background is drawn above and below the FOV so you don't see white space
//TODO: Make an ai code, when in range go toward the player, there are only mellee attacks for simplicities sake!
package ca.cognizantgames.screens;

import ca.cognizantgames.assets.NithKeyboardHandler;
import ca.cognizantgames.game.TerrainGen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class SinglePlayer implements Screen {
    Game game;

    SpriteBatch batch;
    //Start declaring resources here!

    TerrainGen gen;
    Sprite[] backGround;
    Sprite[] platform;
    Sprite[] lava;

    NithKeyboardHandler Key;

    OrthographicCamera camera;

    Vector3 playerPos;

    public static final int SPEED = 150;
    public static final int MAP_LENGTH = 10;
    public static final int OFFSET = 300;

    public SinglePlayer(Game game){
        this.game = game;
        gen = new TerrainGen(MAP_LENGTH * 15);
        batch = new SpriteBatch();
        backGround = new Sprite[MAP_LENGTH];
        platform = new Sprite[MAP_LENGTH * 15];
        lava = new Sprite[MAP_LENGTH * 20];

        Key = new NithKeyboardHandler();
        Gdx.input.setInputProcessor(Key);

        camera = new OrthographicCamera();

        playerPos = new Vector3(310, 360, 0);
    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
            drawBackground();
            drawMap();
        batch.end();

        tick(delta);
        camera.position.set(playerPos);
        camera.update();
    }

    public void tick(float delta){
        if(Key.W){
            playerPos.y += SPEED * delta;
        }else if(Key.S){
            playerPos.y -= SPEED * delta;
        }
        if(Key.D){
            playerPos.x += SPEED * delta;
        }else if(Key.A){
            playerPos.x -= SPEED * delta;
        }
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
            platform[i].setPosition(i * (int) platform[i].getWidth(), (gen.getyData()[i] * (int) platform[i].getHeight()) + OFFSET);
        }
        for(int i = 0; i < lava.length; i++){
            for(int j = 0; j < 9; j++){
                lava[i].draw(batch);
                lava[i].setPosition(i * lava[i].getWidth(), j * lava[i].getHeight());
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void show() {
        gen.Generate(0, 3);
        for(int i = 0; i < backGround.length; i++) {
            backGround[i] = new Sprite(new Texture(Gdx.files.internal("core/assets/images/gameback.png")));

        }
        for(int i = 0; i < platform.length; i ++){
            platform[i] = new Sprite(new Texture(Gdx.files.internal("core/assets/images/floor2.png")));
        }
        for(int i = 0; i < lava.length; i++){
            lava[i] = new Sprite(new Texture(Gdx.files.internal("core/assets/images/lava.png")));
        }

        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
