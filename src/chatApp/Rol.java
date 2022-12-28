package chatApp;

public class Rol extends Izınler{
	 private String rol;

	    public String getRol() {
	        return rol;
	    }

	    //burasi setRol() olarak dusunulebilir
	    void rolBelirle(String rol){
	        this.rol=rol;
	        //if yonetici ise

	        if(rol.equalsIgnoreCase("yonetici")) {
	            String[] izinlerArr = {"uye-ekle","grup-ismi-degistir","uye-sil","rol-ata"};
	            this.IzinlerEkle(izinlerArr);
	        }
	        if(rol.equalsIgnoreCase("moderator")){
	            String[] izinlerArr = {"uye-ekle","uye-sil"};
	            this.IzinlerEkle(izinlerArr);
	        }

	    }

}
