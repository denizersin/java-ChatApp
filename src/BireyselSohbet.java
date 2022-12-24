import java.util.Map;
import java.util.stream.Collectors;

public class BireyselSohbet extends Sohbet{
    BireyselSohbet(Kullanici k1){
        super();
        while (true) {
            ChatApp.kullanicilariListele();
            System.out.print("olusturulacak sohbet id'si gir:");
            int ikinciKullaniciId = -1;
            ikinciKullaniciId = Main.scan.nextInt();
            if (!ChatApp.kullanicilarMapi.containsKey(ikinciKullaniciId)) {
                System.out.println("boyle bir id yok");
                continue;
            }
            this.id = Main.benzersizIdGetir2(ChatApp.bireyselSohbetHashMapi, ChatApp.grupSohbetiHashMapi);
            Kullanici k2 = ChatApp.kullanicilarMapi.get(ikinciKullaniciId);
            ChatApp.bireyselSohbetHashMapi.put(this.id, this);
            sohbetUyeleri.put(k1.id, k1);
            sohbetUyeleri.put(k2.id, k2);
            break;
        }
    }

    void sohbetOnizle(Kullanici kullanici){
        this.kullanici=kullanici;
        System.out.print("id:"+this.id+"[");
        sohbetUyeleri.forEach((key, value) -> System.out.print(value.isim+","));
        System.out.print("]");
        if(sonMesajIndex==0){
            System.out.println("henuz bir mesaj atilmadi");
            return;
        }
        System.out.print(this.mesajlarDizisi[sonMesajIndex-1].gonderenK.isim+": "+ this.mesajlarDizisi[this.sonMesajIndex-1].getIcerik());
        System.out.println(" ----okunmamis mesaj sayisi:" +
                ""+kullanici.bildirimlerMapi.get(this.id));
    }

    void sohbeteGir(Kullanici kullanici){
        this.kullanici=kullanici;
        okunduOlarakIsaretle();
        String mesaj="";
        int secim=0;
        //sonsuz dongu..
        gondermeMenusu();
    }

}
