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
class UtenteDAO {
    // Metodi per interagire con il database o il sistema di archiviazione dei dati
    public void salvaUtente(Utente utente) {
        // Implementazione per salvare l'utente nel database
    }

    public Utente caricaUtente(int idUtente) {
        // Implementazione per caricare un utente dal database
        return null; // Solo a scopo di esempio
    }

    public void aggiornaUtente(Utente utente) {
        // Implementazione per aggiornare l'utente nel database
    }

    public void eliminaUtente(int idUtente) {
        // Implementazione per eliminare un utente dal database
    }
}
