package sample;

import java.net.*;
import java.io.*;


import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;


import java.util.LinkedList;
import java.math.*;


import javafx.scene.Group;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;




public class Serwer extends Thread{
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
   // Logika logika;
    PrintWriter pr;
   // boolean wyslij_pionek;
   // Circle ostatni_wyslij;
   // Circle nowy_wyslij;
    sample.Plansza plansza;



    public Serwer(LinkedList kwadraty_lista, LinkedList kola_red_lista, LinkedList kola_white_lista, Group root,LinkedList zajete_red, LinkedList zajete_white,
                  int roz_x, int roz_y,boolean wyslij_pionek,Circle ostatni_wyslij,Circle nowy_wyslij){
        this.kwadraty_lista = kwadraty_lista;
        this.kola_red_lista = kola_red_lista;
        this.kola_white_lista = kola_white_lista;
        this.zajete_red = zajete_red;
        this.zajete_white = zajete_white;
        this.root = root;
        this.roz_x = roz_x;
        this.roz_y = roz_y;
    }
    public Serwer (sample.Plansza plansza){
        this.plansza = plansza;
    }
    @Override
    public void run(){
       // logika =  new Logika(kwadraty_lista,kola_red_lista,kola_white_lista,root,zajete_red,zajete_white,roz_x,roz_y, wyslij_pionek, ostatni_wyslij, nowy_wyslij);
        System.out.println("asdfsadfasd");
       // Circle koloo;
        //koloo = kola_red_lista.get(0);
        //System.out.println(koloo.getCenterX());
        //System.out.println(koloo.getCenterY());
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(4999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket s = null;
        try {
            s = ss.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("client connected");
        Circle pomoc;

        pr = null;
        try {
            pr = new PrintWriter(s.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
      //  pr.println("100 100 100 100");
      //  pr.flush();

        InputStreamReader in = null;
        try {
            in = new InputStreamReader(s.getInputStream());
        } catch (IOException e) {
            //e.printStackTrace();
        }
        BufferedReader bf = new BufferedReader(in);
        String str = null;



        while (true) {


                /*
                try {
                    str = bf.readLine();
                    System.out.println(str);
                } catch (IOException e) {
                    // e.printStackTrace();
                }
                */



            if (str != null) {
                System.out.println(str);
              //  pr.println("100 100 100 100");
                //pr.flush();


                //logika.ustaw_Na_Pole(czytaj(str,1),czytaj(str,2),czytaj(str,3),czytaj(str,4));


            }
            wyslij_drugi_gracz();
           // System.out.println("asdfsdaf");
        }
    }

    public double czytaj(String str,int ktory){
        int licz = 0;
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
    public void wyslij_drugi_gracz(){

     ///  System.out.println(plansza.logika.wyslij_pionek);
     //  if(ostatni_wyslij != null){
     //      System.out.println(ostatni_wyslij.getCenterX());
     //  }
        pr.println("xxx");
        pr.flush();

        if(plansza.logika.wyslij_pionek){
            pr.println("RUSZYLEM  sie");
            pr.flush();

            System.out.println("WYSYLAAMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
           String dane_slowo = stworzString();
            pr.println(dane_slowo);
            pr.flush();
            if(plansza.logika.ktos_bil){
                String pozycja_usuniecia = bijString();
                pr.println(pozycja_usuniecia);
                pr.flush();
                plansza.logika.ktos_bil = false;
            }
            plansza.logika.wyslij_pionek = false;
        }
    }

    public  String stworzString() {
        String wynik = "Ruch";

        int zamint;
        System.out.println(plansza.logika.ostatni_wyslij_x);
        zamint = (int) plansza.logika.ostatni_wyslij_x;  // ostatni x
        wynik += String.valueOf(zamint);
        wynik += " ";
        zamint = (int) plansza.logika.ostatni_wyslij_y; // ostatni y
        wynik += String.valueOf(zamint);
        wynik += " ";
        zamint = (int) plansza.logika.nowy_wyslij_x;    //nowy x
        wynik += String.valueOf(zamint);
        wynik += " ";
        zamint = (int) plansza.logika.nowy_wyslij_y;   // nowy y
        wynik += String.valueOf(zamint);
        wynik += " ";
        return wynik;
    }
    public String bijString(){
        String wynik = "Bijj";
        int zamint;
        System.out.println(plansza.logika.poz_usun_x);
        zamint = (int) plansza.logika.poz_usun_x;  // ostatni x
        wynik += String.valueOf(zamint);
        wynik += " ";
        zamint = (int) plansza.logika.poz_usun_y; // ostatni y
        wynik += String.valueOf(zamint);
        wynik += " ";

        return wynik;
    }



}
