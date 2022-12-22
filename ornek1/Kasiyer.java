package ornek1;

import java.util.Scanner;

public class Kasiyer {
	Scanner scanner = new Scanner(System.in);
	Scanner scanner2 = new Scanner(System.in);
	
	Kasa kasa;
	
	
	public Kasiyer() {
		kasa = new Kasa();
		
	}
	
	public Kasiyer(String isim) {
		kasa = new Kasa(isim);
	}
	
	public void isVakti() {
		int yiyecekKodu=0;
		double fiyat=0;
		
		while (yiyecekKodu != 5) {
			System.out.println("lütfen bir seçim yappınız.");
			yiyecekKodu = scanner.nextInt();
			
			if (yiyecekKodu == 5) {
				break;
			}
			
			System.out.println("Lütfen yaptığınız seçimin fiyatını yazınız.");
			fiyat = scanner2.nextDouble();
			
			
			kasa.hesapla(yiyecekKodu, fiyat);
			
			
		}
		kasa.fisYazdir();
	}
}
