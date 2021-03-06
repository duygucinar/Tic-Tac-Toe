package oyun;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Oyun {

    public static void main(String[] args) throws IOException {
        
        Scanner input = new Scanner(System.in);
        
        String kr,ad,secim,s2,s;
        oyuncu player = null;
        oyuncu computer = null;
        oyunTahtasi board;
        
        boolean k1,k2;
        int eleman=0;
        String str;
        String[] tahta = new String[7];
        char[][] yeni;
        char kr1;
        
        
        System.out.print("Kaydedilmiş oyuna devam etmek ister misiniz ? (E,e:Evet  H,h:Hayır)  :");
        s=input.next();
        
        if(s.equals("E") || s.equals("e")){
            FileInputStream fstream = new FileInputStream("dosya.txt");
            DataInputStream dStream = new DataInputStream(fstream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));
            
            ad=bReader.readLine();
            
            
            if(ad==null){
                System.out.println("Kaydedilmiş oyun yoktur!");
                System.out.println("Oyunu yeniden çalıştırınız ...");
                System.exit(0);
            }
            
            kr=bReader.readLine();
            
            if(kr.equals("x") || kr.equals("X")){
                player = new oyuncu(true,'X');
                computer = new oyuncu(false,'O');
            }
            else{
                player = new oyuncu(true,'O');
                computer = new oyuncu(false,'X');
            }
            
            player.isim=ad;
            
            System.out.println("Oyuncu adı   :"+player.isim);
            System.out.println("Kullanılacak harf  :"+player.karakter);
            
            while((str=bReader.readLine())!=null){
                tahta[eleman]=str;
                eleman++;
            }
            
            yeni = new char[eleman][eleman];
        
            for(int i=0;i<eleman;i++){
                for(int j=0;j<eleman;j++){
                    yeni[i][j]=tahta[i].charAt(j);
                }
            }
            
            board = new oyunTahtasi(yeni,eleman);
            
        }
        else{
            do{
               board = new oyunTahtasi();
               if(board.n!=3 && board.n!=5 && board.n!=7){
                   System.out.println("Tahta boyutunu 3,5 yada 7 olabilir !!");
               }
            }while(board.n!=3 && board.n!=5 && board.n!=7);
            
            
            System.out.print("Adınızı giriniz  :");
            ad=input.next();

            System.out.print("Harf seçmek ister misiniz ? (E,e:Evet ; H,h :Hayır)   :");
            secim=input.next();
        
            if(secim.equals("E") || secim.equals("e")){
                do{
                    System.out.print("X/O ?   :");
                    kr=input.next();
            
                    switch (kr) {
                        case "X":
                        case "x":
                            player = new oyuncu();
                            player.isim=ad;
                            computer = new oyuncu(false);
                            break;
                        case "O":
                        case "o":
                            player = new oyuncu(true,'O');
                            player.isim=ad;
                            computer = new oyuncu(false,'X');
                            break;
                        default:
                            System.out.println("Yanlış harf seçimi!!");
                            break;
                    }
                }while(kr.equals("X")==false && kr.equals("x")==false && kr.equals("O")==false && kr.equals("o")==false);
            }
            else{
                player= new oyuncu();
                player.isim=ad;
                computer = new oyuncu(false);
            }
        }
        
        do{
            board.yazdir();
            
            do{
                player.insanoyuncuHamlesiKontrol(board.n);
                if(player.getX()==9){
                    break;
                }
                k1=board.hamleyiYaz(player.x, player.y,player.karakter);
            }while(k1!=true);
            
            if(board.isFull()==true || board.kazanan(player.karakter)==true || player.getX()==9){
                break;
            }
            
            do{
                computer.pcHamle(board.n);
                k2=board.pchamleyiYaz(computer.x, computer.y, computer.karakter);
            }while(k2!=true);
     
        }while(board.kazanan(player.karakter)!=true && board.kazanan(computer.karakter)!=true);
       
        
        
        if(player.getX()==9){
            System.out.print("Oyunu kaydetmek ister misiniz ? (E,e:Evet  H,h:Hayır)  :");
            s2=input.next();
             
            if(s2.equals("E") || s2.equals("e")){
                File file = new File("dosya.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }

                FileWriter fileWriter = new FileWriter(file, false);
                BufferedWriter bWriter = new BufferedWriter(fileWriter);
                
                bWriter.write(player.isim);
                bWriter.newLine();
                bWriter.write(player.karakter);
                bWriter.newLine();
                
                for(int i=0;i<board.n;i++){
                    for(int j=0;j<board.n;j++){
                        bWriter.write(board.tahta[i][j]);
                    }
                    bWriter.newLine();
                }
                
                
                bWriter.close();
                System.out.println("Oyun kaydedildi!");
            }
            else
                System.out.println("Oyun kaydedilmedi!");
            
            System.exit(0);
            
        }
        
        board.yazdir();
        
        if(board.kazanan(player.karakter)==true){
            System.out.println("\nTebrikler! "+player.isim+" kazandı\n");
        }
        
        else if(board.kazanan(computer.karakter)==true){
            System.out.println("\nÜzgünüz! Kaybettiniz\n");
        }
        
        else if(board.kazanan(player.karakter)==false && board.kazanan(computer.karakter)==false && board.isFull()==true){
            System.out.println("\nBerabere!\n");
        }
        
    }
}
