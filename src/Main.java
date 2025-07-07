import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenido. Por favor, introduce los datos del cliente:");

        Clientes c = new Clientes();
        c.pedirDatos();

        System.out.println("\nDatos del cliente registrados correctamente:");
        System.out.println(c);


    }
}