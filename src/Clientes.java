import java.util.Scanner;
import java.util.regex.Pattern;

public class Clientes {
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private String emailConfirmado;
    private byte edad;

    public Clientes() {
    }
    public Clientes(String nombre, String apellidos, String dni, String email, String emailConfirmado, byte edad) {
        setNombre(nombre);
        setApellidos(apellidos);
        setDni(dni);
        setEmail(email);
        setEmailConfirmado(emailConfirmado);
        setEdad(edad);
    }

    public void pedirDatos() {
        int op = 0;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("*** 1. Crear Cliente ***");
            System.out.println("*** 2. Salir ***");
            String opcion = sc.next();
            try {
                op = Integer.parseInt(opcion);
                switch (op) {
                    case 1 -> {
                        while (true) {
                            System.out.println("Introduce el Nombre: ");
                            setNombre(sc.next());
                            System.out.println("Introduce los Apellidos: ");
                            setApellidos(sc.next());
                            System.out.println("Introduce la Edad: ");
                            setEdad(sc.nextByte());
                            System.out.println("Introduce el DNI: ");
                            setDni(sc.next());
                            System.out.println("Introduce el Email: ");
                            setEmail(sc.next());
                            System.out.println("Confirme el Email: ");
                            setEmailConfirmado(sc.next());
                        }
                    }
                    case 2 -> {
                        System.out.println("Cerrando aplicación...");
                        break;
                    }
                    default -> System.out.println("Opción no válida");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (op == 2);

    }

    // Setters mejorados
    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            System.out.println("El nombre no puede estar vacío");
        }
    }

    public void setApellidos(String apellidos) {
        if (apellidos != null && !apellidos.trim().isEmpty()) {
            this.apellidos = apellidos;
        } else {
            System.out.println("Los apellidos no pueden estar vacíos");
        }
    }

    public void setDni(String dni) {
        if (validarDNI(dni)) {
            this.dni = dni;
        } else {
            System.out.println("DNI no válido");
        }
    }

    public void setEdad(byte edad) {
        if (edad >= 18 && edad <= 65) {
            this.edad = edad;
        } else {
            System.out.println("Edad inválida. Mínima de 18 y máxima de 65 años");
        }
    }

    public void setEmail(String email) {
        if (validarFormatoEmail(email)) {
            this.email = email;
        } else {
            System.out.println("Formato de email inválido");
        }
    }

    public void setEmailConfirmado(String emailConfirmado) {
        if (this.email != null && emailConfirmado.equals(this.email)) {
            this.emailConfirmado = emailConfirmado;
        } else {
            System.out.println("El email confirmado no coincide con el email");
        }
    }


    private boolean validarFormatoEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    // Métodos de validación del DNI (igual que los tenías)
    public static boolean validarDNI(String dni) {
        if (dni == null) return false;
        String regex = "^[0-9]{8}[A-Za-z]$";

        if (dni.matches(regex)) {
            String numero = dni.substring(0, 8);
            char letra = dni.charAt(8);
            return validarLetra(numero, letra);

        } else {
            return false;
        }
    }

    private static boolean validarLetra(String numero, char letra) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int num = Integer.parseInt(numero);

        int indice = num % 23;
        char letraEsperada = letras.charAt(indice);

        return Character.toUpperCase(letra) == letraEsperada;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailConfirmado() {
        return emailConfirmado;
    }

    public byte getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "Cliente → " +
                "\nNombre: " + nombre +
                "\nApellidos: " + apellidos +
                "\nDni: " + dni +
                "\nEmail: " + email +
                "\nEdad: " + edad + " años";
    }
}