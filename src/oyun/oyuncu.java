package oyun;
import java.util.Scanner;
import java.util.Random;

public class oyuncu {
    
    char karakter;
    String isim;
    boolean insan;
    int x;
    int y;
    
    public oyuncu(){
        karakter='X';
        insan=true; 
    }
    
    public oyuncu(boolean insanmi){
        
        insan=insanmi;
        if(insan==true){
            karakter='X';
        }
        else{
            karakter='O';
        }
    }
    
    public oyuncu(boolean insanmi,char kr){
        karakter=kr;
        insan=insanmi;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public char karakteriAl(){
        return karakter;
    }
    
    boolean oyuncuTurunuAl(){
        return insan;
    }
    
    void insanoyuncuHamlesiKontrol(int n){
        Scanner input = new Scanner(System.in);
        System.out.print("Lütfen hamle yapınız!  (Çıkış için 9 9'a basınız)  :");
        x=input.nextInt();
        y=input.nextInt();
        
    }
    
    void pcHamle(int n){
        Random r = new Random();
        
        x=r.nextInt(n);
        y=r.nextInt(n);
    }
}
