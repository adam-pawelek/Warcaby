package sample;

public class Pozycja {
    int x;
    int y;
    int roz_x;
    int roz_y;
    int do_x;
    int do_y;
    public Pozycja(int x,int y, int roz_x,int roz_y){
        this.x = x;
        this.y = y;
        this.roz_x = roz_x;
        this.roz_y = roz_y;
        this.do_x = x + roz_x;
        this.do_y = y + roz_y;
    }
}
