import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class NormalTower implements Tower {
    private double PosX;
    private  double PosY;
    private   double Speed = 7;
    private  double Damage = 20;
    private  double Range  = 150;
    private boolean isShotting = false;
    Bullet bullet = null;


    public NormalTower(double posX, double posY) {
        PosX = posX;
        PosY = posY;
    }


    private List<Image> images;
    @Override
    public double getPosX() {
        return PosX;
    }

    @Override
    public double getPosY() {
        return PosY;
    }

    @Override
    public Bullet getBullet() {
        return this.bullet;
    }

    @Override
    public void setBullet(Bullet b) {
        this.bullet = b;
    }

    @Override
    public void setShotting(boolean b) {
        this.isShotting = b;
    }

    @Override
    public boolean isShotting() {
        return this.isShotting;
    }

    @Override
    public List<Image> getImage() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("image/towerDefense_title404.png"));
        //images.add(new Image("image/towerDefense_tile181.png"));
         return images;
    }

    @Override
    public double getSpeed() {
        return Speed;
    }

    @Override
    public double getDamage() {
        return Damage;
    }

    @Override
    public double getRange() {
        return Range;
    }

    @Override
    public void move(long time, double poX, double poY) {};
}
