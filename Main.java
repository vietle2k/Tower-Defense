import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import javafx.scene.text.Text;
import javafx.util.Duration;

import javafx.scene.media.MediaPlayer;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private GameMenu gameMenu;
    MediaPlayer mediaPlayer;

    @Override
    public void start(Stage primaryStage) throws Exception {
                Media music = new Media("file:///C:/Music/music.mp3");
                mediaPlayer = new MediaPlayer(music);
                mediaPlayer.setVolume(0.1);
        Pane root = new Pane();
        root.setPrefSize(800, 600);

        InputStream iStream = getClass().getResourceAsStream("image/Menu.png");
        Image img = new Image(iStream);
        iStream.close();

        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(800);
        imgView.setFitHeight(600);

        this.gameMenu = new GameMenu();

        root.getChildren().addAll(imgView, gameMenu);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class GameMenu extends Parent {
        public GameMenu() {
            VBox menu0 = new VBox(10);

            menu0.setTranslateX(300);
            menu0.setTranslateY(250);
            final int offset = 400;
            MenuButton NewGame = new MenuButton("NEW GAME");
            NewGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    Platform.runLater(new Runnable() {
                        public void run() {
                            new GameScreen().start(new Stage());

                        }
                    });

                }
            });

            MenuButton Exit = new MenuButton("EXIT");
            Exit.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    System.exit(0);
                }
            });
            MenuButton Start = new MenuButton("START MUSIC");
            Start.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                mediaPlayer.play();

                }
            });
            MenuButton Stop = new MenuButton("STOP MUSIC");
            Stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                mediaPlayer.stop();
                }
            });
            menu0.getChildren().addAll(NewGame, Exit, Start, Stop);
            Rectangle bg = new Rectangle(800, 600);
            bg.setFill(Color.BLACK);
            bg.setOpacity(0.4);

            this.getChildren().addAll(bg, menu0);

        }
    }

    private static class MenuButton extends StackPane {

        private Text text;

        public MenuButton(String name) {
            this.text = new Text(name);
            text.getFont();
            this.text.setFont(Font.font("Verdana",20));
            this.text.setFill(Color.WHITE);

            final Rectangle bg = new Rectangle(250, 30);
            bg.setOpacity(0.7);
            bg.setFill(Color.BLACK);
            GaussianBlur blur = new GaussianBlur(3.5);
            bg.setEffect(blur);

            this.setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);

            this.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    bg.setTranslateX(20);
                    text.setTranslateX(20);
                    bg.setFill(Color.WHITE);
                    text.setFill(Color.BLACK);
                }
            });

            this.setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    bg.setTranslateX(0);
                    text.setTranslateX(0);
                    bg.setFill(Color.BLACK);
                    text.setFill(Color.WHITE);
                }
            });
        }

    }
           /* Map map = new Map();
            public void setMap(Stage theStage)
            {
                theStage.setTitle( "Canvas Example" );

                Group group = new Group();
                Scene theScene = new Scene( group );
                theStage.setScene( theScene );

                Canvas canvas = new Canvas( 3000, 2000 );
                map.draw(canvas);
                group.getChildren().add( canvas );

                theStage.show();
            } */
}

