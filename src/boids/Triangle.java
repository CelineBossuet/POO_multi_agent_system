package boids;

import java.awt.Color;
import java.awt.Graphics2D;
import gui.GraphicalElement;

public class Triangle implements GraphicalElement {
    private int x;
    private int y;
    private Vector direction;
    private Color color;
    private int length;

    /**
     * Classe qui permet de tracer des triangles
     * @param x
     * @param y
     * @param direction
     * @param color
     * @param length
     */
    public Triangle(int x, int y, Vector direction, Color color, int length){
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.color = color;
        this.length = length;
    }

    /**
     * @return x
     */
    public int getX() {
        return this.x;
    }

    /**
     * @return y
     */
    public int getY() {
        return this.y;
    }

    @Override
    public void paint(Graphics2D g2d){
        double norme = this.direction.norme();

        int x1 = x + (int)((this.direction.getX()/norme)*length);
        int y1 = y + (int)((this.direction.getY()/norme)*length);
        int x2 = x - (int)(((this.direction.getX() + this.direction.getY())/norme)*length);
        int y2 = y + (int)(((this.direction.getX() - this.direction.getY())/norme)*length);
        int x3 = x + (int)(((this.direction.getY() - this.direction.getX())/norme)*length);
        int y3 = y - (int)(((this.direction.getX() + this.direction.getY())/norme)*length);

        int[] allX = {x1, x2, x3};
        int[] allY = {y1, y2, y3};

        g2d.setColor(color);
        g2d.drawPolygon(allX, allY, 3);
        g2d.fillPolygon(allX, allY, 3);

    }

    /**
     * Translate le triangle
     * @param dx
     * @param dy
     */
    public void translate(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
}