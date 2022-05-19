package com.codegym.games.racer;

import com.codegym.engine.cell.*;

public class RacerGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
    }
}
