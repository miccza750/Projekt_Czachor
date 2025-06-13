import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Klienci {
    public static ArrayList<Konto> listaKontKlientow = new ArrayList<>();
    public static ArrayList<Parking> listaParkingow = new ArrayList<>();
    public void wczytajKlientow(){
        try {
            File plik = new File("resources\\Klienci.txt");
            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                String linia = odczyt.nextLine();
                Konto kontoO = new Konto(linia.split("\\|")[0],linia.split("\\|")[1],linia.split("\\|")[2]);
                listaKontKlientow.add(kontoO);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("błąd pliku Klienci.txt");
            e.printStackTrace();
        }
    }
    public void dodajKlienta(String imie, String nazwisko, String email){
        Konto kontoDoDodania = new Konto(imie, nazwisko, email);
        listaKontKlientow.add(kontoDoDodania);
    }
    public void zapiszKlientow(){
        try (FileWriter zapis = new FileWriter("resources\\Konta.txt")) {
            for (Konto konto : listaKontKlientow) {
                zapis.write(konto.toString());
            }
            System.out.println("Zapisano zmiany!");
        } catch (IOException e) {
            System.out.println("Błąd pliku: " + e.getMessage());
        }
    }
    public void wyswietlKlientow(){
        for (Konto klient : listaKontKlientow){
            System.out.println(klient.toString());
        }
    }
    public void wczytajParkingi(){
        try {
            File plik = new File("resources\\Parkingi.txt");
            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                String linia = odczyt.nextLine();
                Parking parking = new Parking(linia.split("\\|")[0],linia.split("\\|")[1],linia.split("\\|")[2]);
                listaParkingow.add(parking);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("błąd pliku Parkingi.txt");
            e.printStackTrace();
        }
    }
}
