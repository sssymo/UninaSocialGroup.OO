package classi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class gruppo {
    private String nomeGruppo;
    private int idGruppo;
    private String descrizioneGruppo;
    private LocalDate dataCreazione;
    private List<Tag> tagList;

    public gruppo(String nomeGruppo, int idGruppo, String descrizioneGruppo, LocalDate dataCreazione) {
        this.nomeGruppo = nomeGruppo;
        this.idGruppo = idGruppo;
        this.descrizioneGruppo = descrizioneGruppo;
        this.dataCreazione = dataCreazione;
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

    public int getIdGruppo() {
        return idGruppo;
    }

    public void setIdGruppo(int idGruppo) {
        this.idGruppo = idGruppo;
    }

    public String getDescrizioneGruppo() {
        return descrizioneGruppo;
    }

    public void setDescrizioneGruppo(String descrizioneGruppo) {
        this.descrizioneGruppo = descrizioneGruppo;
    }

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
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

