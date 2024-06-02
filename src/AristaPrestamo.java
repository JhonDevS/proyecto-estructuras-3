public class AristaPrestamo {
    private NodoCliente cliente;
    private NodoLibro libro;

    public AristaPrestamo(NodoCliente cliente, NodoLibro libro) {
        this.cliente = cliente;
        this.libro = libro;
    }

    public NodoCliente getCliente() {
        return cliente;
    }

    public NodoLibro getLibro() {
        return libro;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AristaPrestamo that = (AristaPrestamo) obj;
        return cliente.equals(that.cliente) && libro.equals(that.libro);
    }

    @Override
    public int hashCode() {
        int result = cliente.hashCode();
        result = 31 * result + libro.hashCode();
        return result;
    }
}
