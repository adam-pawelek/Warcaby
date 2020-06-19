package sample;

import javafx.application.Platform;

import java.net.*;
import java.io.*;

import javafx.concurrent.Task;

/**
 * Klasa potrzeban do odbierania danych od serwera
 */
public class Klient implements Runnable{
    sample.Plansza plansza;
    String str;
    String poprzedni = new String();

    /**
     * Konstruktor klienta
     * @param plansza Plansza na ktorej gra gracz
     */
    public Klient(sample.Plansza plansza){
        this.plansza = plansza;
    }
    @Override
    public void run(){

        Socket s  = null;
        try {
            s = new Socket("localhost",4999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter pr = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //pr.println("hello");
        //pr.flush();
      //  pr.println("25 75 700 500 ");
     //   pr.flush();
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader bf = new BufferedReader(in);


        int x = 0;
        str = null;
        while (true) {
            //pr.println("25 75 700 500 ");
           // pr.flush();
            try {
                str = bf.readLine();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(!str.equals(null) && !str.equals("xxx") && !str.equals("RUSZYLEM  sie")) {
                String rodzaj_ruchu = new String();
                rodzaj_ruchu = "";
                rodzaj_ruchu += str.charAt(0);
                rodzaj_ruchu += str.charAt(1);
                rodzaj_ruchu += str.charAt(2);
                rodzaj_ruchu += str.charAt(3);
                if(rodzaj_ruchu.equals("Ruch")) {
                    System.out.println(str);
                    plansza.logika.ustaw_Na_Pole(czytaj(str, 1), czytaj(str, 2), czytaj(str, 3), czytaj(str, 4));
                }
                if(rodzaj_ruchu.equals("Bijj")){
                    System.out.println("WYSYla komunikat o biciu!!!");
                   // plansza.logika.ustaw_Na_Pole(czytaj(str, 1), czytaj(str, 2), czytaj(str, 3), czytaj(str, 4));

                   //plansza.logika.usunCzerwone(czytaj(str,1),czytaj(str,2));
                    poprzedni = "";
                    for( int i = 0; i < str.length(); i++){
                        poprzedni += str.charAt(i);
                    }
                    Platform.runLater(()-> {

                        final boolean b = plansza.logika.usunCzerwone(czytaj(poprzedni, 1), czytaj(poprzedni, 2));
                        final boolean c = plansza.logika.usunBiale(czytaj(poprzedni, 1), czytaj(poprzedni, 2));
                    });
                    //plansza.logika.ustaw_Na_Pole(czytaj(str, 1), czytaj(str, 2), 500, 500);
                }
            }
        }


    }

    /**
     * Funkcja potrzebna do odczytania otrzymanej wiadomosci
     * @param str otrzyamany string
     * @param ktory ktory gracz aktualnie gra
     * @return zwraca pozycje odczytana z stringa
     */

    public double czytaj(String str,int ktory){
        int licz = 4;
        String pom = new String(" ");
        String wynik = new String("");
        System.out.println("a" + pom + "b");
        for (int i = 0; i < ktory; i++) {
            while ( !((int) str.charAt(licz) == 32)) {
                if(i == ktory - 1) {
                    wynik += str.charAt(licz);
                }
                System.out.println(str.charAt(licz));
                licz++;


            }
            licz++;
        }
        int zamiana= Integer.parseInt(wynik);
        System.out.println(zamiana);
        return (double) zamiana;
    }

}
