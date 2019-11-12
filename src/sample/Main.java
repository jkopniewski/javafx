/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Orientation;
import javafx.geometry.Point3D;
import javafx.scene.*;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.PickResult;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;


/**
 * @author afsal villan
 * @version 1.0
 *
 * http://www.genuinecoder.com
 */
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;



/**
 * @author Jakub
 * @version 1.0
 *
 *
 */
public class Main extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private int monety = 0;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);


    @Override
    public void start(Stage primaryStage) {

        Camera camera = new PerspectiveCamera();
        camera.setTranslateZ(-1000);
        camera.setTranslateY(-125);

        //Slider slider = prepareSlider();

        Group groupAutomat = prepareAutomat();



        Group root = new Group();
        root.getChildren().addAll(groupAutomat);

        Scene scene = new Scene(root, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.LIGHTGREY);
        scene.setCamera(camera);

      /*  BorderPane pane = new BorderPane();
        pane.setCenter(subScene);
        Button button = new Button("Moneta");

        ToolBar toolBar = new ToolBar(button);
        toolBar.setOrientation(Orientation.VERTICAL);
        pane.setRight(toolBar);
        pane.setPrefSize(300,300);

        Scene scene = new Scene(pane);*/


        groupAutomat.translateXProperty().set(WIDTH / 2);
        groupAutomat.translateYProperty().set(HEIGHT / 2);
        groupAutomat.translateZProperty().set(-1500);

        initMouseControl(groupAutomat, scene);


        primaryStage.setTitle("Automat");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private Group prepareAutomat(){
        //widok automatu
        Box boxDol = new Box(150, 60, 80);
        Box boxLewyBok = new Box(10, 300, 80);
        Box boxPrawyBok = new Box(60, 300, 80);
        Box boxGora = new Box(205, 10, 80);
        Box boxTyl = new Box(200, 300, 10);
        Box polka1 = new Box(150, 10, 25);

        Rectangle szyba = new Rectangle(150, 265);

        Cylinder puszka1 = new Cylinder(10f,35f);
        Cylinder puszka2 = new Cylinder(10f,35f);
        Cylinder puszka3 = new Cylinder(10f,35f);

        puszka1.setTranslateY(-220);
        puszka1.setTranslateX(-50);
        puszka1.setTranslateZ(20);

        puszka2.setTranslateY(-220);
        puszka2.setTranslateX(-10);
        puszka2.setTranslateZ(20);

        puszka3.setTranslateY(-220);
        puszka3.setTranslateX(30);
        puszka3.setTranslateZ(20);

        PhongMaterial texture1 = new PhongMaterial();
        Image obudowa = new Image("sample/resources/dark-metal-texture.jpg");
        texture1.setDiffuseMap(obudowa);

        PhongMaterial test = new PhongMaterial();
        szyba.setFill(Color.LIGHTBLUE);
        szyba.setOpacity(0.3);
        szyba.setTranslateY(-270);
        szyba.setTranslateZ(-35);
        szyba.setTranslateX(-75);

        polka1.setTranslateY(-200);
        polka1.setTranslateZ(20);

        boxDol.setMaterial(texture1);
        boxLewyBok.setMaterial(texture1);
        boxPrawyBok.setMaterial(texture1);
        boxGora.setMaterial(texture1);
        boxTyl.setMaterial(texture1);

        boxTyl.setTranslateY(-120);
        boxTyl.setTranslateX(20);
        boxTyl.setTranslateZ(36);

        boxLewyBok.setTranslateX(-80);
        boxLewyBok.setTranslateY(-120);

        boxPrawyBok.setTranslateX(90);
        boxPrawyBok.setTranslateY(-120);

        boxGora.setTranslateY(-275);
        boxGora.setTranslateX(17.5);

        Group panel = preparePanel(puszka1, puszka2, puszka3);

        SmartGroup groupAutomat = new SmartGroup();
        groupAutomat.getChildren().addAll(boxDol, boxLewyBok, boxPrawyBok, boxGora, boxTyl, polka1, puszka1, puszka2, puszka3, szyba, panel);

        return groupAutomat;
    }

    //private Group prepareProdukty();
    private Group preparePanel(Cylinder puszka1, Cylinder puszka2, Cylinder puszka3){
        Rectangle panel = new Rectangle(45, 150);

        panel.setTranslateZ(-41);
        panel.setTranslateX(68);
        panel.setTranslateY(-250);
        panel.setFill(Color.GREY);

        Rectangle doMonet = new Rectangle(5, 10);

        //moneta.setTranslateZ();
        doMonet.setTranslateX(88);
        doMonet.setTranslateY(-240);
        doMonet.setTranslateZ(-42);

        Rectangle produkt1 = new Rectangle(15, 10);
        Rectangle produkt2 = new Rectangle(15, 10);
        Rectangle produkt3 = new Rectangle(15, 10);

        produkt1.setTranslateZ(-42);
        produkt1.setTranslateX(78);
        produkt1.setTranslateY(-200);

        produkt2.setTranslateZ(-42);
        produkt2.setTranslateX(78);
        produkt2.setTranslateY(-180);

        produkt3.setTranslateZ(-42);
        produkt3.setTranslateX(78);
        produkt3.setTranslateY(-160);

        Cylinder moneta = new Cylinder(5,2);

            doMonet.setOnMouseClicked(e -> {
                moneta.setTranslateZ(-45);
                moneta.setTranslateX(90);
                moneta.setTranslateY(-235);
                //moneta.setRotate(90);

                RotateTransition rt = new RotateTransition();
                rt.setDuration(Duration.seconds(2.5));
                rt.setAxis(Rotate.Z_AXIS);
                rt.setByAngle(90);
                rt.setNode(moneta);
                rt.play();


                System.out.println("Wrzucono monete 2 zl");
                monety = 1;
            });


        produkt1.setOnMouseClicked(ev -> { if(monety==1) {
            Cylinder prod1 = puszka1;
            //Bounds boundsInScreen = puszka.localToScreen(puszka.getBoundsInLocal());

            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(prod1);
            rt.setDuration(Duration.millis(1000));
            rt.setByAngle(90);
            rt.play();
            Line linePath = new Line(-50, -220, -50, -10);
            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(2.5));

            pt.setNode(prod1);
            pt.setPath(linePath);

            pt.play();
            produkt1.setOnMouseClicked(null);
            monety = 0;
            //System.out.println(boundsInScreen);
        }
            });
        produkt2.setOnMouseClicked(ev->{ if(monety==1) { Cylinder prod2 = puszka2;
            //Bounds boundsInScreen = puszka.localToScreen(puszka.getBoundsInLocal());

            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(prod2);
            rt.setDuration(Duration.millis(1000));
            rt.setByAngle(90);
            rt.play();
            Line linePath = new Line(-10, -220, -10, -10);
            //Line linePath = new Line(-30,-220,-30,-10);
            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(2.5));

            pt.setNode(prod2);
            pt.setPath(linePath);

            pt.play();
            produkt2.setOnMouseClicked(null);
            //System.out.println(boundsInScreen);
            monety = 0; } });
                produkt3.setOnMouseClicked(ev->{
            if(monety==1) {
                Cylinder prod3 = puszka3;
                //Bounds boundsInScreen = puszka.localToScreen(puszka.getBoundsInLocal());

                RotateTransition rt = new RotateTransition();
                rt.setAxis(Rotate.X_AXIS);
                rt.setNode(prod3);
                rt.setDuration(Duration.millis(1000));
                rt.setByAngle(90);
                rt.play();
                Line linePath = new Line(30, -220, 30, -10);
                //Line linePath = new Line(-30,-220,-30,-10);
                PathTransition pt = new PathTransition();
                pt.setDuration(Duration.seconds(2.5));

                pt.setNode(prod3);
                pt.setPath(linePath);

                pt.play();
                produkt3.setOnMouseClicked(null);
                //System.out.println(boundsInScreen);
                monety = 0;
            }
        });






        //PhongMaterial texture = new PhongMaterial();
        //Image obudowaP = new Image("sample/resources/texture.png");
        //texture.setDiffuseMap(obudowaP);

        SmartGroup groupPanel = new SmartGroup();
        groupPanel.getChildren().addAll(panel, produkt1, produkt2, produkt3, doMonet, moneta);


        return groupPanel;
    }

    /*private Button prepareMoneta(){
        Button moneta = new Button();
        mone

        return moneta;
    }*/

    private void initMouseControl(Group group, Scene scene) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS)
        );
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

    class SmartGroup extends Group {

        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotateByY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }
    }
}