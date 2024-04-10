package classi;

import java.sql.Timestamp;

public class notifica {
    private int idNotifica;
    private int idpost;
    private int idUtente;
    private int idGruppo;
    private Timestamp data_notifica;
    private Timestamp orario_notifica;
    private String desc_notifica;
    private String desc;


    public notifica(int idNotifica, int idpost, int idUtente, int idGruppo, Timestamp data_notifica, Timestamp orario_notifica, String desc_notifica) {
        this.idNotifica = idNotifica;
        this.idpost = idpost;
        this.idUtente = idUtente;
        this.idGruppo = idGruppo;
        this.data_notifica = data_notifica;
        this.orario_notifica = orario_notifica;
        this.desc_notifica = desc_notifica;
        
    }

    public int getIdNotifica() {
        return idNotifica;
    }

    public void setIdNotifica(int idNotifica) {
        this.idNotifica = idNotifica;
    }
    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
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

  
    public Timestamp getData_notifica() {
        return data_notifica;
    }

    public void setData_notifica(Timestamp data_notifica) {
        this.data_notifica = data_notifica;
    }


    public Timestamp getOrario_notifica() {
        return orario_notifica;
    }

    public void setOrario_notifica(Timestamp orario_notifica) {
        this.orario_notifica = orario_notifica;
    }

  
    public String getDesc_notifica() {
        return desc_notifica;
    }

    public void setDesc_notifica(String desc_notifica) {
        this.desc_notifica = desc_notifica;
    }

    
}
