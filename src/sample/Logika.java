package sample;


import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;
import java.math.*;

public class Logika {
    LinkedList <Rectangle> kwadraty_lista;// = new LinkedList<Rectangle>();
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
    Circle ostatni_czerwony_bil;
    Circle ostatni_bialy_bil;
    boolean bylruch = false;
    boolean bialyBil = false;
    boolean czerwonyBil = false;

    public boolean wyslij_pionek;
    public Circle ostatni_wyslij;
    public  Circle nowy_wyslij;
    public  double ostatni_wyslij_x;
    public  double ostatni_wyslij_y;
    public  double nowy_wyslij_x;
    public double nowy_wyslij_y;
    boolean ktos_bil;


    public Logika(LinkedList kwadraty_lista, LinkedList kola_red_lista, LinkedList kola_white_lista, Group root,LinkedList zajete_red, LinkedList zajete_white,
    int roz_x, int roz_y, boolean wyslij_pionek,Circle ostatni_wyslij,Circle nowy_wyslij){
        this.kwadraty_lista = kwadraty_lista;
        this.kola_red_lista = kola_red_lista;
        this.kola_white_lista = kola_white_lista;
        this.zajete_red = zajete_red;
        this.zajete_white = zajete_white;
        this.root = root;
        this.roz_x = roz_x;
        this.roz_y = roz_y;
        this.wyslij_pionek = wyslij_pionek;
        this.ostatni_wyslij = ostatni_wyslij;
        this.nowy_wyslij = nowy_wyslij;
    }

    public void ktoryPioenkCzerwone(Circle koloo){

            ostatni_x_red = (int) koloo.getCenterX();
            ostatni_y_red = (int) koloo.getCenterY();
            ostatni_czerwony = koloo;
         //   System.out.println("kolo czerwone");


    }
    public  void  ktoryPionekBiale(Circle koloo){

            ostatni_bialy = koloo;

    }


    public  void  nacisnietyKwadrat(Rectangle kwadrat){
      //  System.out.println("Kwadrat");
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
    public boolean to_pole_zawiera(double poz_x, double poz_y){
        poz_x = poz_x - roz_x /2;
        poz_y = poz_y - roz_y /2;
        for (Circle koloo : kola_red_lista){
            if (Math.abs(poz_y - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(poz_x - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                return true;
            }

        }
        for (Circle koloo : kola_white_lista){
            if (Math.abs(poz_y - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(poz_x - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
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

    public boolean to_pole_miesci(double poz_x, double poz_y){
            if (poz_x > 0 && poz_y > 0 && poz_x < roz_x * 8 && poz_y < roz_y * 8 ){
                return true;
            }
            return false;
    }


    public void  ruchBiale(Rectangle kwadrat){
        bylruch = false;
        if (bialyBil == true){
            ostatni_bialy = ostatni_bialy_bil;
        }
        ostatni_wyslij = ostatni_bialy;
        ostatni_wyslij_x = ostatni_bialy.getCenterX();
        ostatni_wyslij_y = ostatni_bialy.getCenterY();
        if(!to_pole_zawiera(kwadrat)){
         //   System.out.println("Ruch biale");
            bylruch =  ruchBialePrawo(kwadrat) || bylruch;
            bylruch = ruchBialeLewo(kwadrat) || bylruch;
            bialyBil = bijBialePrawoGora(kwadrat) || bialyBil;
            bialyBil = bijBialeLewoGora(kwadrat) || bialyBil;
            bialyBil = bijBialeLewoDol(kwadrat) || bialyBil;
            bialyBil = bijBialePrawoDol(kwadrat) || bialyBil;
            bylruch = bylruch || bialyBil;
          //  System.out.println("RUCHYYYYYYYY");
            System.out.println(ostatni_bialy.getCenterX());
            if(bialyBil == true){
                ostatni_bialy_bil = ostatni_bialy;
            }

            if (bialyBil && ( moze_bijBialePrawoGora() || moze_bijBialeLewoGora() || moze_bijBialeLewoDol() || moze_bijBialePrawoDol() )){
                ktory_gracz = ktory_gracz;
                nowy_wyslij = ostatni_bialy;
                nowy_wyslij_x = ostatni_bialy.getCenterX();
                nowy_wyslij_y = ostatni_bialy.getCenterY();
                ktos_bil = true;
                wyslij_do_watku();
            }
            else if (bylruch){
                ktory_gracz += 1;
                ktory_gracz= ktory_gracz % 2;
                bylruch = false;
                bialyBil = false;
                nowy_wyslij = ostatni_bialy;
                nowy_wyslij_x = ostatni_bialy.getCenterX();
                nowy_wyslij_y = ostatni_bialy.getCenterY();
                wyslij_do_watku();
            }
        }


    }
    public boolean ruchBialePrawo(Rectangle kwadrat){
       // System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() + roz_x,ostatni_bialy.getCenterY() - roz_y,kwadrat)){
            ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;
    }

    public boolean ruchBialeLewo(Rectangle kwadrat){
       // System.out.println("asdfasdf");
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
    public boolean moze_bijBialePodstawa(int xx, int yy){
        if (!to_pole_zawiera(ostatni_bialy.getCenterX() + roz_x * 2 * xx,ostatni_bialy.getCenterY() + roz_y * 2 * yy)) {
            if (to_pole_miesci(ostatni_bialy.getCenterX() + roz_x * 2 * xx, ostatni_bialy.getCenterY() + roz_y * 2 * yy)) {
                if (to_pole_zawiera_czerwone(ostatni_bialy.getCenterX() + roz_x * xx, ostatni_bialy.getCenterY() + roz_y * yy)) {
              //      System.out.println("moze_bij");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moze_bijBialePrawoGora(){
        return moze_bijBialePodstawa(1,-1);
    }
    public boolean moze_bijBialePrawoDol(){
        return moze_bijBialePodstawa(1,1);
    }
    public boolean moze_bijBialeLewoGora(){
        return moze_bijBialePodstawa(-1,-1);
    }
    public boolean moze_bijBialeLewoDol(){
        return moze_bijBialePodstawa(-1,1);
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
        if (czerwonyBil == true){
            ostatni_czerwony = ostatni_czerwony_bil;
        }
        ostatni_wyslij = ostatni_czerwony;
        ostatni_wyslij_x = ostatni_czerwony.getCenterX();
        ostatni_wyslij_y = ostatni_czerwony.getCenterY();
        if(!to_pole_zawiera(kwadrat)){
          //  System.out.println("Ruch czerwoen");
            bylruch = bylruch || ruchCzerwonePrawo(kwadrat);
            bylruch = bylruch || ruchCzerwoneLewo(kwadrat);
            czerwonyBil =  bijCzerwoneLewoDol(kwadrat) || czerwonyBil;
            czerwonyBil =  bijCzerwonePrawoDol(kwadrat) || czerwonyBil;
            czerwonyBil =  bijCzerwoneLewoGora(kwadrat) || czerwonyBil;
            czerwonyBil =  bijCzerwonePrawoGora(kwadrat) || czerwonyBil;
            bylruch = bylruch || czerwonyBil;
           // System.out.println("CZEEWRWWERWEERW");
            System.out.println(czerwonyBil);



            if(czerwonyBil == true){
                ostatni_czerwony_bil = ostatni_czerwony;
            }

            if (czerwonyBil && ( moze_bijCzerwoneLewoGora() || moze_bijCzerwoneLewoGora() || moze_bijCzerwonePrawoDol() || moze_bijCzerwoneLewoDol())){
                ktory_gracz = ktory_gracz;
                nowy_wyslij = ostatni_czerwony;
                nowy_wyslij_x = ostatni_czerwony.getCenterX();
                nowy_wyslij_y = ostatni_czerwony.getCenterY();
                wyslij_do_watku();
            }
            else if (bylruch){
                ktory_gracz += 1;
                ktory_gracz= ktory_gracz % 2;
                bylruch = false;
                czerwonyBil = false;
                nowy_wyslij = ostatni_czerwony;
                nowy_wyslij_x = ostatni_czerwony.getCenterX();
                nowy_wyslij_y = ostatni_czerwony.getCenterY();
                wyslij_do_watku();
            }



        }


    }






    public boolean moze_bijCzerwonePodstawa(int xx, int yy){
        if (!to_pole_zawiera(ostatni_czerwony.getCenterX() + roz_x * 2 * xx,ostatni_czerwony.getCenterY() + roz_y * 2 * yy)) {
            if (to_pole_miesci(ostatni_czerwony.getCenterX() + roz_x * 2 * xx, ostatni_czerwony.getCenterY() + roz_y * 2 * yy)) {
                if (to_pole_zawiera_biale(ostatni_czerwony.getCenterX() + roz_x * xx, ostatni_czerwony.getCenterY() + roz_y * yy)) {
                  //  System.out.println("moze_bij CZerowne");
                    return true;
                }
            }
        }
        return false;
    }

    public boolean moze_bijCzerwonePrawoGora(){
        return moze_bijCzerwonePodstawa(1,-1);
    }
    public boolean moze_bijCzerwonePrawoDol(){
        return moze_bijCzerwonePodstawa(1,1);
    }
    public boolean moze_bijCzerwoneLewoGora(){
        return moze_bijCzerwonePodstawa(-1,-1);
    }
    public boolean moze_bijCzerwoneLewoDol(){
        return moze_bijCzerwonePodstawa(-1,1);
    }






    public  boolean ruchCzerwonePrawo(Rectangle kwadrat){
      //  System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() + roz_x,ostatni_czerwony.getCenterY() + roz_y,kwadrat)){
            ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;

    }
    public boolean  ruchCzerwoneLewo(Rectangle kwadrat){
      //  System.out.println("asdfasdf");
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
             //   System.out.println("Bij P");
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
             //   System.out.println("Bij P");
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
             //   System.out.println("CZemu Nie dziala");
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
            //    System.out.println("CZemu Nie dziala");
                kola_red_lista.remove(pom_usun);
                root.getChildren().remove(pom_usun);
                return  true;
            }

        }
        return  false;
    }

    Circle  znajdz_Pionek(double poz_x, double poz_y){
        poz_x = poz_x - roz_x /2;
        poz_y = poz_y - roz_y /2;
        for (Circle koloo : kola_red_lista){
            if (Math.abs(poz_y - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(poz_x - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                return koloo;
            }

        }
        for (Circle koloo : kola_white_lista){
            if (Math.abs(poz_y - (koloo.getCenterY() - roz_y / 2)) < roz_y / 2 && Math.abs(poz_x - (koloo.getCenterX() - roz_x /2)) < roz_x /2 ){
                return koloo;
            }

        }
        return  null;

    }
    public void ustaw_Na_Pole(double wczesniej_poz_x,double wczesniej_poz_y, double teraz_poz_x, double teraz_poz_y){
        Circle koloo = znajdz_Pionek(wczesniej_poz_x,wczesniej_poz_y);
      //  System.out.println("Ustaw na pole");
        koloo.setCenterX(teraz_poz_x);
        koloo.setCenterY(teraz_poz_y);
    }

    public  void wyslij_do_watku(){
     //   System.out.println("Wysylanie do watku");
        wyslij_pionek = true;
        //System.out.println(wyslij_pionek);

    }

    public  boolean stan_wyslij_pionek(){
        return wyslij_pionek;
    }







}
