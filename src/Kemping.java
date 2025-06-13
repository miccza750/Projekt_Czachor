import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Kemping extends Parking implements ZarzadzanieParkingiem{
    public Kemping(int ilosc_miejsc, boolean calo_dobowy) {
        super(ilosc_miejsc, calo_dobowy);
    }

    @Override
    public void dodajMiejsce(String rodzaj) {

    }

    @Override
    public void usunMiejsce(int id) {

    }

    @Override
    public void pokazWszystkieMiejsca() {

    }
}
