package sample;


import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.LinkedList;
import java.math.*;

import static javafx.scene.paint.Color.*;

/**
 * Klasa obslugujaca logike gry
 */

public class Logika {
    LinkedList <Rectangle> kwadraty_lista;// = new LinkedList<Rectangle>();
    LinkedList <Circle> kola_red_lista;
    LinkedList <Circle> kola_white_lista;
    LinkedList zajete_red = new LinkedList<Pozycja>();
    LinkedList zajete_white = new LinkedList<Pozycja>();

    LinkedList krole_czerowne = new LinkedList<Pozycja>();
    LinkedList krole_biale = new LinkedList<Pozycja>();
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
    public boolean ktos_bil;
    public int poz_usun_x;
    public int poz_usun_y;
    Text historia_gry;
    Text napisz_ktory_gracz;

    /**
     *  Konstruktor klasy
     * @param kwadraty_lista - plansza do gry
     * @param kola_red_lista - pionki czerwone
     * @param kola_white_lista pionki biale
     * @param root layout
     * @param zajete_red zajete pionki czerwone
     * @param zajete_white zajete pionki biale
     * @param roz_x rozmiar x planszy
     * @param roz_y rozmiar y planszy
     * @param wyslij_pionek wysyla pionek
     * @param ostatni_wyslij wysyla poprzednia pozycje pionka
     * @param nowy_wyslij  wysyla terazniejsza pozycje pionka
     * @param historia_gry historia gry
     */


    public Logika(LinkedList kwadraty_lista, LinkedList kola_red_lista, LinkedList kola_white_lista, Group root,LinkedList zajete_red, LinkedList zajete_white,
    int roz_x, int roz_y, boolean wyslij_pionek,Circle ostatni_wyslij,Circle nowy_wyslij,Text historia_gry ){
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
        this.historia_gry = historia_gry;
        napisz_ktory_gracz = new Text();
        napisz_ktory_gracz.setText("Czerwony zaczyna");
        napisz_ktory_gracz.setX(450);
        napisz_ktory_gracz.setY(20);
        root.getChildren().add(napisz_ktory_gracz);
    }

    public void ktoryPioenkCzerwone(Circle koloo){

            ostatni_x_red = (int) koloo.getCenterX();
            ostatni_y_red = (int) koloo.getCenterY();
            if (ostatni_czerwony != null) {
                 ostatni_czerwony.setFill(RED);
                 if (czyJestKrolem_czerwone(ostatni_czerwony)){
                     ostatni_czerwony.setFill(DARKGOLDENROD);
                 }
            }
            ostatni_czerwony = koloo;
            if (ktory_gracz == 0) {
                ostatni_czerwony.setFill(DARKMAGENTA);

            }
         //   System.out.println("kolo czerwone");


    }
    public  void  ktoryPionekBiale(Circle koloo){
        if (ostatni_bialy != null) {
            ostatni_bialy.setFill(WHITE);
            if(czyJestKrolem_biale(ostatni_bialy)){
                ostatni_bialy.setFill(DARKBLUE);
            }
        }
            ostatni_bialy = koloo;
            if(ktory_gracz == 1) {
                ostatni_bialy.setFill(GREEN);
            }


    }

    /**
     * Jest wykonywany gdy nacisniesz kwadrat na planszy
     * @param kwadrat nacisniety kwadrat na planszy
     */
    public  void  nacisnietyKwadrat(Rectangle kwadrat){
      //  System.out.println("Kwadrat");
        if (ktory_gracz == 0){

            ruchCzerwone(kwadrat);
        }
        else {
            ruchBiale(kwadrat);
        }

    }

    /**
     * Sprawdza czy na tym polu znajduje sie jakis pionek
     * @param kwadrat nacisniete pole myszka
     * @return zwraca prawda jesl sie znacjduje
     */
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

    /**
     * Sprawdza czy to pole zawiera jakis pionek
     * @param poz_x pozycja x rozpatrywana
     * @param poz_y pozycja y rozpatrywana
     * @return zwraca prawda jesl sie zawiera
     */

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

    /**
     * Sprawdza czy to pole zawiera biale pionki
     * @param poz_x - pozycja x sprawdzana
     * @param poz_y pozycja y sprawdzana
     * @return zwraca prawda jesli zawiera
     */
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

    /**
     * Sprawdza czy to pole zawiera czerwone pionki
     * @param poz_x rozpatrywana pozycja x
     * @param poz_y rozpatrywana pozycja y
     * @return zwraca prawda jesli zawiera
     */
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

    /**
     * Sprawdza czy ruch na to pole jest dozwolone
     * @param poz_x pozycja x pola
     * @param poz_y pozycja y pola
     * @param kwadrat nacisniety kwadrat
     * @return zwraca prawda jesl dozwolone
     */

    public boolean to_pole_dozwolone(double poz_x, double poz_y,Rectangle kwadrat){
        poz_x = poz_x - roz_x / 2;
        poz_y = poz_y - roz_y / 2;
        if (Math.abs( poz_x - kwadrat.getX()) < roz_x / 2 && Math.abs(poz_y - kwadrat.getY()) < roz_y / 2){
            return true;
        }
        return false;
    }

    /**
     * Sprawdza czy proba wykonania ruchu miesci sie w planszy
     * @param poz_x rozpatrywana x pozycja
     * @param poz_y rozpatywanan y pozycja
     * @return zwraca prawda jesli sie miesci
     */
    public boolean to_pole_miesci(double poz_x, double poz_y){
            if (poz_x > 0 && poz_y > 0 && poz_x < roz_x * 8 && poz_y < roz_y * 8 ){
                return true;
            }
            return false;
    }

    /**
     * Metoda obslugujaca ruch Bialych Pionkow
     * @param kwadrat - przyjmmuje pole na ktore chce sie poruszyc gracz
     */

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
            if(czyJestKrolem_biale(ostatni_bialy)){
                bylruch = ruchBialeLewooDol(kwadrat) || bylruch;
                bylruch = ruchBialePrawoDol(kwadrat) || bylruch;
            }
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


                uaktualnij_historie();
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
                napisz_ktory_gracz.setText("Ruch Czerwony");
                ostatni_bialy.setFill(WHITE);
                if(czyJestKrolem_biale(ostatni_bialy)){
                    ostatni_bialy.setFill(DARKBLUE);
                }
                uaktualnij_historie();
                wyslij_do_watku();
            }
        }
        uaktualnij_krole_biale();


    }

    /**
     * Wykonuje ruch Bialymi do Przodu w Prawo
     * @param kwadrat pole na ktore gracz chce sie ruszyc
     * @return zwraca prawda jesli ruch sie powiodl
     */

    public boolean ruchBialePrawo(Rectangle kwadrat){
       // System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() + roz_x,ostatni_bialy.getCenterY() - roz_y,kwadrat)){
            ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;
    }

    /**
     * Wykonuje ruch Bialymi do przdu w lewo
     * @param kwadrat miejsce na ktore chce sie przemiescic
     * @return zwraca prawda jesli ruch sie udal
     */
    public boolean ruchBialeLewo(Rectangle kwadrat){
       // System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() - roz_x,ostatni_bialy.getCenterY() - roz_y,kwadrat)){
            ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;
    }

    // To dla krola

    /**
     * Metoda dostepna tylko dla krrola
     * @param kwadrat miejsce na ktore ma sie ruszyc
     * @return zwraca czy ruch sie udal
     */
    public boolean ruchBialePrawoDol(Rectangle kwadrat){
        // System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() + roz_x,ostatni_bialy.getCenterY() + roz_y,kwadrat)){
            ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;
    }
    // To dla krola

    /**
     * Metoda dostepna tylko dla krola
     * @param kwadrat miejsce na ktore ma sie ruszyc
     * @return zwraca true jesli ruch sie udal
     */
    public boolean ruchBialeLewooDol(Rectangle kwadrat){
        // System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_bialy.getCenterX() - roz_x,ostatni_bialy.getCenterY() + roz_y,kwadrat)){
            ostatni_bialy.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_bialy.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;
    }

    /**
     * Bicie bialych
     * @param kwadrat miejsce na ktore ma sie udac pionek
     * @return true jesli bicei sie udalo
     */
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

    /**
     * funkcja sprawdza czy mozlie jest bicie
     * @param xx  -1 lewo 1 prawo
     * @param yy -1 gora 1 dol
     * @return zwraca czy moze bic
     */
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

    /**
     * Bicie bialych
     * @param kwadrat miejsce na ktore ma sie udac pionek
     * @return true jesli bicei sie udalo
     */
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
    /**
     * Bicie bialych
     * @param kwadrat miejsce na ktore ma sie udac pionek
     * @return true jesli bicei sie udalo
     */
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
    /**
     * Bicie bialych
     * @param kwadrat miejsce na ktore ma sie udac pionek
     * @return true jesli bicei sie udalo
     */
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


    /**
     * Metoda obslugujaca ruch czerwonych
     * @param kwadrat pole na ktore ma sie ruszyc czerwony
     */

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
            bylruch = ruchCzerwonePrawo(kwadrat) || bylruch;
            bylruch = ruchCzerwoneLewo(kwadrat) || bylruch;
            if(czyJestKrolem_czerwone(ostatni_czerwony)){
                bylruch = ruchCzerwonePrawoDol(kwadrat) || bylruch;
                bylruch = ruchCzerwoneLewoDol(kwadrat) || bylruch;
            }
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
                uaktualnij_historie();

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


                napisz_ktory_gracz.setText("Ruch Bialy");
                ostatni_czerwony.setFill(RED);
                if (czyJestKrolem_czerwone(ostatni_czerwony)){
                    ostatni_czerwony.setFill(DARKGOLDENROD);
                }


                uaktualnij_historie();
                wyslij_do_watku();
            }



        }
        uaktualnij_krole_czerwone();

    }


    /**
     * Sprawdza czy Czerwwone moga bic
     * @param xx -1 lewo 1 prawo
     * @param yy -1 dol  1 gora
     * @return
     */

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


    /**
     * Wykonuje ruch w Prawo
     * @param kwadrat pole ktore nacisnal gracz
     * @return true - jesli ruch sie udal
     */


    public  boolean ruchCzerwonePrawo(Rectangle kwadrat){
      //  System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() + roz_x,ostatni_czerwony.getCenterY() + roz_y,kwadrat)){
            ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;

    }
    /**
     * Wykonuje ruch w Lewo
     * @param kwadrat pole ktore nacisnal gracz
     * @return true - jesli ruch sie udal
     */
    public boolean  ruchCzerwoneLewo(Rectangle kwadrat){
      //  System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() - roz_x,ostatni_czerwony.getCenterY() + roz_y,kwadrat)){
            ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;

    }

    //to jest dla krola
    /**
     * Wykonuje ruch w Prawo Dol (tylko dla krola)
     * @param kwadrat pole ktore nacisnal gracz
     * @return true - jesli ruch sie udal
     */

    public  boolean ruchCzerwonePrawoDol(Rectangle kwadrat){
        //  System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() + roz_x,ostatni_czerwony.getCenterY() - roz_y,kwadrat)){
            ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;

    }
    //to dla krola

    /**
     * Wykonuje ruch w Lewo Dol (tylko dla krola)
     * @param kwadrat pole ktore nacisnal gracz
     * @return true - jesli ruch sie udal
     */
    public  boolean ruchCzerwoneLewoDol(Rectangle kwadrat){
        //  System.out.println("asdfasdf");
        if (to_pole_dozwolone(ostatni_czerwony.getCenterX() - roz_x,ostatni_czerwony.getCenterY() - roz_y,kwadrat)){
            ostatni_czerwony.setCenterX(kwadrat.getX() + roz_x / 2);
            ostatni_czerwony.setCenterY(kwadrat.getY() + roz_y / 2);
            return  true;
        }
        return false;

    }


    /**
     * Wykonuje bicie
     * @param kwadrat -pole na ktore ma wykonac bicie
     * @return zwraca tru jesli bicie sie udalo
     */

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

    /**
     * Wykonuje bicie
     * @param kwadrat -pole na ktore ma wykonac bicie
     * @return zwraca tru jesli bicie sie udalo
     */

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

    /**
     * Wykonuje bicie
     * @param kwadrat -pole na ktore ma wykonac bicie
     * @return zwraca tru jesli bicie sie udalo
     */

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

    /**
     * Wykonuje bicie
     * @param kwadrat -pole na ktore ma wykonac bicie
     * @return zwraca tru jesli bicie sie udalo
     */

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


    /**
     * Funkcja ktora usuwa pionek Bialy
     * @param poz_x pozycja x pionka bialego
     * @param poz_y pozycja y pionka bailego
     * @return zwraca prawda jesli bicie sie udalo
     */
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

                //do Serwer-klient
                ktos_bil = true;
                poz_usun_x = (int) poz_x + roz_x / 2;
                poz_usun_y = (int) poz_y + roz_y / 2;


                return  true;
            }

        }
        return  false;
    }

    /**
     * Metoda usuwa czerwony pionek
     * @param poz_x pozycja x usuwanego pionka
     * @param poz_y pozycja y usuwanego pionka
     * @return zwraca tru jesli usuniecie sie udalo
     */

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

                //do Serwer-klient
                ktos_bil = true;
                poz_usun_x = (int) poz_x + roz_x / 2;
                poz_usun_y = (int) poz_y + roz_y / 2;


                return  true;
            }

        }
        return  false;
    }

    /**
     * Znajduje pionek
     * @param poz_x pozycja x pionka
     * @param poz_y pozycja y pionka
     * @return zwraca pionek
     */

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

    /**
     * Funkcja potrzebna do obslugi komunikacji Serwe Klient
     * ustawia na wyznaczone pole pionek
     * @param wczesniej_poz_x wczesniejsza pozycja  x pionka
     * @param wczesniej_poz_y wczesniejsz pozycja y pionka
     * @param teraz_poz_x terazniejsza pozycja x pionka
     * @param teraz_poz_y terazniejsz apozycja y pionka
     */
    public void ustaw_Na_Pole(double wczesniej_poz_x,double wczesniej_poz_y, double teraz_poz_x, double teraz_poz_y){
        Circle koloo = znajdz_Pionek(wczesniej_poz_x,wczesniej_poz_y);
      //  System.out.println("Ustaw na pole");

        koloo.setCenterX(teraz_poz_x);
        koloo.setCenterY(teraz_poz_y);
        uaktualnij_krole_biale();
        uaktualnij_krole_czerwone();

    }

    /**
     * Wysyla do watku inforamcje o wykonanym ruchu
     */
    public  void wyslij_do_watku(){
     //   System.out.println("Wysylanie do watku");
        wyslij_pionek = true;
        //System.out.println(wyslij_pionek);

    }

    /**
     * Wysyla pionek
     * @return zwraca pionek
     */
    public  boolean stan_wyslij_pionek(){
        return wyslij_pionek;
    }

    /**
     * Metoda sprawdza czy pojawil sie nowy bialy krol
     * @return zwraca prawda jesli sie pojawil
     */
    public boolean uaktualnij_krole_biale(){
        for(Circle koloo: kola_white_lista){
            if (koloo.getCenterY() < roz_y && !krole_biale.contains(koloo)){
                krole_biale.add(koloo);
                koloo.setFill(DARKBLUE);
                return true;
            }
        }


        return false;
    }


    /**
     * Metoda sprawdza czy pojawil sie nowy czerwony krol
     * @return zwraca prawda jesli sie pojawil
     */

    public boolean uaktualnij_krole_czerwone(){
        for(Circle koloo: kola_red_lista){
            if (koloo.getCenterY() > 7* roz_y && !krole_czerowne.contains(koloo)){
                krole_czerowne.add(koloo);
                koloo.setFill(DARKGOLDENROD);
                return true;
            }
        }
        return false;
    }

    /**
     * Sprawdza czy rozpatrywany pioenek bialy jest krolem
     * @param koloo rozpatrywany pionek
     * @return zwraca prawda jesli jest krolem
     */
    public boolean czyJestKrolem_biale(Circle koloo){

            if (krole_biale.contains(koloo)){
                return true;
            }

        return false;
    }

    /**
     * Sprawdza czy rozpatrywany pioenek czerwony jest krolem
     * @param koloo rozpatrywany pionek
     * @return zwraca prawda jesli jest krolem
     */
    public boolean czyJestKrolem_czerwone(Circle koloo){

            if (krole_czerowne.contains(koloo)){
                return true;
            }

        return false;
    }

    /**
     * Metoda uaktualniajaca historie
     */

    public void uaktualnij_historie(){
        String pomw = new String();

        if (ktory_gracz == 1){
            pomw = "Czerwone ";
        }
        else {
            pomw = "Biale         ";
        }

        int pomzam = (int) ostatni_wyslij_x;
        pomzam = pomzam / roz_x;
        pomw += String.valueOf(zworc_litere_szachwonicy(pomzam))  ;
        historia_gry.setText(historia_gry.getText()+ pomw);

        pomw = "";
        pomzam = (int) ostatni_wyslij_y;
        pomzam = pomzam / roz_y + 1;
        pomw += String.valueOf(pomzam) + "  na   ";
        historia_gry.setText(historia_gry.getText()+ pomw);

        pomw = "";
        pomzam = (int) nowy_wyslij.getCenterX();
        pomzam = pomzam / roz_x;
        pomw += String.valueOf(zworc_litere_szachwonicy(pomzam)) ;
        historia_gry.setText(historia_gry.getText()+ pomw);

        pomw = "";
        pomzam = (int) nowy_wyslij.getCenterY();
        pomzam = pomzam / roz_y + 1;
        pomw += String.valueOf(pomzam) + "\n";
        historia_gry.setText(historia_gry.getText()+ pomw);


    }

    /**
     * Zwraca listere szachownicy
     * @param liczba pozycja pionka x
     * @return zwraca litere
     */
    public String zworc_litere_szachwonicy(int liczba){
        String wyn = new String();
        wyn = "";
        char pomzam = (char) (97 + liczba);
        wyn += pomzam;
        return  wyn;

    }







}
