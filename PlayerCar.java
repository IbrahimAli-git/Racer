package com.codegym.games.racer;

public class PlayerCar extends GameObject {
    private static int playerCarHeight = ShapeMatrix.PLAYER.length;
    public int speed = 1;
    private Direction direction;

    public PlayerCar() {
        super(RacerGame.WIDTH/2+2, RacerGame.HEIGHT-playerCarHeight-1, ShapeMatrix.PLAYER);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(){
        switch (direction){
            case LEFT: x--; break;
            case RIGHT: x++; break;
        }
    }
}
