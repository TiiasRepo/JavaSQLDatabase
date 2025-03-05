
package collectiondatabase;


import static collectiondatabase.DBUtil.closeConnection;
import static collectiondatabase.DBUtil.openConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField peliNimi;
    @FXML
    private TextField peliID;
    @FXML
    private TextField peliKunto;
    @FXML
    private TextField peliUsed;
    @FXML
    private TextField peliVuosi;
    @FXML
    private TableColumn<Games, String> cPeliID;
    @FXML
    private TableColumn<Games, String> cPeliNimi;
    @FXML
    private TableColumn<Games, String> cPeliKunto;
    @FXML
    private TableColumn<Games, String> cPeliUsed;
    @FXML
    private TableColumn<Games, Integer> cPeliVuosi;
    @FXML
    private TextArea peliTapahtumat;
    @FXML
    private Button addPeli;
    @FXML
    private TextField peliIdfunction;
    @FXML
    private TextField peliUusiNimi;
    @FXML
    private Button peliEtsi;
    @FXML
    private Button peliUpdate;
    @FXML
    private Button peliPoista;
    @FXML
    private Button peliShowAll;
    @FXML
    private TextField musaNimi;
    @FXML
    private TextField musaID;
    @FXML
    private TextField musaKunto;
    @FXML
    private TextField musaUsed;
    @FXML
    private TableColumn<Albums, String> cMusaId;
    @FXML
    private TableColumn<Albums, String> cMusaNimi;
    @FXML
    private TableColumn<Albums, String> cMusaKunto;
    @FXML
    private TableColumn<Albums, String> cMusaUsed;
    @FXML
    private TextArea musaTapahtumat;
    @FXML
    private Button addMusa;
    @FXML
    private TextField musaIdfunction;
    @FXML
    private TextField musaUusiNimi;
    @FXML
    private Button musaEtsi;
    @FXML
    private Button musaUpdate;
    @FXML
    private Button musaPoista;
    @FXML
    private Button musaShowAll;
    @FXML
    private TextField mangaNimi;
    @FXML
    private TextField MangaId;
    @FXML
    private TextField mangaKunto;
    @FXML
    private TextField mangaUsed;
    @FXML
    private TableColumn<Mangas, String> cMangaId;
    @FXML
    private TableColumn<Mangas, String> cMangaNimi;
    @FXML
    private TableColumn<Mangas, String> cMangaKunto;
    @FXML
    private TableColumn<Mangas, String> cMangaUsed;
    @FXML
    private TextArea mangaTapahtumat;
    @FXML
    private Button addManga;
    @FXML
    private TextField mangaIdfunction;
    @FXML
    private TextField mangaUusiNimi;
    @FXML
    private Button mangaEtsi;
    @FXML
    private Button mangaUpdate;
    @FXML
    private Button mangaPoista;
    @FXML
    private Button mangaShowAll;
    @FXML
    private TextField figuuriNimi;
    @FXML
    private TextField figuuriId;
    @FXML
    private TextField figuuriKunto;
    @FXML
    private TextField figuuriUsed;
    @FXML
    private TableColumn<Figures, String> cFifuuriId;
    @FXML
    private TableColumn<Figures, String> cFiguuriNimi;
    @FXML
    private TableColumn<Figures, String> cFiguuriKunto;
    @FXML
    private TableColumn<Figures, String> cFiguuriUsed;
    @FXML
    private TextArea figuuriTapahtumat;
    @FXML
    private Button addFiguuri;
    @FXML
    private TextField figuuriIdfunction;
    @FXML
    private TextField figuuriUusiNimi;
    @FXML
    private Button figuuriEtsi;
    @FXML
    private Button figuuriUpdate;
    @FXML
    private Button figuuriPoista;
    @FXML
    private Button figuuriShowAll;
    @FXML
    private TextField pehmoNimi;
    @FXML
    private TextField pehmoId;
    @FXML
    private TextField pehmoKunto;
    @FXML
    private TextField pehmoUsed;
    @FXML
    private TableColumn<Plushies, String> cPehmoId;
    @FXML
    private TableColumn<Plushies, String> cPehmoNimi;
    @FXML
    private TableColumn<Plushies, String> cPehmoKunto;
    @FXML
    private TableColumn<Plushies, String> cPehmoUsed;
    @FXML
    private TextArea pehmoTapahtumat;
    @FXML
    private Button addPehmo;
    @FXML
    private TextField pehmoIdfunction;
    @FXML
    private TextField pehmoUusiNimi;
    @FXML
    private Button pehmoEtsi;
    @FXML
    private Button pehmoUpdate;
    @FXML
    private Button pehmoPoista;
    @FXML
    private Button pehmoShowAll;
    
    private Executor exec;
    @FXML
    private TableView gameTable;
    @FXML
    private TableView mangaTable;
    @FXML
    private TableView albumTable;
    @FXML
    private TableView figureTable;
    @FXML
    private TableView plushieTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        try{
      
        Connection conn = DBUtil.openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
        DBUtil.createDatabase(conn, "USERNAME");
        
        DBUtil.closeConnection(conn);
        
        }catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
 /*
        setCellValueFactory on kolumneita varten, -> merkit ovat Lambda lauseita.
        jos arvo on double tai int, laitetaan perään asObject();
     
        */

        //multithreadingia: luodaan executor joka käyttää daemon säikeitä:
        
       exec = Executors.newCachedThreadPool((runnable) -> {
            Thread t = new Thread (runnable);
            t.setDaemon(true);
            return t;
        }); 
       
       cPeliID.setCellValueFactory(cellData -> cellData.getValue().peliIdProperty());
        cPeliNimi.setCellValueFactory(cellData -> cellData.getValue().peliNimiProperty());
        cPeliKunto.setCellValueFactory(cellData -> cellData.getValue().peliKuntoProperty());
        cPeliUsed.setCellValueFactory(cellData -> cellData.getValue().peliPreownedProperty());
        cPeliVuosi.setCellValueFactory(cellData -> cellData.getValue().julkvuosiProperty().asObject());
       
       cMangaId.setCellValueFactory(cellData -> cellData.getValue().mangaIdProperty());
        cMangaNimi.setCellValueFactory(cellData -> cellData.getValue().mangaNimiProperty());
        cMangaKunto.setCellValueFactory(cellData -> cellData.getValue().mangaKuntoProperty());
        cMangaUsed.setCellValueFactory(cellData -> cellData.getValue().mangaPreownedProperty());
    
        cMusaId.setCellValueFactory(cellData -> cellData.getValue().albumIdProperty());
        cMusaNimi.setCellValueFactory(cellData -> cellData.getValue().albumNimiProperty());
        cMusaKunto.setCellValueFactory(cellData -> cellData.getValue().albumKuntoProperty());
        cMusaUsed.setCellValueFactory(cellData -> cellData.getValue().albumPreownedProperty());
        
        cFifuuriId.setCellValueFactory(cellData -> cellData.getValue().figureIdProperty());
        cFiguuriNimi.setCellValueFactory(cellData -> cellData.getValue().figureNimiProperty());
        cFiguuriKunto.setCellValueFactory(cellData -> cellData.getValue().figureKuntoProperty());
        cFiguuriUsed.setCellValueFactory(cellData -> cellData.getValue().figurePreownedProperty());
        
        cPehmoId.setCellValueFactory(cellData -> cellData.getValue().plushieIdProperty());
        cPehmoNimi.setCellValueFactory(cellData -> cellData.getValue().plushieNimiProperty());
        cPehmoKunto.setCellValueFactory(cellData -> cellData.getValue().plushieKuntoProperty());
        cPehmoUsed.setCellValueFactory(cellData -> cellData.getValue().plushiePreownedProperty());
       
    }    

 

    @FXML
    private void searchGame(ActionEvent event) throws ClassNotFoundException, SQLException {
         try {
            //Hae peli tiedot
            Connection con = DBUtil.openConnection("jdbc:mariadb://localhost:USERNAME");
            Games game = GamesDAO.searchGame(peliIdfunction.getText(), con);
            // Täytä Pelit Tableviewissa ja näytä Textareassa
            
            populateAndShowGame(game);
            closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
            peliTapahtumat.setText("Virhe tapahtui kun haettiin peliä.\n" + e);
            throw e;
        }
        
    }
    
    //Täytä Pelit
    private void populateGames (Games game) throws ClassNotFoundException {
        //Observalist ableviewille
        ObservableList<Games> gameData = FXCollections.observableArrayList();
        //Lisää peli observalistaan
        gameData.add(game);
        gameTable.setItems(gameData);
    }
    
    //asetetaan peli tiedot teksti arealle
    private void setGameInfoToTextArea ( Games game) {
        peliTapahtumat.setText("pelin nimi: " + game.getPeliNimi() + "\n" +
                "Julkaisuvuosi: " + game.getJulkvuosi());
    }
    // täytä Pelit tableviewille multithreadilla
    private void fillGamesTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Games>> task = new Task<List<Games>>(){
            @Override
            public ObservableList<Games> call() throws Exception{
                return GamesDAO.searchGames();
            }
        };
        
         task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> gameTable.setItems((ObservableList<Games>) task.getValue()));
        exec.execute(task);
    }
    
    
        // Täytä Pelit Tableviewille ja näytä peli Textareassa
    private void populateAndShowGame(Games game) throws ClassNotFoundException {
        if (game != null) {
            populateGames(game);
            setGameInfoToTextArea(game);
        } else {
            peliTapahtumat.setText("Tätä peliä ei ole olemassa!\n");
        }
    }
    // Täytä pelit Tableviewille

    private void populateGames (ObservableList<Games> gameData) throws ClassNotFoundException {
        //Laitetaan itemit gameTablelle
        gameTable.setItems(gameData);
    }
    
     @FXML
    private void insertGame(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            GamesDAO.insertGame(peliNimi.getText(),peliID.getText(),peliUsed.getText(),peliKunto.getText(),peliVuosi.getText(), con);
            closeConnection(con);
            peliTapahtumat.setText("Peli lisätty! \n");
        } catch (SQLException e) {
            peliTapahtumat.setText("Nyt tuli ongelma kun lisättiin peli... " + e);
            throw e;
        }
    
        
    }
    
    @FXML
    private void updateGameName(ActionEvent event) throws SQLException, ClassNotFoundException {
         try {
             Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            GamesDAO.updateGameNimi(peliIdfunction.getText(),peliUusiNimi.getText(), con);
            closeConnection(con);
            peliTapahtumat.setText("Nimi on päivitetty: " + peliIdfunction.getText() + "\n");
        } catch (SQLException e) {
            peliTapahtumat.setText("Ongelma tuli kun päivitettiin nimeä: " + e);
        }
    }
        
    

    @FXML
    private void deleteGame(ActionEvent event) throws SQLException, ClassNotFoundException {
      
      try {
          Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            GamesDAO.deleteGameWithId(peliIdfunction.getText(), con);
            closeConnection(con);
            peliTapahtumat.setText("Peli pyyhitty! " + peliIdfunction.getText() + "\n");
        } catch (SQLException e) {
            peliTapahtumat.setText("Ongelma tuli kun pyyhittiin " + e);
            throw e;
        }
        
    }

    @FXML
    private void searchGames(ActionEvent event) throws SQLException, ClassNotFoundException {
       
      try {
            //Hae kaikki Pelit tiedot
            ObservableList<Games> gameData = GamesDAO.searchGames();
          
            populateGames(gameData);
        } catch (SQLException e){
            System.out.println("Virhe tapahtui kun haettiin pelejä.\n" + e);
            throw e;
        }
    }   
    
    // ************************************** ****************
    // TÄSTÄ ALKAA MANGAT
    // ******************************************************
    
    @FXML
    private void searchManga(ActionEvent event) throws ClassNotFoundException, SQLException {
         try {
        
            Connection con = DBUtil.openConnection("jdbc:mariadb://localhosUSERNAMEPASSWORD");
            Mangas manga = MangasDAO.searchManga(mangaIdfunction.getText(), con);
      
            
            populateAndShowManga(manga);
            closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
            mangaTapahtumat.setText("Virhe tapahtui kun haettiin peliä.\n" + e);
            throw e;
        }
        
    }
    
    private void populateMangas (Mangas manga) throws ClassNotFoundException {
  
        ObservableList<Mangas> mangaData = FXCollections.observableArrayList();
   
        mangaData.add(manga);
        mangaTable.setItems(mangaData);
    }
    

    private void setMangaInfoToTextArea ( Mangas manga) {
        mangaTapahtumat.setText("Mangan nimi: " + manga.getMangaNimi() + "\n" +
                "Kunto: " + manga.getMangaKunto());
    }
   
    private void fillMangasTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Mangas>> task = new Task<List<Mangas>>(){
            @Override
            public ObservableList<Mangas> call() throws Exception{
                return MangasDAO.searchMangas();
            }
        };
        
         task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> mangaTable.setItems((ObservableList<Mangas>) task.getValue()));
        exec.execute(task);
    }
    
     
    private void populateAndShowManga(Mangas manga) throws ClassNotFoundException {
        if (manga != null) {
            populateMangas(manga);
            setMangaInfoToTextArea(manga);
        } else {
            mangaTapahtumat.setText("Tätä mangaa ei ole olemassa!\n");
        }
    }
    
     private void populateMangas (ObservableList<Mangas> mangaData) throws ClassNotFoundException {
    
        mangaTable.setItems(mangaData);
    }
    
       @FXML
    private void insertManga(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            MangasDAO.insertManga(mangaNimi.getText(),MangaId.getText(),mangaUsed.getText(),mangaKunto.getText(), con);
            closeConnection(con);
            mangaTapahtumat.setText("Manga lisätty! \n");
        } catch (SQLException e) {
            mangaTapahtumat.setText("Nyt tuli ongelma kun lisättiin manga... " + e);
            throw e;
        }
        
    }
     
     @FXML
    private void updateMangaName(ActionEvent event) throws SQLException, ClassNotFoundException {
         try {
             Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            MangasDAO.updateMangaNimi(mangaIdfunction.getText(),mangaUusiNimi.getText(), con);
            closeConnection(con);
            mangaTapahtumat.setText("Nimi on päivitetty: " + mangaIdfunction.getText() + "\n");
        } catch (SQLException e) {
            mangaTapahtumat.setText("Ongelma tuli kun päivitettiin nimeä: " + e);
        }
    }
    
        @FXML
    private void deleteManga(ActionEvent event) throws SQLException, ClassNotFoundException {
      
      try {
          Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            MangasDAO.deleteMangaWithId(mangaIdfunction.getText(), con);
            closeConnection(con);
            mangaTapahtumat.setText("Manga pyyhitty! " + mangaIdfunction.getText() + "\n");
        } catch (SQLException e) {
            mangaTapahtumat.setText("Ongelma tuli kun pyyhittiin " + e);
            throw e;
        }
        
    }
     
    @FXML
    private void searchMangas(ActionEvent event) throws SQLException, ClassNotFoundException {
       
      try {
      
            ObservableList<Mangas> mangaData = MangasDAO.searchMangas();
          
            populateMangas(mangaData);
        } catch (SQLException e){
            System.out.println("Virhe tapahtui kun haettiin mangoja.\n" + e);
            throw e;
        }
    }   
    
    
    //***************************************
    //  MUSIIKKI ALKAA TÄSTÄ
    //*************************************
    
     @FXML
    private void searchAlbum(ActionEvent event) throws ClassNotFoundException, SQLException {
         try {
         
            Connection con = DBUtil.openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            Albums album = AlbumsDAO.searchAlbum(musaIdfunction.getText(), con);
            // Täytä Pelit Tableviewissa ja näytä Textareassa
            
            populateAndShowAlbum(album);
            closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
            musaTapahtumat.setText("Virhe tapahtui kun haettiin albumia.\n" + e);
            throw e;
        }
        
    }
    
    private void populateAlbums (Albums album) throws ClassNotFoundException {
  
        ObservableList<Albums> albumData = FXCollections.observableArrayList();
   
        albumData.add(album);
        albumTable.setItems(albumData);
    }
    

    private void setAlbumInfoToTextArea ( Albums album) {
        musaTapahtumat.setText("Albumin nimi: " + album.getAlbumNimi() + "\n" +
                "Kunto: " + album.getAlbumKunto());
    }
   
    private void fillAlbumsTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Albums>> task = new Task<List<Albums>>(){
            @Override
            public ObservableList<Albums> call() throws Exception{
                return AlbumsDAO.searchAlbums();
            }
        };
        
         task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> albumTable.setItems((ObservableList<Albums>) task.getValue()));
        exec.execute(task);
    }
    
    private void populateAndShowAlbum(Albums album) throws ClassNotFoundException {
        if (album != null) {
            populateAlbums(album);
            setAlbumInfoToTextArea(album);
        } else {
            musaTapahtumat.setText("Tätä albumia ei ole olemassa!\n");
        }
    }
    
     private void populateAlbums (ObservableList<Albums> albumData) throws ClassNotFoundException {
    
        albumTable.setItems(albumData);
    }
    
         @FXML
    private void insertAlbum(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            AlbumsDAO.insertAlbum(musaNimi.getText(),musaID.getText(),musaUsed.getText(),musaKunto.getText(), con);
            closeConnection(con);
            musaTapahtumat.setText("Albumi lisätty! \n");
        } catch (SQLException e) {
            musaTapahtumat.setText("Nyt tuli ongelma kun lisättiin albumi... " + e);
            throw e;
        }
        
    }
     
      @FXML
    private void updateAlbumName(ActionEvent event) throws SQLException, ClassNotFoundException {
         try {
             Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            AlbumsDAO.updateAlbumNimi(musaIdfunction.getText(),musaUusiNimi.getText(), con);
            closeConnection(con);
            musaTapahtumat.setText("Nimi on päivitetty: " + musaIdfunction.getText() + "\n");
        } catch (SQLException e) {
            musaTapahtumat.setText("Ongelma tuli kun päivitettiin nimeä: " + e);
        }
    } 
    
         @FXML
    private void deleteAlbum(ActionEvent event) throws SQLException, ClassNotFoundException {
      
      try {
          Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            AlbumsDAO.deleteAlbumWithId(musaIdfunction.getText(), con);
            closeConnection(con);
            musaTapahtumat.setText("Albumi pyyhitty! " + musaIdfunction.getText() + "\n");
        } catch (SQLException e) {
            musaTapahtumat.setText("Ongelma tuli kun pyyhittiin " + e);
            throw e;
        }
        
    }
    
     @FXML
    private void searchAlbums(ActionEvent event) throws SQLException, ClassNotFoundException {
       
      try {
      
            ObservableList<Albums> albumData = AlbumsDAO.searchAlbums();
          
            populateAlbums(albumData);
        } catch (SQLException e){
            System.out.println("Virhe tapahtui kun haettiin albumeita.\n" + e);
            throw e;
        }
    }   
    
    // *******************************************
    // TÄSTÄ ALKAA FIGUURIT
    // *******************************************
    
      @FXML
    private void searchFigure(ActionEvent event) throws ClassNotFoundException, SQLException {
         try {
         
            Connection con = DBUtil.openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            Figures figure = FiguresDAO.searchFigure(figuuriIdfunction.getText(), con);
         
            
            populateAndShowFigure(figure);
            closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
            figuuriTapahtumat.setText("Virhe tapahtui kun haettiin figuuria.\n" + e);
            throw e;
        }
        
    }
    
    private void populateFigures (Figures figure) throws ClassNotFoundException {
  
        ObservableList<Figures> figureData = FXCollections.observableArrayList();
   
        figureData.add(figure);
        figureTable.setItems(figureData);
    }
    

    private void setFigureInfoToTextArea ( Figures figure) {
        figuuriTapahtumat.setText("Figuurin nimi: " + figure.getFigureNimi() + "\n" +
                "Kunto: " + figure.getFigureKunto());
    }
   
    private void fillFiguresTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Figures>> task = new Task<List<Figures>>(){
            @Override
            public ObservableList<Figures> call() throws Exception{
                return FiguresDAO.searchFigures();
            }
        };
        
         task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> figureTable.setItems((ObservableList<Figures>) task.getValue()));
        exec.execute(task);
    }
    
    private void populateAndShowFigure(Figures figure) throws ClassNotFoundException {
        if (figure != null) {
            populateFigures(figure);
            setFigureInfoToTextArea(figure);
        } else {
            figuuriTapahtumat.setText("Tätä figuuria ei ole olemassa!\n");
        }
    }
    
     private void populateFigures (ObservableList<Figures> figureData) throws ClassNotFoundException {
    
        figureTable.setItems(figureData);
    }
    
         @FXML
    private void insertFigure(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            FiguresDAO.insertFigure(figuuriNimi.getText(),figuuriId.getText(),figuuriUsed.getText(),figuuriKunto.getText(), con);
            closeConnection(con);
            figuuriTapahtumat.setText("Figuuri lisätty! \n");
        } catch (SQLException e) {
            figuuriTapahtumat.setText("Nyt tuli ongelma kun lisättiin figuuri... " + e);
            throw e;
        }
        
    }
    
       @FXML
    private void updateFigureName(ActionEvent event) throws SQLException, ClassNotFoundException {
         try {
             Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            FiguresDAO.updateFigureNimi(figuuriIdfunction.getText(),figuuriUusiNimi.getText(), con);
            closeConnection(con);
            figuuriTapahtumat.setText("Nimi on päivitetty: " + figuuriIdfunction.getText() + "\n");
        } catch (SQLException e) {
            figuuriTapahtumat.setText("Ongelma tuli kun päivitettiin nimeä: " + e);
        }
    } 
    
           @FXML
    private void deleteFigure(ActionEvent event) throws SQLException, ClassNotFoundException {
      
      try {
          Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            FiguresDAO.deleteFigureWithId(figuuriIdfunction.getText(), con);
            closeConnection(con);
            figuuriTapahtumat.setText("Figuuri pyyhitty! " + figuuriIdfunction.getText() + "\n");
        } catch (SQLException e) {
            figuuriTapahtumat.setText("Ongelma tuli kun pyyhittiin " + e);
            throw e;
        }
        
    }
    
     @FXML
    private void searchFigures(ActionEvent event) throws SQLException, ClassNotFoundException {
       
      try {
      
            ObservableList<Figures> figureData = FiguresDAO.searchFigures();
          
            populateFigures(figureData);
        } catch (SQLException e){
            System.out.println("Virhe tapahtui kun haettiin figuureita.\n" + e);
            throw e;
        }
    }   
    
    // *******************************************
    // TÄSTÄ ALKAA PEHMOT
    // *******************************************
    
    
      @FXML
    private void searchPlushie(ActionEvent event) throws ClassNotFoundException, SQLException {
         try {
         
            Connection con = DBUtil.openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            Plushies plushie = PlushiesDAO.searchPlushie(pehmoIdfunction.getText(), con);
         
            
            populateAndShowPlushie(plushie);
            closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
            pehmoTapahtumat.setText("Virhe tapahtui kun haettiin pehmoa.\n" + e);
            throw e;
        }
        
    }
    
     private void populatePlushies (Plushies plushie) throws ClassNotFoundException {
  
        ObservableList<Plushies> plushieData = FXCollections.observableArrayList();
   
        plushieData.add(plushie);
        plushieTable.setItems(plushieData);
    }
    

    private void setPlushieInfoToTextArea ( Plushies plushie) {
        pehmoTapahtumat.setText("Pehmon nimi: " + plushie.getPlushieNimi() + "\n" +
                "Kunto: " + plushie.getPlushieKunto());
    }
   
    private void fillPlushiesTable(ActionEvent event) throws SQLException, ClassNotFoundException {
        Task<List<Plushies>> task = new Task<List<Plushies>>(){
            @Override
            public ObservableList<Plushies> call() throws Exception{
                return PlushiesDAO.searchPlushies();
            }
        };
        
         task.setOnFailed(e-> task.getException().printStackTrace());
        task.setOnSucceeded(e-> plushieTable.setItems((ObservableList<Plushies>) task.getValue()));
        exec.execute(task);
    }
    
    private void populateAndShowPlushie(Plushies plushie) throws ClassNotFoundException {
        if (plushie != null) {
            populatePlushies(plushie);
            setPlushieInfoToTextArea(plushie);
        } else {
            pehmoTapahtumat.setText("Tätä pehmoa ei ole olemassa!\n");
        }
    }
    
     private void populatePlushies (ObservableList<Plushies> plushieData) throws ClassNotFoundException {
    
        plushieTable.setItems(plushieData);
    }
    
           @FXML
    private void insertPlushie(ActionEvent event) throws SQLException, ClassNotFoundException {
        try {
            Connection con = openConnection("jdbc:mariadb://localhostUSERNAMEPASSWORD");
            PlushiesDAO.insertPlushie(pehmoNimi.getText(),pehmoId.getText(),pehmoUsed.getText(),pehmoKunto.getText(), con);
            closeConnection(con);
            pehmoTapahtumat.setText("Pehmolelu lisätty! \n");
        } catch (SQLException e) {
            pehmoTapahtumat.setText("Nyt tuli ongelma kun lisättiin pehmolelu... " + e);
            throw e;
        }
        
    }
     
        @FXML
    private void updatePlushieName(ActionEvent event) throws SQLException, ClassNotFoundException {
         try {
             Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            PlushiesDAO.updatePlushieNimi(pehmoIdfunction.getText(),pehmoUusiNimi.getText(), con);
            closeConnection(con);
            pehmoTapahtumat.setText("Nimi on päivitetty: " + pehmoIdfunction.getText() + "\n");
        } catch (SQLException e) {
            pehmoTapahtumat.setText("Ongelma tuli kun päivitettiin nimeä: " + e);
        }
    } 
    
           @FXML
    private void deletePlushie(ActionEvent event) throws SQLException, ClassNotFoundException {
      
      try {
          Connection con = openConnection("jdbc:mariadb://localhost:USERNAMEPASSWORD");
            PlushiesDAO.deletePlushieWithId(pehmoIdfunction.getText(), con);
            closeConnection(con);
            pehmoTapahtumat.setText("Pehmolelu pyyhitty! " + pehmoIdfunction.getText() + "\n");
        } catch (SQLException e) {
            pehmoTapahtumat.setText("Ongelma tuli kun pyyhittiin " + e);
            throw e;
        }
        
    }
    
     @FXML
    private void searchPlushies(ActionEvent event) throws SQLException, ClassNotFoundException {
       
      try {
      
            ObservableList<Plushies> plushieData = PlushiesDAO.searchPlushies();
          
            populatePlushies(plushieData);
        } catch (SQLException e){
            System.out.println("Virhe tapahtui kun haettiin pehmoleluja.\n" + e);
            throw e;
        }
    }    
    
    
         @FXML
    private void menuCloseClicked(ActionEvent event) {
        
        Platform.exit();
        System.exit(0);
    }    
}
    
  

    

   
    

