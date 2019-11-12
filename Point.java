import java.util.*;
public class Point {
    public double x;
    public double y;
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double distance(Point other){
        return Math.sqrt(Math.pow(this.getX()-other.getX(),2)+Math.pow(this.getY()-other.getY(),2));
    }
}
