package com.codegym.games.racer.road;

import com.codegym.engine.cell.Game;
import com.codegym.games.racer.PlayerCar;
import com.codegym.games.racer.RacerGame;
import java.util.ArrayList;
import java.util.Iterator;
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

    public void draw(Game game){
        for (RoadObject item : items) {
            item.draw(game);
        }
    }

    public void move(int boost){
        for (RoadObject item : items) {
            item.move(boost+ item.speed);
        }
        deletePassedItems();
    }

    public boolean checkCrash(PlayerCar car){
        boolean isCrashable = false;
        for (RoadObject item : items) {
            if (item.isCollision(car)){
                isCrashable = true;
            }
        }
        return isCrashable;
    }

    private boolean spikeExists(){
        boolean isContainingASpike = false;
        for (RoadObject item : items) {
            if (item instanceof Spike){
                isContainingASpike = true;
            }
        }
        return isContainingASpike;
    }

    private void generateSpike(Game game){
        int i = game.getRandomNumber(100);
        if (i < 10 && !spikeExists()){
            addRoadObject(RoadObjectType.SPIKE, game);
        }
    }

    public void generateNewRoadObjects(Game game){
        generateSpike(game);
    }

    private void deletePassedItems(){
        Iterator<RoadObject> iterator = items.listIterator();
        while (iterator.hasNext()){
            RoadObject roadObject = iterator.next();
            if (roadObject.y >= RacerGame.HEIGHT){
                iterator.remove();
            }
        }

    }
}
