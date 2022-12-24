public class Rol extends Izinler{
   private String rol;

    public String getRol() {
        return rol;
    }

    //burasi setRol() olarak dusunulebilir
    void rolBelirle(String rol){
        this.rol=rol;
        //if yonetici ise
        if(rol.equals("yonetici")) {
            String[] izinlerArr = {"uye-ekle","grup-ismi-degistir","uye-sil","rol-ata"};
            this.IzinlerEkle(izinlerArr);
        }
        if(rol.equals("moderator")){
            String[] izinlerArr = {"uye-ekle","uye-sil"};
            this.IzinlerEkle(izinlerArr);
        }

    }
}

