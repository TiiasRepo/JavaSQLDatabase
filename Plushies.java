
package collectiondatabase;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Plushies {
    
    public StringProperty plushie_Nimi;
    public StringProperty plushie_Id;
    public StringProperty plushie_Kunto;
    public StringProperty plushie_Preowned;
    
    public Plushies() {
     
        this.plushie_Nimi = new SimpleStringProperty();
        this.plushie_Id = new SimpleStringProperty();
        this.plushie_Kunto = new SimpleStringProperty();
        this.plushie_Preowned = new SimpleStringProperty();
       
    }
    
    public StringProperty getPlushieNimi() {
        return plushie_Nimi;
    }
    public StringProperty plushieNimiProperty(){
        return plushie_Nimi;
    }
    

    public StringProperty getPlushieId() {
        return plushie_Id;
    }
    
    public StringProperty plushieIdProperty(){
        return plushie_Id;
    }

    
    public StringProperty getPlushieKunto() {
        return plushie_Kunto;
    }
    
    public StringProperty plushieKuntoProperty(){
        return plushie_Kunto;
    }
    
    public StringProperty getPlushiePreowned() {
        return plushie_Preowned;
    }
    
    public StringProperty plushiePreownedProperty(){
        return plushie_Preowned;
    }
    
    public void setPlushieNimi(String plushieNimi) {
         this.plushie_Nimi.set(plushieNimi);
    }

    public void setPlushieId(String plushieId) {
        this.plushie_Id.set(plushieId);
    }


    public void setPlushieKunto(String plushieKunto) {
        this.plushie_Kunto.set(plushieKunto);
    }

    public void setPlushiePreowned(String plushiePreowned) {
        this.plushie_Preowned.set(plushiePreowned);
    }
    
}
