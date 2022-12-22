import java.util.Date;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Map;

public class Sohbet {
    int id;
    HashMap<Integer,Kullanici> sohbetUyeleri;
    int sonMesajIndex=0;
    Mesaj[] mesajlarDizisi;
    Sohbet(){
        mesajlarDizisi=new Mesaj[9999];
        sohbetUyeleri=new HashMap<Integer,Kullanici>();

    }


    //mesajlariListele()

    void mesajSil(){

    }
    void mesajGonder(String mesaj){
    }
    void mesajlariListele(){
        for (int i = 0; i<sonMesajIndex ; i++) {
            String gonderenIsim=mesajlarDizisi[i].gonderenK.isim;
            String mesaj=mesajlarDizisi[i].mesaj;
            Boolean iletildiMi=mesajlarDizisi[i].iletildiMi;
            Boolean gorulduMu=mesajlarDizisi[i].gorulduMu;
            System.out.println(gonderenIsim+":"+mesaj+" i:"+iletildiMi+" g:"+gorulduMu);
        }
    }


    void sohbetOnizle(){

    }

}
