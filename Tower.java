import javafx.scene.image.Image;

import java.util.List;

public interface Tower extends GameTile {
    @Override
    double getPosX();

    @Override
    double getPosY();
    Bullet getBullet();
    void setBullet(Bullet b);
    void setShotting(boolean b);
    boolean isShotting();
    @Override
    List<Image> getImage();

    double getSpeed();

    double getDamage();

    double getRange();


    void move(long time, double poX, double poY);
}
