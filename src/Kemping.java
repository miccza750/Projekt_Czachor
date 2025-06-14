import java.sql.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Kemping extends Parking {
    int miejscaZElektryka;

    public Kemping(String idParkingu, String nazwa, String iloscMiejsc, String miejscaZElektryka, String cenaZaDzien) {
        super(idParkingu,nazwa, iloscMiejsc,cenaZaDzien);
        this.miejscaZElektryka = Integer.parseInt(miejscaZElektryka);
    }
    public String formatDoPliku() {
        return idParkingu+"|K|"+nazwa+"|"+iloscMiejsc+"|"+miejscaZElektryka+"\n";
    }

    @Override
    public String toString() {
        return
                "Id Parkingu: " + idParkingu + ", Nazwa: " + nazwa +
                ", Ilość Miejsc: " + iloscMiejsc +
                ", Miejsca Z Elektryką: " + miejscaZElektryka +", cena za dzień: " +cenaZaDzien;
    }

    public int getMiejscaZElektryka() {
        return miejscaZElektryka;
    }
}
