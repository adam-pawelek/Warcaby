package sample;


import java.io.IOException;
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
import sample.Plansza;

import static javafx.application.Application.launch;
import static javafx.scene.paint.Color.*;

import java.net.*;
import java.io.*;

public class KolejnyGracz extends Application {
    Stage window;
    Scene scena_menu, scena_plansza, scena_zasady;

    Button przycisk_stream;
    Button przycisk_komputer;
    Button przycisk_zasady;
    Button przycisk_menu_plansza;
    LinkedList kwadraty_lista = new LinkedList<Rectangle>();
    LinkedList kola_red_lista = new LinkedList<Circle>();
    LinkedList kola_white_lista = new LinkedList<Circle>();

    LinkedList zajete_red = new LinkedList<Pozycja>();

    LinkedList zajete_white = new LinkedList<Pozycja>();

    static  int roz_x = 50;
    static  int roz_y = 50;
    boolean wyslij_pionek;
    Circle ostatni_wyslij;
    Circle nowy_wyslij;






    public static void main(String[] args) throws IOException {
        //Thread t1 = new Thread(new Serwer(kwadraty_lista,kola_red_lista,kola_white_lista,root,zajete_red,zajete_white,roz_x,roz_y));
        // t1.start();

        launch(args);


    }
    @Override
    public void start(Stage primaryStage) throws Exception{






        window = primaryStage;
        //przycisk1
        przycisk_zasady = new Button();
        przycisk_zasady.setText("Zasady");
        przycisk_stream = new Button();
        przycisk_stream.setText("Multiplayer");
        przycisk_komputer = new Button();
        przycisk_komputer.setText("komputer");

        przycisk_menu_plansza = new Button();
        przycisk_menu_plansza.setText("Menu");
        przycisk_menu_plansza.setOnAction(e -> window.setScene(scena_menu));


        // menu
        VBox layout  = new VBox(20);
        layout.getChildren().addAll(przycisk_zasady,przycisk_stream,przycisk_komputer);
        scena_menu = new Scene(layout,1000,500);
        primaryStage.setScene(scena_menu);
        primaryStage.show();
        przycisk_zasady.setOnAction(e -> window.setScene(scena_zasady));
        przycisk_stream.setOnAction(e -> window.setScene(scena_plansza));



        //zasady
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(przycisk_menu_plansza);
        scena_zasady = new Scene(layout2,1000,500);

        //plansza
        Group root = new Group();
        scena_plansza = new Scene(root,1000,500);
        root.getChildren().add(przycisk_menu_plansza);


        // tworzy plansze
        //Tworze plansze
        Plansza plansza = new Plansza(kwadraty_lista,kola_red_lista,kola_white_lista,root,zajete_red,zajete_white,roz_x,roz_y,wyslij_pionek, ostatni_wyslij, nowy_wyslij);
        plansza.rysujKwadraty(roz_x,roz_y);
        /*
        int licz = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Rectangle kwadratt = new Rectangle(roz_x * i,roz_y * j,roz_x,roz_y);
                if (licz % 2 == 0) {
                    kwadratt.setFill(AQUA);
                }
                else {
                    kwadratt.setFill(BLACK);
                }
                kwadraty_lista.add(kwadratt);

                root.getChildren().add(kwadratt);
                licz+=1;
            }
            licz+=1;
        }
        */

        //tworzy pionki
        //czerwone
        /*
        int licz = 1;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++){
                Circle koloo = new Circle(roz_x * i + roz_x / 2,roz_y * j+ roz_y / 2,roz_x / 2);
                if (licz % 2 == 0) {
                    koloo.setFill(RED);
                    koloo.setOnMouseClicked(e -> koloo.setCenterX(200));
                    Pozycja pom = new Pozycja(roz_x * i,roz_y * j,roz_x,roz_y,koloo);
                    zajete_red.add(pom);
                }
                else{
                    licz+=1;
                    continue;
                }
                kola_red_lista.add(koloo);
                root.getChildren().add(koloo);
                licz+=1;
            }

        }
        */
        plansza.rysujCzerwone(roz_x,roz_y);
        plansza.rysujBiale(roz_x,roz_y);

        //biale
        /*
        int licz = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 5; j < 8; j++){
                Circle koloo = new Circle(roz_x * i + roz_x / 2,roz_y * j+ roz_y / 2,roz_x / 2);
                if (licz % 2 == 0) {
                    koloo.setFill(WHITE);
                    Pozycja pom = new Pozycja(roz_x * i,roz_y * j,roz_x,roz_y,koloo);
                    zajete_white.add(pom);
                }
                else{
                    licz+=1;
                    continue;
                }
                kola_white_lista.add(koloo);
                root.getChildren().add(koloo);
                licz+=1;
            }
        }

        /*
         */






        //czarne




        //kwadrat
        /*
        Rectangle kwadrat = new Rectangle(100,100,100,100);
        kwadrat.setOnMouseClicked(e -> System.out.println(kwadrat.getX()));
        root.getChildren().add(kwadrat);





        //kolo

        Circle kolo = new Circle(150, 150, 50, AQUA);
        kolo.setOnMouseClicked(e -> System.out.println("kolo"));
        root.getChildren().add(kolo);


*/



        //Thread t1 = new Thread(new Serwer(kwadraty_lista,kola_red_lista,kola_white_lista,root,zajete_red,zajete_white,roz_x,roz_y, wyslij_pionek, ostatni_wyslij, nowy_wyslij));
        //t1.start();


        Thread t1 = new Thread(new Klient(plansza));
        t1.start();


    }

}