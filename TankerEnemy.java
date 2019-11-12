import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class TankerEnemy implements Enemy,MovableEntity {
    private double posX  = 10;
    private double posY  = 610;
    private double Armor = 30;
    private double Bonus = 20;
    private long startTime;
    private double Bood = 300;
    private double Speed = 7;
    private List<Point> points;
    private long appearTime;
    private GameField gf;
    @Override
    public void setGameField(GameField gf) {
        this.gf = gf;
    }
//    public NormalEnemy(List<Point> points) {
//        this.points.add(new Point(390,610));
//       // this.points.add(new Point());
//    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public TankerEnemy(double posX, double posY, long startTime, long appearTime) {
        this.posX = posX;
        this.posY = posY;
        this.appearTime = appearTime;
        this.startTime = startTime;
    }

    public long getAppearTime() {
        return appearTime;
    }

    public void setAppearTime(long appearTime) {
        this.appearTime = appearTime;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public List<Image> getImage() {
        ArrayList<Image> images = new ArrayList<Image>();
        images.add(new Image("image/TankerEnemy.png"));
        // images.add(new Image("image/towerDefense_tile0182.png"));
        return images;
    }

    @Override
    public double getBood() {
        return Bood;
    }

    @Override
    public void truMau(double dame) {
        this.Bood-= dame;
        if(this.Bood<=0) {
            gf.enemyDestroyCallback(this, false);
        }
    }
    @Override
    public double getArmor() {
        return Armor;
    }

    @Override
    public double getSpeed() {
        return Speed;
    }

    @Override
    public double getBonus() {
        return Bonus;
    }

    @Override
    public void move(long time) {
        long theTime  = startTime - time;
        if(posX<350){
            posX += Speed * 0.5;
            return;
        }
        if(posY>145 && posX <790){
            posY-= Speed *0.5;
            return;
        }
        if(posX>=790&& posY<530){
            posY+= Speed*0.5;
            return;
        }
        posX+= Speed *0.5;
        if(posX > 1300) {
            gf.enemyDestroyCallback(this, true);
        }
    }
    public void onDestroyed(){
//
    }

}
