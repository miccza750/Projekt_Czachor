public class Main {
    public static void main(String[] args) {
        Klienci klienci = new Klienci();
        klienci.wczytajKlientow();

        Menu menu = new Menu();
        menu.start();
    }
}

