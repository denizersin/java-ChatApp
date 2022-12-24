class Mesaj {
    static int X=0;
    Kullanici gonderenK;
    private String icerik;
    int gonderimTarihi=0;
    boolean iletildiMi;
    boolean gorulduMu;
    String tip;
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
