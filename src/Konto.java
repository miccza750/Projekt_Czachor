public class Konto {
    private String rola;
    private String email;
    private String imie;
    private String nazwisko;
    private String haslo;

    public Konto(String rola, String email, String imie, String nazwisko, String haslo) {
        this.rola = rola;
        this.email = email;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.haslo = haslo;
    }
    public boolean sprawdz_poprawnosc_hasla(String email_sprawdzany, String haslo_sprawdzane){
        return haslo_sprawdzane.equals(haslo) && email_sprawdzany.equals(email);
    }

    public boolean zmienHaslo(String stareHaslo, String noweHaslo) {
        if (stareHaslo.equals(noweHaslo)) {
            this.haslo = noweHaslo;
            return true;
        }
        return false;
    }

    public void ustawHaslo(String nowe_haslo) {
        if(zmienHaslo(haslo,nowe_haslo)){
            System.out.println("Podane hasło jest takie samo jak poprzednie!");
        }else{
            System.out.println("Hasło poprawnie zmienione");
        }
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
