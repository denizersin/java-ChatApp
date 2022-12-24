import java.util.Map;
import java.util.stream.Collectors;

public class BireyselSohbet extends Sohbet{
    BireyselSohbet(Kullanici k1,Kullanici k2){
        super();
        sohbetUyeleri.put(k1.id,k1);
        sohbetUyeleri.put(k2.id,k2);
    }

    void sohbetOnizle(Kullanici kullanici){
        System.out.print("id:"+this.id+"[");
        sohbetUyeleri.forEach((key, value) -> System.out.print(value.isim+","));
        System.out.print("]");
        if(sonMesajIndex==0){
            System.out.println("henuz bir mesaj atilmadi");
            return;
        }
        System.out.print(this.mesajlarDizisi[sonMesajIndex-1].gonderenK.isim+": "+ this.mesajlarDizisi[this.sonMesajIndex-1].mesaj);
        System.out.println(" ----okunmamis mesaj sayisi:" +
                ""+kullanici.bildirimlerMapi.get(this.id));
    }

    void sohbeteGir(Kullanici kullanici){
        this.kullanici=kullanici;
        okunduOlarakIsaretle();
        String mesaj="";
        int secim=0;
        //sonsuz dongu..
        mesajGondermeISlemi();
    }



}
