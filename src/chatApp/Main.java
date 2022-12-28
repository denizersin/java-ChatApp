package chatApp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	  public static int benzersizIdGetir(Map map){
	        // parametre: HashMap <int,any>
	        for (int i=0;i<99999;i++){
	            if(!map.containsKey(i)){
	                return i;
	            }
	        }
	        return 0;
	    }
	    public static boolean beklenmeyenDurumSorgula(String girilenDeger){
	        Pattern p = Pattern.compile("([0-9])");
	        Matcher m = p.matcher(girilenDeger);
	        try {
	            Integer.parseInt(girilenDeger);
	            return true;
	        }
	        catch (Exception e){
	            System.out.println("Lütfen sayı giriniz.");
	            return false;
	        }
	    }

	    //bireysel ve grup sohbet idleri cakismasin diye yazilidi.
	    public static int benzersizIdGetir2(Map map1,Map map2){
	        for (int i=0;i<99999;i++){
	            System.out.println(map1.containsKey(i));
	            System.out.println(map2.containsKey(i));
	            if(!map1.containsKey(i)&&!map2.containsKey(i)){
	                System.out.println(i);
	                return i;
	            }
	        }
	        return 0;
	    }
	    static Scanner scan=new Scanner(System.in);
	    public static void main(String[] args) {
	        ChatApp uygulama=new ChatApp();
	    }


}
