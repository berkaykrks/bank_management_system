package json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class read {
   

    public static void main (String[] args)
    {
    	Scanner scanner = new Scanner(System.in);
    	
    	bank bankaIslemler  = new bank();
    	
    	System.out.println("tc no giriniz : ");
    	String tc = scanner.nextLine();
    	
    	System.out.println("sifre giriniz : ");
    	String sifre =scanner.nextLine();
    	
    	
    	
    	String sifreDogrulama = bankaIslemler.sifreKontrol(sifre);
    	
    	if(!sifreDogrulama.isEmpty()) 
    	{
    		bankaIslemler.islemler(tc, sifreDogrulama);
    	}
    	
    	
    }
    
}
