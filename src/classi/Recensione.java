package classi;
import java.util.ArrayList;
import java.util.List;

public class Recensione {
    private int idRecensione;
    private String testo;

    // Costruttore, getter e setter
    public Recensione(int idRecensione, String testo) {
        this.idRecensione = idRecensione;
        this.testo = testo;
    }

    public int getIdRecensione() {
        return idRecensione;
    }

    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}

    // Classe per l'accesso ai dati degli utenti
	