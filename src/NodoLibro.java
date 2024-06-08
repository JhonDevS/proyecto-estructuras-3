public class NodoLibro {
    private String isbn;
    private String titulo;
    private String autor;

    public NodoLibro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodoLibro that = (NodoLibro) o;
        return isbn.equals(that.isbn);
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Título: " + titulo + ", Autor: " + autor;
    }
}
