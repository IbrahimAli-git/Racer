package com.codegym.games.racer;

import com.codegym.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH/2;
    public static final int ROADSIDE_WIDTH = 14;
    private RoadMarking roadMarking;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    private void createGame(){
        roadMarking = new RoadMarking();
        drawScene();
    }

    private void drawScene(){
        drawField();
        roadMarking.draw(this);
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
}
