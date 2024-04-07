package classi;
import java.util.ArrayList;
import java.util.List;

public class Recensione {
    private int idRecensione;
    private int idUtente;
    private int idPost;
    private String testo;

    // Costruttore, getter e setter
    public Recensione(int idRecensione, int idUtente, int idPost, String testo) {
        this.idRecensione = idRecensione;
        this.idUtente = idUtente;
        this.idPost = idPost;
        this.testo = testo;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}