
package collectiondatabase;

import com.sun.rowset.CachedRowSetImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DBUtil {
    public static Connection openConnection(String connString) throws SQLException{
        Connection con = DriverManager.getConnection(connString);
        System.out.println("\t>> Yhteys ok");
        
        return con;
    }
    
      public static void closeConnection(Connection c) throws SQLException{
        if(c != null){
            c.close();
        }
        System.out.println("\t>> Tietokantayhteys suljettu");

    }
      
      public static void createDatabase(Connection c, String kokoelma) throws SQLException{
        
        Statement stmt = c.createStatement();
        stmt.executeQuery("DROP DATABASE IF EXISTS " + kokoelma);
        
        System.out.println("\t>> Tietokanta " + kokoelma + " tuhottu");
        
        stmt.executeQuery("CREATE DATABASE " + kokoelma);
        
        System.out.println("\t>> Tietokanta " + kokoelma + " luotu");
        
        stmt.executeQuery("USE " + kokoelma);
        
        System.out.println("\t>> Käytetään tietokantaa " + kokoelma);
        
        stmt.executeUpdate("CREATE TABLE Pelit (" + "PeliID VARCHAR(30)," + "Nimi VARCHAR(100)," + "Julkvuosi INT," + "Kunto VARCHAR(20)," + "Preowned VARCHAR(10)," + "PRIMARY KEY (PeliID))");
        stmt.executeUpdate("CREATE TABLE Mangat (" + "MangaID VARCHAR(30)," + "Nimi VARCHAR(100)," + "Kunto VARCHAR(20)," + "Preowned VARCHAR(10)," + "PRIMARY KEY (MangaID))");
        stmt.executeUpdate("CREATE TABLE Pehmolelut (" + "PehmoID VARCHAR(30)," + "Nimi VARCHAR(100)," + "Kunto VARCHAR(20)," + "Preowned VARCHAR(10)," + "PRIMARY KEY (PehmoID))");
        stmt.executeUpdate("CREATE TABLE Figuurit (" + "FiguuriID VARCHAR(30)," + "Nimi VARCHAR(100)," + "Kunto VARCHAR(20)," + "Preowned VARCHAR(10)," + "PRIMARY KEY (FiguuriID))");
        stmt.executeUpdate("CREATE TABLE Pelimusiikki (" + "MusaID VARCHAR(30)," + "Nimi VARCHAR(100)," + "Kunto VARCHAR(20)," + "Preowned VARCHAR(10)," + "PRIMARY KEY (MusaID))");
        
       
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Final Fantasy\",\"Final Fantasy VII Remake\",2020,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Blazblue\",\"BlazBlue: Calamity Trigger\",2008,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Chrono Cross\",\"Chrono Cross\",1999,\"Kehno\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Silicon Valley\",\"Space Station Silicon Valley\",1998,\"Kohtalainen\",\"KYLLÄ\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Mario\",\"Super Mario World\",1990,\"Kehno\",\"KYLLÄ\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"lcrc\",\"Locoroco\",2006,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"nier\",\"NieR: Automata\",2017,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"starocean3\",\"Star Ocean The Last Hope: International\",2009,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"tekken7\",\"Tekken 7\",2015,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Bleach\",\"Bleach: Heat The Soul\",2005,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"DevilMayCry3\",\"Devil May Cry 3\",2005,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"KingdomHearts2\",\"Kingdom Hearts 2\",2005,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Zelda3\",\"The Legend of Zelda: A Link to the Past\",1991,\"Kohtalainen\",\"KYLLÄ\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Mario Kart\",\"Super Mario Kart\",1992,\"Hyvä\",\"KYLLÄ\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"SilentHill3\",\"Silent Hill 3\",2003,\"Hyvä\",\"KYLLÄ\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"SilentHill2\",\"Silent Hill 2\",2001,\"Kehno\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"PokemonORAS\",\"Pokemon Omega Ruby\",2014,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"PokemonDP\",\"Pokemon Pearl\",2006,\"Hyvä\",\"EI\")");
        stmt.executeUpdate("INSERT INTO Pelit VALUES(\"Suikoden2\",\"Suikoden 2\",1998,\"Kehno\",\"EI\")");
        
        
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"Pokemon DP\",\"Pokemon: Giratina & The Sky Warrior\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"Devilmaycry31\",\"Devil May Cry 3 Vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"Devilmaycry32\",\"Devil May Cry 3 Vol.2\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"blazbluemanga\",\"BlazBlue: Chimelical Complex vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"SaiyukiReload3\",\"Saiyuki Reload vol.3\",\"Kehno\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"GetBackers12\",\"GetBackers Vol.12\",\"Hyvä\",\"KYLLÄ\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"Bleach1\",\"Bleach Vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"KingdomHearts2_1\",\"Kingdom Hearts 2 Vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"KingdomHearts2_2\",\"Kingdom Hearts 2 Vol.2\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"Kuroshitsuji1\",\"Kuroshitsuji I\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"Beastars1\",\"Beastars Vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"yugioh1\",\"Yu-Gi-Oh! vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"saiyuki1\",\"Saiyuki vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"Suikoden3_1\",\"Suikoden 3 Vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"basara\",\"Sengoku basara Comic Anthology Setouchi Chronicle\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"snk1\",\"Titaanien Sota Vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Mangat VALUES(\"gingadensetsuweed1\",\"Weed Vol.1\",\"Hyvä\",\"EI\")");
        
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"bleachChibi\",\"Grimmjow chibi pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"PalkiaHuge\",\"Palkia jätti pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"KingdomHeartsPehmo\",\"Axel pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"SephirothPehmo\",\"FINAL FANTASY VII Action Doll Sephiroth\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"Sephiroth1997\",\"Sephiroth Banpresto pehmo\",\"Kehno\",\"KYLLÄ\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"Yeastkensleepy\",\"Shibakoppe Nukkuva pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"Yeastkentongue\",\"Tosa-anko - kieli ulkona pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"SaiyukiSanzoBig\",\"Saiyuki RELOAD BLAST Yorinui Genjyo Sanzo pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"BasaraMotochika\",\"Sengoku Basara pehmo: Motochika Chousokabe\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"BasaraMouriMotonari\",\"Sengoku Basara pehmo: Mouri Motonari\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"Larvitar1\",\"Larvitar\",\"Kohtalainen\",\"KYLLÄ\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"FNAFSL\",\"Funtime Freddy pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"FNAF1\",\"Golden Freddy Collector's Plush\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"FNAF1_2\",\"Bonnie Collector's Plush\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"amongus\",\"Among Us - Crewmate Plush (green)\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"yeastkencostume\",\"Tosa-anko in a cat costume\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"PokemonTyranitar\",\"Tyranitar medium pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"FNAF2\",\"Marionette pehmo\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pehmolelut VALUES(\"FNAF3\",\"Springtrap pehmo\",\"Hyvä\",\"EI\")");
       
       
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"BleachGrimmjow\",\"Grimmjow Funko Pop Figure\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"kingdomhearts2\",\"Axel Play Arts 2 Figure\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"FFVIIAC\",\"Sephiroth Play Arts Kai FFVII: ADVENT CHILDREN\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"yugioh\",\"Marik Ishtar Artfx J Statue\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"Basara\",\"Mouri Motonari Action Figure\",\"Kehno\",\"KYLLÄ\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"PokemonDP\",\"Palkia Soft Vinyl Figure\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"PokemonDP2\",\"Giratina Soft Vinyl Figure\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"PokemonDP3\",\"Giratina Large Figure\",\"Kehno\",\"KYLLÄ\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"Saiyuki\",\"Genjyo Sanzo PVC Statue\",\"Kehno\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"HakuoukiOkita\",\"Okita Souji Nendoroid\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"Aliens\",\"Neca Alien Queen\",\"Kohtalainen\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"Aliens2\",\"Alien 8-bit Funko Pop Figure\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"Talesofxillia\",\"Gaius Keychain\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"StarWars\",\"BB-8 App Enabled Droid\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"FFVIII\",\"Cerberus Action Figure\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"FFVII\",\"Sephiroth Static Arts Statue\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"FFVIII_2\",\"Tiatat Action Figure\",\"Kohtalainen\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Figuurit VALUES(\"AnimalCrossing\",\"Tomotachidooru - Dom\",\"Hyvä\",\"EI\")");
        
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"KDSD-00253-4\",\"BlazBlue -Calamity Trigger- Original Soundtrack\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"SSCX-10040\",\"Chrono Cross Original Soundtrack\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"SQEX-10589-91\",\"NieR: Automata Original Soundtrack\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"SMCL-205-6\",\"Sengoku BASARA 3 Original Soundtracks\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"SQEX-10139-42\",\"STAR OCEAN -THE LAST HOPE- Original Soundtrack\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"NACL-1225\",\"TEKKEN2 STRIKE FIGHTING Vol.1\",\"Hyvä\",\"KYLLÄ\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"NACL-1229\",\"TEKKEN2 STRIKE FIGHTING Vol.2\",\"Hyvä\",\"KYLLÄ\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"SMCL-251-2\",\"Sengoku BASARA 3 Utage Original Soundtracks\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"SQEX-10001-4\",\"FINAL FANTASY VII Original Sound Track\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"KOLA-038\",\"Silent Hill 3 Original Soundtracks\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"KMCA-120\",\"Silent Hill 2 Original Soundtracks\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"TRCD-10014\",\"Grandia II ~POVO~\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"TRCD-10013\",\"Grandia II ~DEUS~\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"OVCP-0004\",\"Nintendo 3DS Pokémon Omega Ruby & Alpha Sapphire Super Music Complete\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"TOCT-25871-2\",\"Kingdom Hearts 2 Original Soundtrack\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"KICA-7935-6\",\"GENSOSUIKODEN II ORIGINAL GAME SOUNDTRACK Vol.1\",\"Hyvä\",\"EI\")");
       stmt.executeUpdate("INSERT INTO Pelimusiikki VALUES(\"KICA-7937-8\",\"GENSOSUIKODEN II ORIGINAL GAME SOUNDTRACK Vol.2\",\"Hyvä\",\"EI\")");
       
       
       
    }
      
       public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        //Julistetaan statement, resultSet ja CachedResultSet nullina
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;
        try {
            //Yhdistetään Azureen 
            Connection con = openConnection("jdbc:mariadb://localhost:USERPASSWORD");
         
            
            System.out.println("Select statement: " + queryStmt + "\n");

            //Luodaan statement
          
            stmt = con.createStatement();
            stmt.executeQuery("USE USERNAMEPASSWORD");
            //Tehdään select (query) operation
            resultSet = stmt.executeQuery(queryStmt);

          
            //Tämä estää "java.sql.SQLRecoverableException: Closed Connection: next" errorin
            //Joten käytetään CachedRowSet
            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
            closeConnection(con);
        } catch (SQLException e) {
            System.out.println("ExecuteQuery ei toiminut koska: " + e);
            throw e;
        } finally {
            if (resultSet != null) {
                //Suljetaan resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Suljetaan Statement
                stmt.close();
            }
           
        }
        //Palautetaan CachedRowSet
        return crs;
    }
      
         //Update (Update/Insert/Delete)
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {
        //Null statement
        Statement stmt = null;
        try {
           
            Connection con = openConnection("jdbc:mariadb://localhost:USERPASSWORD");
           
            
          
            stmt = con.createStatement();
            stmt.executeQuery("USE USERNAME");
            //Ajetaan executeUpdate
            stmt.executeUpdate(sqlStmt);
            closeConnection(con);
        } catch (SQLException e) {
            System.out.println("ExecuteUpdate ei toiminut koska: " + e);
            throw e;
        } finally {
            if (stmt != null) {
                
                stmt.close();
            }
          
        }
    }
       
       private static void createTable(Connection c, String sql) throws SQLException{
        
        Statement stmt = c.createStatement();
        stmt.executeQuery(sql);
        System.out.println("\t>> Taulu luotu");
        
        
    }

}
