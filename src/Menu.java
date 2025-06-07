import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public void wyswietl_wybrana_opcje(int a, String nazwa){
        System.out.println("\n");
        System.out.println("=".repeat(25));
        System.out.println("Wybrano opcję nr: "+a+". "+nazwa);
        System.out.println("=".repeat(25));
    }
    public void wyswietl_opcje(String opcje[]){
        System.out.println("=".repeat(25));
        for (int i = 0; i < opcje.length; i++) {
            System.out.println(i+". "+opcje[i]);
        }
        System.out.println("=".repeat(25));
    }

    public void start(){
        SesjaLogowania sesja = new SesjaLogowania();
        sesja.login();
        while(true){
            String[] opcje = {"Dodaj użytkownika",""};
            Scanner scanner = new Scanner(System.in);
            wyswietl_opcje(opcje);
            try {
                int wyjscie = scanner.nextInt();
                wyswietl_wybrana_opcje(wyjscie, opcje[wyjscie]);
                switch (wyjscie) {
                    case 1:
                        System.out.println("wyjście z programu.");
                        break;
                    default:
                        System.out.println("bład");
                }

            }catch (InputMismatchException | ArrayIndexOutOfBoundsException e){
                System.out.println("bład, proszę podać liczbę w zakresie!");
            }
        }
    }
}
