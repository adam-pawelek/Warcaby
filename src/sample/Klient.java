package sample;

import java.net.*;
import java.io.*;


public class Klient {
    public static void main(String[] args) throws IOException{

        Socket s  = new Socket("localhost",4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        //pr.println("hello");
        //pr.flush();
        pr.println("25 75 120 120 ");
        pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str  = bf.readLine();
        System.out.println(str);

    }
}
