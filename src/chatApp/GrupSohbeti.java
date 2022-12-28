package chatApp;

import javax.crypto.spec.PSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class GrupSohbeti extends Sohbet{
	 String grupIsmi;
	    //default olarak grupRolleriMapi.put(yaraticiID,yaratici(Kullanici).rol);
	    HashMap<Integer,String> grupRolleriMapi;
	    HashMap<Integer,Kullanici> kullanicilarMapi=ChatApp.db.kullanicilarMapi;
	    GrupSohbeti(Kullanici kullanici){
	        //kullanici=yonetici
	        super();
	        grupRolleriMapi=new HashMap<Integer,String>();
	        this.id=Main.benzersizIdGetir2(ChatApp.db.grupSohbetiHashMapi,ChatApp.db.bireyselSohbetHashMapi);
	         String grupIsmi;
	         System.out.print("Lütfen Grup İsmini Belirleyiniz : "); //orijinal kodda syso yok !!! - Burak
	        grupIsmi=Main.scan.next();
	        ChatApp.db.grupSohbetiEkle(this);
	        this.grupIsmi=grupIsmi;
	        this.grupRolleriMapi.put(kullanici.id,"yonetici");
	        this.sohbetUyeleri.put(kullanici.id,kullanici);
	    }


	    void secenekleriListele(){

	        int secim=-1; 
	        while (true){
	            System.out.println("•Üye eklemek için [1]'e basınız.");
	            System.out.println("•Üye silmek için [2]'ye basınız.");
	            System.out.println("•Grup ismini değiştirmek için [3]'e basınız.");
	            System.out.println("•Bir üyeye rol atamak için [4]'e basınız.");
				System.out.println("•Çıkmak için [0]'a basınız.");

				secim=Main.scan.nextInt();

	            if (secim==1){
	                uyeEkle();
	            }
	            else if(secim==2){
	                uyeSil();
	            }
	            else if(secim==3){
	                grupIsmiDegistir();
	            }
	            else if(secim==4){
	                rolAta();
	            }
	            else if(secim==0){
	                return;
	            }
	            else {
	                secenekleriListele();
	            }
	        }


	    }
	    void uyeEkle(){
	        if(Arrays.asList(kullanici.izinler).contains("uye-ekle")){
	            System.out.println("[YETKİSİ VAR]"); 
	            ChatApp.kullanicilariListele(); //orijinal kodda bu kısım yok !!! - Burak
	            System.out.println("Eklenecek üye ID'sini giriniz: ");
	            int kId=Main.scan.nextInt();
	            sohbetUyeleri.put(kId,kullanicilarMapi.get(kId));
	            grupRolleriMapi.put(kId,"normal-uye");
	            return;
	        }
	        izinYokUyarisi();

	    }
	    void uyeSil(){
	        if(Arrays.asList(kullanici.izinler).contains("uye-sil")){
	            System.out.println("[YETKİSİ VAR]");
	            int secim=-1;
	            uyeleriGoruntule(); //orijinal kodda bu kısım yok !!! - Burak
	            System.out.print("Silinecek üyenin ID'sini giriniz: ");
	            secim=Main.scan.nextInt();
	            if(sohbetUyeleri.containsKey(secim)){
	                sohbetUyeleri.remove(secim);
	            }
	            else {
	                System.out.println("Böyle bir üye bulunamadı.");
	                uyeSil();
	            }
	            return;
	        }
	        izinYokUyarisi();
	    }
	    void uyeleriGoruntule(){
	        int id;
	        Kullanici k;
	        for (Map.Entry<Integer, Kullanici> set :
	                sohbetUyeleri.entrySet()) {
	            id=set.getKey();
	            k=set.getValue();
	            System.out.println("ID : "+k.id+" "+k.isim+" rol: "+grupRolleriMapi.get(id)); // orijinal kodda rol'ün başında boşluk yok !!! - Burak
	        }																	//orijinal kodda k.id yok !!! - Burak
	    }
	    void izinYokUyarisi(){
	        System.err.println("[BUNA YETKİN YOK]");
	    }
	    void rolAta(){
	        if(Arrays.asList(kullanici.izinler).contains("rol-ata")){
	            System.out.println("[YETKİN VAR]");
	            int kId=-1;
	            System.out.println("Rolün atanacağı kullanıcının ID'si : ");
	            uyeleriGoruntule(); //bu kısım yoktu !!! - Burak
	            kId=Main.scan.nextInt();
	            if(sohbetUyeleri.containsKey(kId)){
	                String atanacakRol="";
	                System.out.println("[yonetici]   [moderator]   [normal-uye]");
	                System.out.println("Atanacak rolü yazıyla giriniz : ");
	                atanacakRol=Main.scan.next();
	                grupRolleriMapi.put(kId,atanacakRol);
	                System.out.println(grupRolleriMapi.get(kId));
	                System.out.println(kId+" ID'li kisiye "+atanacakRol+"rolü atandı."); //düzenlendi - Burak
	            }
	            else {
	                System.out.println("Kullanıcı bulunamadı.");
	                rolAta();
	            }
	            return;
	        }
	        izinYokUyarisi();
	    }
	    void grupIsmiDegistir(){
	        if(Arrays.asList(kullanici.izinler).contains("grup-ismi-degistir")){
	            String yeniGrupIsmi;
	        	System.out.println("[YETKİN VAR]");
	            System.out.println("Lütfen yeni grup ismini giriniz : ");
	            yeniGrupIsmi = Main.scan.next();
	            this.grupIsmi = yeniGrupIsmi;//orijinal kodda bu kısım yok !!! - Burak
	            return;
	        }
	        izinYokUyarisi();
	    }


	    @Override
	    void sohbetOnizle(Kullanici kullanici){
	        this.kullanici=kullanici;

	        
	        System.out.print("\n≪Grup sohbet ID:"+this.id+ "≫Grup adi: "+"[");
	        System.out.print(this.grupIsmi);
	        if(sonMesajIndex==0){
	        	System.out.print("] ");
	            System.out.println("\n :( Henüz bir mesaj atılmadı.\n • • • • • • • \nMesaj olusturmak veya baska bir islem yapmak icin lutfen sohbet id'si girin.");
	            return;
	        }
			System.out.println("\n----okunmamis mesaj sayisi:" +
					""+kullanici.bildirimlerMapi.get(this.id));
			//orijinal kodda burası çalışmıyor
	        System.out.print("\nGelen son mesaj=  "+mesajlarDizisi[sonMesajIndex-1].gonderenK.isim+": "+(this.mesajlarDizisi[this.sonMesajIndex-1].getIcerik()));


	    }
	    void sohbeteGir(Kullanici kullanici){
	        this.kullanici=kullanici;
	        okunduOlarakIsaretle();

	        kullanici.rolBelirle(grupRolleriMapi.get(kullanici.id));
	        System.out.println("Gruptaki rolunuz = " + kullanici.getRol()); //orijinal kodda gereksiz yazı var  !!! - Burak



	        int secim;
	        while (true){
	            mesajlariListele();
	            System.out.println("•Mesaj göndermek için [1]'e basınız.");
	            System.out.println("•Seçenekleri listelemek için [2]'ye basınız.");
	            System.out.println("•Üyeleri görüntülemek için [3]'e basınız.");
	            System.out.println("•Çıkmak için [0]'a basınız.");
	            secim=Main.scan.nextInt();
	            if(secim==1) {
	                String mesaj = "";
	                int secim2 = 0;
	                gondermeMenusu();
	            }
	            else if(secim==2){
	                secenekleriListele();
	            }
	            else if(secim==3){
	                uyeleriGoruntule();
	            }
	            else  if(secim==0){
	                return;
	            }
	        }
	    }
	}


