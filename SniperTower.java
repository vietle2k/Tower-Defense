import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class SniperTower implements Tower {
    private double PosX;
    private  double PosY;
    private   double Speed = 4;
    private  double Damage = 50;
    private  double Range  = 200;
    private Bullet bullet;
    private boolean isShotting;
    @Override
    public void setShotting(boolean b) {
        this.isShotting = b;
    }

    @Override
    public boolean isShotting() {
        return this.isShotting;
    }
    @Override
    public double getPosX() {
        return PosX;
    }

    @Override
    public double getPosY()
    {
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

    public SniperTower(double posX, double posY) {
        PosX = posX;
        PosY = posY;
    }

    @Override
    public List<Image> getImage() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("image/towerDefense_title401.png"));
        //images.add(new Image("image/towerDefense_tile0180.png"));
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
    public void move(long time, double poX, double poY) {

    }

//    @Override
//    public double getSpeed() {
//        return 0;
//    }
//
//    @Override
//    public double getDamage() {
//        return 0;
//    }
//
//    @Override
//    public double getRange() {
//        return 0;
//    }
}
