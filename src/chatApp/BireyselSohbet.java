package chatApp;

import java.util.Map;
import java.util.stream.Collectors;

public class BireyselSohbet extends Sohbet {
	BireyselSohbet(Kullanici k1){
        super();
        //sohbet yaratma metodu
        while (true) {
            ChatApp.kullanicilariListele();
            System.out.print("kullanici id giriniz:");
            String secim="";
            secim=Main.scan.next();
            if(!Main.beklenmeyenDurumSorgula(secim)){
                continue;
            }
            int ikinciKullaniciId = Integer.parseInt(secim);

            if (!ChatApp.db.kullaniciSorguluaById(ikinciKullaniciId)) {
                System.out.println("Kullanıcı Bulunamadı");
                continue;
            }
            this.id = Main.benzersizIdGetir2(ChatApp.db.bireyselSohbetHashMapi, ChatApp.db.grupSohbetiHashMapi);
            Kullanici k2 = ChatApp.db.kullanicilarMapi.get(ikinciKullaniciId);
            ChatApp.db.bireyselSohbetEkl(this);
            sohbetUyeleri.put(k1.id, k1);
            sohbetUyeleri.put(k2.id, k2);
            break;
        }
    }

    @Override
    void sohbetOnizle(Kullanici kullanici){
        this.kullanici=kullanici;
        System.out.print(" ≪sohbet ID:"+this.id+ "≫ Sohbet uyeleri:" +"[");
        sohbetUyeleri.forEach((key, value) -> System.out.print(value.isim+"&"));
        System.out.print("]");
        if(sonMesajIndex==0){
            System.out.println("\n :( Henüz bir mesaj atılmadı.\n • • • • • • • \n");
            return;
        }
        System.out.println("\n----Okunmamış mesaj sayısı:" +
                ""+kullanici.bildirimlerMapi.get(this.id));
        System.out.print("\nGelen son mesaj= "+mesajlarDizisi[sonMesajIndex-1].gonderenK.isim+": "+(this.mesajlarDizisi[this.sonMesajIndex-1].getIcerik())+"\n");

    }
    void sohbeteGir(Kullanici kullanici){
        this.kullanici=kullanici;
        this.okunduOlarakIsaretle();
        gondermeMenusu();
    }


}


