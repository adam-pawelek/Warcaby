package sample;

import javafx.scene.shape.Circle;

/**
 * Klasa dzieki ktorej moge trzymac pozycje pionka
 */

public class Pozycja {
    int x;
    int y;
    int roz_x;
    int roz_y;
    int do_x;
    int do_y;
    Circle pionek;

    /**
     * Konstruktor klasy Pozycja
     * @param x pozycja x
     * @param y pozycja y
     * @param roz_x rozmaia x kwadratu
     * @param roz_y rozmiar y kwadratu
     * @param pionek kolo pionek
     */
    public Pozycja(int x,int y, int roz_x,int roz_y,Circle pionek){
        this.x = x;
        this.y = y;
        this.roz_x = roz_x;
        this.roz_y = roz_y;
        this.do_x = x + roz_x;
        this.do_y = y + roz_y;
        this.pionek = pionek;
    }
}
