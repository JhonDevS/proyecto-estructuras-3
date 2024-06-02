import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        // Libros por defecto
        NodoLibro libro1 = new NodoLibro("123456789", "El Quijote", "Miguel de Cervantes");
        NodoLibro libro2 = new NodoLibro("987654321", "Cien Años de Soledad", "Gabriel García Márquez");

        biblioteca.insertarLibro(libro1);
        biblioteca.insertarLibro(libro2);

        // Clientes por defecto
        NodoCliente cliente1 = new NodoCliente("1", "Juan Perez");
        NodoCliente cliente2 = new NodoCliente("2", "Maria Gomez");

        biblioteca.crearCliente(cliente1);
        biblioteca.crearCliente(cliente2);

        // Menú
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menú:");
            System.out.println("1. Ingresar un libro");
            System.out.println("2. Ingresar un usuario");
            System.out.println("3. Hacer un préstamo");
            System.out.println("4. Hacer una devolución");
            System.out.println("5. Ver los libros prestados por un cliente");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese ISBN del libro: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Ingrese título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese autor del libro: ");
                    String autor = scanner.nextLine();
                    NodoLibro nuevoLibro = new NodoLibro(isbn, titulo, autor);
                    biblioteca.insertarLibro(nuevoLibro);
                    System.out.println("Libro ingresado correctamente.");
                    break;

                case 2:
                    System.out.print("Ingrese ID del usuario: ");
                    String idCliente = scanner.nextLine();
                    System.out.print("Ingrese nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    NodoCliente nuevoCliente = new NodoCliente(idCliente, nombre);
                    biblioteca.crearCliente(nuevoCliente);
                    System.out.println("Usuario ingresado correctamente.");
                    break;

                case 3:
                    System.out.print("Ingrese ID del usuario: ");
                    String idClientePrestamo = scanner.nextLine();
                    System.out.print("Ingrese ISBN del libro: ");
                    String isbnPrestamo = scanner.nextLine();
                    NodoCliente clientePrestamo = biblioteca.buscarClientePorId(idClientePrestamo);
                    NodoLibro libroPrestamo = biblioteca.buscarLibroPorIsbn(isbnPrestamo);
                    if (clientePrestamo != null && libroPrestamo != null) {
                        biblioteca.prestarLibro(clientePrestamo, libroPrestamo);
                        System.out.println("Préstamo realizado correctamente.");
                    } else {
                        System.out.println("Cliente o libro no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese ID del usuario: ");
                    String idClienteDevolucion = scanner.nextLine();
                    System.out.print("Ingrese ISBN del libro: ");
                    String isbnDevolucion = scanner.nextLine();
                    NodoCliente clienteDevolucion = biblioteca.buscarClientePorId(idClienteDevolucion);
                    NodoLibro libroDevolucion = biblioteca.buscarLibroPorIsbn(isbnDevolucion);
                    if (clienteDevolucion != null && libroDevolucion != null) {
                        biblioteca.devolverLibro(clienteDevolucion, libroDevolucion);
                        System.out.println("Devolución realizada correctamente.");
                    } else {
                        System.out.println("Cliente o libro no encontrado.");
                    }
                    break;

                case 5:
                    System.out.print("Ingrese ID del usuario: ");
                    String idClienteConsulta = scanner.nextLine();
                    NodoCliente clienteConsulta = biblioteca.buscarClientePorId(idClienteConsulta);
                    if (clienteConsulta != null) {
                        Set<NodoLibro> librosPrestados = biblioteca.verLibrosPrestados(clienteConsulta);
                        if (librosPrestados.isEmpty()) {
                            System.out.println("El cliente no tiene libros prestados.");
                        } else {
                            System.out.println("Libros prestados por el cliente:");
                            for (NodoLibro libro : librosPrestados) {
                                System.out.println(libro);
                            }
                        }
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
}