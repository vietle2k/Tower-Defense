import javafx.scene.image.Image;

import java.util.List;

public interface GameTile extends GameEntity {
    @Override
    double getPosX();

    @Override
    double getPosY();

    @Override
    List<Image> getImage();

}
