package classiDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TagDao {
    private static Connection connection;
    public TagDao(Connection connection) {
    	this.connection=connection;
    }
    static String Insert_Tag="INSERT INTO tag (tipologiatag) VALUES (?)";
    static String Insert_In_Possiede="INSERT INTO possiede (idgruppo,tipologia) VALUES (?,?)";
public static boolean InsertTipologiaIfNotExistEAssociaAGruppo(String tag,int id) {
	try (PreparedStatement S1 = connection.prepareStatement(Insert_Tag)) {
		S1.setString(1, tag);
	S1.executeUpdate();

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try (PreparedStatement S2 = connection.prepareStatement(Insert_In_Possiede)) {
		S2.setInt(1,id);
		S2.setString(2,tag);
		S2.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	return false;
}



static String gettags="select tipologia from possiede where idgruppo=? order by tipologia asc";
public static String getTagsForGruppo(int idGruppo) {
	// TODO Auto-generated method stub
	try (PreparedStatement S1 = connection.prepareStatement(gettags)) {
	S1.setInt(1, idGruppo);
	ResultSet rs=S1.executeQuery();
	ArrayList<String> tags=new ArrayList<>();

	while (rs.next()) {
		tags.add(rs.getString(1));
	}
	System.out.println(tags);
	String tagss="";
	for(String t : tags){
	
		tagss=tagss+t+",";
	}
	if(tagss=="") {
		return "";
	}else {
		 tagss = tagss.substring(0, tagss.length() - 1);
		 tagss=tagss+".";
		return tagss;
	}

	

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	return "nessun tag trovato";
}



public static void DeleteTags( int idGruppo) {
	// TODO Auto-generated method stub
	try (PreparedStatement S1 = connection.prepareStatement("DELETE FROM possiede WHERE idgruppo=? ")) {
		S1.setInt(1, idGruppo);
		S1.executeQuery();		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


}