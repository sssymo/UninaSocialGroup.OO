package classi;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gruppo {
    private String nomeGruppo;
    private int idGruppo;
    private String descrizioneGruppo;
    private Date dataCreazione;
    private List<Tag> tagList;


    public Gruppo(String nomeGruppo, int i, String descrizioneGruppo, Date data,List<Tag> tagList) {
        this.nomeGruppo = nomeGruppo;
        this.idGruppo = i;
        this.descrizioneGruppo = descrizioneGruppo;
        this.dataCreazione = data;
        this.tagList = tagList;
    }
  //costruttore senza tag, per i casi in cui non serve visualizzare i tags (ad esempio nella reportinterface)
    public Gruppo(int int1, String string, Date date, String string2) {
		// TODO Auto-generated constructor stub
        this.nomeGruppo = string;
        this.idGruppo = int1;
        this.descrizioneGruppo = string2;
        this.dataCreazione = date;
	}

	public void addTag(Tag tag) {
        this.tagList.add(tag);
    }

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


    @Override
    public String toString() {

        return nomeGruppo ;
    }
}

