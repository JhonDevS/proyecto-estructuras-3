import java.util.HashSet;
import java.util.Set;

public class Biblioteca {
    private Set<NodoLibro> libros;
    private Set<NodoCliente> clientes;
    private Set<AristaPrestamo> prestamos;

    public Biblioteca() {
        libros = new HashSet<>();
        clientes = new HashSet<>();
        prestamos = new HashSet<>();
    }

    public void insertarLibro(NodoLibro libro) {
        libros.add(libro);
    }

    public Set<NodoLibro> verLibros() {
        return libros;
    }

    public void crearCliente(NodoCliente cliente) {
        clientes.add(cliente);
    }


    public Set<NodoCliente> verClientes() {
        return clientes;
    }

    public NodoCliente buscarClientePorId(String idCliente) {
        for (NodoCliente cliente : clientes) {
            if (cliente.getId().equals(idCliente)) {
                return cliente;
            }
        }
        return null;
    }

    public NodoLibro buscarLibroPorIsbn(String isbn) {
        for (NodoLibro libro : libros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        return null;
    }


    public void prestarLibro(NodoCliente cliente, NodoLibro libro) {
        if (clientes.contains(cliente) && libros.contains(libro)) {
            prestamos.add(new AristaPrestamo(cliente, libro));
        } else {
            throw new IllegalArgumentException("Cliente o Libro no existe");
        }
    }

    public void devolverLibro(NodoCliente cliente, NodoLibro libro) {
        prestamos.remove(new AristaPrestamo(cliente, libro));
    }

    public Set<NodoLibro> verLibrosPrestados(NodoCliente cliente) {
        Set<NodoLibro> librosPrestados = new HashSet<>();
        for (AristaPrestamo prestamo : prestamos) {
            if (prestamo.getCliente().equals(cliente)) {
                librosPrestados.add(prestamo.getLibro());
            }
        }
        return librosPrestados;
    }
}