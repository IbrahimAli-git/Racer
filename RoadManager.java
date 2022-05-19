package com.codegym.games.racer.road;

import com.codegym.engine.cell.Game;
import com.codegym.games.racer.RacerGame;
import java.util.ArrayList;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;
    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private List<RoadObject> items = new ArrayList<>();


    private RoadObject createRoadObject(RoadObjectType roadObject, int x, int y){
        if (roadObject == RoadObjectType.SPIKE){
            return new Spike(x, y);
        } else {
            return null;
        }
    }

    private void addRoadObject(RoadObjectType type, Game game){
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject object = createRoadObject(type, x, y);
        if (object != null) items.add(object);
    }
}
