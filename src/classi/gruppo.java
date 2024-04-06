package classi;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class gruppo {
    private String nomeGruppo;
    private String idGruppo;
    private String descrizioneGruppo;
    private Date dataCreazione;
    private List<Tag> tagList;

    public gruppo(String nomeGruppo, String i, String descrizioneGruppo, Date data) {
        this.nomeGruppo = nomeGruppo;
        this.idGruppo = i;
        this.descrizioneGruppo = descrizioneGruppo;
        this.dataCreazione = data;
        this.tagList = new ArrayList<>();
    }

    public void addTag(Tag tag) {
        this.tagList.add(tag);
    }

    // getters and setters for all attributes
    public String getNomeGruppo() {
        return nomeGruppo;
    }

    public void setNomeGruppo(String nomeGruppo) {
        this.nomeGruppo = nomeGruppo;
    }

    public String getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(String idGruppo) {
        this.idGruppo = idGruppo;
    }

    public String getDescrizioneGruppo() {
        return descrizioneGruppo;
    }

    public void setDescrizioneGruppo(String descrizioneGruppo) {
        this.descrizioneGruppo = descrizioneGruppo;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    // toString method to print the group details
    @Override
    public String toString() {
        return "Gruppo{" +
                "nomeGruppo='" + nomeGruppo + '\'' +
                ", idGruppo=" + idGruppo +
                ", descrizioneGruppo='" + descrizioneGruppo + '\'' +
                ", dataCreazione=" + dataCreazione +
                ", tagList=" + tagList +
                '}';
    }
}

