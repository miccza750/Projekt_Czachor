import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static String[] wszystkieOpcje = {"Dodaj użytkownika","Usuń użytkownika","Zmień dane użytkownika","Wyjście z programu"};
    public static String[] opcjeAdmina = {"Usuń pracownika","Dodaj pracownika","Zmień hasło swoje lub pracownika"};
    public void wyswietl_wybrana_opcje(int a, String nazwa){
        System.out.println("\n");
        System.out.println("=".repeat(25));
        System.out.println("Wybrano opcję nr: "+a+". "+nazwa);
        System.out.println("=".repeat(25));
    }
    public void wyswietl_opcje(String opcje[]){
        System.out.println("=".repeat(25));
        for (int i = 0; i < opcje.length; i++) {
            System.out.println(i+1+". "+opcje[i]);
        }
        System.out.println("=".repeat(25));
    }
    public void panelAdmina(){
        System.out.println("Czy chcesz użyć metod wyłącznie dla admina? (T/N)");
        Scanner scanner = new Scanner(System.in);
        String odpowiedz = scanner.next();
        while(!(odpowiedz.equals("T") || odpowiedz.equals("N"))){
            odpowiedz = scanner.next();
        }
        if(odpowiedz.equals("T")){
            wyswietl_opcje(opcjeAdmina);
        }
    }
    public void start(){
        SesjaLogowania sesja = new SesjaLogowania();
        sesja.login();
        if(sesja.permisja.equals(Permisja.ADMIN)){
            panelAdmina();
        }
        while(true){
            Scanner scanner = new Scanner(System.in);
            wyswietl_opcje(wszystkieOpcje);
            try {
                int wyjscie = scanner.nextInt();
                wyswietl_wybrana_opcje(wyjscie, wszystkieOpcje[wyjscie]);
                switch (wyjscie) {
                    case 1:
                        System.out.println("podaj");
                        break;
                    default:
                        System.out.println("bład");
                }
            }
            catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("bład, proszę podać liczbę w zakresie!");
            }
        }
    }
}
