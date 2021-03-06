package com.codegym.games.racer;

import com.codegym.engine.cell.*;
import com.codegym.games.racer.road.RoadManager;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH/2;
    public static final int ROADSIDE_WIDTH = 14;
    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private boolean isGameStopped;
    private FinishLine finishLine;
    private static final int RACE_GOAL_CARS_COUNT = 40;
    private ProgressBar progressBar;
    private int score;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    private void createGame(){
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        isGameStopped = false;
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        score = 3500;
        drawScene();
        setTurnTimer(40);
    }

    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.WHITE, "Game Over", Color.BLACK, 75);
        stopTurnTimer();
        player.stop();
    }

    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.WHITE, "Win", Color.BLACK, 75);
        stopTurnTimer();
    }

    private void drawScene(){
        drawField();
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
        finishLine.draw(this);
        progressBar.draw(this);
    }

    private void drawField(){
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                if (i == CENTER_X){
                    setCellColor(i, j, Color.AQUA);
                } else if (i >= ROADSIDE_WIDTH && i < (WIDTH-ROADSIDE_WIDTH)){
                    setCellColor(i, j, Color.CORAL);
                } else {
                    setCellColor(i, j, Color.FIREBRICK);
                }
            }
        }
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x < 0 || y < 0 || x >= 64 || y >= 64){

        } else {
            super.setCellColor(x, y, color);
        }
    }

    private void moveAll(){
        roadMarking.move(player.speed);
        player.move();
        roadManager.move(player.speed);
        finishLine.move(player.speed);
        progressBar.move(roadManager.getPassedCarsCount());
    }

    @Override
    public void onTurn(int step) {
        if (roadManager.checkCrash(player)) {
            gameOver();
            drawScene();
            return;
        } if (roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT){
            finishLine.show();
        }
        if (finishLine.isCrossed(player)) {
            win();
            drawScene();
            return;
        }
        score -= 5;
        setScore(score);
        moveAll();
        roadManager.generateNewRoadObjects(this);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case RIGHT: player.setDirection(Direction.RIGHT); break;
            case LEFT: player.setDirection(Direction.LEFT); break;
        }

        if (key == Key.SPACE && isGameStopped){
            createGame();
        } else if (key == Key.UP){
            player.speed = 2;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.RIGHT && player.getDirection() == Direction.RIGHT){
            player.setDirection(Direction.NONE);
        } else if (key == Key.LEFT && player.getDirection() == Direction.LEFT){
            player.setDirection(Direction.NONE);
        } else if (key == Key.UP){
            player.speed = 1;
        }
    }
}
