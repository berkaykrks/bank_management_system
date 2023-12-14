package json;

public class Customer {
    private String firstName;
    private String lastName;
    private String telefonNumarasi;
    private String adres;
    private String dogumTarih;
    private String sifre;
    private String hesapNumarasi;
    private String tcKimlik;
    private double bakiye;
    private double kartEkstre;
    private double kartBorc;
    

    public Customer(String firstName, String lastName, String telefonNumarasi, String adres,String dogumTarih, String sifre, String hesapNumarasi, String tcKimlik, Double bakiye, Double kartEkstre, Double kartBorc) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.telefonNumarasi = telefonNumarasi;
        this.adres = adres;
        this.dogumTarih = dogumTarih;
        this.sifre = sifre;
        this.hesapNumarasi = hesapNumarasi;
        this.tcKimlik=tcKimlik;
        this.bakiye=bakiye;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

	public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(String telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getDogumTarih() {
		return dogumTarih;
	}

	public void setDogumTarih(String dogumTarih) {
		this.dogumTarih = dogumTarih;
	}

	public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getHesapNumarasi() {
        return hesapNumarasi;
    }

    public void setHesapNumarasi(String hesapNumarasi) {
        this.hesapNumarasi = hesapNumarasi;
    }

	public String getTcKimlik() {
		return tcKimlik;
	}

	public void setTcKimlik(String tcKimlik) {
		this.tcKimlik = tcKimlik;
	}
	
	 public double getBakiye() {
		 
			return bakiye;
	}

	public void setBakiye(double bakiye) {
		this.bakiye = bakiye;
	}

	public double getKartEkstre() {
		return kartEkstre;
	}

	public void setKartEkstre(double kartEkstre) {
		this.kartEkstre = kartEkstre;
	}

	public double getKartBorc() {
		return kartBorc;
	}

	public void setKartBorc(double kartBorc) {
		this.kartBorc = kartBorc;
	}
    
    
}
