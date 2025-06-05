import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Kemping extends Parking implements ZarzadzanieParkingiem{
    int miejscaZPradem;
    ArrayList<Konto> lista = new ArrayList<Konto>();
    public Kemping(int ilosc_miejsc, boolean calo_dobowy) {
        super(ilosc_miejsc, calo_dobowy);
    }

    public Kemping(int ilosc_miejsc, boolean calo_dobowy, int miejscaZPradem) {
        super(ilosc_miejsc, calo_dobowy);
        this.miejscaZPradem = miejscaZPradem;

    }

    @Override
    public void dodajMiejsce(String rodzaj) {
        if(rodzaj.equals("elektryczne")){
            miejscaZPradem++;
        }
    }

    @Override
    public void usunMiejsce(int id) {

    }

    @Override
    public void pokazWszystkieMiejsca() {

    }
}
