import javax.crypto.spec.PSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GrupSohbeti extends  Sohbet{
    String grupIsmi;
    //default olarak grupRolleriMapi.put(yaraticiID,yaratici(Kullanici).rol);
    HashMap<Integer,String> grupRolleriMapi;
    HashMap<Integer,Kullanici> kullanicilarMapi;
    GrupSohbeti(Kullanici kullanici){
    super();
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
            int kId=Main.scan.nextInt();
            sohbetUyeleri.put(kId,kullanicilarMapi.get(kId));
            grupRolleriMapi.put(kId,"normal-uye");
            return;
        }
        izinYokUyarisi();

    }
    void uyeSil(){
        if(Arrays.asList(kullanici.izinler).contains("uye-sil")){
            System.out.println("yapabilir");
            int secim=-1;
            System.out.print("id gir:");
            secim=Main.scan.nextInt();
            if(sohbetUyeleri.containsKey(secim)){
                sohbetUyeleri.remove(secim);
            }
            else {
                System.out.println("bu kullanici uye degil");
                uyeSil();
            }
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
            int kId=-1;
            System.out.print("rol atancak k id:");
            kId=Main.scan.nextInt();
            if(sohbetUyeleri.containsKey(kId)){
                String atanacakRol="";
                System.out.print("atanacak rolu gi (1.yonetici,2.moderator,3.normal-uye");
                atanacakRol=Main.scan.next();
                grupRolleriMapi.put(kId,atanacakRol);
                System.out.println(grupRolleriMapi.get(kId));
                System.out.println(kId+"idli kisiye "+atanacakRol+"rolu atandi");
            }
            else {
                System.out.println("boyle bir uye yok");
                rolAta();
            }
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

    void sohbetOnizle(){
        System.out.println("wqeqwe");
        System.out.print("id:"+this.id+"[");
        System.out.print(this.grupIsmi);
        if(sonMesajIndex==0){
            System.out.println("mesaj yok");
            return;
        }
        System.out.println("] "+mesajlarDizisi[sonMesajIndex-1].gonderenK.isim+": "+(this.mesajlarDizisi[this.sonMesajIndex-1].mesaj));
        System.out.println(" ----okunmamis mesaj sayisi:" +
                ""+kullanici.bildirimlerMapi.get(this.id));

    }

    void sohbeteGir(Kullanici kullanici,HashMap<Integer,Kullanici> kullanicilarMapi){
        this.kullanicilarMapi=kullanicilarMapi;
        this.kullanici=kullanici;
        okunduOlarakIsaretle();

        kullanici.rolBelirle(grupRolleriMapi.get(kullanici.id));
        System.out.println(kullanici.rol+"sadljasdja");;



        int secim;
        while (true){
            mesajlariListele();
            System.out.println("mesaj gondermek icin 1");
            System.out.println("secenekleri gormek icin 2");
            System.out.println("uyeleri gormek icin 3");
            System.out.println("cikmak 0");
            secim=Main.scan.nextInt();
            if(secim==1) {
                String mesaj = "";
                int secim2 = 0;
                mesajGondermeISlemi();
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
