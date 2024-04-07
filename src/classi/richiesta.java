package classi;
import java.time.LocalDateTime;
public class richiesta {
    private int idUtente;
    private int idGruppo;
    private LocalDateTime dataRichiesta;

    public richiesta(int idUtente, int idGruppo, LocalDateTime dataRichiesta) {
        this.idUtente = idUtente;
        this.idGruppo = idGruppo;
        this.dataRichiesta = dataRichiesta;
    }

    // getters and setters

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public int getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        this.idGruppo = idGruppo;
    }

    public LocalDateTime getDataRichiesta() {
        return dataRichiesta;
    }

    public void setDataRichiesta(LocalDateTime dataRichiesta) {
        this.dataRichiesta = dataRichiesta;
    }
}