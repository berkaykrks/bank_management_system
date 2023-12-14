package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Type;
import java.security.DrbgParameters.NextBytes;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class bank {
    private static final String JSON_FILE_PATH = "D:\\Users\\berka\\eclipse-workspace\\bank_management_system\\src\\json\\veri.json";

    public static void main(String[] args) {
        
    }
    
    public static void islemler(String tc, String sifre)
    {
    	Scanner scanner = new Scanner(System.in);
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Customer>>() {}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);
            

            for (Customer customer : customers) {
                if (customer.getTcKimlik().toLowerCase().equals(tc) && customer.getSifre().toLowerCase().equals(sifre)) {
                	
                    System.out.println("Yapmak istediğiniz işlemi seçiniz : ");
                    System.out.println("Kredi Ödemesi Yapma (1)");
                    System.out.println("Kredi Kartı Ekstresi Ödeme (2)");
                    System.out.println("Para Transferi (3)");
                    System.out.println("Para Yatır (4)");
                    System.out.println("Para Çek (5)");
                    System.out.println("Seçim : ");
                    int secim = scanner.nextInt();
                    
                    double  transferTutar=0;
                    if(secim == 1){
                    	borcOdeme(tc, sifre);
                    }
                    else if(secim == 2) {
                    	kartEkstreOdeme(tc, sifre);
                    }
                    else if(secim == 3) {      
                        System.out.println("Alıcı Hesap Numarası giriniz: ");
                        String alan = scanner.next();

                        System.out.println("Transfer Tutarını giriniz: ");
                        transferTutar = scanner.nextDouble();
                        
                        
                        performTransfer(customers, tc, alan, transferTutar);
                        
                    }
                    else if(secim == 4) {
                    	paraYatir(tc, sifre);
                    }
                    else if (secim == 5) {
						paraCek(tc, sifre);
					}
                    else {
                    	System.out.println("Yanlış Bir Seçim Yapıldı...");
					}
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String sifreKontrol(String sifre) {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Customer>>() {}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);

            for (Customer customer : customers) {
                String dogumTarihi = customer.getDogumTarih();
                
                if (sifre.equals(dogumTarihi)) {
                    System.out.println("Girilen şifre doğum tarihinizle aynı sistemdeki şifreden ise farklı olamaz.\nLütfen başka bir şifre giriniz.");
                    return "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sifre;
    }

    public static void performTransfer(List<Customer> customers, String gonderenTc, String aliciHesapNumara, double transferMiktar) {
        for (Customer gonderen : customers) {
            if (gonderen.getTcKimlik().toLowerCase().equals(gonderenTc)) {
                for (Customer alici : customers) {
                    if (alici.getHesapNumarasi().equals(aliciHesapNumara)) {
                        if (gonderen.getBakiye() >= transferMiktar) {
                            gonderen.setBakiye(gonderen.getBakiye() - transferMiktar);
                            alici.setBakiye(alici.getBakiye() + transferMiktar);

                            try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                                Gson gson = new Gson();
                                gson.toJson(customers, writer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println( alici.getFirstName()+" "+alici.getLastName()+ " adlı kişiye " + transferMiktar + " TL Para Transferiniz Başarıyla Gerçekleştirildi.");
                        } else {
                            System.out.println("Yetersiz bakiye. Transfer gerçekleştirilemedi.");
                        }
                        return;
                    }
                }
                System.out.println("Alıcı hesap bulunamadı.");
                return;
            }
        }
        System.out.println("Gönderen hesap bulunamadı.");
    }

    public static void borcOdeme(String tc, String sifre) {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Customer>>() {}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);

            for (Customer customer : customers) {
                if (customer.getTcKimlik().toLowerCase().equals(tc) && customer.getSifre().toLowerCase().equals(sifre)) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Kalan Kredi Borcunuz: " + customer.getKartBorc());
                    System.out.println("Borcunuzun Ne Kadarını Ödemek İstiyorsunuz? : ");
                    double miktar = scanner.nextDouble();

                    if (miktar <= customer.getKartBorc()) {
                        customer.setKartBorc(customer.getKartBorc() - miktar);
                        System.out.println("Borcunuzun " + miktar + " TL'si Ödendi");
                        System.out.println("Kalan Borç: " + customer.getKartBorc());

                        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                            gson.toJson(customers, writer);
                        }
                    } else {
                        System.out.println("Borcunuzdan fazla ödeme yapamazsınız.");
                    }

                    return; 
                }
            }

            System.out.println("Giriş yapılan hesap bulunamadı.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public static void kartEkstreOdeme(String tc, String sifre) {
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Customer>>() {}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);

            for (Customer customer : customers) {
                if (customer.getTcKimlik().toLowerCase().equals(tc) && customer.getSifre().toLowerCase().equals(sifre)) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Kalan Kredi Kartı Ekstre Borcunuz: " + customer.getKartEkstre());
                    System.out.println("Asgari miktarı: " + customer.getKartEkstre() / 5);
                    System.out.println("Kalan Ekstrenizi veya asgari miktarını ödemek zorundasınız.");
                    System.out.println("Ödeme: ");
                    double miktar = scanner.nextDouble();

                    if (miktar >= customer.getKartEkstre() / 5 && miktar <= customer.getKartEkstre()) {
                        customer.setKartEkstre(customer.getKartEkstre() - miktar);
                        System.out.println("Ekstrenin " + customer.getKartEkstre() + " TL'si Ödendi");
                        System.out.println("Kalan Borç: " + customer.getKartEkstre());

                        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                            gson.toJson(customers, writer);
                        }
                    } else {
                        System.out.println("Kalan ekstreden fazla ve asgari miktarından az ödeme yapamazsınız.");
                    }

                    return; 
                }
            }

            System.out.println("Giriş yapılan hesap bulunamadı.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public static void paraYatir(String tc, String sifre) {
        Scanner scanner = new Scanner(System.in);
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Customer>>() {}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);

            for (Customer customer : customers) {
                if (customer.getTcKimlik().toLowerCase().equals(tc) && customer.getSifre().toLowerCase().equals(sifre)) {
                    System.out.println("Yatrılacak Tutarı Giriniz: ");
                    double miktar = scanner.nextDouble();

                    if (miktar <= 500) {
                        customer.setBakiye(customer.getBakiye() + miktar);
                        System.out.println("Bakiye güncellendi ");

                        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                            gson.toJson(customers, writer);
                        }
                        System.out.println("Güncel Bakiye : "+customer.getBakiye());
                    } else {
                        System.out.println("Yatırılan miktar 500 TL'den fazla olamaz. \nLütfen geçerli bir miktar giriniz.");
                    }

                    return; 
                }
            }

            System.out.println("Giriş yapılan hesap bulunamadı.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void paraCek(String tc, String sifre) {
        Scanner scanner = new Scanner(System.in);
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Customer>>() {}.getType();
            List<Customer> customers = gson.fromJson(reader, listType);

            for (Customer customer : customers) {
                if (customer.getTcKimlik().toLowerCase().equals(tc) && customer.getSifre().toLowerCase().equals(sifre)) {
                    System.out.println("Bakiye : "+customer.getBakiye());
                	System.out.println("Çekilecek Tutarı Giriniz: ");
                    double miktar = scanner.nextDouble();

                    if (miktar <= customer.getBakiye()) {
                        customer.setBakiye(customer.getBakiye() - miktar);
                        System.out.println("Bakiye güncellendi");

                        try (FileWriter writer = new FileWriter(JSON_FILE_PATH)) {
                            gson.toJson(customers, writer);
                        }
                        System.out.println("Güncel Bakiye : "+customer.getBakiye());
                    } else {
                        System.out.println("Çekilen miktar bakiyeden fazla olamaz. \nLütfen geçerli bir miktar giriniz.");
                    }

                    return; 
                }
            }

            System.out.println("Giriş yapılan hesap bulunamadı.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
