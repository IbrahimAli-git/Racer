package com.codegym.games.racer.road;

import com.codegym.games.racer.RacerGame;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;

    private RoadObject createRoadObject(RoadObjectType roadObject, int x, int y){
        if (roadObject == RoadObjectType.SPIKE){
            return new Spike(x, y);
        } else {
            return null;
        }
    }
}
