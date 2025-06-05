public class Main {
    public static void main(String[] args) {
        Konto admin = new Konto("admin","admin@gmai.com","admin","admin","admin");
        admin.ustawHaslo("admin1");
        admin.pokazDane();
        Parking park = new Parking(13,true);
        System.out.println(park.czyOtwarty(13));
        Menu menu = new Menu();
        menu.start();
    }
}

