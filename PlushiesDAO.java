
package collectiondatabase;

import static collectiondatabase.DBUtil.openConnection;
import static collectiondatabase.FiguresDAO.getFiguresFromResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class PlushiesDAO {
    
       //*******************************
    //SELECT  Pehmo
    //*******************************
    
       public static Plushies searchPlushie (String plushieID, Connection c) throws SQLException, ClassNotFoundException {
        
         try {

        
              Statement stmt = c.createStatement();
        
                stmt.executeQuery("USE USERNAME");

                String query = "SELECT * FROM Pehmolelut WHERE PehmoID LIKE '%"+plushieID+"'";
                PreparedStatement preparedStmt = c.prepareStatement(query);


        ResultSet rsPlushie = DBUtil.dbExecuteQuery(query);
 
        Plushies plushies = getPlushiesFromResultSet(rsPlushie);

        return plushies;
        } catch (SQLException e) {
            System.out.println("Etsiessä " + plushieID + " tapahtui virhe: " + e);
           
            throw e;
       
        }
       }
            public static Plushies getPlushiesFromResultSet(ResultSet rs) throws SQLException
    {
        Plushies plushie = null;
        if (rs.next()) {
            plushie = new Plushies(); 
            plushie.setPlushieNimi(rs.getString("Nimi"));
            plushie.setPlushieId(rs.getString("PehmoID"));
            plushie.setPlushieKunto(rs.getString("Kunto"));
            plushie.setPlushiePreowned(rs.getString("Preowned"));
          
        }
        return plushie;
    }
    
     //*******************************
    //SELECT Pehmot
    //*******************************
    public static ObservableList<Plushies> searchPlushies () throws SQLException, ClassNotFoundException {
       
        Statement stmt = null;
     
        String selectStmt = "SELECT * FROM Pehmolelut";
        
     
        try {
            Connection con = openConnection("jdbc:mariadb://localhost:USERNAME");
           stmt = con.createStatement();
        
    
            ResultSet rsPlushie = DBUtil.dbExecuteQuery(selectStmt);

            ObservableList<Plushies> plushieList = getPlushiesList(rsPlushie);


            return plushieList;
        } catch (SQLException e) {
            System.out.println("Figuureitten hakeminen epäonnistui: " + e);
      
            throw e;
        }
    } 
    
      //Select * from Pehmolelut
    private static ObservableList<Plushies> getPlushiesList(ResultSet rs) throws SQLException, ClassNotFoundException {
   
        ObservableList<Plushies> plushieList = FXCollections.observableArrayList();

        while (rs.next()) {
            Plushies plushie = new Plushies();
            plushie.setPlushieNimi(rs.getString("Nimi"));
            plushie.setPlushieId(rs.getString("PehmoID"));
            plushie.setPlushieKunto(rs.getString("Kunto"));
            plushie.setPlushiePreowned(rs.getString("Preowned"));
   
            plushieList.add(plushie);
        }
 
        return plushieList;
    }
    
    //*************************************
    //UPDATE pehmon nimi
    //*************************************
    public static void updatePlushieNimi (String plushieId, String plushieNimi, Connection c) throws SQLException, ClassNotFoundException {
   
        try {
            Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE USERNAME");

                String query = "UPDATE Pehmolelut SET Nimi = ? WHERE PehmoID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);

                preparedStmt.setString(1, plushieNimi);
                preparedStmt.setString(2, plushieId);
                preparedStmt.executeQuery();

        } catch (SQLException e) {
            System.out.print("No virhe tuli nimen päivityksessä: " + e);
            throw e;
        }
    }
    //*************************************
    //DELETE Pehmo
    //*************************************
    public static void deletePlushieWithId (String plushieID, Connection c) throws SQLException, ClassNotFoundException {
       
        try {
        Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE USERNAME");

                String query = "DELETE FROM Pehmolelut WHERE PehmoID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);


                preparedStmt.setString(1, plushieID);
                preparedStmt.executeQuery();
        
        
      
        } catch (SQLException e) {
            System.out.print("Poisto ei onnistunut koska: " + e);
            throw e;
        }
    }
       
    //*************************************
    //INSERT Pehmo
    //*************************************
    public static void insertPlushie (String id, String nimi, String kunto, String preowned, Connection c) throws SQLException, ClassNotFoundException {
      
        try {
            Statement stmt = c.createStatement();
            stmt.executeQuery("USE USERNAME");
            String query = "INSERT INTO Pehmolelut (PehmoID, Nimi, Kunto, Preowned) " + " VALUES(?, ?, ?, ?)";
                PreparedStatement preparedStmt = c.prepareStatement(query);

                preparedStmt.setString(1, nimi);
                preparedStmt.setString(2, id);
                preparedStmt.setString(3, preowned);
                preparedStmt.setString(4, kunto);
                preparedStmt.executeQuery();

        } catch (SQLException e) {
            System.out.print("Virhe tapahtui: " + e);
            throw e;
        }
    }
    
}
