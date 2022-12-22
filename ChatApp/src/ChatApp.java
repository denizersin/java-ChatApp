import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChatApp {
    //id=>Kullanici
    HashMap<Integer,Kullanici> kullanicilarMapi;

    HashMap<Integer ,BireyselSohbet> bireyselSohbetHashMapi;
    HashMap<Integer ,GrupSohbeti> grupSohbetiHashMapi;


    Kullanici kullanici;


    ChatApp(){
        kullanicilarMapi=new HashMap<Integer,Kullanici>();
        bireyselSohbetHashMapi=new HashMap<Integer,BireyselSohbet>();
        grupSohbetiHashMapi=new HashMap<Integer,GrupSohbeti>();

        uyeOl("ersin","e","1"); //id =0;
        uyeOl("burak","b","1"); //id =1;

        girisYap();

    }
    void uyeOl(String isim,String kAdi,String sifre){
        //kAdi alinabilir mi?
        Kullanici yeniKullanici=new Kullanici(Main.benzersizIdGetir(kullanicilarMapi),isim,kAdi,sifre);
        kullanicilarMapi.put(yeniKullanici.id,yeniKullanici);
    }
    void girisYap(){
        String sifre;
        int kId;
        System.out.print("kId:");
        kId=Main.scan.nextInt();
        System.out.print("sifre:");
        sifre=Main.scan.next();
        if(kullanicilarMapi.containsKey(kId)&&kullanicilarMapi.get(kId).sifre.equals(sifre)){
            kullanici=kullanicilarMapi.get(kId);
            uygulamaDongusu();
        }
        else {
            System.err.println("tekrar gir");
            girisYap();
        }
    }
    void cikisYap(){

    }
    void bireyselSohbetOlustur(){
        kullanicilariListele();
        System.out.print("olusturulacak sohbet id'si gir:");
        int ikinciKullaniciId=-1;
        ikinciKullaniciId=Main.scan.nextInt();
        if(kullanicilarMapi.containsKey(ikinciKullaniciId)){

        }
        else {
            System.out.println("boyle bir id yok");
            bireyselSohbetOlustur();
            return;
        }
        //kullanicilarin hepsi listelenir ve kullanicidan sohbet
        // baslatmak istedigi kisinin id'si alinit

        Kullanici ikinciKullanici=kullanicilarMapi.get(ikinciKullaniciId);

        BireyselSohbet sohbet1=new BireyselSohbet(kullanici,ikinciKullanici);
        sohbet1.id=Main.benzersizIdGetir2(bireyselSohbetHashMapi,grupSohbetiHashMapi);
        sohbet1.sohbetUyeleri.put(kullanici.id,kullanici);
        sohbet1.sohbetUyeleri.put(ikinciKullanici.id,ikinciKullanici);

        bireyselSohbetHashMapi.put(sohbet1.id,sohbet1);
    }
    void grupSohbetOlustur(){

    }
    void kullaniciSohbetleriGoster(){
        Map<Integer, BireyselSohbet> kullaniciBireyselSohbetler = bireyselSohbetHashMapi.entrySet().stream()
                .filter(x -> x.getValue().sohbetUyeleri.containsKey(kullanici.id))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        Map<Integer, GrupSohbeti> kullaniciGrupSohbetler = grupSohbetiHashMapi.entrySet().stream()
                .filter(x -> x.getValue().sohbetUyeleri.containsKey(kullanici.id))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        kullaniciBireyselSohbetler.forEach((key, value) -> {value.sohbetOnizle(kullanici);});
        kullaniciGrupSohbetler.forEach((key, value) -> {value.sohbetOnizle();});
        int shobetId=0;
        System.out.println("sohbet id giriniz");
        shobetId=Main.scan.nextInt();
        if(bireyselSohbetHashMapi.containsKey(shobetId)){
            bireyselSohbetHashMapi.get(shobetId).sohbeteGir(kullanici);
        }
        else if(grupSohbetiHashMapi.containsKey(shobetId)){
            grupSohbetiHashMapi.get(shobetId).sohbeteGir(kullanici,kullanicilarMapi);
        }
        else {
            System.out.println("gecerli id gir");
            //basa don
            kullaniciSohbetleriGoster();
        }
        //sohbetId sohbete git...


    }
    void uygulamaDongusu(){
        int secim=-1;
        while (true){
            System.out.println("sohbetleri goster 1");
            System.out.println("sohbet olustur 2");
            System.out.println("grup olustur 3");
            System.out.println("tekrar giris yap 4");
            secim=Main.scan.nextInt();
            if(secim==0){

            }
            else if(secim==1){
                kullaniciSohbetleriGoster();
            }
            else if(secim==2){
                bireyselSohbetOlustur();
            }
            else if(secim==3){
                grupOlustur();
            }
            else if(secim==4){
                girisYap();
            }
            else {

            }
            //Bildirimleri goster 1 ana menu?
            //Sohbetleri Goster 2 -> sohbete git? ana menu?
            //bireyselSohbetOlustur 3 ->
            //grupOlustur 4
        }
    }

    void kullanicilariListele(){
        for (Map.Entry<Integer, Kullanici> set :
                kullanicilarMapi.entrySet()) {
            Kullanici k=set.getValue();
            System.out.println("["+k.id+"]"+k.isim);
        }
    }

    void grupOlustur(){
       int newId=Main.benzersizIdGetir2(grupSohbetiHashMapi,bireyselSohbetHashMapi);
       String grupIsmi;
       grupIsmi=Main.scan.next();
       GrupSohbeti g=new GrupSohbeti(kullanici);
       grupSohbetiHashMapi.put(newId,g);
       g.grupIsmi=grupIsmi;
       g.grupRolleriMapi.put(kullanici.id,"yonetici");
       g.sohbetUyeleri.put(kullanici.id,kullanici);
    }



}
