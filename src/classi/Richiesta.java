package classi;

import java.time.LocalDateTime;
public class Richiesta {
    private int idUtente;
    private int idGruppo;
    private LocalDateTime dataRichiesta;
    private LocalDateTime orarichiesta;
    public Richiesta(int idUtente, int idGruppo, LocalDateTime dataRichiesta,LocalDateTime orarichiesta) {
        this.idUtente = idUtente;
        this.idGruppo = idGruppo;
        this.dataRichiesta = dataRichiesta;
        this.orarichiesta=orarichiesta;
    }




	public Richiesta(int int1, int int2) {
		// TODO Auto-generated constructor stub
        this.idUtente = int1;
        this.idGruppo = int2;
	}

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
    public LocalDateTime getorarichiesta() {
        return orarichiesta;
    }

    public void setorarichiesta(LocalDateTime orarichiesta) {
        this.orarichiesta = orarichiesta;
    }
}