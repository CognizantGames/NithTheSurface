package ca.cognizantgames.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Enemy {

    AIController controller;

    Vector2 enemyPos;

    Sprite enemy;

    public Rectangle enemyBox;

    public Enemy(Vector2 enemyPos){
        this.enemyPos = enemyPos;
        controller = new AIController(enemyPos);
        enemy = new Sprite(new Texture(Gdx.files.internal("core/assets/images/imp.png")));
        enemyBox = new Rectangle((int) enemyPos.x, (int) enemyPos.y, 32, 32);
    }

    public void checkMove(float delta, Vector2 playerPos){
        controller.move(delta, playerPos);
        enemyPos = controller.getPos();

        enemy.setPosition(enemyPos.x, enemyPos.y);

        enemyBox.setLocation((int) enemyPos.x, (int) enemyPos.y);

        if(enemyPos.x < playerPos.x){
            enemy.setFlip(true, false);
        }else if(enemyPos.x > playerPos.x){
            enemy.setFlip(false, false);
        }
    }
    public Sprite getEnemy(){
        return this.enemy;
    }
    public Vector2 getEnemyPos(){
        return this.enemyPos;
    }
}
