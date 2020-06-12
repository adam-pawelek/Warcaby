package sample;


import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.math.*;

public class Logika {
    LinkedList kwadraty_lista = new LinkedList<Rectangle>();
    LinkedList <Circle> kola_red_lista;
    LinkedList <Circle> kola_white_lista;
    LinkedList zajete_red = new LinkedList<Pozycja>();
    LinkedList zajete_white = new LinkedList<Pozycja>();
    Group root;
    int ostatni_x_red;
    int ostatni_y_red;
    int roz_x;
    int roz_y;
    int ktory_gracz = 0; // kto wykonuje ruch  biale czy czerwone
    Circle ostatni_czerwony;
    Circle ostatni_bialy;
    boolean bylruch = false;

    public Logika(LinkedList kwadraty_lista, LinkedList kola_red_lista, LinkedList kola_white_lista, Group root,LinkedList zajete_red, LinkedList zajete_white,
    int roz_x, int roz_y){
        this.kwadraty_lista = kwadraty_lista;
        this.kola_red_lista = kola_red_lista;
        this.kola_white_lista = kola_white_lista;
        this.zajete_red = zajete_red;
        this.zajete_white = zajete_white;
        this.root = root;
        this.roz_x = roz_x;
        this.roz_y = roz_y;
    }

    public void ktoryPioenkCzerwone(Circle koloo){
        if (ktory_gracz == 0) {
            ostatni_x_red = (int) koloo.getCenterX();
            ostatni_y_red = (int) koloo.getCenterY();
            ostatni_czerwony = koloo;
            System.out.println("kolo czerwone");

        }
    }
    public  void  ktoryPionekBiale(Circle koloo){
        if (ktory_gracz == 1){
            ostatni_bialy = koloo;
        }
    }


    public  void  nacisnietyKwadrat(Rectangle kwadrat){
        System.out.println("Kwadrat");
        if (ktory_gracz == 0){
            ruchCzerwone(kwadrat);
        }
        else {
            ruchBiale(kwadrat);
        }

    }

    public boolean to_pole_zawiera(Rectangle kwadrat){
        for (Circle koloo : kola_red_lista){
            if (Math.abs(kwadrat.getY() - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(kwadrat.getX() - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                return true;
            }

        }
        for (Circle koloo : kola_white_lista){
            if (Math.abs(kwadrat.getY() - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(kwadrat.getX() - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                return true;
            }

        }
        return false;
    }
    public boolean to_pole_zawiera_biale(double poz_x, double poz_y){
        poz_x = poz_x - roz_x /2;
        poz_y = poz_y - roz_y /2 ;
        for (Circle koloo : kola_white_lista){
            if (Math.abs(poz_y - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(poz_x - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                return true;
            }

        }
        return false;
    }
    public boolean to_pole_zawiera_czerwone(double poz_x, double poz_y){
        poz_x = poz_x - roz_x /2;
        poz_y = poz_y - roz_y /2 ;
        for (Circle koloo : kola_red_lista){
            if (Math.abs(poz_y - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(poz_x - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                return true;
            }

        }
        return false;
    }


    public boolean to_pole_dozwolone(double poz_x, double poz_y,Rectangle kwadrat){
        poz_x = poz_x - roz_x / 2;
        poz_y = poz_y - roz_y / 2;
        if (Math.abs( poz_x - kwadrat.getX()) < roz_x / 2 && Math.abs(poz_y - kwadrat.getY()) < roz_y / 2){
            return true;
        }
        return false;
    }


    public void  ruchBiale(Rectangle kwadrat){
        bylruch = false;
        if(!to_pole_zawiera(kwadrat)){
            System.out.println("Ruch biale");
            bylruch = bylruch || ruchBialePrawo(kwadrat);
            bylruch = bylruch || ruchBialeLewo(kwadrat);
            bylruch = bylruch || bijBialePrawoGora(kwadrat);
            bylruch = bylruch || bijBialeLewoGora(kwadrat);
            bylruch = bylruch || bijBialeLewoDol(kwadrat);
            bylruch = bylruch || bijBialePrawoDol(kwadrat);
        }
        if (bylruch){
            ktory_gracz += 1;
            ktory_gracz= ktory_gracz % 2;
        }
    }
    public boolean ruchBialePrawo(Rectangle kwadrat){
        System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() + roz_x,ostatni_bialy.getCenterY() - roz_y,kwadrat)){
            ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;
    }

    public boolean ruchBialeLewo(Rectangle kwadrat){
        System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() - roz_x,ostatni_bialy.getCenterY() - roz_y,kwadrat)){
            ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;
    }


    public boolean bijBialePrawoGora(Rectangle kwadrat){
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() + roz_x * 2,ostatni_bialy.getCenterY() - roz_y * 2,kwadrat)) {
            if (to_pole_zawiera_czerwone(ostatni_bialy.getCenterX() + roz_x, ostatni_bialy.getCenterY() - roz_y)) {
                usunCzerwone(ostatni_bialy.getCenterX() + roz_x, ostatni_bialy.getCenterY() - roz_y);
                ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
                ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
                return  true;

            }
        }
        return false;
    }

    public boolean bijBialeLewoGora(Rectangle kwadrat){
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() - roz_x * 2,ostatni_bialy.getCenterY() - roz_y * 2,kwadrat)) {
            if (to_pole_zawiera_czerwone(ostatni_bialy.getCenterX() - roz_x, ostatni_bialy.getCenterY() - roz_y)) {
                usunCzerwone(ostatni_bialy.getCenterX() - roz_x, ostatni_bialy.getCenterY() - roz_y);
                ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
                ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
                return  true;

            }
        }
        return false;
    }
    public boolean bijBialeLewoDol(Rectangle kwadrat){
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() - roz_x * 2,ostatni_bialy.getCenterY() + roz_y * 2,kwadrat)) {
            if (to_pole_zawiera_czerwone(ostatni_bialy.getCenterX() - roz_x, ostatni_bialy.getCenterY() + roz_y)) {
                usunCzerwone(ostatni_bialy.getCenterX() - roz_x, ostatni_bialy.getCenterY() + roz_y);
                ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
                ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
                return  true;

            }
        }
        return false;
    }
    public boolean bijBialePrawoDol(Rectangle kwadrat){
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() + roz_x * 2,ostatni_bialy.getCenterY() + roz_y * 2,kwadrat)) {
            if (to_pole_zawiera_czerwone(ostatni_bialy.getCenterX() + roz_x, ostatni_bialy.getCenterY() + roz_y)) {
                usunCzerwone(ostatni_bialy.getCenterX() + roz_x, ostatni_bialy.getCenterY() + roz_y);
                ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
                ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
                return  true;

            }
        }
        return false;
    }





    public  void ruchCzerwone(Rectangle kwadrat){
        bylruch = false;
        if(!to_pole_zawiera(kwadrat)){
            System.out.println("Ruch czerwoen");
            bylruch = bylruch || ruchCzerwonePrawo(kwadrat);
            bylruch = bylruch || ruchCzerwoneLewo(kwadrat);
            bylruch = bylruch || bijCzerwoneLewoDol(kwadrat);
            bylruch = bylruch || bijCzerwonePrawoDol(kwadrat);
            bylruch = bylruch || bijCzerwoneLewoGora(kwadrat);
            bylruch = bylruch || bijCzerwonePrawoGora(kwadrat);
        }
        if (bylruch){
            ktory_gracz += 1;
            ktory_gracz= ktory_gracz % 2;
        }

    }

    public  boolean ruchCzerwonePrawo(Rectangle kwadrat){
        System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() + roz_x,ostatni_czerwony.getCenterY() + roz_y,kwadrat)){
            ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;

    }
    public boolean  ruchCzerwoneLewo(Rectangle kwadrat){
        System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() - roz_x,ostatni_czerwony.getCenterY() + roz_y,kwadrat)){
            ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;

    }

    public boolean bijCzerwoneLewoDol(Rectangle kwadrat){
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() - roz_x * 2,ostatni_czerwony.getCenterY() + roz_y * 2,kwadrat)) {
            if (to_pole_zawiera_biale(ostatni_czerwony.getCenterX() - roz_x, ostatni_czerwony.getCenterY() + roz_y)) {
                usunBiale(ostatni_czerwony.getCenterX() - roz_x, ostatni_czerwony.getCenterY() + roz_y);
                ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
                ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
                return  true;

            }
        }
        return false;
    }

    public boolean bijCzerwoneLewoGora(Rectangle kwadrat){
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() - roz_x * 2,ostatni_czerwony.getCenterY() - roz_y * 2,kwadrat)) {
            if (to_pole_zawiera_biale(ostatni_czerwony.getCenterX() - roz_x, ostatni_czerwony.getCenterY() - roz_y)) {
                usunBiale(ostatni_czerwony.getCenterX() - roz_x, ostatni_czerwony.getCenterY() - roz_y);
                ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
                ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
                return  true;

            }
        }
        return false;
    }



    public  boolean bijCzerwonePrawoDol(Rectangle kwadrat){
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() + roz_x * 2,ostatni_czerwony.getCenterY() + roz_y * 2,kwadrat)) {
            if (to_pole_zawiera_biale(ostatni_czerwony.getCenterX() + roz_x,ostatni_czerwony.getCenterY() + roz_y)) {
                System.out.println("Bij P");
                usunBiale(ostatni_czerwony.getCenterX() + roz_x, ostatni_czerwony.getCenterY() + roz_y);
                ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
                ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
                return true;
            }
        }
        return false;
    }

    public  boolean bijCzerwonePrawoGora(Rectangle kwadrat){
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() + roz_x * 2,ostatni_czerwony.getCenterY() - roz_y * 2,kwadrat)) {
            if (to_pole_zawiera_biale(ostatni_czerwony.getCenterX() + roz_x,ostatni_czerwony.getCenterY() - roz_y)) {
                System.out.println("Bij P");
                usunBiale(ostatni_czerwony.getCenterX() + roz_x, ostatni_czerwony.getCenterY() - roz_y);
                ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
                ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
                return true;
            }
        }
        return false;
    }



    public boolean  usunBiale(double poz_x,double poz_y){
        poz_x = poz_x - roz_x /2;
        poz_y = poz_y - roz_y /2 ;
        Circle pom_usun = new Circle(1,1,1);
        for (Circle koloo : kola_white_lista){
            if (Math.abs(poz_y - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(poz_x - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                pom_usun = koloo;
                System.out.println("CZemu Nie dziala");
                kola_white_lista.remove(pom_usun);
                root.getChildren().remove(pom_usun);
                return  true;
            }

        }
        return  false;
    }

    public boolean  usunCzerwone(double poz_x,double poz_y){
        poz_x = poz_x - roz_x /2;
        poz_y = poz_y - roz_y /2 ;
        Circle pom_usun = new Circle(1,1,1);
        for (Circle koloo : kola_red_lista){
            if (Math.abs(poz_y - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(poz_x - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                pom_usun = koloo;
                System.out.println("CZemu Nie dziala");
                kola_red_lista.remove(pom_usun);
                root.getChildren().remove(pom_usun);
                return  true;
            }

        }
        return  false;
    }





}
