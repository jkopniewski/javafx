/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.util.Duration;
import java.io.File;
import javafx.scene.media.*;


/**
 * @author Jakub Kopniewski, Piotr Kamiński, Michał Krzewicki, Patryb Borucki
 * @version 1.0
 *
 *
 */
public class Main extends Application {

    private static final int WIDTH = 1366;
    private static final int HEIGHT = 786;
    private int monety = 0;
    private int produkty = 6;
    private int clickCount= 0;


    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);


    @Override
    public void start(Stage primaryStage) {

        Camera camera = new PerspectiveCamera();
        camera.setTranslateZ(-900);
        camera.setTranslateY(-135);

        Group groupAutomat = prepareAutomat();

        Group root = new Group();
        root.getChildren().addAll(groupAutomat);

        Scene scene = new Scene(root, WIDTH, HEIGHT, true, SceneAntialiasing.BALANCED);
        scene.setFill(Color.LIGHTGREY);
        scene.setCamera(camera);

        groupAutomat.translateXProperty().set(WIDTH / 2);
        groupAutomat.translateYProperty().set(HEIGHT / 2);
        groupAutomat.translateZProperty().set(-1500);

        initMouseControl(groupAutomat, scene);

        primaryStage.setTitle("Automat z napojami");
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
        Box polka2 = new Box(150, 10, 25);
        Rectangle szyba = new Rectangle(150, 265);
        Rectangle klapa1 = new Rectangle(90, 40);
        Box klapa2 = new Box(90,40,2);

        PhongMaterial polka = new PhongMaterial();
        polka.setDiffuseColor(Color.BLACK);
        szyba.setFill(Color.LIGHTBLUE);
        szyba.setOpacity(0.3);
        szyba.setTranslateY(-270);
        szyba.setTranslateZ(-35);
        szyba.setTranslateX(-75);

        polka1.setTranslateY(-200);
        polka1.setTranslateZ(20);
        polka1.setMaterial(polka);

        polka2.setTranslateY(-107);
        polka2.setTranslateZ(20);
        polka2.setMaterial(polka);

        boxTyl.setTranslateY(-120);
        boxTyl.setTranslateX(20);
        boxTyl.setTranslateZ(36);

        boxLewyBok.setTranslateX(-80);
        boxLewyBok.setTranslateY(-120);

        boxPrawyBok.setTranslateX(90);
        boxPrawyBok.setTranslateY(-120);

        boxGora.setTranslateY(-275);
        boxGora.setTranslateX(17.5);

        klapa1.setTranslateZ(-41);
        klapa1.setTranslateX(-50);
        klapa1.setTranslateY(-20);

        klapa2.setTranslateZ(-41);
        klapa2.setTranslateX(-5);


        PhongMaterial texture1 = new PhongMaterial();
        Image obudowa = new Image("sample/resources/dark-metal-texture.jpg");
        texture1.setDiffuseMap(obudowa);

        PhongMaterial texture_klapa = new PhongMaterial();
        Image klapa = new Image("sample/resources/texture-panel.jpg");
        texture_klapa.setDiffuseMap(klapa);
        klapa2.setMaterial(texture_klapa);

        boxDol.setMaterial(texture1);
        boxLewyBok.setMaterial(texture1);
        boxPrawyBok.setMaterial(texture1);
        boxGora.setMaterial(texture1);
        boxTyl.setMaterial(texture1);

        //puszki z napojami
        Cylinder puszka1 = new Cylinder(10f,35f);
        Cylinder puszka2 = new Cylinder(10f,35f);
        Cylinder puszka3 = new Cylinder(10f,35f);
        Cylinder puszka4 = new Cylinder(10f,35f);
        Cylinder puszka5 = new Cylinder(10f,35f);
        Cylinder puszka6 = new Cylinder(10f,35f);

        puszka1.setTranslateY(-220);
        puszka1.setTranslateX(-50);
        puszka1.setTranslateZ(20);

        puszka2.setTranslateY(-220);
        puszka2.setTranslateX(-10);
        puszka2.setTranslateZ(20);

        puszka3.setTranslateY(-220);
        puszka3.setTranslateX(30);
        puszka3.setTranslateZ(20);

        puszka4.setTranslateY(-130);
        puszka4.setTranslateX(-50);
        puszka4.setTranslateZ(20);

        puszka5.setTranslateY(-130);
        puszka5.setTranslateX(-10);
        puszka5.setTranslateZ(20);

        puszka6.setTranslateY(-130);
        puszka6.setTranslateX(30);
        puszka6.setTranslateZ(20);


        //tekstury puszek


        PhongMaterial texture_cola = new PhongMaterial();
        Image cola = new Image("sample/resources/cocacola.jpg");
        texture_cola.setDiffuseMap(cola);

        PhongMaterial texture_pepsi = new PhongMaterial();
        Image pepsi = new Image("sample/resources/pepsi.jpg");
        texture_pepsi.setDiffuseMap(pepsi);

        PhongMaterial texture_sprite = new PhongMaterial();
        Image sprite = new Image("sample/resources/sprite.jpg");
        texture_sprite.setDiffuseMap(sprite);

        puszka1.setMaterial(texture_cola);
        puszka4.setMaterial(texture_cola);
        puszka2.setMaterial(texture_pepsi);
        puszka5.setMaterial(texture_pepsi);
        puszka3.setMaterial(texture_sprite);
        puszka6.setMaterial(texture_sprite);

        Group panel = preparePanel(puszka1, puszka2, puszka3, puszka4, puszka5, puszka6);

        SmartGroup groupAutomat = new SmartGroup();
        groupAutomat.getChildren().addAll(boxDol, klapa1, klapa2, boxLewyBok, boxPrawyBok, boxGora, boxTyl, polka1, polka2, puszka1, puszka2, puszka3, puszka4, puszka5, puszka6, szyba, panel);

        return groupAutomat;
    }

    private Group preparePanel(Cylinder puszka1, Cylinder puszka2, Cylinder puszka3, Cylinder puszka4, Cylinder puszka5, Cylinder puszka6){
       //tekstury panelu, ekranu i przycisków
        PhongMaterial texture_panel = new PhongMaterial();
        Image panel_t = new Image("sample/resources/texture-panel.jpg");
        texture_panel.setDiffuseMap(panel_t);

        String pathM = "G://Projekty Java/kck-projekt/src/sample/moneta.mp3";
        AudioClip audioM = new AudioClip(new File(pathM).toURI().toString());

        String pathP = "G://Projekty Java/kck-projekt/src/sample/puszka.mp3";
        AudioClip audioP = new AudioClip(new File(pathP).toURI().toString());

        String pathP2 = "G://Projekty Java/kck-projekt/src/sample/puszka2.mp3";
        AudioClip audioP2 = new AudioClip(new File(pathP2).toURI().toString());

        PhongMaterial texture_alert1 = new PhongMaterial();
        Image alert1 = new Image("sample/resources/alert1.png");
        texture_alert1.setDiffuseMap(alert1);

        PhongMaterial texture_alert2 = new PhongMaterial();
        Image alert2 = new Image("sample/resources/alert2.png");
        texture_alert2.setDiffuseMap(alert2);

        PhongMaterial texture_alert3 = new PhongMaterial();
        Image alert3 = new Image("sample/resources/alert3.png");
        texture_alert3.setDiffuseMap(alert3);

        PhongMaterial texture_moneta = new PhongMaterial();
        Image moneta_2 = new Image("sample/resources/moneta_2.png");
        texture_moneta.setDiffuseMap(moneta_2);

        PhongMaterial texture_C = new PhongMaterial();
        Image cocacola_logo = new Image("sample/resources/cocacola-logo.png");
        texture_C.setDiffuseMap(cocacola_logo);

        PhongMaterial texture_P = new PhongMaterial();
        Image pepsi_logo = new Image("sample/resources/pepsi-logo.jpg");
        texture_P.setDiffuseMap(pepsi_logo);

        PhongMaterial texture_S = new PhongMaterial();
        Image sprite_logo = new Image("sample/resources/sprite-logo.jpg");
        texture_S.setDiffuseMap(sprite_logo);

        Box panel = new Box(45, 150,1);

        Box tlo_ekranu = new Box(30, 10, 1);

        tlo_ekranu.setTranslateZ(-42);
        tlo_ekranu.setTranslateY(-130);
        tlo_ekranu.setTranslateX(90);
        tlo_ekranu.setMaterial(texture_alert1);

        panel.setTranslateZ(-41);
        panel.setTranslateX(90);
        panel.setTranslateY(-190);
        panel.setMaterial(texture_panel);

        Rectangle doMonet = new Rectangle(4, 10);
        doMonet.setTranslateX(88);
        doMonet.setTranslateY(-240);
        doMonet.setTranslateZ(-42);

        Cylinder moneta = new Cylinder(5,2);
        moneta.setMaterial(texture_moneta);
        //przyciski do wyboru napojów
        Box produkt1 = new Box(15, 10, 1);
        Box produkt2 = new Box(15, 10, 1);
        Box produkt3 = new Box(15, 10, 1);
        Box produkt4 = new Box(15, 10, 1);
        Box produkt5 = new Box(15, 10, 1);
        Box produkt6 = new Box(15, 10, 1);

        //dodanie tekstur do przycisków

        produkt1.setMaterial(texture_C);
        produkt4.setMaterial(texture_C);

        produkt2.setMaterial(texture_P);
        produkt5.setMaterial(texture_P);

        produkt3.setMaterial(texture_S);
        produkt6.setMaterial(texture_S);
        //ustawienie przycisków
        produkt1.setTranslateZ(-42);
        produkt1.setTranslateX(78);
        produkt1.setTranslateY(-200);

        produkt2.setTranslateZ(-42);
        produkt2.setTranslateX(78);
        produkt2.setTranslateY(-180);

        produkt3.setTranslateZ(-42);
        produkt3.setTranslateX(78);
        produkt3.setTranslateY(-160);

        produkt4.setTranslateZ(-42);
        produkt4.setTranslateX(100);
        produkt4.setTranslateY(-200);

        produkt5.setTranslateZ(-42);
        produkt5.setTranslateX(100);
        produkt5.setTranslateY(-180);

        produkt6.setTranslateZ(-42);
        produkt6.setTranslateX(100);
        produkt6.setTranslateY(-160);


        //funkcje wybierania
            doMonet.setOnMouseClicked(e -> {
                if(clickCount==0) {
                    clickCount=1;
                    System.out.println("Wrzucono żeton");
                    moneta.setTranslateZ(-45);
                    moneta.setTranslateX(90);
                    moneta.setTranslateY(-235);
                    moneta.setRotate(90);
                    TranslateTransition tr = new TranslateTransition(Duration.millis(750), moneta);
                    tr.setToZ(-35);
                    tr.play();

                    audioM.play();
                    monety = 1;
                    tlo_ekranu.setMaterial(texture_alert2);
                    if (produkty == 0) {
                        doMonet.setOnMouseClicked(null);
                        tlo_ekranu.setMaterial(texture_alert3);
                        moneta.setOpacity(0);
                    }
                }
                else {
                    doMonet.setDisable(true);
                }
            });


        produkt1.setOnMouseClicked(ev -> { if(monety==1) {

            Cylinder prod1 = puszka1;
            audioP.play();
            TranslateTransition tr = new TranslateTransition(Duration.millis(300), puszka1);
            tr.setToZ(-15);
            tr.play();
            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(prod1);
            rt.setDuration(Duration.millis(1000));
            rt.setByAngle(90);
            rt.play();
            Line linePath = new Line(-50, -220, -50, -10);

            PathTransition pt = new PathTransition();

            pt.setDuration(Duration.seconds(1.8));

            pt.setNode(prod1);
            pt.setPath(linePath);

            pt.play();
            produkt1.setOnMouseClicked(null);
            doMonet.setDisable(false);
            tlo_ekranu.setMaterial(texture_alert1);

            monety--;
            clickCount--;
            produkty--;

        }
        /*klapa2.setOnMouseClicked(event -> {
            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(klapa2);
            rt.setDuration(Duration.millis(1000));
            rt.setByAngle(65);
            rt.setAutoReverse(true);
            rt.play();
        });*/

        });
        produkt2.setOnMouseClicked(ev->{ if(monety==1) { Cylinder prod2 = puszka2;
            audioP.play();
            TranslateTransition tr = new TranslateTransition(Duration.millis(300), puszka2);
            tr.setToZ(-15);
            tr.play();
            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(prod2);
            rt.setDuration(Duration.millis(2000));
            rt.setByAngle(90);
            rt.play();
            Line linePath = new Line(-10, -220, -10, -10);

            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(1.8));

            pt.setNode(prod2);
            pt.setPath(linePath);

            pt.play();
            produkt2.setOnMouseClicked(null);
            doMonet.setDisable(false);

            monety--;
            clickCount--;
            produkty--;
            tlo_ekranu.setMaterial(texture_alert1);
        } });

        produkt3.setOnMouseClicked(ev->{ if(monety==1) { Cylinder prod3 = puszka3;
            audioP.play();
            TranslateTransition tr = new TranslateTransition(Duration.millis(300), puszka3);
            tr.setToZ(-15);
            tr.play();
            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(prod3);
            rt.setDuration(Duration.millis(1000));
            rt.setByAngle(90);
            rt.play();
            Line linePath = new Line(30, -220, 30, -10);

            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(1.8));

            pt.setNode(prod3);
            pt.setPath(linePath);

            pt.play();
            produkt3.setOnMouseClicked(null);
            doMonet.setDisable(false);

            monety--;
            clickCount--;
            produkty--;
            tlo_ekranu.setMaterial(texture_alert1);
            }
        });
        produkt4.setOnMouseClicked(ev->{ if(monety==1) { Cylinder prod4 = puszka4;
            audioP2.play();
            TranslateTransition tr = new TranslateTransition(Duration.millis(300), puszka4);
            tr.setToZ(-15);
            tr.play();
            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(prod4);
            rt.setDuration(Duration.millis(1000));
            rt.setByAngle(90);
            rt.play();
            Line linePath = new Line(-50, -130, -50, -10);

            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(1));

            pt.setNode(prod4);
            pt.setPath(linePath);

            pt.play();
            produkt4.setOnMouseClicked(null);
            doMonet.setDisable(false);

            monety--;
            produkty--;
            clickCount--;
            tlo_ekranu.setMaterial(texture_alert1);
        }
        });
        produkt5.setOnMouseClicked(ev->{ if(monety==1) { Cylinder prod5 = puszka5;
            audioP2.play();
            TranslateTransition tr = new TranslateTransition(Duration.millis(300), puszka5);
            tr.setToZ(-15);
            tr.play();
            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(prod5);
            rt.setDuration(Duration.millis(1000));
            rt.setByAngle(90);
            rt.play();
            Line linePath = new Line(-10, -130, -10, -10);

            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(1));

            pt.setNode(prod5);
            pt.setPath(linePath);

            pt.play();
            produkt5.setOnMouseClicked(null);
            doMonet.setDisable(false);

            monety--;
            produkty--;
            clickCount--;
            tlo_ekranu.setMaterial(texture_alert1);
        }
        });
        produkt6.setOnMouseClicked(ev->{ if(monety==1) { Cylinder prod6 = puszka6;
            audioP2.play();
            TranslateTransition tr = new TranslateTransition(Duration.millis(300), puszka6);
            tr.setToZ(-15);
            tr.play();
            RotateTransition rt = new RotateTransition();
            rt.setAxis(Rotate.X_AXIS);
            rt.setNode(prod6);
            rt.setDuration(Duration.millis(1000));
            rt.setByAngle(90);
            rt.play();
            Line linePath = new Line(30, -130, 30, -10);

            PathTransition pt = new PathTransition();
            pt.setDuration(Duration.seconds(1));

            pt.setNode(prod6);
            pt.setPath(linePath);

            pt.play();
            produkt6.setOnMouseClicked(null);
            doMonet.setDisable(false);

            monety--;
            produkty--;
            clickCount--;
            tlo_ekranu.setMaterial(texture_alert1);

        }
        });


        SmartGroup groupPanel = new SmartGroup();
        groupPanel.getChildren().addAll(panel, produkt1, produkt2, produkt3, produkt4, produkt5, produkt6, doMonet, moneta,tlo_ekranu);


        return groupPanel;
    }


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
        scene.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - delta);
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