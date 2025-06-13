public class Parking {
protected int iloscMiejsc;
protected int godzinaOtwarcia;
protected int godzinaZamkniecia;

    // konstruktor gdy parking ma przedział godzin otwarcia
    public Parking(String iloscMiejsc,String godzinaOtwarcia, String godzinaZamkniecia) {
        this.iloscMiejsc = Integer.valueOf(iloscMiejsc);
        this.godzinaOtwarcia = Integer.valueOf(godzinaOtwarcia);
        this.godzinaZamkniecia = Integer.valueOf(godzinaZamkniecia);
    }

    public boolean czyOtwarty(int godzina){
        return godzina >= godzinaOtwarcia && godzina <= godzinaZamkniecia;
    }

}
