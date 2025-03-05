
package collectiondatabase;

import static collectiondatabase.DBUtil.openConnection;
import static collectiondatabase.MangasDAO.getMangasFromResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AlbumsDAO {
    
      
          //*******************************
    //SELECT  Album
    //*******************************
    
       public static Albums searchAlbum (String musaID, Connection c) throws SQLException, ClassNotFoundException {
        
         try {

        
              Statement stmt = c.createStatement();
        
                stmt.executeQuery("USE Käyttäjänimi tai joku muu tähän");

                String query = "SELECT * FROM Pelimusiikki WHERE MusaID LIKE '%"+musaID+"'";
                PreparedStatement preparedStmt = c.prepareStatement(query);


        ResultSet rsAlbum = DBUtil.dbExecuteQuery(query);
 
        Albums albums = getAlbumsFromResultSet(rsAlbum);

        return albums;
        } catch (SQLException e) {
            System.out.println("Etsiessä " + musaID + " tapahtui virhe: " + e);
           
            throw e;
       
        }
       }
    
       public static Albums getAlbumsFromResultSet(ResultSet rs) throws SQLException
    {
        Albums album = null;
        if (rs.next()) {
            album = new Albums(); 
            album.setAlbumNimi(rs.getString("Nimi"));
            album.setAlbumId(rs.getString("MusaID"));
            album.setAlbumKunto(rs.getString("Kunto"));
            album.setAlbumPreowned(rs.getString("Preowned"));
          
        }
        return album;
    }
     
    
    //*******************************
    //SELECT Albums
    //*******************************
    public static ObservableList<Albums> searchAlbums () throws SQLException, ClassNotFoundException {
       
        Statement stmt = null;
     
        String selectStmt = "SELECT * FROM Pelimusiikki";
        
     
        try {
            Connection con = openConnection("jdbc:mariadb://localhost:SALASANAT JNE");
           stmt = con.createStatement();
        
    
            ResultSet rsAlbum = DBUtil.dbExecuteQuery(selectStmt);

            ObservableList<Albums> albumList = getAlbumsList(rsAlbum);


            return albumList;
        } catch (SQLException e) {
            System.out.println("Albumien hakeminen epäonnistui: " + e);
      
            throw e;
        }
    }    
     
     //Select * from Albumit
    private static ObservableList<Albums> getAlbumsList(ResultSet rs) throws SQLException, ClassNotFoundException {
   
        ObservableList<Albums> albumList = FXCollections.observableArrayList();

        while (rs.next()) {
            Albums album = new Albums();
            album.setAlbumNimi(rs.getString("Nimi"));
            album.setAlbumId(rs.getString("MusaID"));
            album.setAlbumKunto(rs.getString("Kunto"));
            album.setAlbumPreowned(rs.getString("Preowned"));
   
            albumList.add(album);
        }
 
        return albumList;
    }
    
     //*************************************
    //UPDATE albumin nimi
    //*************************************
    public static void updateAlbumNimi (String musaId, String albumNimi, Connection c) throws SQLException, ClassNotFoundException {
   
        try {
            Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE KÄYTTÄJÄNIMI TAI JOKU MUU");

                String query = "UPDATE Pelimusiikki SET Nimi = ? WHERE MusaID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);

                preparedStmt.setString(1, albumNimi);
                preparedStmt.setString(2, musaId);
                preparedStmt.executeQuery();

        } catch (SQLException e) {
            System.out.print("No virhe tuli nimen päivityksessä: " + e);
            throw e;
        }
    }
    
    //*************************************
    //DELETE Albumi
    //*************************************
    public static void deleteAlbumWithId (String musaID, Connection c) throws SQLException, ClassNotFoundException {
       
        try {
        Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE KÄYTTÄJÄNIMI TAI JOKU MUU");

                String query = "DELETE FROM Pelimusiikki WHERE MusaID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);


                preparedStmt.setString(1, musaID);
                preparedStmt.executeQuery();
        
        
      
        } catch (SQLException e) {
            System.out.print("Poisto ei onnistunut koska: " + e);
            throw e;
        }
    }

    //*************************************
    //INSERT Album
    //*************************************
    public static void insertAlbum (String id, String nimi, String kunto, String preowned, Connection c) throws SQLException, ClassNotFoundException {
      
        try {
            Statement stmt = c.createStatement();
            stmt.executeQuery("USE KÄYTTÄJÄNIMI TAI JOKU MUU");
            String query = "INSERT INTO Pelimusiikki (MusaID, Nimi, Kunto, Preowned) " + " VALUES(?, ?, ?, ?)";
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
