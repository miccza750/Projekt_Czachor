import java.io.FileWriter;
import java.io.IOException;

public class Konto {
    public static int licznik = 0;
    private int id;
    private String email;
    private String imie;
    private String nazwisko;

    public Konto(String imie, String nazwisko, String email) {
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        licznik++;
        this.id = licznik;
    }
    public void pokazDane(){
        System.out.println("=".repeat(25));
        System.out.println("id: "+id);
        System.out.println("imie: "+imie);
        System.out.println("nazwisko: "+nazwisko);
        System.out.println("Email: "+email);
        System.out.println("=".repeat(25));
    }

    @Override
    public String toString() {
        return id + "|" + imie + "|" + nazwisko + "|" + email + "\n";
    }
}
