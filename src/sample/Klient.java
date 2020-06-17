package sample;

import java.net.*;
import java.io.*;


public class Klient {
    public static void main(String[] args) throws IOException{

        Socket s  = new Socket("localhost",4999);
        PrintWriter pr = new PrintWriter(s.getOutputStream());
        //pr.println("hello");
        //pr.flush();
      //  pr.println("25 75 700 500 ");
     //   pr.flush();
        InputStreamReader in = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(in);
        String str;

        int x = 0;
        while (true) {
            //pr.println("25 75 700 500 ");
           // pr.flush();
            str = bf.readLine();
            if(!str.equals(null) && !str.equals("xxx")) {
                System.out.println(str);
            }
        }


    }
}
