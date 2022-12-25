package chatApp;

import java.util.Date;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Map;


public abstract class Sohbet {
	 Kullanici kullanici;
	    int id;
	    HashMap<Integer,Kullanici> sohbetUyeleri;
	    int sonMesajIndex=0;
	    Mesaj[] mesajlarDizisi;
	    Sohbet(){
	        mesajlarDizisi=new Mesaj[9999];
	        sohbetUyeleri=new HashMap<Integer,Kullanici>();
	    }

	    abstract void sohbetOnizle(Kullanici kullanici);
	    abstract void sohbeteGir(Kullanici kullanici);

	    void mesajlariListele(){
	        for (int i = 0; i<sonMesajIndex ; i++) {
	            String gonderenIsim=mesajlarDizisi[i].gonderenK.isim;
	            String icerik=mesajlarDizisi[i].getIcerik();
	            Boolean iletildiMi=mesajlarDizisi[i].iletildiMi;
	            Boolean gorulduMu=mesajlarDizisi[i].gorulduMu;
	            String tip=mesajlarDizisi[i].tip;
	            System.out.println(gonderenIsim+":"+"~"+tip+" "+icerik+
	                    " i:"+iletildiMi+" g:"+gorulduMu);
	        }
	    }
	    void okunduOlarakIsaretle(){
	        
	        int bildirimSayisi=kullanici.bildirimlerMapi.get(this.id)==null?0:kullanici.bildirimlerMapi.get(this.id);
	        kullanici.bildirimlerMapi.put(this.id,0);

	        for (int i = sonMesajIndex-1; i >=(sonMesajIndex-bildirimSayisi) ; i--) {
	            mesajlarDizisi[i].gorulduMu=true;
	        }
	    }
	    void mesajGonderme(String tip, String icerik){
	        Mesaj msj=new Mesaj();
	        msj.tip=tip;
	        msj.setIcerik(icerik);;
	        msj.gonderenK=kullanici;
	        mesajlarDizisi[sonMesajIndex++]=msj;
	        int guncelBildirimSayisi=0;
	        for (Map.Entry<Integer, Kullanici> set :
	                sohbetUyeleri.entrySet()) {
	            Kullanici k=set.getValue();
	            int key=set.getKey();
	            if(k.id!=kullanici.id){
	                guncelBildirimSayisi= k.bildirimlerMapi.get(this.id)==null?0:k.bildirimlerMapi.get(this.id);
	                k.bildirimlerMapi.put(this.id,guncelBildirimSayisi+1);
	                System.out.println("bildirim set edildi"+k.id+""+kullanici.id+k.bildirimlerMapi.get(this.id));
	                System.out.println(k.bildirimlerMapi.get(this.id));
	            }
	        }
	    }
	    void gondermeMenusu(){
	        String secim="";

	        while (true) {
	            for (int i = 0; i < 50; ++i) System.out.println();
	            this.mesajlariListele();
	            System.out.println("Metin göndermek için [1]'e basınız.");
	            System.out.println("Resim göndermek için [2]'e basınız.");
	            System.out.println("Ses göndermek için [3]'e basınız.");
	            System.out.println("Çıkmak için [0]'e basınız.");
	            secim = Main.scan.next();
	            if (secim.equals("1")) {
	                String mesaj="";
	                System.out.println("Mesajı giriniz: ");
	                mesaj=Main.scan.next();
	                mesajGonderme("metin", mesaj);
	            }
	            else if(secim.equals("2")){
	                String resimUrl="";
	                System.out.println("Resim URL'sini giriniz : ");
	                resimUrl=Main.scan.next();
	                mesajGonderme("resim", resimUrl);
	            }
	            else if(secim.equals("3")){
	                String sesUrl="";
	                System.out.println("Ses URL'sini giriniz.");
	                sesUrl=Main.scan.next();
	                mesajGonderme("ses", sesUrl);
	            }
	            else if (secim.equals("0")) {
	                return;
	            } else {
	                System.out.println("Hatalı giriş yaptınız.");
	            }
	        }
	    }

}
