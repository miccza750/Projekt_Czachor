import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    public static String[] opcjePracownika = {"Usuń użytkownika","Dodaj użytkownika","Zmień dane użytkownika","Wyjście z programu","Wyloguj"};
    public static String[] opcjeAdmina = {"Usuń pracownika","Dodaj pracownika","Zmień hasło swoje lub pracownika","Panel pracownika","Wyloguj"};

    public SesjaLogowania sesja = new SesjaLogowania();
    public void zmmianahasla(){
        if(this.sesja.permisja.equals(Permisja.ADMIN)){
            System.out.println("login, któremu będzie przepisane nowe hasło:");
            String login = scanner.next();
            if(sesja.czyLoginWLiscie(login)) {
                System.out.println("nowe hasło:");
                String haslo = scanner.next();
                sesja.zmianaHasla(login, haslo);
            }else {
                System.out.println("Brak w bazie loginu: " + login);
            }
        }
        else{
            System.out.println("nowe hasło:");
            String haslo = scanner.next();
            sesja.zmianaHasla(sesja.loginUzytkownika, haslo);
        }
    }
    public void wyloguj(){
        this.sesja.permisja = Permisja.BRAK;
    }
    //Wyświetlanie opcji
    public void wyswietl_wybrana_opcje(int a, String nazwa){
        System.out.println("\n");
        System.out.println("=".repeat(25));
        System.out.println("Wybrano opcję nr: "+(a+1)+". "+nazwa);
        System.out.println("=".repeat(25));
    }
    public void wyswietl_opcje(String opcje[]){
        System.out.println("=".repeat(25));
        for (int i = 0; i < +opcje.length; i++) {
            System.out.println(i+1+". "+opcje[i]);
        }
        System.out.println("=".repeat(25));
    }
    public void panelAdmina(){
            System.out.println("Opcje Admina:");
            wyswietl_opcje(opcjeAdmina);
            try {
                int wyjscie = scanner.nextInt();
                wyswietl_wybrana_opcje(wyjscie, opcjeAdmina[wyjscie-1]);
                switch (wyjscie) {
                    case 1:
                        System.out.println("podaj login pracownika do usunięcia: ");
                        try {
                            String loginDoUsuniecia = scanner.next();
                            sesja.usunPracownika(loginDoUsuniecia);
                        }
                        catch (UsuniecieAdmina e){
                            System.out.println("Błąd: " + e.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("podaj login pracownika: ");
                        String loginDododania = scanner.next();
                        System.out.println("podaj login pracownika: ");
                        String hasloDododania = scanner.next();
                        sesja.dodajPracownika(loginDododania,hasloDododania);
                        break;
                    case 3:
                        zmmianahasla();
                        break;
                    case 4:
                        System.out.println("opcje pracownika: ");
                        wyswietl_opcje(opcjePracownika);
                        System.out.println("Przejście do opcji wiąże się z wyjściem z panelu Admina, czy napewno chcesz wyjść (T/N)");
                        String opcja = scanner.next();
                        if(opcja.equals("T")){
                            sesja.permisja = Permisja.PRACOWNIK;
                        }
                        break;
                    case 5:
                        wyloguj();
                        break;
                }
            }
            // obsługa wyjątku gdy wyjscie jest poza zakresem indexów tablicy opcji, obsługuje również litery itd.
            catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("bład, proszę podać liczbę w zakresie!");
                scanner.nextLine();
            }
        }
    public void panelPracownika(){
            wyswietl_opcje(opcjePracownika);
            try {
                int wyjscie = scanner.nextInt();
                wyswietl_wybrana_opcje(wyjscie, opcjePracownika[wyjscie-1]);
                switch (wyjscie) {
                    //usuwanie użytkownika
                    case 1:
                        System.out.println("podaj imie: ");
                        //Uzytkownicy.dodajUzytkownika();
                        break;
                    //dodawanie użytkownika
                    case 2:
                        System.out.println("podaj");
                        break;
                    //usuwanie użytkownika
                    case 3:
                        System.out.println("podaj");
                        break;
                    //usuwanie użytkownika
                    case 4:
                        System.out.println("podaj");
                        break;
                    //usuwanie użytkownika
                    case 5:
                        System.out.println("podaj");
                        break;
                }
            }
            catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("bład, proszę podać liczbę w zakresie!");
                scanner.nextLine();
            }
    }
    public void start(){
        sesja.odczytZPliku();
        while(true){
        switch (sesja.permisja){
            case ADMIN:
                panelAdmina();
                break;
            case PRACOWNIK:
                panelPracownika();
                break;
            case BRAK:
                sesja.login();
                this.start();
                break;
        }
        }
    }
}
