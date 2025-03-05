
package collectiondatabase;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Mangas {
    
    public StringProperty manga_Nimi;
    public StringProperty manga_Id;
    public StringProperty manga_Kunto;
    public StringProperty manga_Preowned;
    
    public Mangas() {
     
        this.manga_Nimi = new SimpleStringProperty();
        this.manga_Id = new SimpleStringProperty();
        this.manga_Kunto = new SimpleStringProperty();
        this.manga_Preowned = new SimpleStringProperty();
       
    }
    
    public StringProperty getMangaNimi() {
        return manga_Nimi;
    }
    public StringProperty mangaNimiProperty(){
        return manga_Nimi;
    }
    

    public StringProperty getMangaId() {
        return manga_Id;
    }
    
    public StringProperty mangaIdProperty(){
        return manga_Id;
    }

    
    public StringProperty getMangaKunto() {
        return manga_Kunto;
    }
    
    public StringProperty mangaKuntoProperty(){
        return manga_Kunto;
    }
    
    public StringProperty getMangaPreowned() {
        return manga_Preowned;
    }
    
    public StringProperty mangaPreownedProperty(){
        return manga_Preowned;
    }
    
    public void setMangaNimi(String mangaNimi) {
         this.manga_Nimi.set(mangaNimi);
    }

    public void setMangaId(String mangaId) {
        this.manga_Id.set(mangaId);
    }



    public void setMangaKunto(String mangaKunto) {
        this.manga_Kunto.set(mangaKunto);
    }

    public void setMangaPreowned(String mangaPreowned) {
        this.manga_Preowned.set(mangaPreowned);
    }
    
}
