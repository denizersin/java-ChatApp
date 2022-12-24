import java.util.HashMap;

public class Kullanici extends Rol{
    int id;
    String isim;
    private String kullaniciAdi;

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    private String sifre;

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    HashMap<Integer,Integer> bildirimlerMapi;
    //bildirimlerMapi.put(id,bildirimSayisi)
    public Kullanici(int id, String isim, String kullaniciAdi, String sifre) {
        this.id = id;
        this.isim = isim;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        bildirimlerMapi=new HashMap<Integer,Integer>();
    }
}
