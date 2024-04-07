package classi;

public class notifica {
    private int idNotifica;
    private int idUtente;
    private String testo;

    public notifica(int idNotifica, int idUtente, String testo) {
        this.idNotifica = idNotifica;
        this.idUtente = idUtente;
        this.testo = testo;
    }

    public int getIdNotifica() {
        return idNotifica;
    }

    public void setIdNotifica(int idNotifica) {
        this.idNotifica = idNotifica;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
