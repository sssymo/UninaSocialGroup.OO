package classi;
import java.util.ArrayList;
import java.util.List;

public class Utente {
    private String username;
    private int idUtente;
    private String password;
    private String bio;
    private int numPubblicazioni;
    private int numGruppiPartecipanti;
    private List<Recensione> recensioni; // Elenco delle recensioni dell'utente

    // Costruttore
    public Utente(String username, int idUtente, String password, String bio) {
        this.username = username;
        this.idUtente = idUtente;
        this.password = password;
        this.bio = bio;
        this.numPubblicazioni = 0; // Inizialmente l'utente non ha pubblicazioni
        this.numGruppiPartecipanti = 0; // Inizialmente l'utente non partecipa a nessun gruppo
        this.recensioni = new ArrayList<>();
    }
    

    // Metodi getter e setter per gli attributi
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getNumPubblicazioni() {
        return numPubblicazioni;
    }

    public void setNumPubblicazioni(int numPubblicazioni) {
        this.numPubblicazioni = numPubblicazioni;
    }

    public int getNumGruppiPartecipanti() {
        return numGruppiPartecipanti;
    }

    public void setNumGruppiPartecipanti(int numGruppiPartecipanti) {
        this.numGruppiPartecipanti = numGruppiPartecipanti;
    }

    // Metodi per gestire le recensioni
    public void aggiungiRecensione(Recensione recensione) {
        recensioni.add(recensione);
    }

    public void rimuoviRecensione(Recensione recensione) {
        recensioni.remove(recensione);
    }

    // Altri metodi utili per l'utente
}
