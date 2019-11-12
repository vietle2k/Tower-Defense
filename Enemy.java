import javafx.scene.image.Image;

import java.awt.*;
import java.util.List;

public interface Enemy extends GameEntity{
    public void setGameField(GameField gf);
    @Override
    double getPosX();

    @Override
    double getPosY();

    @Override
    List<Image> getImage();

    double getBood();
    void truMau(double dame);
    double getArmor();

    double getSpeed();

    double getBonus();

}
