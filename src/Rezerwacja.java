public class Rezerwacja {
    public static int maxId = 0;
    public int idRezerwacji;
    public int idParkingu;
    public int idKlienta;
    public int iloscDni;
    public boolean czyEletryczne;
    public static int licznik =0;
    public Rezerwacja(String idRezerwacji,String idParkingu, String idKlienta, String iloscDni, String czyEletryczne) {
        this.idRezerwacji = Integer.parseInt(idRezerwacji);
        if(this.idRezerwacji > maxId){
            maxId = this.idRezerwacji;
        }
        this.idParkingu = Integer.parseInt(idParkingu);
        this.idKlienta = Integer.parseInt(idKlienta);
        this.iloscDni = Integer.parseInt(iloscDni);
        this.czyEletryczne = Boolean.valueOf(czyEletryczne);
    }
    public Rezerwacja(int idParkingu,int idKlienta,int iloscDni, boolean czyEletryczne) {
        this.idRezerwacji = ++maxId;
        this.idParkingu = idParkingu;
        this.idKlienta = idKlienta;
        this.iloscDni = iloscDni;
        this.czyEletryczne = Boolean.valueOf(czyEletryczne);
    }
    public String formatDoZapisu() {
        return
                idRezerwacji + "|" + idParkingu +
                "|" + idKlienta +
                "|" + iloscDni + "|" + czyEletryczne +
                '\n';
    }

    @Override
    public String toString() {
        return
                "\nid Rezerwacji: " + idRezerwacji +
                "\nid Parkingu: " + idParkingu +
                "\nid Klienta: " + idKlienta +
                "\nilość Dni: " + iloscDni + "\n Czy elektryczne: " +czyEletryczne;

    }
}
