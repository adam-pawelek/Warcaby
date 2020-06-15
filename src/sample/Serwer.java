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




public class Serwer implements Runnable{
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


    public Serwer(LinkedList kwadraty_lista, LinkedList kola_red_lista, LinkedList kola_white_lista, Group root,LinkedList zajete_red, LinkedList zajete_white,
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
    @Override
    public void run(){
        System.out.println("asdfsadfasd");
        Circle koloo;
        koloo = kola_red_lista.get(0);
        System.out.println(koloo.getCenterX());
        System.out.println(koloo.getCenterY());
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

        PrintWriter pr = null;
        try {
            pr = new PrintWriter(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        pr.println("100 100 100 100");
        pr.flush();


        while (true) {
            InputStreamReader in = null;
            try {
                in = new InputStreamReader(s.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader bf = new BufferedReader(in);
            String str = null;
            try {
                str = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (str != null) {
                System.out.println(str);
                pr.println("100 100 100 100");
                pr.flush();

                Logika logika =  new Logika(kwadraty_lista,kola_red_lista,kola_white_lista,root,zajete_red,zajete_white,roz_x,roz_y);
                logika.ustaw_Na_Pole(czytaj(str,1),czytaj(str,2),700,400);


            }
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
}
