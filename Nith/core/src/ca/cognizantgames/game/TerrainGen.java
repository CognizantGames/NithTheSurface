package ca.cognizantgames.game;

import java.util.Random;

public class TerrainGen {
    Random random;

    int[] yData;

    public TerrainGen(int length){
        random = new Random();
        yData = new int[length];
    }

    public void Generate(long roughness){
        int max = (int) roughness / 2;
        int min = (int) -roughness / 2;
        for(int i = 0; i < yData.length; i++){
            yData[i] = random.nextInt((max - min) + 1) + min;
        }
    }

    public int[] getyData(){
        if(yData != null) {
            return this.yData;
        }
        return null;
    }
}
