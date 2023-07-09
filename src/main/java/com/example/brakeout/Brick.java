package com.example.brakeout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Brick extends GraphicsItem{

    static private int gridRows;
    static private int gridCols;
    private Color color;

    public static void setGridCols(int gridCols){
        Brick.gridCols = gridCols;
    }

    public static void setGridRows(int gridRows) {
        Brick.gridRows = gridRows;
    }

    public Brick(int x, int y, Color color){
        this.width = canvasWidth/gridCols;
        this.height = canvasHeight/gridRows;
        this.x = width * x;
        this.y = height * y;
        this.color = color;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(color);
        graphicsContext.fillRect(x, y, width, height);

    }

    public enum CrushType {
        NoCrush,
        HorizontalCrush,
        VerticalCrush
    }

    public CrushType checkCollision(Point2D top, Point2D bottom, Point2D left, Point2D right) {
        boolean isTopCollision = top.getY() <= (y + getHeight()) && top.getY() >= y;
        boolean isBottomCollision = bottom.getY() >= y && bottom.getY() <= (y + getHeight());
        boolean isLeftCollision = left.getX() <= (x + getWidth()) && left.getX() >= x;
        boolean isRightCollision = right.getX() >= x && right.getX() <= (x + getWidth());

        if (isTopCollision && isLeftCollision && isRightCollision) {
            return CrushType.VerticalCrush;
        } else if (isLeftCollision && isTopCollision && isBottomCollision) {
            return CrushType.HorizontalCrush;
        } else {
            return CrushType.NoCrush;
        }

    }
}
