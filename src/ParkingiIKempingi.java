import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParkingiIKempingi {

    public static ArrayList<Parking> listaParkingow = new ArrayList<>();
    public static ArrayList<Kemping> listaKempiongow = new ArrayList<>();
    public static ArrayList<Rezerwacja> listaRezerwacji = new ArrayList<>();
    public void wczytajParkingi() {
        try {
            File plik = new File("resources\\Parkingi.txt");
            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                String linia = odczyt.nextLine();
                String[] listaP;
                listaP = linia.split("\\|");
                if (listaP[1].equals("P")) {
                    Parking parking = new Parking(listaP[0], listaP[2], listaP[3], listaP[4]);
                    listaParkingow.add(parking);
                }
                if (listaP[1].equals("K")) {
                    Kemping kemping = new Kemping(listaP[0], listaP[2], listaP[3], listaP[4], listaP[5]);
                    listaKempiongow.add(kemping);
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("błąd pliku Parkingi.txt");
            e.printStackTrace();
        }
    }
    public void wczytajRezerwacje() {
        try {
            File plik = new File("resources\\Rezerwacje.txt");
            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                String linia = odczyt.nextLine();
                String[] listaP;
                listaP = linia.split("\\|");
                Rezerwacja rezerwacja = new Rezerwacja(listaP[0], listaP[1], listaP[2], listaP[3],listaP[4]);
                listaRezerwacji.add(rezerwacja);
            }
        } catch (FileNotFoundException e) {
            System.out.println("błąd pliku Rezerwacje.txt");
            e.printStackTrace();
        }
    }
    public void wyswietlParkingi() {
        System.out.println("Parkingi: ");
        for (Parking parking : listaParkingow) {
            System.out.println(parking.toString());
        }
        System.out.println("Kempingi: ");
        for (Kemping Kemping : listaKempiongow) {
            System.out.println(Kemping.toString());
        }
    }
    public void wyswietlRezerwacje() {
        int licznik = 1;
        for (Rezerwacja rezerwacja : listaRezerwacji) {
            System.out.println("\nRezerwacja nr: " + licznik);
            for (Parking parking : listaParkingow) {
                if (rezerwacja.idRezerwacji == parking.getIdParkingu()) {
                    System.out.println("nazwa parkingu: " + parking.getNazwa() + rezerwacja.toString()+"\n");
                }
            }
        }
    }
    public void usunRezerwacje(int id) {
        for (int i = listaRezerwacji.size() - 1; i >= 0; i--) {
            if (listaRezerwacji.get(i).idRezerwacji == id) {
                listaRezerwacji.remove(listaRezerwacji.get(id-1));
                System.out.println("Poprawnie usunięto");
            }
        }
        zapiszRezerwacje();
    }
    public void dodajRezerwacje(Rezerwacja rezerwacja) {
        listaRezerwacji.add(rezerwacja);
        System.out.println("Dodano poprawnie");
        zapiszRezerwacje();
    }
    public void zapiszRezerwacje() {
        try (FileWriter zapis = new FileWriter("resources\\Rezerwacje.txt")) {
            for (Rezerwacja rezerwacja : listaRezerwacji) {
                zapis.write(rezerwacja.formatDoZapisu());
            }
            System.out.println("Zapisano zmiany!");
        } catch (IOException e) {
            System.out.println("Błąd pliku: " + e.getMessage());
        }
    }
    public boolean czyWLiscie(int id) {
        for (Parking parking : listaParkingow) {
            if (parking.getIdParkingu() == id) {
                return true;
            }
        }
        for (Kemping kemping : listaKempiongow) {
            if (kemping.getIdParkingu() == id) {
                return true;
            }
        }
        return false;
    }
    public boolean czyJestMiejsceElektryczne(int id) {
        int licznik = 0;
        for (Rezerwacja rezerwacja : listaRezerwacji) {
            for (Parking parking : listaKempiongow){
            if (rezerwacja.czyEletryczne && parking.getIdParkingu() == id) {
                licznik++;
                System.out.println("r");
            }
            }
        }
        for (Kemping kemping : listaKempiongow) {
            return kemping.getMiejscaZElektryka() >= licznik;
        }
        return false;
    }
    public boolean czyJestMiejsce(int id) {
        int licznik = 0;
        for (Rezerwacja rezerwacja : listaRezerwacji) {
            if (rezerwacja.idParkingu == id) {
                licznik++;
            }
        }
        for (Parking parking : listaParkingow) {
            if(parking.getIdParkingu()==id) {
                return parking.getIloscMiejsc() >= licznik;
            }
        }
        for (Kemping kemping : listaKempiongow) {
            if(kemping.getIdParkingu()==id) {
                return kemping.getIloscMiejsc() >= licznik;
            }
        }
        return false;
    }
    public int policzKosztRezerwacji(int iloscDni, boolean czyElektryczne){
        for(Parking parking : listaParkingow) {
            return iloscDni * parking.cenaZaDzien;
        }
        for(Kemping kemping : listaKempiongow) {
            if(czyElektryczne) {
                return iloscDni * (kemping.cenaZaDzien + 100);
            }
            return iloscDni * kemping.cenaZaDzien;
        }
        return 0;
    }
    public boolean czyKemping(int id){
        for (Kemping kemping : listaKempiongow){
            if (kemping.getIdParkingu() == id){
                return true;
            }
        }
        return false;
    }
}

