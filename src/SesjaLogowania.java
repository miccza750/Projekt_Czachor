import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SesjaLogowania {
    public void login(){
        HashMap<String,String> lista_kont = new HashMap<>();
        try {
            File myObj = new File("C:\\Users\\pokel\\Desktop\\Projekt_Czachor\\src\\Konta.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String linia = myReader.nextLine();
                lista_kont.put(linia.split("\\|")[0],linia.split("\\|")[1]);
            }
        }
            catch (FileNotFoundException e) {
                System.out.println("błąd pliku Konto.txt");
                e.printStackTrace();
            }
        while(true){

        }
    }
}
