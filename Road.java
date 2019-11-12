import javafx.scene.image.Image;

import java.util.List;

public class Road implements GameTile {
    @Override
    public double getPosX() {
        return 0;
    }

    @Override
    public double getPosY() {
        return 0;
    }

    @Override
    public List<Image> getImage() {
        return null;
    }


    public Point[]points  = new Point[10];
}
