
package collectiondatabase;
import static collectiondatabase.DBUtil.closeConnection;
import static collectiondatabase.DBUtil.openConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;


public class GamesDAO {
    
        //*******************************
    //SELECT  Game
    //*******************************
    
       public static Games searchGame (String peliID, Connection c) throws SQLException, ClassNotFoundException {
        
         try {

        
              Statement stmt = c.createStatement();
        
                stmt.executeQuery("USE USERNAME");

                String query = "SELECT * FROM Pelit WHERE PeliID LIKE '%"+peliID+"'";
                PreparedStatement preparedStmt = c.prepareStatement(query);

              
              
        //Haetaan ResultSet  dbExecuteQuery metodista
        ResultSet rsGame = DBUtil.dbExecuteQuery(query);
        //Lähetetään ResultSet getEmployeeFromResultSet metodiin ja haetaan games objekti
        Games games = getGamesFromResultSet(rsGame);
        //Palautetaan games objekti
        return games;
        } catch (SQLException e) {
            System.out.println("Etsiessä " + peliID + " tapahtui virhe: " + e);
           
            throw e;
       
    }
}

//Käytetään ResultSet tietokannasta parametrinä ja asetetaan Games objektin attribuutit ja palautetaan Games objekti.
    public static Games getGamesFromResultSet(ResultSet rs) throws SQLException
    {
        Games game = null;
        if (rs.next()) {
            game = new Games();
            game.setJulkvuosi(rs.getInt("Julkvuosi")); 
            game.setPeliNimi(rs.getString("Nimi"));
            game.setPeliId(rs.getString("PeliID"));
            game.setPeliKunto(rs.getString("Kunto"));
            game.setPeliPreowned(rs.getString("Preowned"));
          
        }
        return game;
    }

 //*******************************
    //SELECT Pelit
    //*******************************
    public static ObservableList<Games> searchGames () throws SQLException, ClassNotFoundException {
       
        Statement stmt = null;
     
        String selectStmt = "SELECT * FROM Pelit";
        
     
        try {
            Connection con = openConnection("jdbc:mariadb://localhostUSERNAMEPASSWORD");
           stmt = con.createStatement();
        
            //Haetaan ResultSet dbExecuteQuery metodista
            ResultSet rsGame = DBUtil.dbExecuteQuery(selectStmt);

            //Lähetetään ResultSet  getGamesList:n metodi ja hae game objekti
            ObservableList<Games> gameList = getGamesList(rsGame);

            //Palautetaan game objekti
            return gameList;
        } catch (SQLException e) {
            System.out.println("Pelien hakeminen epäonnistui: " + e);
      
            throw e;
        }
    }

    //Select * from Pelit
    private static ObservableList<Games> getGamesList(ResultSet rs) throws SQLException, ClassNotFoundException {
        // Julkaistaan observalist mikä sisältää Pelit objektit
        ObservableList<Games> gameList = FXCollections.observableArrayList();

        while (rs.next()) {
            Games game = new Games();
            game.setJulkvuosi(rs.getInt("Julkvuosi"));
            game.setPeliNimi(rs.getString("Nimi"));
            game.setPeliId(rs.getString("PeliID"));
            game.setPeliKunto(rs.getString("Kunto"));
            game.setPeliPreowned(rs.getString("Preowned"));
            //Lisätään peli ObservableListaan
            gameList.add(game);
        }
        //palautetaan gameList (Games ObservableLista)
        return gameList;
    }

//*************************************
    //UPDATE Pelin nimi
    //*************************************
    public static void updateGameNimi (String peliId, String peliNimi, Connection c) throws SQLException, ClassNotFoundException {
   
        try {
            Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE USERNAME");

                String query = "UPDATE Pelit SET Nimi = ? WHERE PeliID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);

                preparedStmt.setString(1, peliNimi);
                preparedStmt.setString(2, peliId);
                preparedStmt.executeQuery();

        } catch (SQLException e) {
            System.out.print("No virhe tuli nimen päivityksessä: " + e);
            throw e;
        }
    }

//*************************************
    //DELETE Peli
    //*************************************
    public static void deleteGameWithId (String peliID, Connection c) throws SQLException, ClassNotFoundException {
       
        try {
        Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE USERNAME");

                String query = "DELETE FROM Pelit WHERE PeliID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);


                preparedStmt.setString(1, peliID);
                preparedStmt.executeQuery();
        
        
      
        } catch (SQLException e) {
            System.out.print("Poisto ei onnistunut koska: " + e);
            throw e;
        }
    }

    //*************************************
    //INSERT Peli
    //*************************************
    public static void insertGame (String id, String nimi, String julkvuosi, String kunto, String preowned, Connection c) throws SQLException, ClassNotFoundException {
      
        try {
            Statement stmt = c.createStatement();
            stmt.executeQuery("USE USERNAME");
            String query = "INSERT INTO Pelit (PeliID, Nimi, Julkvuosi, Kunto, Preowned) " + " VALUES(?, ?, ?, ?, ?)";
                PreparedStatement preparedStmt = c.prepareStatement(query);

                preparedStmt.setString(1, nimi);
                preparedStmt.setString(2, id);
                preparedStmt.setString(3, preowned);
                preparedStmt.setString(4, kunto);
                preparedStmt.setString(5, julkvuosi);
                preparedStmt.executeQuery();

        } catch (SQLException e) {
            System.out.print("Virhe tapahtui: " + e);
            throw e;
        }
    }
}
