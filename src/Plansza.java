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


public class Plansza {
    LinkedList kwadraty_lista ;
    LinkedList kola_red_lista;
    LinkedList kola_white_lista;
    LinkedList zajete_red;
    LinkedList zajete_white;
    Group root;
    Logika logika;
    int roz_x;
    int roz_y;
    public Plansza(LinkedList kwadraty_lista,LinkedList kola_red_lista,LinkedList kola_white_lista,Group root,LinkedList zajete_red,LinkedList zajete_white,
                   int roz_x, int roz_y){
        this.kwadraty_lista = kwadraty_lista;
        this.kola_red_lista = kola_red_lista;
        this.kola_white_lista = kola_white_lista;
        this.zajete_red = zajete_red;
        this.zajete_white = zajete_white;
        this.root = root;
        this.roz_x = roz_x;
        this.roz_y = roz_y;
        logika = new Logika(kwadraty_lista,kola_red_lista,kola_white_lista,root,zajete_red,zajete_white,roz_x,roz_y);
    }

    public void rysujKwadraty(int roz_x, int roz_y){
        int licz = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Rectangle kwadratt = new Rectangle(roz_x * i,roz_y * j,roz_x,roz_y);
                kwadratt.setOnMouseClicked(e -> logika.nacisnietyKwadrat(kwadratt));
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
    }

    public void  rysujCzerwone(int roz_x, int roz_y){
        int licz = 1;
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 3; j++){
                Circle koloo = new Circle(roz_x * i + roz_x / 2,roz_y * j+ roz_y / 2,roz_x / 2);
                if (licz % 2 == 0) {
                    koloo.setFill(RED);
                    koloo.setOnMouseClicked(e -> logika.ktoryPioenkCzerwone(koloo));
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
    }

    public  void  rysujBiale(int roz_x, int roz_y){
        int licz = 0;
        for(int i = 0; i < 8; i++){
            for(int j = 5; j < 6; j++){
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
    }


}
