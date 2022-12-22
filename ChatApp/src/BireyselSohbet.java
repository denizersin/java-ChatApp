import java.util.Map;
import java.util.stream.Collectors;

public class BireyselSohbet extends Sohbet{
    Kullanici aliciK;
    Kullanici gonderenK;
    BireyselSohbet(Kullanici k1, Kullanici k2){
        super();
        this.gonderenK=k1;
        this.aliciK=k2;

    }

    @Override
    void mesajGonder(String mesaj){
        Mesaj msj=new Mesaj();
        msj.mesaj=mesaj;
        msj.aliciK=this.aliciK;
        msj.gonderenK=this.gonderenK;
        mesajlarDizisi[sonMesajIndex++]=msj;
        int guncelBildirimSayisi=0;
        guncelBildirimSayisi= aliciK.bildirimlerMapi.get(this.id)==null?0:aliciK.bildirimlerMapi.get(this.id);
        aliciK.bildirimlerMapi.put(this.id,guncelBildirimSayisi+1);
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
        //bildirimleri sifirla ve goruldu olarak isaretle
        int bildirimSayisi=kullanici.bildirimlerMapi.get(this.id)==null?0:kullanici.bildirimlerMapi.get(this.id);
        kullanici.bildirimlerMapi.put(this.id,0);

        for (int i = sonMesajIndex-1; i >=(sonMesajIndex-bildirimSayisi) ; i--) {
            mesajlarDizisi[i].gorulduMu=true;
        }
        gonderenK=kullanici;
        for (Map.Entry<Integer, Kullanici> set :
                sohbetUyeleri.entrySet()) {
                if(set.getValue().id!=kullanici.id){
                    aliciK=set.getValue();
                }
        }
        String mesaj="";
        int secim=0;

        while (true){
            this.mesajlariListele();
            System.out.println("mesaj gondermek icin 1");
            System.out.print("cikmak icin 0");
            secim=Main.scan.nextInt();
            if(secim==1){
                System.out.print("mesagi gir:");
                mesaj= Main.scan.next();
                mesajGonder(mesaj);
            }
            else if(secim==0){
                return;
            }
        else {
                System.out.println("hatali giris");
            }

        }
    }



}
