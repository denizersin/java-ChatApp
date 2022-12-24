import java.util.Date;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Map;

public class Sohbet {
    Kullanici kullanici;
    int id;
    HashMap<Integer,Kullanici> sohbetUyeleri;
    int sonMesajIndex=0;
    Mesaj[] mesajlarDizisi;
    Sohbet(){
        mesajlarDizisi=new Mesaj[9999];
        sohbetUyeleri=new HashMap<Integer,Kullanici>();
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
    void mesajGonderme(String mesaj){
        Mesaj msj=new Mesaj();
        msj.mesaj=mesaj;
        msj.gonderenK=kullanici;
        mesajlarDizisi[sonMesajIndex++]=msj;
        int guncelBildirimSayisi=0;
        for (Map.Entry<Integer, Kullanici> set :
                sohbetUyeleri.entrySet()) {
            Kullanici k=set.getValue();
            int key=set.getKey();
            if(k.id!=kullanici.id){
                System.out.println("bildirim set edildi"+k.id+""+kullanici.id);
                guncelBildirimSayisi= k.bildirimlerMapi.get(this.id)==null?0:k.bildirimlerMapi.get(this.id);
                k.bildirimlerMapi.put(this.id,guncelBildirimSayisi+1);
            }
        }
    }
    void okunduOlarakIsaretle(){
        int bildirimSayisi=kullanici.bildirimlerMapi.get(this.id)==null?0:kullanici.bildirimlerMapi.get(this.id);
        kullanici.bildirimlerMapi.put(this.id,0);

        for (int i = sonMesajIndex-1; i >=(sonMesajIndex-bildirimSayisi) ; i--) {
            mesajlarDizisi[i].gorulduMu=true;
        }
    }
    void mesajGondermeISlemi(){
        String secim="";

        while (true) {
            this.mesajlariListele();
            System.out.println("mesaj gondermek icin 1");
            System.out.println("cikmak icin 0");
            secim = Main.scan.next();
            if (secim.equals("1")) {
                String mesaj="";
                System.out.println("mesaji giriniz");
                mesaj=Main.scan.next();
                mesajGonderme(mesaj);
            } else if (secim.equals("0")) {
                return;
            } else {
                System.out.println("hatali giris");
            }
        }
    }



}
