package com.example.brakeout;


import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ball extends GraphicsItem {
    private Point2D moveVector = new Point2D(1, -1).normalize();
    private double velocity = 500;
    private Point2D lastPosition;

    public Ball() {
        x = -100;
        y = -100;
        width = height = canvasHeight * .015;
    }

    public Point2D getLastPosition(){
        return lastPosition;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x, y, width, height);
    }

    public void setPosition(Point2D point) {
        this.x = point.getX() - width/2;
        this.y = point.getY() - height/2;
        lastPosition = point;
    }

    public void updatePosition(double diff) {
        lastPosition = new Point2D(x,y);
        x += moveVector.getX() * velocity * diff;
        y += moveVector.getY() * velocity * diff;
    }

    public void bounceHorizontally(){
        moveVector = new Point2D(-moveVector.getX(), moveVector.getY()).normalize();
    }

    public void bounceVertically(){
        moveVector = new Point2D(moveVector.getX(), -moveVector.getY()).normalize();
    }

    public Point2D getTopLeft(){
        return new Point2D(x-width/2,y-height/2);
    }

    public Point2D getBottomLeft(){
        return new Point2D(x-width/2,y+height/2);
    }

    public Point2D getTopRight(){
        return new Point2D(x+width/2,y-width/2);
    }

    public Point2D getBottomRight(){
        return new Point2D(x+width/2,y+height/2);
    }

    public void bounceFromPaddle(double hitPosition){
        moveVector = new Point2D(moveVector.getX() * hitPosition, moveVector.getY());
    }

}
