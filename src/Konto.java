import com.sun.source.tree.ReturnTree;

import java.io.FileWriter;
import java.io.IOException;

public class Konto {
    private int id;
    private String email;
    private String imie;
    private String nazwisko;
    public static int maxId = 0;
    public Konto(String id, String imie, String nazwisko, String email) {
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = Integer.parseInt(id);
        if(this.id>maxId){
            maxId = this.id;
        }
    }
    public Konto(String imie, String nazwisko, String email) {
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = ++maxId;
    }

    public void pokazDane(){
        System.out.println("=".repeat(25));
        System.out.println("id: "+id);
        System.out.println("imie: "+imie);
        System.out.println("nazwisko: "+nazwisko);
        System.out.println("Email: "+email);
        System.out.println("=".repeat(25));
    }
    public String formatDoPliku() {
        return id + "|" + imie + "|" + nazwisko + "|" + email +"\n";
    }
    public int getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Konto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }
}
