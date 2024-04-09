package classi;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Post {
	public Post(int idpost2, int iduutente, int idgruppo2, String desc2, Date data_pubblicazione2,
			Time ora_pubblicazione) {
		// TODO Auto-generated constructor stub
		this.data_pubblicazione=data_pubblicazione2;
		this.desc=desc2;
		this.idutente=iduutente;
		this.idgruppo=idgruppo2;
		this.idpost=idpost2;
		this.orario_pubblicazione=ora_pubblicazione;
		
	}
	private int idpost;
	private int idutente;
	private int idgruppo;
	private String desc;
	private Date data_pubblicazione;
private Time orario_pubblicazione;
private Byte foto;


public int getIdpost() {
    return idpost;
}

public void setIdpost(int idpost) {
    this.idpost = idpost;
}

public int getIdutente() {
    return idutente;
}

public void setIdutente(int idutente) {
    this.idutente = idutente;
}

public int getIdgruppo() {
    return idgruppo;
}

public void setIdgruppo(int idgruppo) {
    this.idgruppo = idgruppo;
}

public String getDesc() {
    return desc;
}

public void setDesc(String desc) {
    this.desc = desc;
}

public Date getData_pubblicazione() {
    return data_pubblicazione;
}

public void setData_pubblicazione(Date data_pubblicazione) {
    this.data_pubblicazione = data_pubblicazione;
}

public Time getOrario_pubblicazione() {
    return orario_pubblicazione;
}

public void setOrario_pubblicazione(Time orario_pubblicazione) {
    this.orario_pubblicazione = orario_pubblicazione;
}

public Byte getFoto() {
    return foto;
}

public void setFoto(Byte foto) {
    this.foto = foto;
}

}
