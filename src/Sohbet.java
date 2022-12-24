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
            String mesaj=mesajlarDizisi[i].getIcerik();
            Boolean iletildiMi=mesajlarDizisi[i].iletildiMi;
            Boolean gorulduMu=mesajlarDizisi[i].gorulduMu;
            System.out.println(gonderenIsim+":"+mesaj+" i:"+iletildiMi+" g:"+gorulduMu);
        }
    }
    void mesajGonderme(String mesaj){
        Mesaj msj=new Mesaj();
        msj.setIcerik(mesaj);;
        msj.gonderenK=kullanici;
        mesajlarDizisi[sonMesajIndex++]=msj;
        int guncelBildirimSayisi=0;
        for (Map.Entry<Integer, Kullanici> set :
                sohbetUyeleri.entrySet()) {
            Kullanici k=set.getValue();
            int key=set.getKey();
            if(k.id!=kullanici.id){
                guncelBildirimSayisi= k.bildirimlerMapi.get(this.id)==null?0:k.bildirimlerMapi.get(this.id);
                k.bildirimlerMapi.put(this.id,guncelBildirimSayisi+1);
                System.out.println("bildirim set edildi"+k.id+""+kullanici.id+k.bildirimlerMapi.get(this.id));

            }
        }
    }
    void okunduOlarakIsaretle(){
        System.out.println("OKUNDUUUU!!!");
        int bildirimSayisi=kullanici.bildirimlerMapi.get(this.id)==null?0:kullanici.bildirimlerMapi.get(this.id);
        kullanici.bildirimlerMapi.put(this.id,0);

        for (int i = sonMesajIndex-1; i >=(sonMesajIndex-bildirimSayisi) ; i--) {
            mesajlarDizisi[i].gorulduMu=true;
        }
    }
    void gondermeMenusu(){
        String secim="";

        while (true) {
            for (int i = 0; i < 50; ++i) System.out.println();
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

     class Mesaj {
        static int X=0;
        Kullanici gonderenK;
        String icerik;
        int gonderimTarihi=0;
        boolean iletildiMi;
        boolean gorulduMu;
        String type;
        Mesaj(){
            gonderimTarihi=X;
            X++;
            iletildiMi=true;
            gorulduMu=false;
        }
        String getIcerik(){
            return this.icerik;
        }
         void setIcerik(String icerik){
            this.icerik=icerik;
        }
    }
    class Metin extends Mesaj{

        Metin(){
        super();
        }
    }
    class Image extends Mesaj{
        Image(){
           super();
        }
    }

    class Ses extends Mesaj{
        Ses(){
            super();
        }
    }

}
