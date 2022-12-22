package ornek1;

public class Kasa {
	private double toplam_satis_miktari;
	private double gunluk_kar;
	private double salatadan_edilen_kar;
	private int tatli_sayisi;
	private int salata_sayisi;

	public Kasa() {
		System.out.println("Hoş Geldin");
		this.gunluk_kar=0;

		
	}

	public Kasa(String isim) {
		System.out.println("Hoş Geldin " + isim);
		this.gunluk_kar=0;

	}

	public double getToplam_satis_miktari() {
		return toplam_satis_miktari;
	}

	public void setToplam_satis_miktari(double toplam_satis_miktari) {
		this.toplam_satis_miktari = toplam_satis_miktari;
	}

	public double getGunluk_kar() {
		return gunluk_kar;
	}

	public void setGunluk_kar(double gunluk_kar) {
		this.gunluk_kar = gunluk_kar;
	}

	public double getSalatadan_edilen_kar() {
		return salatadan_edilen_kar;
	}

	public void setSalatadan_edilen_kar(double salatadan_edilen_kar) {
		this.salatadan_edilen_kar = salatadan_edilen_kar;
	}

	public int getTatli_sayisi() {
		return tatli_sayisi;
	}

	public void setTatli_sayisi(int tatli_sayisi) {
		this.tatli_sayisi = tatli_sayisi;
	}

	public int getSalata_sayisi() {
		return salata_sayisi;
	}

	public void setSalata_sayisi(int salata_sayisi) {
		this.salata_sayisi = salata_sayisi;
	}

	
	
	
	
	public void hesapla(int secim , double fiyat) {
		topSatHesapla(fiyat);
		gunlukKrHesapla(secim, fiyat);
	}
	
	private void topSatHesapla(double fiyat) {
		setToplam_satis_miktari(getToplam_satis_miktari()+fiyat);
	}
	
	private void gunlukKrHesapla(int secim , double fiyat) {
		if (secim == 1) {
			double kar = fiyat*(0.3);
			setGunluk_kar(getGunluk_kar()+kar);
			salataKari(kar);
			salata_sayisi++;
		}
		else if (secim == 2) {
			double kar = fiyat*(0.25);
			setGunluk_kar(getGunluk_kar()+kar);
		}
		else if (secim == 3) {
			double kar = fiyat*(0.35);
			setGunluk_kar(getGunluk_kar()+kar);
		}
		else if (secim == 4) {
			double kar = fiyat*(0.2);
			setGunluk_kar(getGunluk_kar()+kar);
			tatliSayisiArttir();
		}
		else if (secim == 5) {
			System.out.println("Alışveriş Bitti");
		}
		else {
			System.out.println("Lütfen geçerli bir seçim giriniz.");
		}
	}
	
	private void salataKari(double kar) {
		setSalatadan_edilen_kar(getSalatadan_edilen_kar()+kar);
	}
	
	private void tatliSayisiArttir() {
		setTatli_sayisi(getTatli_sayisi()+1);
	}
	
	public void fisYazdir() {
		System.out.println("Toplam Satış Miktarı : "+getToplam_satis_miktari()+"\nGünlük Kar Miktarı : "
				+getGunluk_kar()+"\nOrtalama Salata Karı : "+getSalatadan_edilen_kar()/salata_sayisi
				+"\nTatlı Sayısı : "+getTatli_sayisi());
	}
}
