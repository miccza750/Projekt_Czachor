public class Parking {
public int idParkingu;
protected int iloscMiejsc;
protected String nazwa;
protected int cenaZaDzien;
    // konstruktor gdy parking ma przedział godzin otwarcia
    public Parking(String idParkingu, String nazwa, String iloscMiejsc, String cenaZaDzien) {
        this.idParkingu = Integer.parseInt(idParkingu);
        this.nazwa = nazwa;
        this.iloscMiejsc = Integer.parseInt(iloscMiejsc);
        this.cenaZaDzien = Integer.parseInt(cenaZaDzien);
    }
    public int getIdParkingu() {
        return idParkingu;
    }
    public int getIloscMiejsc() {
        return iloscMiejsc;
    }
    public String getNazwa() {
        return nazwa;
    }
    @Override
    public String toString() {
        return
                "id Parkingu: " + idParkingu + ", nazwa: " + nazwa +
                ", Ilosc Miejsc: " + iloscMiejsc;
    }
}
