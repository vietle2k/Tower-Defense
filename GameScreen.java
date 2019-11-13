import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import javax.swing.text.html.parser.Entity;

public class GameScreen extends Application{
    Button exitButton = new Button();
    GameStage gameStage = new GameStage();
    GameField gameField = new GameField();
    private boolean isDragging = false;
    double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;
    double x, y;

    public void start(Stage theStage)
    {
        theStage.initStyle(StageStyle.UNDECORATED);
        theStage.setWidth(22*64);
        theStage.setHeight(13*64);
        theStage.setX(0);
        theStage.setY(0);
        theStage.setTitle( "Tower Defense" );
        Text text = new Text();
       // text.setText("");
        final Group root = new Group();
       //Scene theScene = new Scene( root );
        Scene theScene = new Scene(root, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        theStage.setScene( theScene );
        final Canvas canvas = new Canvas( 3000, 2000 );
        final Canvas field = new Canvas( 3000, 2000 );
        final Canvas draw = new Canvas(3000,2000);
//        final Canvas menu  = new Canvas(3000,2000);
//        Pane root1 = new Pane();
//        root1.setPrefSize(1071, 600);
//
//        InputStream iStream =  getClass().getResourceAsStream("image/Menu.png");
//        Image img = new Image(iStream);
//        iStream.close();
//
//        ImageView imgView = new ImageView(img);
//        imgView.setFitWidth(1071);
//        imgView.setFitHeight(600);
//        this.gameMenu = new GameMenu();
//
//        root1.getChildren().addAll(imgView, gameMenu);
//
//        Scene scene = new Scene(root1);
//        theStage.setScene(scene);
//        theStage.show();
        gameStage.draw(canvas);
//        gameStage.draw(draw);
//button
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.SHIFT){
                    try {
                        System.out.println(System.currentTimeMillis());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //final Circle circle = new Circle();
        final Button button1 = new Button();
        InputStream inputStream = getClass().getResourceAsStream("image/towerDefense_title404.png");
        final Image image = new Image(inputStream);
        final ImageView  imageView = new ImageView(image);
//        imageView.setX(500);
//        imageView.setY(500);
        button1.setGraphic(imageView);
        button1.setLayoutX(1280);
        button1.setLayoutY(164);

        final Button button4 = new Button("Price: $20, Damage: 20, Range: 100");
        //button4.setMaxHeight(500);
        button4.setLayoutY(164);
        button4.setLayoutX(1000);
        button4.setVisible(false);

//        InputStream inputStreamm = getClass().getResourceAsStream("image/truongcon.png");
//        final Image imagee = new Image(inputStreamm);
//        final ImageView  imageVieww = new ImageView(imagee);
//        imageVieww.setX(1200);
//        imageVieww.setY(64);
        button4.setPrefSize(280,45);
        button1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button4.setVisible(!button4.isVisible());
               // draw.getGraphicsContext2D().drawImage(image,1280,64);
                //System.out.println("clicked");

            }
        });

        button1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getScreenX() - button1.getLayoutX();
                y = mouseEvent.getScreenY() - button1.getLayoutY();
                orgSceneX = mouseEvent.getSceneX();
                orgSceneY = mouseEvent.getSceneY();
//                circle.setCenterX(orgSceneX);
//                circle.setCenterY(orgSceneX);
//                circle.setRadius(50.0f);
                //root.getChildren().add(circle);
                double range = (new NormalTower(0,0)).getRange();
                draw.getGraphicsContext2D().strokeOval(1280-range+28,170-range+28 , range*2, range*2);
                draw.getGraphicsContext2D().drawImage(image,1280,164);
                orgTranslateX = draw.getTranslateX()+x - 32;
                isDragging = true;
                orgTranslateY =  draw.getTranslateY()+y -32;
//                draw.setTranslateY(y);
//                draw.setTranslateX(x);

                //orgTranslateY = ((Circle)(mouseEvent.getSource())).getTranslateY();
            }
        });
        button1.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double offsetX = mouseEvent.getSceneX() - orgSceneX;
                double offsetY = mouseEvent.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                if(isDragging) {
                  //  root.getChildren().add(circle);
                    draw.setTranslateX(newTranslateX);
                    draw.setTranslateY(newTranslateY);
                }
            }
        });
        button1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                orgSceneX = mouseEvent.getScreenX();
                orgSceneY = mouseEvent.getScreenY();
                boolean isOk = true;
                for(GameEntity e: gameField.getEntities()){
                    if(e instanceof Tower){
//                        System.out.println(e.getPosX());
//                        System.out.println(orgSceneX - 32);
//                        System.out.println(Math.abs(e.getPosX()-orgSceneX+32));
//                        System.out.println(Math.abs(e.getPosY()-orgSceneY+32));
                        if(Math.abs(e.getPosX()-orgSceneX+32)<62 && Math.abs(e.getPosY()-orgSceneY+32)<62) {
                            isOk = false;
                            break;
                        }


                    }
                }
                if(isOk&&gameField.money>=20)gameField.getEntities().add(new NormalTower(orgSceneX-32,orgSceneY-32));
                gameField.money-=20;
                if(gameField.money<0)gameField.money+=20;
                draw.setTranslateX(0);
                draw.setTranslateY(0);
                draw.getGraphicsContext2D().clearRect(0,0,3000,2000);
                isDragging = false;
            }
        });


//        final void keyUpHandler(KeyEvent keyEvent) {
//        final KeyCode keyCode = keyEvent.getCode();
//        if (keyCode == KeyCode.W) {
        //button1.setOnMousePressed();
        final Button button2 = new Button();
        InputStream inputStream2 = getClass().getResourceAsStream("image/towerDefense_title401.png");
        final Image image2 = new Image(inputStream2);
        ImageView  imageView2 = new ImageView(image2);
        button2.setGraphic(imageView2);
        button2.setLayoutX(1280);
        button2.setLayoutY(215);
        final Button button5 = new Button("Price: $40, Damage: 50, Range: 200,");
        //button4.setMaxHeight(500);
        button5.setLayoutY(215);
        button5.setLayoutX(1000);
        button5.setPrefSize(280,42);
        button5.setVisible(false);
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button5.setVisible(!button5.isVisible());
            }
        });
        isDragging = false;
        button2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getScreenX() - button2.getLayoutX();
                y = mouseEvent.getScreenY() - button2.getLayoutY();
                orgSceneX = mouseEvent.getSceneX();
                orgSceneY = mouseEvent.getSceneY();
                double range = (new SniperTower(0,0)).getRange();
                draw.getGraphicsContext2D().strokeOval(1280-range+28,215-range+28 , range*2, range*2);
                draw.getGraphicsContext2D().drawImage(image2,1280,215);
                orgTranslateX = draw.getTranslateX()+x - 32;
                isDragging = true;
                orgTranslateY =  draw.getTranslateY()+y -32;
//                draw.setTranslateY(y);
//                draw.setTranslateX(x);

                //orgTranslateY = ((Circle)(mouseEvent.getSource())).getTranslateY();
            }
        });
        button2.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double offsetX = mouseEvent.getSceneX() - orgSceneX;
                double offsetY = mouseEvent.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                if(isDragging) {
                    draw.setTranslateX(newTranslateX);
                    draw.setTranslateY(newTranslateY);
                }
            }
        });
        button2.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                orgSceneX = mouseEvent.getScreenX();
                orgSceneY = mouseEvent.getScreenY();
                boolean isOk = true;
                for(GameEntity e: gameField.getEntities()){
                    if(e instanceof Tower){
//                        System.out.println(e.getPosX());
//                        System.out.println(orgSceneX - 32);
//                        System.out.println(Math.abs(e.getPosX()-orgSceneX+32));
//                        System.out.println(Math.abs(e.getPosY()-orgSceneY+32));
                        if(Math.abs(e.getPosX()-orgSceneX+32)<62 && Math.abs(e.getPosY()-orgSceneY+32)<62) {
                            isOk = false;
                            break;
                        }



                    }
                }
                if(isOk&&gameField.money>=40)gameField.getEntities().add(new SniperTower(orgSceneX-32,orgSceneY-32));
                gameField.money-=40;
                if(gameField.money<0) gameField.money+=40;
                //gameField.getEntities().add(new SniperTower(orgSceneX-32,orgSceneY-32));
                draw.setTranslateX(0);
                draw.setTranslateY(0);
                draw.getGraphicsContext2D().clearRect(0,0,3000,2000);
                isDragging = false;
            }
        });
        final Button button3 = new Button();
        InputStream inputStream3 = getClass().getResourceAsStream("image/towerDefense_title402.png");
        final Image image3 = new Image(inputStream3);
        ImageView  imageView3 = new ImageView(image3);
        button3.setGraphic(imageView3);
        button3.setLayoutX(1280);
        button3.setLayoutY(260);
        final Button button6 = new Button("Price: $60, Damage: 35, Range: 150");
        //button4.setMaxHeight(500);
        button6.setLayoutY(260);
        button6.setLayoutX(1000);
        button6.setPrefSize(280,42);
        button6.setVisible(false);
        button3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                button6.setVisible(!button6.isVisible());
            }
        });

        button3.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                x = mouseEvent.getScreenX() - button3.getLayoutX();
                y = mouseEvent.getScreenY() - button3.getLayoutY();
                orgSceneX = mouseEvent.getSceneX();
                orgSceneY = mouseEvent.getSceneY();
                double range = (new MachineGunTower(0,0)).getRange();
                draw.getGraphicsContext2D().strokeOval(1280-range+28,260-range+28 , range*2, range*2);
                draw.getGraphicsContext2D().drawImage(image3,1280,260);
                orgTranslateX = draw.getTranslateX()+x - 32;
                isDragging = true;
                orgTranslateY =  draw.getTranslateY()+y -32;
//                draw.setTranslateY(y);
//                draw.setTranslateX(x);

                //orgTranslateY = ((Circle)(mouseEvent.getSource())).getTranslateY();
            }
        });
        button3.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                double offsetX = mouseEvent.getSceneX() - orgSceneX;
                double offsetY = mouseEvent.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                if(isDragging) {
                    draw.setTranslateX(newTranslateX);
                    draw.setTranslateY(newTranslateY);
                }
            }
        });
        button3.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                orgSceneX = mouseEvent.getScreenX();
                orgSceneY = mouseEvent.getScreenY();
                boolean isOk = true;
                for(GameEntity e: gameField.getEntities()){
                    if(e instanceof Tower){
//                        System.out.println(e.getPosX());
//                        System.out.println(orgSceneX - 32);
//                        System.out.println(Math.abs(e.getPosX()-orgSceneX+32));
//                        System.out.println(Math.abs(e.getPosY()-orgSceneY+32));
                        if(Math.abs(e.getPosX()-orgSceneX+32)<62 && Math.abs(e.getPosY()-orgSceneY+32)<62) {
                            isOk = false;
                            break;
                        }


                    }
                }
                if(isOk&&gameField.money>=60)gameField.getEntities().add(new MachineGunTower(orgSceneX-32,orgSceneY-32));
                gameField.money-=60;
                if(gameField.money<0) gameField.money+=60;
                draw.setTranslateX(0);
                draw.setTranslateY(0);
                draw.getGraphicsContext2D().clearRect(0,0,3000,2000);
                isDragging = false;
            }
        });
       // exitButton.setGraphic(imageView);
//        exitButton.setLayoutX(1280);
//        exitButton.setLayoutY(0);
//        exitButton.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent actionEvent) {
//                System.exit(0);
//            }
//        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                field.getGraphicsContext2D().clearRect(0,0,3000,2000);
                gameField.render(field,l);

            }
        };
        timer.start();
        root.getChildren().add( canvas );
        root.getChildren().add(field);
        root.getChildren().add(draw);
        //root.getChildren().add(exitButton);
       // root.getChildren().addAll(button1,button2,button3,button4,button5,button6);
        root.getChildren().addAll(button1,button4,button2,button5,button3,button6);
        theStage.show();
    }
}
