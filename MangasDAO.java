
package collectiondatabase;

import static collectiondatabase.DBUtil.openConnection;
import static collectiondatabase.GamesDAO.getGamesFromResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class MangasDAO {
    
          //*******************************
    //SELECT  Manga
    //*******************************
    
       public static Mangas searchManga (String mangaID, Connection c) throws SQLException, ClassNotFoundException {
        
         try {

        
              Statement stmt = c.createStatement();
        
                stmt.executeQuery("USE USERNAME");

                String query = "SELECT * FROM Mangat WHERE MangaID LIKE '%"+mangaID+"'";
                PreparedStatement preparedStmt = c.prepareStatement(query);


        ResultSet rsManga = DBUtil.dbExecuteQuery(query);
 
        Mangas mangas = getMangasFromResultSet(rsManga);

        return mangas;
        } catch (SQLException e) {
            System.out.println("Etsiessä " + mangaID + " tapahtui virhe: " + e);
           
            throw e;
       
        }
       }
       
       public static Mangas getMangasFromResultSet(ResultSet rs) throws SQLException
    {
        Mangas manga = null;
        if (rs.next()) {
            manga = new Mangas(); 
            manga.setMangaNimi(rs.getString("Nimi"));
            manga.setMangaId(rs.getString("MangaID"));
            manga.setMangaKunto(rs.getString("Kunto"));
            manga.setMangaPreowned(rs.getString("Preowned"));
          
        }
        return manga;
    }
     
   //*******************************
    //SELECT Mangat
    //*******************************
    public static ObservableList<Mangas> searchMangas () throws SQLException, ClassNotFoundException {
       
        Statement stmt = null;
     
        String selectStmt = "SELECT * FROM Mangat";
        
     
        try {
            Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
           stmt = con.createStatement();
        
    
            ResultSet rsManga = DBUtil.dbExecuteQuery(selectStmt);

            ObservableList<Mangas> mangaList = getMangasList(rsManga);


            return mangaList;
        } catch (SQLException e) {
            System.out.println("Mangojen hakeminen epäonnistui: " + e);
      
            throw e;
        }
    }    
     
    //Select * from Mangat
    private static ObservableList<Mangas> getMangasList(ResultSet rs) throws SQLException, ClassNotFoundException {
   
        ObservableList<Mangas> mangaList = FXCollections.observableArrayList();

        while (rs.next()) {
            Mangas manga = new Mangas();
            manga.setMangaNimi(rs.getString("Nimi"));
            manga.setMangaId(rs.getString("MangaID"));
            manga.setMangaKunto(rs.getString("Kunto"));
            manga.setMangaPreowned(rs.getString("Preowned"));
   
            mangaList.add(manga);
        }
 
        return mangaList;
    }
    
    //*************************************
    //UPDATE mangan nimi
    //*************************************
    public static void updateMangaNimi (String mangaId, String mangaNimi, Connection c) throws SQLException, ClassNotFoundException {
   
        try {
            Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE USERNAME");

                String query = "UPDATE Mangat SET Nimi = ? WHERE MangaID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);

                preparedStmt.setString(1, mangaNimi);
                preparedStmt.setString(2, mangaId);
                preparedStmt.executeQuery();

        } catch (SQLException e) {
            System.out.print("No virhe tuli nimen päivityksessä: " + e);
            throw e;
        }
    }
    
    //*************************************
    //DELETE Manga
    //*************************************
    public static void deleteMangaWithId (String mangaID, Connection c) throws SQLException, ClassNotFoundException {
       
        try {
        Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE USERNAME");

                String query = "DELETE FROM Mangat WHERE MangaID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);


                preparedStmt.setString(1, mangaID);
                preparedStmt.executeQuery();
        
        
      
        } catch (SQLException e) {
            System.out.print("Poisto ei onnistunut koska: " + e);
            throw e;
        }
    }

    //*************************************
    //INSERT Manga
    //*************************************
    public static void insertManga (String id, String nimi, String kunto, String preowned, Connection c) throws SQLException, ClassNotFoundException {
      
        try {
            Statement stmt = c.createStatement();
            stmt.executeQuery("USE USERNAME");
            String query = "INSERT INTO Mangat (MangaID, Nimi, Kunto, Preowned) " + " VALUES(?, ?, ?, ?)";
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
    
    

