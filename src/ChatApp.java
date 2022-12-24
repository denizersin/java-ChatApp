import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChatApp {
    //id=>Kullanici

    static Database db;

   private Kullanici kullanici;

    ChatApp(){
        db=new Database();
        uyeOl("ersin","e","1"); //id =0;
        uyeOl("burak","b","1"); //id =1;
        uyeOl("ipek","i","1"); //id =2;
        uyeOl("ahmet","a","1"); //id =3;

        girisYapMenusu();

    }
    void uyeOl(String isim,String kAdi,String sifre){
        //kAdi alinabilir mi?
        Kullanici yeniKullanici=new Kullanici(Main.benzersizIdGetir(Database.kullanicilarMapi),isim,kAdi,sifre);
        db.kullaniciEkle(yeniKullanici);
    }
    void girisYapMenusu(){
        System.out.print("k.adi veye id:");
        String kAdiId=Main.scan.next();
        System.out.print("sifre:");
        String sifre=Main.scan.next();

        Pattern p = Pattern.compile("([0-9])");
        Matcher m = p.matcher(kAdiId);
        if(!m.find()){ //sayi yoksa
            girisYap(kAdiId,sifre);
            //kAdi
        }
        else {
            //sayi varsa id
            int id=Integer.parseInt(kAdiId);
            girisYap(id,sifre);
        }
    }

    //overLoading
    void girisYap(int id,String sifre){
        System.out.println("id");
        if(Database.kullanicilarMapi.containsKey(id)&&Database.kullanicilarMapi.get(id).getSifre().equals(sifre)){
            kullanici=Database.kullanicilarMapi.get(id);
            uygulamaDongusu();
        }
        else {
            System.err.println("tekrar gir");
            girisYapMenusu();
        }
    }
    void girisYap(String kAdi,String sifre){
        System.out.println("kAdi");
        for (Map.Entry<Integer, Kullanici> set :
                Database.kullanicilarMapi.entrySet()) {
                int id=set.getKey();
                Kullanici k=set.getValue();
                if(k.getKullaniciAdi().equals(kAdi)&&k.getSifre().equals(sifre)){
                    //k giris yapar
                    kullanici=k;
                    uygulamaDongusu();
                    return;
                }
        }
        System.out.println("boyle bir kullanici yok");
        girisYapMenusu();
    }
    void bireyselSohbetOlustur(){
        new BireyselSohbet(kullanici);
    }
    void grupSOhbetiOlustur(){
        new GrupSohbeti(kullanici); //kullanici=yonetici
    }
    void kullaniciSohbetleriGoster(){
        Map<Integer, BireyselSohbet> kullaniciBireyselSohbetler = Database.bireyselSohbetHashMapi.entrySet().stream()
                .filter(x -> x.getValue().sohbetUyeleri.containsKey(kullanici.id))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        Map<Integer, GrupSohbeti> kullaniciGrupSohbetler = Database.grupSohbetiHashMapi.entrySet().stream()
                .filter(x -> x.getValue().sohbetUyeleri.containsKey(kullanici.id))
                .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        kullaniciBireyselSohbetler.forEach((key, value) -> {value.sohbetOnizle(kullanici);});
        kullaniciGrupSohbetler.forEach((key, value) -> {value.sohbetOnizle(kullanici);});
        int shobetId=-1;
        System.out.println("sohbet id giriniz");
        System.out.println("cikmak icin -1");
        shobetId=Main.scan.nextInt();
        if(Database.bireyselSohbetHashMapi.containsKey(shobetId)){
            Database.bireyselSohbetHashMapi.get(shobetId).sohbeteGir(kullanici);
        }
        else if(Database.grupSohbetiHashMapi.containsKey(shobetId)){
            Database.grupSohbetiHashMapi.get(shobetId).sohbeteGir(kullanici);
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
                Database.kullanicilarMapi.entrySet()) {
            Kullanici k=set.getValue();
            System.out.println("["+k.id+"]"+k.isim);
        }
    }

    void uygulamaDongusu(){
         String secim="";
        while (true){
            for (int i = 0; i < 50; ++i) System.out.println();

            System.out.println("sohbetleri goster 1");
            System.out.println("sohbet olustur 2");
            System.out.println("grup olustur 3");
            System.out.println("tekrar giris yap 4");
            secim=Main.scan.next();
            if(secim.equals("0")){

            }
            else if(secim.equals("1")){
                kullaniciSohbetleriGoster();
            }
            else if(secim.equals("2")){
                bireyselSohbetOlustur();
            }
            else if(secim.equals("3")){
                grupSOhbetiOlustur();
            }
            else if(secim.equals("4")){
                girisYapMenusu();
            }
            else if(!Main.beklenmeyenDurumSorgula(secim)){

            }
            //Bildirimleri goster 1 ana menu?
            //Sohbetleri Goster 2 -> sohbete git? ana menu?
            //bireyselSohbetOlustur 3 ->
            //grupOlustur 4
        }
    }

    //Inner Class
    class Database{
        static HashMap<Integer,Kullanici> kullanicilarMapi;
        static HashMap<Integer ,BireyselSohbet> bireyselSohbetHashMapi;
        static HashMap<Integer ,GrupSohbeti> grupSohbetiHashMapi;
        Database(){
            kullanicilarMapi=new HashMap<Integer,Kullanici>();
            bireyselSohbetHashMapi=new HashMap<Integer,BireyselSohbet>();
            grupSohbetiHashMapi=new HashMap<Integer,GrupSohbeti>();
        }
        Kullanici getKullaniciById(int id){
            return kullanicilarMapi.get(id);
        }
        void bireyselSohbetEkl(BireyselSohbet sohbet){
            bireyselSohbetHashMapi.put(sohbet.id,sohbet);
        }
        void grupSohbetiEkle(GrupSohbeti sohbet){
            grupSohbetiHashMapi.put(sohbet.id,sohbet);
        }
        boolean kullaniciSorguluaById(int id){
            return kullanicilarMapi.containsKey(id);
        }
        void kullaniciEkle(Kullanici k){
            kullanicilarMapi.put(k.id,k);
        }
    }
}

