import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SesjaLogowania {
    public Permisja permisja;
    private static final HashMap<String,String> listaKont = new HashMap<>();

    public SesjaLogowania() {
        this.permisja = Permisja.BRAK;
    }

    public static boolean czyWLiscie(String login, String haslo) {
        if (listaKont.containsKey(login)) {
            return listaKont.get(login).equals(haslo);
        }
        return false;
    }

    public boolean login(){
        try {
            File plik = new File("C:\\Users\\pokel\\Desktop\\Projekt_Czachor\\src\\Konta.txt");
            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                String linia = odczyt.nextLine();
                listaKont.put(linia.split("\\|")[0],linia.split("\\|")[1]);
            }
        }
            catch (FileNotFoundException e) {
                System.out.println("błąd pliku Konto.txt");
                e.printStackTrace();
            }
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj login: ");
            String login = scanner.next();
            System.out.println("Podaj haslo: ");
            String haslo = scanner.next();
            System.out.println("Sprawdzanie prawidłowości loginu i hasła...");
            if(czyWLiscie(login,haslo)){
                if(login.equals("admin")){
                    permisja = Permisja.ADMIN;
                }
                System.out.println("Zalogowano poprawnie.");
                return true;
            }else{
                System.out.println("Nieprawidłowy login lub hasło, spróbuj ponownie.");
                return this.login();
            }
        }
    }
}
