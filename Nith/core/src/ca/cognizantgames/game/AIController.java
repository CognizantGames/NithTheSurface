package ca.cognizantgames.game;

import com.badlogic.gdx.math.Vector2;

public class AIController {
    Vector2 enemyPos;

    public static final int VIEW_DISTANCE = 200;
    public static final int SPEED = 60;

    public AIController(Vector2 enemyPos){
        this.enemyPos = enemyPos;
    }

    public Vector2 move(float delta, Vector2 playerPos){
        if((enemyPos.x - playerPos.x) < VIEW_DISTANCE){
            if((enemyPos.y - playerPos.y) < VIEW_DISTANCE){
                if(playerPos.x > enemyPos.x){
                    enemyPos.x += SPEED * delta;
                }else if(playerPos.x < enemyPos.x) {
                    enemyPos.x -= SPEED * delta;
                }
                if(playerPos.y > enemyPos.y){
                    enemyPos.y += SPEED * delta;
                }else if(playerPos.y < enemyPos.y){
                    enemyPos.y -= SPEED * delta;
                }
            }
        }
        return enemyPos;
    }

    public Vector2 getPos(){
        return this.enemyPos;
    }
}
