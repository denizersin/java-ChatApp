import java.util.Date;
public class Mesaj {
    static int X=0;
    Kullanici aliciK;
    Kullanici gonderenK;
    String mesaj;
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
}
