package sample;

import java.util.LinkedList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import  javafx.scene.control.Button;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

import javafx.scene.shape.Rectangle;

import static javafx.application.Application.launch;
import static javafx.scene.paint.Color.*;

public class Main extends Application {
    Stage window;
    Scene scena_menu, scena_plansza, scena_zasady;

    Button przycisk_mulitiplayer;
    Button przycisk_komputer;
    Button przycisk_zasady;
    Button przycisk_menu;
    LinkedList kwadraty_lista = new LinkedList<Rectangle>();
    LinkedList kola_lista = new LinkedList<Circle>();

    static  int roz_x = 50;
    static  int roz_y = 50;






    public static void main(String[] args) {

        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception{






        window = primaryStage;
        //przycisk1
        przycisk_zasady = new Button();
        przycisk_zasady.setText("Zasady");
        przycisk_mulitiplayer = new Button();
        przycisk_mulitiplayer.setText("Multiplayer");
        przycisk_komputer = new Button();
        przycisk_komputer.setText("komputer");

        przycisk_menu = new Button();
        przycisk_menu.setText("Menu");
        przycisk_menu.setOnAction(e -> window.setScene(scena_menu));


        // menu
        VBox layout  = new VBox(20);
        layout.getChildren().addAll(przycisk_zasady,przycisk_mulitiplayer,przycisk_komputer);
        scena_menu = new Scene(layout,1000,500);
        primaryStage.setScene(scena_menu);
        primaryStage.show();
        przycisk_zasady.setOnAction(e -> window.setScene(scena_zasady));
        przycisk_mulitiplayer.setOnAction(e -> window.setScene(scena_plansza));



        //zasady
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(przycisk_menu);
        scena_zasady = new Scene(layout2,1000,500);

        //plansza
        Group root = new Group();
        scena_plansza = new Scene(root,1000,500);
        root.getChildren().add(przycisk_menu);
        Rectangle kwadrat = new Rectangle(100,100,100,100);
        kwadrat.setOnMouseClicked(e -> System.out.println(kwadrat.getX()));
        root.getChildren().add(kwadrat);


        // tworzy plansze
        //Tworze plansze
        int licz = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 8; j++){
                Rectangle kwadratt = new Rectangle(roz_x * i,roz_y * j,roz_x,roz_y);
                if (licz % 2 == 0) {
                    kwadratt.setFill(AQUA);
                }
                else {
                    kwadrat.setFill(BROWN);
                }
                kwadraty_lista.add(kwadratt);

                root.getChildren().add(kwadratt);
                licz+=1;
            }
            licz+=1;
        }






        //kolo

        Circle kolo = new Circle(150, 150, 50, AQUA);
        kolo.setOnMouseClicked(e -> System.out.println("kolo"));
        root.getChildren().add(kolo);





    }

}