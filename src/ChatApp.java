import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChatApp {
    //id=>Kullanici
    static HashMap<Integer,Kullanici> kullanicilarMapi;
    static HashMap<Integer ,BireyselSohbet> bireyselSohbetHashMapi;
    static HashMap<Integer ,GrupSohbeti> grupSohbetiHashMapi;


    Kullanici kullanici;


    ChatApp(){
        kullanicilarMapi=new HashMap<Integer,Kullanici>();
        bireyselSohbetHashMapi=new HashMap<Integer,BireyselSohbet>();
        grupSohbetiHashMapi=new HashMap<Integer,GrupSohbeti>();

        uyeOl("ersin","e","1"); //id =0;
        uyeOl("burak","b","1"); //id =1;
        uyeOl("ipek","i","1"); //id =2;
        uyeOl("ahmet","a","1"); //id =3;


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
    void bireyselSohbetOlustur(){
        new BireyselSohbet(kullanici);
    }
    void grupSOhbetiOlustur(){
        new GrupSohbeti(kullanici); //kullanici=yonetici
    }
    void kullaniciSohbetleriGoster(){
        Map<Integer, BireyselSohbet> kullaniciBireyselSohbetler = bireyselSohbetHashMapi.entrySet().stream()
                .filter(x -> x.getValue().sohbetUyeleri.containsKey(kullanici.id))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        Map<Integer, GrupSohbeti> kullaniciGrupSohbetler = grupSohbetiHashMapi.entrySet().stream()
                .filter(x -> x.getValue().sohbetUyeleri.containsKey(kullanici.id))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        kullaniciBireyselSohbetler.forEach((key, value) -> {value.sohbetOnizle(kullanici);});
        kullaniciGrupSohbetler.forEach((key, value) -> {value.sohbetOnizle(kullanici);});
        int shobetId=-1;
        System.out.println("sohbet id giriniz");
        System.out.println("cikmak icin -1");
        shobetId=Main.scan.nextInt();
        if(bireyselSohbetHashMapi.containsKey(shobetId)){
            bireyselSohbetHashMapi.get(shobetId).sohbeteGir(kullanici);
        }
        else if(grupSohbetiHashMapi.containsKey(shobetId)){
            grupSohbetiHashMapi.get(shobetId).sohbeteGir(kullanici);
        }
        else if(shobetId==-1){
            return;
        }
        else {
            System.out.println("gecerli id gir");
            //basa don
            kullaniciSohbetleriGoster();
        }


    }

    static void kullanicilariListele(){
        for (Map.Entry<Integer, Kullanici> set :
                kullanicilarMapi.entrySet()) {
            Kullanici k=set.getValue();
            System.out.println("["+k.id+"]"+k.isim);
        }
    }

    void uygulamaDongusu(){
        int secim=-1;
        while (true){
            for (int i = 0; i < 50; ++i) System.out.println();

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
                grupSOhbetiOlustur();
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


}

