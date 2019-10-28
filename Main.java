import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    GameStage gameStage = new GameStage();
     public void start(Stage theStage)
    {
        theStage.setTitle( "Canvas Example" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( 3000, 2000 );
        gameStage.draw(canvas);
        root.getChildren().add( canvas );

        theStage.show();
    }
}
