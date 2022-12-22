import java.util.HashMap;

public class Kullanici extends Rol{
    int id;
    String isim;
    String kullaniciAdi;
    String sifre;
    HashMap<Integer,Integer> bildirimlerMapi;
    //bildirimlerMapi.put(id,bildirimSayisi)
    HashMap<Integer,Boolean> sessizeAlinanChatlarMapi;
    public Kullanici(int id, String isim, String kullaniciAdi, String sifre) {
        this.id = id;
        this.isim = isim;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        bildirimlerMapi=new HashMap<Integer,Integer>();
    }

    void bildirimleriGoster(){

    }
}
