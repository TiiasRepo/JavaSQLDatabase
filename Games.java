
package collectiondatabase;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Games {
    
    public StringProperty peli_Nimi;
    public StringProperty peli_Id;
    public IntegerProperty julk_vuosi;
    public StringProperty peli_Kunto;
    public StringProperty peli_Preowned;

    
    
    // konstruktori
    public Games() {
        this.julk_vuosi = new SimpleIntegerProperty();
        this.peli_Nimi = new SimpleStringProperty();
        this.peli_Id = new SimpleStringProperty();
        this.peli_Kunto = new SimpleStringProperty();
        this.peli_Preowned = new SimpleStringProperty();
       
    }
    
    // Getterit ja setterit
    
    public StringProperty getPeliNimi() {
        return peli_Nimi;
    }
    public StringProperty peliNimiProperty(){
        return peli_Nimi;
    }
    

    public StringProperty getPeliId() {
        return peli_Id;
    }
    
    public StringProperty peliIdProperty(){
        return peli_Id;
    }

    public IntegerProperty getJulkvuosi() {
        return julk_vuosi;
    }
    
    public IntegerProperty julkvuosiProperty(){
        return julk_vuosi;
    }
    
    public StringProperty getPeliKunto() {
        return peli_Kunto;
    }
    
    public StringProperty peliKuntoProperty(){
        return peli_Kunto;
    }
    
    public StringProperty getPeliPreowned() {
        return peli_Preowned;
    }
    
    public StringProperty peliPreownedProperty(){
        return peli_Preowned;
    }
    
    public void setPeliNimi(String peliNimi) {
         this.peli_Nimi.set(peliNimi);
    }

    public void setPeliId(String peliId) {
        this.peli_Id.set(peliId);
    }

    public void setJulkvuosi(Integer julkvuosi) {
        this.julk_vuosi.set(julkvuosi);
    }

    public void setPeliKunto(String peliKunto) {
        this.peli_Kunto.set(peliKunto);
    }

    public void setPeliPreowned(String peliPreowned) {
        this.peli_Preowned.set(peliPreowned);
    }
    
}
