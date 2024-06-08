import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

public class Main extends JFrame {
    private Biblioteca biblioteca;

    public Main() {
        biblioteca = new Biblioteca();

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

        // Configuración de la ventana principal
        setTitle("Biblioteca");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Crear botones para el menú
        JButton btnIngresarLibro = new JButton("Ingresar un libro");
        JButton btnIngresarUsuario = new JButton("Ingresar un usuario");
        JButton btnHacerPrestamo = new JButton("Hacer un préstamo");
        JButton btnHacerDevolucion = new JButton("Hacer una devolución");
        JButton btnVerLibrosPrestados = new JButton("Ver los libros prestados por un cliente");
        JButton btnSalir = new JButton("Salir");

        // Añadir acción a los botones
        btnIngresarLibro.addActionListener(e -> ingresarLibro());
        btnIngresarUsuario.addActionListener(e -> ingresarUsuario());
        btnHacerPrestamo.addActionListener(e -> hacerPrestamo());
        btnHacerDevolucion.addActionListener(e -> hacerDevolucion());
        btnVerLibrosPrestados.addActionListener(e -> verLibrosPrestados());
        btnSalir.addActionListener(e -> System.exit(0));

        // Añadir botones al panel
        panel.add(btnIngresarLibro);
        panel.add(btnIngresarUsuario);
        panel.add(btnHacerPrestamo);
        panel.add(btnHacerDevolucion);
        panel.add(btnVerLibrosPrestados);
        panel.add(btnSalir);

        // Añadir panel a la ventana
        add(panel);
    }

    private void ingresarLibro() {
        JTextField isbnField = new JTextField();
        JTextField tituloField = new JTextField();
        JTextField autorField = new JTextField();
        Object[] message = {
                "ISBN:", isbnField,
                "Título:", tituloField,
                "Autor:", autorField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Ingresar Libro", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String isbn = isbnField.getText();
            String titulo = tituloField.getText();
            String autor = autorField.getText();
            NodoLibro nuevoLibro = new NodoLibro(isbn, titulo, autor);
            biblioteca.insertarLibro(nuevoLibro);
            JOptionPane.showMessageDialog(this, "Libro ingresado correctamente.");
        }
    }

    private void ingresarUsuario() {
        JTextField idField = new JTextField();
        JTextField nombreField = new JTextField();
        Object[] message = {
                "ID:", idField,
                "Nombre:", nombreField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Ingresar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText();
            String nombre = nombreField.getText();
            NodoCliente nuevoCliente = new NodoCliente(id, nombre);
            biblioteca.crearCliente(nuevoCliente);
            JOptionPane.showMessageDialog(this, "Usuario ingresado correctamente.");
        }
    }

    private void hacerPrestamo() {
        JTextField idField = new JTextField();
        JTextField isbnField = new JTextField();
        Object[] message = {
                "ID Usuario:", idField,
                "ISBN Libro:", isbnField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Hacer Préstamo", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String idCliente = idField.getText();
            String isbn = isbnField.getText();
            NodoCliente cliente = biblioteca.buscarClientePorId(idCliente);
            NodoLibro libro = biblioteca.buscarLibroPorIsbn(isbn);
            if (cliente != null && libro != null) {
                biblioteca.prestarLibro(cliente, libro);
                JOptionPane.showMessageDialog(this, "Préstamo realizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Cliente o libro no encontrado.");
            }
        }
    }

    private void hacerDevolucion() {
        JTextField idField = new JTextField();
        JTextField isbnField = new JTextField();
        Object[] message = {
                "ID Usuario:", idField,
                "ISBN Libro:", isbnField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Hacer Devolución", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String idCliente = idField.getText();
            String isbn = isbnField.getText();
            NodoCliente cliente = biblioteca.buscarClientePorId(idCliente);
            NodoLibro libro = biblioteca.buscarLibroPorIsbn(isbn);
            if (cliente != null && libro != null) {
                biblioteca.devolverLibro(cliente, libro);
                JOptionPane.showMessageDialog(this, "Devolución realizada correctamente.");
            } else {
                JOptionPane.showMessageDialog(this, "Cliente o libro no encontrado.");
            }
        }
    }

    private void verLibrosPrestados() {
        String idCliente = JOptionPane.showInputDialog(this, "Ingrese ID del usuario:");
        NodoCliente cliente = biblioteca.buscarClientePorId(idCliente);
        if (cliente != null) {
            Set<NodoLibro> librosPrestados = biblioteca.verLibrosPrestados(cliente);
            if (librosPrestados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El cliente no tiene libros prestados.");
            } else {
                StringBuilder mensaje = new StringBuilder("Libros prestados por el cliente:\n");
                for (NodoLibro libro : librosPrestados) {
                    mensaje.append(libro).append("\n");
                }
                JOptionPane.showMessageDialog(this, mensaje.toString());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
}
