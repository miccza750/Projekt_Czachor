public class Parking {
protected int ilosc_miejsc;
protected boolean calo_dobowy;
protected int godzina_otwarcia;
protected int godzina_zamkniecia;
    // konstruktor gdy parking jest całodobowy
    public Parking(int ilosc_miejsc, boolean calo_dobowy) {
        this.ilosc_miejsc = ilosc_miejsc;
        this.calo_dobowy = calo_dobowy;
    }

    // konstruktor gdy parking ma przedział godzin otwarcia
    public Parking(int ilosc_miejsc, boolean calo_dobowy, int godzina_otwarcia, int godzina_zamkniecia) {
        this.ilosc_miejsc = ilosc_miejsc;
        this.calo_dobowy = calo_dobowy;
        this.godzina_otwarcia = godzina_otwarcia;
        this.godzina_zamkniecia = godzina_zamkniecia;
    }

    public boolean czyOtwarty(int godzina){
        if(calo_dobowy){
            return true;
        }
        return godzina >= godzina_otwarcia && godzina <= godzina_zamkniecia;
    }

}
