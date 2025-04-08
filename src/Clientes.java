import java.util.Scanner;
import java.util.regex.Pattern;

public class Cliente {
    protected String nombre;
    protected String apellidos;
    protected String dni;
    protected String email;
    protected String emailConfirmado;
    protected byte edad;

    // Constructor sin parámetros para creación interactiva
    public cliente() {
        // Se inicializará con los setters interactivos
    }

    // Constructor con parámetros (con validación)
    public cliente(String nombre, String apellidos, String dni, String email, String emailConfirmado, byte edad) {
        setNombre(nombre);
        setApellidos(apellidos);
        setDni(dni);
        setEmail(email);
        setEmailConfirmado(emailConfirmado);
        setEdad(edad);
    }

    // Métodos para entrada interactiva (lo que necesitas)
    public void pedirDatosInteractivos() {
        Scanner scanner = new Scanner(System.in);

        // Pedir nombre hasta que sea válido
        while (true) {
            System.out.print("Introduce el nombre: ");
            String nombreInput = scanner.nextLine();
            if (nombreInput != null && !nombreInput.trim().isEmpty()) {
                this.nombre = nombreInput;
                break;
            } else {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        }

        // Pedir apellidos hasta que sean válidos
        while (true) {
            System.out.print("Introduce los apellidos: ");
            String apellidosInput = scanner.nextLine();
            if (apellidosInput != null && !apellidosInput.trim().isEmpty()) {
                this.apellidos = apellidosInput;
                break;
            } else {
                System.out.println("Error: Los apellidos no pueden estar vacíos.");
            }
        }

        // Pedir DNI hasta que sea válido
        while (true) {
            System.out.print("Introduce el DNI (8 números y 1 letra): ");
            String dniInput = scanner.nextLine();
            if (validarDNI(dniInput)) {
                this.dni = dniInput;
                break;
            } else {
                System.out.println("Error: DNI no válido. Debe tener 8 números seguidos de una letra válida.");
            }
        }

        // Pedir email hasta que sea válido
        while (true) {
            System.out.print("Introduce el email: ");
            String emailInput = scanner.nextLine();
            if (validarFormatoEmail(emailInput)) {
                this.email = emailInput;
                break;
            } else {
                System.out.println("Error: Email no válido. Debe tener un formato correcto.");
            }
        }

        // Pedir confirmación de email hasta que coincida
        while (true) {
            System.out.print("Confirma el email: ");
            String emailConfirmadoInput = scanner.nextLine();
            if (emailConfirmadoInput.equals(this.email)) {
                this.emailConfirmado = emailConfirmadoInput;
                break;
            } else {
                System.out.println("Error: Los emails no coinciden.");
            }
        }

        // Pedir edad hasta que sea válida
        while (true) {
            try {
                System.out.print("Introduce la edad (18-65): ");
                byte edadInput = Byte.parseByte(scanner.nextLine());
                if (edadInput >= 18 && edadInput <= 65) {
                    this.edad = edadInput;
                    break;
                } else {
                    System.out.println("Error: La edad debe estar entre 18 y 65 años.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes introducir un número válido.");
            }
        }
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

    // Validación de formato de email
    private boolean validarFormatoEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        // Expresión regular para validar email
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    // Métodos de validación del DNI (igual que los tenías)
    public static boolean validarDNI(String dni) {
        if (dni == null) return false;

        // Expresión regular para verificar el formato del DNI
        String regex = "^[0-9]{8}[A-Za-z]$";

        // Verificamos si el DNI coincide con la expresión regular
        if (dni.matches(regex)) {
            // Extraemos el número y la letra del DNI
            String numero = dni.substring(0, 8);
            char letra = dni.charAt(8);

            // Comprobamos que la letra sea correcta según el número
            return validarLetra(numero, letra);
        } else {
            return false;
        }
    }

    private static boolean validarLetra(String numero, char letra) {
        // Tabla de letras según el resto del número dividido por 23
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

        // Convertimos el número a un entero y calculamos el módulo 23
        int num = Integer.parseInt(numero);
        int indice = num % 23;

        // Obtenemos la letra esperada según el módulo
        char letraEsperada = letras.charAt(indice);

        // Comparamos la letra proporcionada con la esperada
        return Character.toUpperCase(letra) == letraEsperada;
    }

    // Getters
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
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", emailConfirmado='" + emailConfirmado + '\'' +
                ", edad=" + edad +
                '}';
    }
}