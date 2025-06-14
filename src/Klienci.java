import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Klienci {
    public static ArrayList<Konto> listaKontKlientow = new ArrayList<>();
    public void wczytajKlientow(){
        try {
            File plik = new File("resources\\Klienci.txt");
            Scanner odczyt = new Scanner(plik);
            while (odczyt.hasNextLine()) {
                String linia = odczyt.nextLine();
                if (linia.isEmpty()) continue;
                String[] listaK = linia.split("\\|");
                Konto kontoO = new Konto(listaK[0],listaK[1],listaK[2],listaK[3]);
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
        zapiszDoPliku();
    }
    public void usunKlienta(int id){
        for (Konto konto : listaKontKlientow) {
            if(konto.getId()==id) {
                listaKontKlientow.remove(konto);
            }
        }
        zapiszDoPliku();
    }
    public void zapiszDoPliku() {
        try (FileWriter zapis = new FileWriter("resources\\Klienci.txt")) {
            for (Konto konto : listaKontKlientow) {
                zapis.write(konto.formatDoPliku());
            }
            System.out.println("Zapisano zmiany!");
        } catch (IOException e) {
            System.out.println("Błąd pliku: " + e.getMessage());
        }
    }
    public void wyswietlKlientow(){
        System.out.println("Ilość klientów: " + listaKontKlientow.size());
        for (Konto klient : listaKontKlientow){
            klient.pokazDane();
        }
    }
    public boolean czyWLiscie(int id){
        for (Konto konto : listaKontKlientow){
            if(konto.getId() == id){
                return true;
            }
        }
        return false;
    }
    public void zmianaImienia(int id, String imie){
        for (Konto konto : listaKontKlientow) {
            if(konto.getId()==id) {
                konto.setImie(imie);
            }
        }
        zapiszDoPliku();
    }
    public void zmianaNazwiska(int id, String nazwisko){
        for (Konto konto : listaKontKlientow) {
            if(konto.getId()==id) {
                konto.setImie(nazwisko);
            }
        }
        zapiszDoPliku();
    }
    public void zmianaEmaila(int id, String email){
        for (Konto konto : listaKontKlientow) {
            if(konto.getId()==id) {
                konto.setImie(email);
            }
        }
        zapiszDoPliku();
    }
}
