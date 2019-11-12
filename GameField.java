import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GameField implements BulletDestroyCallback, EnemyDestroyCallback{
    //boolean
    ArrayList<GameEntity>entities = new ArrayList<GameEntity>();
    public long money = 70;
    public long heart = 30;
    public ArrayList<GameEntity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<GameEntity> entities) {
        this.entities = entities;
    }

    //int i=0;
    long delay = 1000000000;
    public long starttime = 0;
    public GameField() {
        for(int  i=0;i<10;++i){
            Enemy enemy = new NormalEnemy(10,610,0,(i+1)*delay*3+10000000);
            enemy.setGameField(this);
            this.entities.add(enemy);
        }
        for(int j = 0;j<10;++j){
            Enemy enemy = new SmallerEnemy(10,610,0,(j+19)*delay*3+10000000);
            enemy.setGameField(this);
            this.entities.add(enemy);
        }
        for(int j = 0;j<10;++j){
            Enemy enemy = new TankerEnemy(10,610,0,(j+30)*delay*3+10000000);
            enemy.setGameField(this);
            this.entities.add(enemy);
        }
        for(int j = 0;j<5;++j){
            Enemy enemy = new BossEnemy(10,610,0,(45+j)*delay*3+10000000);
            enemy.setGameField(this);
            this.entities.add(enemy);
        }
        for(int j = 0;j<6;++j) {
            Enemy enemy = new BossEnemy(10, 610, 0, (60+j) * delay *3 + 10000000);
            enemy.setGameField(this);
            this.entities.add(enemy);
        }
        for(int j=0;j<20;++j){
            Enemy enemy = new TankerEnemy(10,610,0,(56+j)*delay*3+10000000);
            enemy.setGameField(this);
            this.entities.add(enemy);
            Enemy enemy1 = new NormalEnemy(10,610,0,(59+j)*delay*3+10000000);
            enemy1.setGameField(this);
            this.entities.add(enemy1);
            Enemy enemy2 = new SmallerEnemy(10,610,0,(65+j)*delay*3+10000000);
            enemy2.setGameField(this);
            this.entities.add(enemy2);
        }

    }

    public void render(Canvas canvas, long time){
//        Bullet bullet = new Bullet(0,0);
//        Image image = bullet.getImage1();
        for(int i= 0;i<entities.size();++i){
            GameEntity ent = entities.get(i);
            if(ent instanceof Enemy){
                Point point = new Point(ent.getPosX(),ent.getPosY());
                for(int j = 0;j<entities.size();++j){
                    GameEntity ent1 = entities.get(j);
                    if(ent1 instanceof Tower) {
                        Point point1 = new Point(ent1.getPosX(),ent1.getPosY());
                        double kc = point.distance(point1);
                        if(point.distance(point1)<= ((Tower) ent1).getRange()){
                            if(!((Tower) ent1).isShotting()){
                                Bullet b = new Bullet(ent1.getPosX(),ent1.getPosY(),ent.getPosX(), ent.getPosY(), this, (Enemy)ent, ((Tower) ent1).getDamage());
                                this.getEntities().add(b);
                                ((Tower) ent1).setBullet(b);
                                ((Tower) ent1).setShotting(true);
                            }
                           // for()
                            //System.out.println(point.distance(point1));
//                                canvas.getGraphicsContext2D().drawImage(image,ent.getPosX(),ent.getPosY());
                        }

                    }
                }
            }
        }

        if(starttime==0){
            starttime = time;
        }

       // System.out.println(time-starttime);
//
//        if(i<200 && i%20 == 0){
//            this.entities.add(new NormalEnemy(10,610,0,(i+1)*458992600));
//        }
//        i++;
        //
      //  if(this.entities.size()==0) this.entities.add(new NormalEnemy(10,610, time));
        for(int i =0;i<entities.size();++i){
            GameEntity ent = entities.get(i);
            List<Image> list = ent.getImage();
            if(ent instanceof MovableEntity){
                if(!(((MovableEntity)ent).getAppearTime()*2<time-starttime)){
                    continue;
                }else{
                    ((MovableEntity) ent).setStartTime(starttime + ((MovableEntity) ent).getAppearTime());
                }
            }
            for(Image j : list)
                canvas.getGraphicsContext2D().drawImage(j,ent.getPosX(),ent.getPosY());
            if(ent instanceof MovableEntity){
                ((MovableEntity)ent).move(time);
            }

        }
            String heart = "heart: "+ this.heart;
            String money = "money: "+ this.money;
            canvas.getGraphicsContext2D().strokeText(heart,1250,115);
            canvas.getGraphicsContext2D().strokeText(money,1250,140);
            canvas.getGraphicsContext2D().setFont(Font.font("Times New Roman",20));
            canvas.getGraphicsContext2D().setFill(Color.RED);
        //        Text text= new Text("health: "+ this.heart);
        //        text.setX(100);
        //        text.setY(100);


    }

    @Override
    public void destroyBullet(Bullet b) {
        for(GameEntity e : getEntities()){
            if(e instanceof Tower){
                if(((Tower) e).getBullet() == b) {
                    ((Tower) e).setShotting(false);
                }
            }
        }
        this.getEntities().remove(b);
    }

    @Override
    public void enemyDestroyCallback(Enemy e, boolean catchTarget) {
        if (catchTarget) {
            if (e instanceof NormalEnemy) this.heart--;
            else if (e instanceof SmallerEnemy) this.heart = this.heart - 2;
            else if (e instanceof TankerEnemy) this.heart = this.heart - 3;
            else if (e instanceof BossEnemy) this.heart = this.heart - 4;
            //if (heart < 0) {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("YOU LOSE");
//                alert.setHeaderText("COMEBACK MENU");
//                ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
//                ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.NO);
//                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
//                Optional<ButtonType> result = alert.showAndWait();
//                if(result.get()==buttonTypeYes) {
//                    Platform.runLater(new Runnable() {
//                        public void run() {
//                            try {
//                                new Main().start(new Stage());
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//                    });
//                }
//                else System.exit(0);
//
//            }
            } else {
                this.money += e.getBonus();
            }
//        System.out.println("money: "+ this.money);
//        System.out.println("heart: "+ this.heart);
            //Text
            this.entities.remove(e);
        }

}
