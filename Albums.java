
package collectiondatabase;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Albums {
    
    public StringProperty album_Nimi;
    public StringProperty album_Id;
    public StringProperty album_Kunto;
    public StringProperty album_Preowned;
    
    public Albums() {
     
        this.album_Nimi = new SimpleStringProperty();
        this.album_Id = new SimpleStringProperty();
        this.album_Kunto = new SimpleStringProperty();
        this.album_Preowned = new SimpleStringProperty();
       
    }
    
    public StringProperty getAlbumNimi() {
        return album_Nimi;
    }
    public StringProperty albumNimiProperty(){
        return album_Nimi;
    }
    

    public StringProperty getAlbumId() {
        return album_Id;
    }
    
    public StringProperty albumIdProperty(){
        return album_Id;
    }

    
    public StringProperty getAlbumKunto() {
        return album_Kunto;
    }
    
    public StringProperty albumKuntoProperty(){
        return album_Kunto;
    }
    
    public StringProperty getAlbumPreowned() {
        return album_Preowned;
    }
    
    public StringProperty albumPreownedProperty(){
        return album_Preowned;
    }
    
    public void setAlbumNimi(String albumNimi) {
         this.album_Nimi.set(albumNimi);
    }

    public void setAlbumId(String albumId) {
        this.album_Id.set(albumId);
    }



    public void setAlbumKunto(String albumKunto) {
        this.album_Kunto.set(albumKunto);
    }

    public void setAlbumPreowned(String albumPreowned) {
        this.album_Preowned.set(albumPreowned);
    }
    
}
