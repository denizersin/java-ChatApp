import  java.util.*
public class Kasiyer {
    public Kasa anakasa;
    public int[] fiyatlar;
    public Kasiyer(){

    }

    // burada olusturulacak
    public Kasiyer(String isim){
        this.anakasa=new Kasa("ersin");
    }
    public void isVakti(){
        int secim;
        Scanner scan = new Scanner(System.in);
        System.out.print("urun kodu gir:");
        secim=scan.nextInt();
    }
}
