import java.io.FileWriter;
import java.io.IOException;

public class Konto {
    private String rola;
    private String email;
    private String imie;
    private String nazwisko;

    public Konto(String rola, String email, String imie, String nazwisko) {
        this.rola = rola;
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
    public void pokazDane(){
        System.out.println("=".repeat(25));
        System.out.println("Email: "+email);
        System.out.println("imie: "+imie);
        System.out.println("nazwisko: "+nazwisko);
        System.out.println("rola: "+rola);
        System.out.println("=".repeat(25));
    }
}
