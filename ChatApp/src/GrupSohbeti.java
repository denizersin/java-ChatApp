import javax.crypto.spec.PSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GrupSohbeti extends  Sohbet{
    String grupIsmi;
    Kullanici kullanici;
    //default olarak grupRolleriMapi.put(yaraticiID,yaratici(Kullanici).rol);
    HashMap<Integer,String> grupRolleriMapi;
    HashMap<Integer,Kullanici> kullanicilarMapi;
    GrupSohbeti(Kullanici kullanici){
    super();

//    this.yonetici=kullanici;
//    this.yonetici.rolBelirle("yonetici");
//    this.grupRolleriMapi.put(yonetici.id,yonetici.rol);

    grupRolleriMapi=new HashMap<Integer,String>();
    }


    void secenekleriListele(){

        int secim=-1;
        while (true){
            System.out.println("cikmak icin 0");
            System.out.println("uye eklemek icin 1");
            System.out.println("uye silmek icin 2");
            System.out.println("grup ismi degistir 3");
            System.out.println("bir uyuyeye rol ata 4");
            secim=Main.scan.nextInt();

            if (secim==1){
                uyeEkle();
            }
            else if(secim==2){
                uyeSil();
            }
            else if(secim==3){
                grupIsmiDegistir();
            }
            else if(secim==4){
                rolAta();
            }
            else if(secim==0){
                return;
            }
            else {
                secenekleriListele();
            }
        }


    }
    void uyeEkle(){
        if(Arrays.asList(kullanici.izinler).contains("uye-ekle")){
            System.out.println("yapabilir");
            System.out.println("eklenecek uye id");
            int id=Main.scan.nextInt();
            sohbetUyeleri.put(id,kullanicilarMapi.get(id));
            grupRolleriMapi.put(id,"normal-uye");
            return;
        }
        izinYokUyarisi();

    }
    void uyeSil(){
        if(Arrays.asList(kullanici.izinler).contains("uye-sil")){
            System.out.println("yapabilir");
            return;
        }
        izinYokUyarisi();
    }
    void uyeleriGoruntule(){
        int id;
        Kullanici k;
        for (Map.Entry<Integer, Kullanici> set :
                sohbetUyeleri.entrySet()) {
            id=set.getKey();
            k=set.getValue();
            System.out.println(k.isim+"rol: "+grupRolleriMapi.get(id));
        }
    }
    void izinYokUyarisi(){
        System.err.println("bu izne sahip degilsin");
    }
    void rolAta(){
        if(Arrays.asList(kullanici.izinler).contains("rol-ata")){
            System.out.println("yapabilir");
            return;
        }
        izinYokUyarisi();
    }
    void grupIsmiDegistir(){
        if(Arrays.asList(kullanici.izinler).contains("grup-ismi-degistir")){
            System.out.println("yapabilir");
            return;
        }
        izinYokUyarisi();
    }

    @Override
    void sohbetOnizle(){
        System.out.println("wqeqwe");
        System.out.print("id:"+this.id+"[");
        System.out.print(this.grupIsmi);
        if(sonMesajIndex==0){
            System.out.println("mesaj yok");
            return;
        }
        System.out.print("] "+mesajlarDizisi[sonMesajIndex-1].gonderenK.isim+": "+(this.mesajlarDizisi[this.sonMesajIndex-1]));


    }
    void mesajGonder(String mesaj){
        Mesaj msj=new Mesaj();
        msj.mesaj=mesaj;
    }
    void sohbeteGir(Kullanici kullanici,HashMap<Integer,Kullanici> kullanicilarMapi){
        this.kullanicilarMapi=kullanicilarMapi;

        this.kullanici=kullanici;
        System.out.println("");
        kullanici.rolBelirle(grupRolleriMapi.get(kullanici.id));

        int secim;
        while (true){
            mesajlariListele();
            System.out.println("mesaj gondermek icin 1");
            System.out.println("secenekleri gormek icin 2");
            System.out.println("secenekleri gormek icin 3");
            System.out.println("cikmak 0");
            secim=Main.scan.nextInt();
            if(secim==1){
                //mesaj gonderme
            }
            else if(secim==2){
                secenekleriListele();
            }
            else if(secim==3){
                uyeleriGoruntule();
            }
            else  if(secim==0){
                return;
            }
        }
    }
}
