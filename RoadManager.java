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
    private static final int PLAYER_CAR_DISTANCE = 12;
    private int passedCarsCount = 0;


    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    private RoadObject createRoadObject(RoadObjectType roadObject, int x, int y){
        if (roadObject == RoadObjectType.SPIKE){
            return new Spike(x, y);
        } else if (roadObject == RoadObjectType.DRUNK_CAR){
            return new MovingCar(x, y);
        } else {
            return new Car(roadObject, x, y);
        }
    }

    private void addRoadObject(RoadObjectType type, Game game){
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject object = createRoadObject(type, x, y);
        if (isRoadSpaceFree(object)) {
            items.add(object);
        }
    }

    private void generateRegularCar(Game game){
        int i = game.getRandomNumber(100);
        int carTypeNumber = game.getRandomNumber(4);
        if (i < 30){
            addRoadObject(RoadObjectType.values()[carTypeNumber], game);
        }
    }

    public void draw(Game game){
        for (RoadObject item : items) {
            item.draw(game);
        }
    }

    public void move(int boost){
        for (RoadObject item : items) {
            item.move(boost+ item.speed, items);
        }
        deletePassedItems();
    }

    public boolean checkCrash(PlayerCar car){
        for (RoadObject item : items) {
            if (item.isCollision(car)){
                return true;
            }
        }
        return false;
    }

    private boolean isRoadSpaceFree(RoadObject object){
        for (RoadObject item : items) {
            if (item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE)){
                return false;
            }
        }
        return true;
    }

    private boolean spikeExists(){
        for (RoadObject item : items) {
            if (item instanceof Spike){
                return true;
            }
        }
        return false;
    }

    private boolean movingCarExists(){
        for (RoadObject item : items) {
            if (item instanceof MovingCar){
                return true;
            }
        }
        return false;
    }

    private void generateMovingCar(Game game){
        int i = game.getRandomNumber(100);
        if (i < 10 && !movingCarExists()){
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
        }
    }

    private void generateSpike(Game game){
        int i = game.getRandomNumber(100);
        if (i < 10 && !spikeExists()){
            addRoadObject(RoadObjectType.SPIKE, game);
        }
    }

    public void generateNewRoadObjects(Game game) {
        generateSpike(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    private void deletePassedItems(){
        Iterator<RoadObject> iterator = items.listIterator();
        while (iterator.hasNext()){
            RoadObject roadObject = iterator.next();
            if (roadObject.y >= RacerGame.HEIGHT){
                iterator.remove();
                if (!(roadObject instanceof Spike)){
                    passedCarsCount++;
                }
            }
        }

    }
}
