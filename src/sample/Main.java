package sample;


import java.io.IOException;
import java.util.Formatter;
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
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import  javafx.scene.control.Button;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Rectangle;
import sample.Plansza;

import static javafx.application.Application.launch;
import static javafx.scene.paint.Color.*;

import java.net.*;
import java.io.*;


import javafx.scene.text.Font.*;
public class Main extends Application {
    Stage window;
    Scene scena_menu, scena_plansza, scena_zasady,scena_jednoosobowa;

    Button przycisk_stream;
    Button przycisk_jednaos;
    Button przycisk_zasady;
    Button przycisk_menu_plansza;
    Button przycisk_menu_zasady;
    Button przycisk_menu_jednoos;
    Button przycisk_pobierz_historie_je;
    Button przycisk_pobierz_historie_st;

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
    Text historia_gry = new Text();


    //Do gry jednoosobowej

    LinkedList kwadraty_listav2 = new LinkedList<Rectangle>();
    LinkedList kola_red_listav2 = new LinkedList<Circle>();
    LinkedList kola_white_listav2 = new LinkedList<Circle>();

    LinkedList zajete_redv2 = new LinkedList<Pozycja>();

    LinkedList zajete_whitev2 = new LinkedList<Pozycja>();

    static  int roz_xv2 = 50;
    static  int roz_yv2 = 50;
    boolean wyslij_pionekv2;
    Circle ostatni_wyslijv2;
    Circle nowy_wyslijv2;
    Text historia_gryv2 = new Text();






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
        przycisk_stream.setText("Stream");
        przycisk_jednaos = new Button();
        przycisk_jednaos.setText("komputer");

        przycisk_menu_plansza = new Button();
        przycisk_menu_plansza.setText("Menu");
        przycisk_menu_plansza.setOnAction(e -> window.setScene(scena_menu));
        przycisk_menu_plansza.setLayoutX(600);
        przycisk_menu_plansza.setLayoutY(400);


        // menu
        VBox layout  = new VBox(20);
        layout.getChildren().addAll(przycisk_zasady, przycisk_stream, przycisk_jednaos);
        scena_menu = new Scene(layout,1000,500);
        primaryStage.setScene(scena_menu);
        primaryStage.show();
        przycisk_zasady.setOnAction(e -> window.setScene(scena_zasady));
        przycisk_stream.setOnAction(e -> window.setScene(scena_plansza));
        przycisk_jednaos.setOnAction(e -> window.setScene(scena_jednoosobowa));



        //zasady
        Group layout2 = new Group();
        przycisk_menu_zasady = new Button();
        layout2.getChildren().addAll(przycisk_menu_zasady);

        przycisk_menu_zasady.setOnAction(e -> window.setScene(scena_menu));
        przycisk_menu_zasady.setLayoutX(50);
        przycisk_menu_zasady.setLayoutY(400);
        przycisk_menu_zasady.setText("menu");
        Text text_zasady = new Text();
        text_zasady.setText("Zasady zastosowane przezemnie sa polaczeniem zasad Polskich i angielskich \n " +
                "Pionek moze poruszac sie do przodu i bic \n" +
                "Pionek jesli zaczal bicie musi je skonczyc (wykonac wszystkie mozliwe bicia) \n" +
                "Bicie nie jest konieczne \n" +
                "Bicie jest mozliwe zarowno do przodu jak i do tylu \n" +
                "W momecie kiedy dojdziemy na drugi koniec planszy dostaniemy krola \n"+
                 "W porownaniu z pionkiem krol moze jeszcze poruszac sie w tyl \n\n\n" +
                "Gra posiada wiele odmian, spośród których uważane za dyscyplinę \n" +
                "sportową są jedynie warcaby polskie (stupolowe) nazywane też międzynarodowymi. \n" +
                "W większości krajów gra odbywa się jednak przeważnie na warcabnicy posiadającej 64 pola \n" +
                "(często zastępowanej szachownicą), z wykorzystaniem 24 pionków (po 12 dla każdego z graczy).");

        text_zasady.setX(200);
        text_zasady.setY(100);
        text_zasady.setScaleX(1.5);
        text_zasady.setScaleY(1.5);

        layout2.getChildren().addAll(text_zasady);


        scena_zasady = new Scene(layout2,1000,500);

        //plansza

        przycisk_pobierz_historie_st = new Button();
        przycisk_pobierz_historie_st.setText("Pobierz Historie");
        przycisk_pobierz_historie_st.setOnAction(e -> {
            Formatter plik;
            try {
                plik = new Formatter("historia_stream.txt");
                plik.format("%s",historia_gry.getText());
                plik.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

        });







        Group root = new Group();
        scena_plansza = new Scene(root,1000,500);
        root.getChildren().add(przycisk_menu_plansza);

        historia_gry.setText("Historia GRY \n");
        historia_gry.setLayoutX(600);
        historia_gry.setLayoutY(100);
       // root.getChildren().add(historia_gry);


        // tworzy plansze
        //Tworze plansze
        Plansza plansza = new Plansza(kwadraty_lista,kola_red_lista,kola_white_lista,root,zajete_red,zajete_white,roz_x,roz_y,wyslij_pionek, ostatni_wyslij, nowy_wyslij,historia_gry);
        plansza.rysujKwadraty(roz_x,roz_y);

        plansza.rysujCzerwone(roz_x,roz_y);
        plansza.rysujBiale(roz_x,roz_y);

        ScrollPane skrolowanie = new ScrollPane();
        skrolowanie.setContent(historia_gry);
        skrolowanie.setMinSize(170, 150);
        skrolowanie.setMaxSize(200,170);
        skrolowanie.setVisible(true);
        skrolowanie.setLayoutX(600);
        skrolowanie.setLayoutY(100);
        root.getChildren().add(skrolowanie);
        root.getChildren().add(przycisk_pobierz_historie_st);







        //czarne


        //gra jednooosobowa

        przycisk_pobierz_historie_je = new Button();
        przycisk_pobierz_historie_je.setText("Pobierz Historie");
        przycisk_pobierz_historie_je.setOnAction(e -> {
            Formatter plik;
            try {
                plik = new Formatter("historia.txt");
                plik.format("%s",historia_gryv2.getText());
                plik.close();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

        });


        przycisk_menu_jednoos = new Button();
        przycisk_menu_jednoos.setText("Menu");
        przycisk_menu_jednoos.setOnAction(e -> window.setScene(scena_menu));
        przycisk_menu_jednoos.setLayoutX(400);
        przycisk_menu_jednoos.setLayoutY(200);

        Group layout_jednoosobowa = new Group();
        scena_jednoosobowa = new Scene(layout_jednoosobowa,1000,500);
        layout_jednoosobowa.getChildren().addAll(przycisk_menu_jednoos);

        Plansza plansza_jednoosobowa = new Plansza(kwadraty_listav2,kola_red_listav2,kola_white_listav2,layout_jednoosobowa,zajete_redv2,zajete_whitev2,roz_xv2,roz_yv2,wyslij_pionekv2, ostatni_wyslijv2, nowy_wyslijv2,historia_gryv2 );
        plansza_jednoosobowa.rysujKwadraty(roz_x,roz_y);
        plansza_jednoosobowa.rysujCzerwone(roz_x,roz_y);
        plansza_jednoosobowa.rysujBiale(roz_x,roz_y);

        historia_gryv2.setText("Historia GRY \n");
        //historia_gryv2.setLayoutX(600);
        //historia_gryv2.setLayoutY(100);
        ScrollPane skrolowaniev2 = new ScrollPane();
        skrolowaniev2.setContent(historia_gryv2);
        skrolowaniev2.setMinSize(170, 150);
        skrolowaniev2.setMaxSize(200,170);
        skrolowaniev2.setVisible(true);
        skrolowaniev2.setLayoutX(600);
        skrolowaniev2.setLayoutY(100);
        layout_jednoosobowa.getChildren().add(skrolowaniev2);
       // layout_jednoosobowa.getChildren().add(historia_gryv2);
        layout_jednoosobowa.getChildren().add(przycisk_pobierz_historie_je);














        //Thread t1 = new Thread(new Serwer(kwadraty_lista,kola_red_lista,kola_white_lista,root,zajete_red,zajete_white,roz_x,roz_y, wyslij_pionek, ostatni_wyslij, nowy_wyslij));
        //t1.start();


        Thread t1 = new Thread(new Serwer(plansza));
        t1.start();


    }

}