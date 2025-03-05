
package collectiondatabase;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Figures {
    
    public StringProperty figure_Nimi;
    public StringProperty figure_Id;
    public StringProperty figure_Kunto;
    public StringProperty figure_Preowned;
    
    public Figures() {
     
        this.figure_Nimi = new SimpleStringProperty();
        this.figure_Id = new SimpleStringProperty();
        this.figure_Kunto = new SimpleStringProperty();
        this.figure_Preowned = new SimpleStringProperty();
       
    }
    
    public StringProperty getFigureNimi() {
        return figure_Nimi;
    }
    public StringProperty figureNimiProperty(){
        return figure_Nimi;
    }
    

    public StringProperty getFigureId() {
        return figure_Id;
    }
    
    public StringProperty figureIdProperty(){
        return figure_Id;
    }

    
    public StringProperty getFigureKunto() {
        return figure_Kunto;
    }
    
    public StringProperty figureKuntoProperty(){
        return figure_Kunto;
    }
    
    public StringProperty getFigurePreowned() {
        return figure_Preowned;
    }
    
    public StringProperty figurePreownedProperty(){
        return figure_Preowned;
    }
    
    public void setFigureNimi(String figureNimi) {
         this.figure_Nimi.set(figureNimi);
    }

    public void setFigureId(String figureId) {
        this.figure_Id.set(figureId);
    }


    public void setFigureKunto(String figureKunto) {
        this.figure_Kunto.set(figureKunto);
    }

    public void setFigurePreowned(String figurePreowned) {
        this.figure_Preowned.set(figurePreowned);
    }
    
}
