
package collectiondatabase;

import static collectiondatabase.AlbumsDAO.getAlbumsFromResultSet;
import static collectiondatabase.DBUtil.openConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class FiguresDAO {
    
    //*******************************
    //SELECT  Figure
    //*******************************
    
       public static Figures searchFigure (String figureID, Connection c) throws SQLException, ClassNotFoundException {
        
         try {

        
              Statement stmt = c.createStatement();
        
                stmt.executeQuery("USE USERNAME");

                String query = "SELECT * FROM Figuurit WHERE FiguuriID LIKE '%"+figureID+"'";
                PreparedStatement preparedStmt = c.prepareStatement(query);


        ResultSet rsFigure = DBUtil.dbExecuteQuery(query);
 
        Figures figures = getFiguresFromResultSet(rsFigure);

        return figures;
        } catch (SQLException e) {
            System.out.println("Etsiessä " + figureID + " tapahtui virhe: " + e);
           
            throw e;
       
        }
       }
       
        public static Figures getFiguresFromResultSet(ResultSet rs) throws SQLException
    {
        Figures figure = null;
        if (rs.next()) {
            figure = new Figures(); 
            figure.setFigureNimi(rs.getString("Nimi"));
            figure.setFigureId(rs.getString("FiguuriID"));
            figure.setFigureKunto(rs.getString("Kunto"));
            figure.setFigurePreowned(rs.getString("Preowned"));
          
        }
        return figure;
    }
     
     //*******************************
    //SELECT Figures
    //*******************************
    public static ObservableList<Figures> searchFigures () throws SQLException, ClassNotFoundException {
       
        Statement stmt = null;
     
        String selectStmt = "SELECT * FROM Figuurit";
        
     
        try {
            Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
           stmt = con.createStatement();
        
    
            ResultSet rsFigure = DBUtil.dbExecuteQuery(selectStmt);

            ObservableList<Figures> figureList = getFiguresList(rsFigure);


            return figureList;
        } catch (SQLException e) {
            System.out.println("Figuureitten hakeminen epäonnistui: " + e);
      
            throw e;
        }
    }    
     
      //Select * from Figuurit
    private static ObservableList<Figures> getFiguresList(ResultSet rs) throws SQLException, ClassNotFoundException {
   
        ObservableList<Figures> figureList = FXCollections.observableArrayList();

        while (rs.next()) {
            Figures figure = new Figures();
            figure.setFigureNimi(rs.getString("Nimi"));
            figure.setFigureId(rs.getString("FiguuriID"));
            figure.setFigureKunto(rs.getString("Kunto"));
            figure.setFigurePreowned(rs.getString("Preowned"));
   
            figureList.add(figure);
        }
 
        return figureList;
    }
    
    //*************************************
    //UPDATE figuurin nimi
    //*************************************
    public static void updateFigureNimi (String figureId, String figureNimi, Connection c) throws SQLException, ClassNotFoundException {
   
        try {
            Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE USERNAME");

                String query = "UPDATE Figuurit SET Nimi = ? WHERE FiguuriID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);

                preparedStmt.setString(1, figureNimi);
                preparedStmt.setString(2, figureId);
                preparedStmt.executeQuery();

        } catch (SQLException e) {
            System.out.print("No virhe tuli nimen päivityksessä: " + e);
            throw e;
        }
    }
    
    //*************************************
    //DELETE Figuuri
    //*************************************
    public static void deleteFigureWithId (String figureID, Connection c) throws SQLException, ClassNotFoundException {
       
        try {
        Statement stmt = c.createStatement();
        
        stmt.executeQuery("USE USERNAME");

                String query = "DELETE FROM Figuurit WHERE FiguuriID = ?";
                PreparedStatement preparedStmt = c.prepareStatement(query);


                preparedStmt.setString(1, figureID);
                preparedStmt.executeQuery();
        
        
      
        } catch (SQLException e) {
            System.out.print("Poisto ei onnistunut koska: " + e);
            throw e;
        }
    }
    
    //*************************************
    //INSERT Figuuri
    //*************************************
    public static void insertFigure (String id, String nimi, String kunto, String preowned, Connection c) throws SQLException, ClassNotFoundException {
      
        try {
            Statement stmt = c.createStatement();
            stmt.executeQuery("USE USERNAME");
            String query = "INSERT INTO Figuurit (FiguuriID, Nimi, Kunto, Preowned) " + " VALUES(?, ?, ?, ?)";
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
