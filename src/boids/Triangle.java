package boids;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import gui.GraphicalElement;

public class Triangle implements GraphicalElement {
    private int x;
    private int y;
    private Vector direction;
    private Color color;
    private int length;


    public Triangle(int x, int y, Vector direction, Color color, int length){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.color = color;
        this.length = length;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public void paint(Graphics2D g2d){
        double n = this.direction.mag();
        int x1 = x + (int)((length)*(this.direction.getX()/n));
        int y1 = y + (int)((length)*(this.direction.getY()/n));
        int x2 = x - (int)((length)*(this.direction.getX()/n));
        int y2 = y - (int)((length)*(this.direction.getY()/n));
        int x3 = x2 + (int)((length)*(-this.direction.getY()/n));
        int y3 = y2 + (int)((length)*(this.direction.getX()/n));
        int x4 = x2 - (int)((length)*(-this.direction.getY()/n));
        int y4 = y2 - (int)((length)*(this.direction.getX()/n));
        int[] xPoints = {x1, x3, x4};
        int[] yPoints = {y1, y3, y4};
        g2d.setColor(color);
        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.setColor(color);
        g2d.fillPolygon(xPoints, yPoints, 3);

    }

    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public String toString() {
        return "";
    }
}