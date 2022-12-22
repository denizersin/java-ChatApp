import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
    public static int benzersizIdGetir2(Map map1,Map map2){
        // parametre: HashMap <int,any>
        for (int i=0;i<99999;i++){
            if(!map1.containsKey(i)&&!map2.containsKey(i)){
                return i;
            }
        }
        return 0;
    }
    static Scanner scan=new Scanner(System.in);
    public static void main(String[] args) {
        ChatApp Uygulama=new ChatApp();
    }
}