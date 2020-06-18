package sample;

import java.net.*;
import java.io.*;


public class Klient implements Runnable{
    sample.Plansza plansza;
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
        String str;

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
                System.out.println(str);
                plansza.logika.ustaw_Na_Pole(czytaj(str,1),czytaj(str,2),czytaj(str,3),czytaj(str,4));
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
