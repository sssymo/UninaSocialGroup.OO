package classiDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	try (PreparedStatement S2 = connection.prepareStatement(Insert_In_Possiede)) {
		S2.setInt(1,id);
		S2.setString(2,tag);
		S2.executeUpdate();
	}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

	
	return false;
}
}
