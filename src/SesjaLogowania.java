import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class SesjaLogowania {
    public Permisja permisja;
    public String loginUzytkownika;
    private static final HashMap<String,String> listaKont = new HashMap<>();

    public SesjaLogowania() {
        this.permisja = Permisja.BRAK;
    }
    //czy haslo jest poprawne dla podanego loginu
    public boolean czyWLiscie(String login, String haslo) {
        if (listaKont.containsKey(login)) {
            return listaKont.get(login).equals(haslo);
        }
        return false;
    }
    // czy login instnieje
    public boolean czyLoginWLiscie(String login){
        return listaKont.containsKey(login);
    }
    public void dodajPracownika(String login,String haslo){
        listaKont.put(login,haslo);
        this.zapiszDoPliku();
    }
    public void usunPracownika(String login) throws UsuniecieAdmina{
            if (listaKont.containsKey(login)) {
                if(login.equals("admin")){
                    throw new UsuniecieAdmina("Admina nie można usunąć!");
                }
                listaKont.remove(login);
                System.out.println("Użytkownik '" + login + "' został usunięty.");
            } else {
                System.out.println("brak w bazie loginu: " + login);
            }
    }
    // zapis listaKont do pliku
    public void zapiszDoPliku() {
        try (FileWriter zapis = new FileWriter("resources\\Konta.txt")) {
            for (String login : listaKont.keySet()) {
                String haslo = listaKont.get(login);
                zapis.write(login + "|" + haslo + "\n");
            }
            System.out.println("Zapisano zmiany!");
        } catch (IOException e) {
            System.out.println("Błąd pliku: " + e.getMessage());
        }
    }
    public void zmianaHasla(String login,String haslo){
        if (listaKont.containsKey(login)) {
            listaKont.put(login, haslo);
        }
        zapiszDoPliku();
    }
    // odczyt kont
    public void odczytZPliku(){
        try {
            File plik = new File("resources\\Konta.txt");
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
    }
    //sesja logowania
    public boolean login(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Podaj login: ");
            String login = scanner.next();
            System.out.println("Podaj haslo: ");
            String haslo = scanner.next();
            System.out.println("Sprawdzanie prawidłowości loginu i hasła...");
            if(czyWLiscie(login,haslo)){
                loginUzytkownika = login;
                System.out.println("Zalogowano poprawnie.");
                if(login.equals("admin")){
                    permisja = Permisja.ADMIN;
                    return true;
                }
                permisja = Permisja.PRACOWNIK;
                return true;
            }else{
                System.out.println("Nieprawidłowy login lub hasło, spróbuj ponownie.");
                return this.login();
            }
        }
    }
}
