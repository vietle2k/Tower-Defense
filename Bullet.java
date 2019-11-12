import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

import javax.swing.text.html.parser.Entity;

public class Bullet implements GameEntity,MovableEntity {
    private double PosX;
    private double PosY;
    private double SpeedFly = 7;

    private double startPosX;
    private double startPosY;
    private  double targetPosX;
    private  double targetPosY;
    private Enemy target;
    private double dame;
    public Image getImage1(){
        return new Image("image/towerDefense_tile298.png");
    }
    GameField gf;
    public Bullet(double startPosX, double startPosY, double targetPosX, double targetPosY, GameField gf, Enemy e, double dame) {
        PosX = startPosX;
        PosY = startPosY;
        this.startPosX = startPosX;
        this.startPosY = startPosY;
        this.targetPosX = targetPosX;
        this.targetPosY = targetPosY;
        this.gf = gf;
        this.target = e;
        this.dame = dame;
    }

    public Bullet(double targetPosX, double targetPosY) {
        this.targetPosX = targetPosX;
        this.targetPosY = targetPosY;
    }

    @Override
    public double getPosX() {
        return PosX;
    }

    @Override
    public double getPosY() {
        return PosY;
    }

    public void setPosX(double posX) {
        PosX = posX;
    }

    public void setPosY(double posY) {
        PosY = posY;
    }

    public void setSpeedFly(double speedFly) {
        SpeedFly = speedFly;
    }

    @Override
    public List<Image> getImage() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("image/towerDefense_tile297.png"));
        images.add(new Image("image/towerDefense_tile298.png"));
        images.add(new Image("image/towerDefense_tile252.png"));
        return images;
    }


    public double getSpeedFly(){
        return SpeedFly;
    }

//    public  double getRange(){
//        return this.getRange();
//    }

    @Override
    public void move(long time) {
        double newPosX = PosX+ (targetPosX-startPosX)/getSpeedFly();
        double newPosY =PosY+ (targetPosY-startPosY)/getSpeedFly();
        if((newPosX-targetPosX)*(PosX-targetPosX) <=0) {
            this.target.truMau(this.dame);
            ((BulletDestroyCallback)gf).destroyBullet(this);
        } else {
            PosX = newPosX;
            PosY = newPosY;
        }

    }

    @Override
    public long getAppearTime() {
        return 0;
    }

    @Override
    public void setStartTime(long l) {

    }
}
