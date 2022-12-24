public class Rol extends Izinler{
    String rol;

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

