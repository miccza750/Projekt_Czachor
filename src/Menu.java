import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    ParkingiIKempingi parkingiIRezerwacje = new ParkingiIKempingi();
    Klienci klienci = new Klienci();

    Scanner scanner = new Scanner(System.in);
    public static String[] opcjePracownika = {"Usuń klienta","Dodaj klienta","Zmień dane klienta","wyświetł wszystkich klientów","wyświetl wszystkie rezerwacje","dodaj rezerwację","usuń rezerwację","Wyświetl parkingi","Wyloguj"};
    public static String[] opcjeAdmina = {"Usuń pracownika","Dodaj pracownika","Zmień hasło swoje lub pracownika","Panel pracownika","Wyloguj"};
    public static String[] polaDoZmiany = {"imie","nazwisko","email"};
    public SesjaLogowania sesja = new SesjaLogowania();
    public void dodajKlienta(){
        System.out.println("podaj imie:");
        String imie = scanner.next();
        System.out.println("podaj nazwisko:");
        String nazwisko = scanner.next();
        System.out.println("podaj email:");
        String email = scanner.next();
        klienci.dodajKlienta(imie,nazwisko,email);
    }
    public void usunKlienta(){
        try {
            System.out.println("podaj id klienta do usuniecia: ");
            int idKlienta = scanner.nextInt();
            if(idKlienta < 0 ){
                System.out.println("Proszę podać liczbę dodatnią!");
            }
            else {
                if (klienci.czyWLiscie(idKlienta)) {
                    klienci.usunKlienta(idKlienta);
                }else {
                    System.out.println("Brak klienta o takim id");
                }
            }
        }catch (InputMismatchException e){
            System.out.println("Id musi być liczbą całkowitą!");
        }
    }
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
    public void dodajRezerwacje(){
        try {
            System.out.println("podaj id parkingu: ");
            int idP = scanner.nextInt();
            System.out.println("podaj id klienta: ");
            int idK = scanner.nextInt();
            System.out.println("podaj ilosc dni pobytu: ");
            int iloscdni = scanner.nextInt();
            if(idP > 0 && idK > 0 && iloscdni > 0) {
                if(klienci.czyWLiscie(idK) && parkingiIRezerwacje.czyWLiscie(idP)){
                    if (parkingiIRezerwacje.czyJestMiejsce(idP)) {
                        if(parkingiIRezerwacje.czyKemping(idP)){
                            System.out.println("czy miejsce ma być elektryczne? (T/N): ");
                            String odpE = scanner.next();
                            if(odpE.equals("T")) {
                                if(parkingiIRezerwacje.czyJestMiejsceElektryczne(idP)) {
                                    Rezerwacja nowarezerwacja = new Rezerwacja(idP, idK, iloscdni, true);
                                    parkingiIRezerwacje.dodajRezerwacje(nowarezerwacja);
                                    System.out.println("Do zapłaty: "+ parkingiIRezerwacje.policzKosztRezerwacji(iloscdni,true)+"zł");
                                }else{
                                    System.out.println("Brak miejsca z elektryką.");
                                }
                            }else{
                                Rezerwacja nowarezerwacja = new Rezerwacja(idP, idK, iloscdni,false);
                                parkingiIRezerwacje.dodajRezerwacje(nowarezerwacja);
                                System.out.println("Do zapłaty: "+ parkingiIRezerwacje.policzKosztRezerwacji(iloscdni,false)+"zł");
                            }
                        }else {
                            Rezerwacja nowarezerwacja = new Rezerwacja(idP, idK, iloscdni,false);
                            parkingiIRezerwacje.dodajRezerwacje(nowarezerwacja);
                            System.out.println("Do zapłaty: "+ parkingiIRezerwacje.policzKosztRezerwacji(iloscdni,false)+"zł");
                        }
                    }else {
                        System.out.println("Brak miejsa na tym parkingu!");
                    }
                }else{
                    System.out.println("Błedne id parkingu lub klienta");
                }
            }else{
                System.out.println("Id musi być dodatnie");
            }
        }
        catch (InputMismatchException e){
            System.out.println("id ma być liczbą!");
        }
    }
    public void usunRezerwacje(){
        System.out.println("podaj id rezerwacji: ");
        try {
            int idInt = scanner.nextInt();
            if(idInt < 0){
                System.out.println("Id musi być dodatnie");
            }else {
                parkingiIRezerwacje.usunRezerwacje(idInt);
            }
        }catch (InputMismatchException e){
            System.out.println("Proszę podać liczbę");
        }
    }
    public void zmianaPolaKlienta(){
        try {
            System.out.println("podaj id: ");
            int id = scanner.nextInt();

            if (klienci.czyWLiscie(id)) {
                wyswietlOpcje(polaDoZmiany);
                int odp = scanner.nextInt();

                try {
                    wyswietlWybranaOpcje(odp,polaDoZmiany[odp-1]);
                    switch (odp) {
                        case 1:
                            System.out.println("Imię: ");
                            String imieDoZmiany = scanner.next();
                            klienci.zmianaImienia(id,imieDoZmiany);
                            break;
                        case 2:
                            System.out.println("Nazwisko: ");
                            String nazwiskoDozmiany = scanner.next();
                            klienci.zmianaNazwiska(id,nazwiskoDozmiany);
                            break;
                        case 3:
                            System.out.println("Email: ");
                            String emailDoZmiany = scanner.next();
                            klienci.zmianaEmaila(id,emailDoZmiany);
                            break;
                    }
                }catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                    System.out.println("Proszę podać liczbę w przedziale 1 do 3!");
                }
            } else {
                System.out.println("brak takiego klienta z tym id!");
            }
        }catch (InputMismatchException e){
            System.out.println("podaj liczbe!");
        }
    }
    public void wyswietlWybranaOpcje(int a, String nazwa){
        System.out.println("\n");
        System.out.println("=".repeat(25));
        System.out.println("Wybrano opcję nr: "+(a)+". "+nazwa);
        System.out.println("=".repeat(25));
    }
    public void wyswietlOpcje(String opcje[]){
        System.out.println("=".repeat(25));
        for (int i = 0; i < +opcje.length; i++) {
            System.out.println(i+1+". "+opcje[i]);
        }
        System.out.println("=".repeat(25));
    }
    public void panelAdmina(){
            System.out.println("Opcje Admina:");
            wyswietlOpcje(opcjeAdmina);
            try {
                int wyjscie = scanner.nextInt();
                wyswietlWybranaOpcje(wyjscie, opcjeAdmina[wyjscie-1]);
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
                        try {
                            System.out.println("podaj login pracownika: ");
                            String loginDododania = scanner.next();
                            System.out.println("podaj hasło pracownika: ");
                            String hasloDododania = scanner.next();
                            sesja.dodajPracownika(loginDododania, hasloDododania);
                        }
                        catch (UsuniecieAdmina e){
                        System.out.println("Błąd: " + e.getMessage());
                    }
                        break;
                    case 3:
                        zmmianahasla();
                        break;
                    case 4:
                        System.out.println("opcje pracownika: ");
                        wyswietlOpcje(opcjePracownika);
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
            wyswietlOpcje(opcjePracownika);
            try {
                int wyjscie = scanner.nextInt();
                wyswietlWybranaOpcje(wyjscie, opcjePracownika[wyjscie-1]);
                switch (wyjscie) {
                    //usuwanie klienta
                    case 1:
                        usunKlienta();
                        break;
                    //dodawanie klienta
                    case 2:
                        dodajKlienta();
                        break;
                    //Zmień dane klienta
                    case 3:
                        zmianaPolaKlienta();
                        break;
                    //wyświetł wszystkich klientów
                    case 4:
                        klienci.wyswietlKlientow();
                        break;
                    //wyświetl wszystkie rezerwacje
                    case 5:
                        parkingiIRezerwacje.wyswietlRezerwacje();
                        break;
                    //dodaj rezerwację
                    case 6:
                        dodajRezerwacje();
                        break;
                    //usuń rezerwację
                    case 7:
                        usunRezerwacje();
                        break;
                    case 8:
                        parkingiIRezerwacje.wyswietlParkingi();
                        break;
                    case 9:
                        wyloguj();
                        break;
                }
            }
            catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("bład, proszę podać liczbę w zakresie!");
                scanner.nextLine();
            }
    }
    public void start(){
        klienci.wczytajKlientow();
        parkingiIRezerwacje.wczytajParkingi();
        parkingiIRezerwacje.wczytajRezerwacje();
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
                break;
        }
        }
    }
}
