/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kck_automat;

import javafx.application.Application;
import javafx.scene.*;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

/**
 * @author afsal villan
 * @version 1.0
 *
 * http://www.genuinecoder.com
 */
public class Main extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 1080;

    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);

    @Override
    public void start(Stage primaryStage) {
        //widok automatu
        Box boxDol = new Box(150, 60, 80);
        Box boxLewyBok = new Box(10, 300, 80);
        Box boxPrawyBok = new Box(60, 300, 80);
        Box boxGora = new Box(205, 10, 80);
        Box boxTyl = new Box(200, 300, 10);
        Rectangle szyba = new Rectangle(150, 265);

        PhongMaterial texture1 = new PhongMaterial();
        Image obudowa = new Image("sample/resources/dark-metal-texture.jpg");
        texture1.setDiffuseMap(obudowa);

        PhongMaterial test = new PhongMaterial();
        szyba.setFill(Color.LIGHTBLUE);
        szyba.setOpacity(0.3);
        szyba.setTranslateY(-270);
        szyba.setTranslateZ(-35);
        szyba.setTranslateX(-75);

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



        Rectangle panel = new Rectangle(45, 150);
        panel.setTranslateZ(-41);
        panel.setTranslateX(68);
        panel.setTranslateY(-250);
        panel.setFill(Color.GREY);

        SmartGroup group = new SmartGroup();
        group.getChildren().addAll(boxDol, boxLewyBok, boxPrawyBok, boxGora, boxTyl, panel, szyba);



        Camera camera = new PerspectiveCamera();
        camera.setTranslateZ(-500);
        camera.setTranslateY(-125);

        Scene scene = new Scene(group, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.LIGHTGREY);
        scene.setCamera(camera);


        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);
        group.translateZProperty().set(-1500);

        initMouseControl(group, scene);



        primaryStage.setTitle("Automat");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initMouseControl(SmartGroup group, Scene scene) {
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