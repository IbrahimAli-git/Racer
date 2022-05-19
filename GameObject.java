package com.codegym.games.racer;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.*;

public class GameObject extends Game {
    public int x;
    public int y;
    public int[][] matrix;
    public int width;
    public int height;

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameObject(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        this.width = matrix.length;
        this.height = matrix.length;
    }

    public void draw(Game game){
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                game.setCellColor(x+i, j+y, Color.values()[matrix[i][j]]);
            }
        }
    }
}

