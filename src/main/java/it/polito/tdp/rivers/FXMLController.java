/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.rivers;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.rivers.model.Model;
import it.polito.tdp.rivers.model.Popolatore;
import it.polito.tdp.rivers.model.River;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxRiver"
    private ComboBox<River> boxRiver; // Value injected by FXMLLoader

    @FXML // fx:id="txtStartDate"
    private TextField txtStartDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtEndDate"
    private TextField txtEndDate; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumMeasurements"
    private TextField txtNumMeasurements; // Value injected by FXMLLoader

    @FXML // fx:id="txtFMed"
    private TextField txtFMed; // Value injected by FXMLLoader

    @FXML // fx:id="txtK"
    private TextField txtK; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML
    void doScegliFiume(ActionEvent event) {
    	
    	Popolatore p = this.model.popolaTxtDatoFiume(boxRiver.getValue());
    	
    	txtStartDate.setText(p.getInizio().toString());
    	txtEndDate.setText(p.getFine().toString());
    	txtNumMeasurements.setText(Integer.toString(p.getN_misurazioni()));
    	txtFMed.setText(Float.toString(p.getMedia()));
    	
    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	if (txtStartDate.getText().equals("") || txtEndDate.getText().equals("") || 
    			txtNumMeasurements.getText().equals("") || txtFMed.getText().equals("")) {
    		txtResult.appendText("Si prega di scegliere un fiume! \n");
    		return;
    	}
    	
    	if (txtK.getText().equals("")) {
    		txtResult.appendText("Il coefficiente K non può essere nullo! \n");
    		return;
    	}
    	
    	float k = 0.0f;
    	try {
    		k = Float.parseFloat(txtK.getText());
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Il coefficiente K deve essere numerico e positivo! \n");
    		return;
    	}
    	
    	
    	txtResult.appendText(this.model.Simulatore(
    			boxRiver.getValue(),
    			txtStartDate.getText(), 
    			txtEndDate.getText(), 
    			Integer.parseInt(txtNumMeasurements.getText()), 
    			Float.parseFloat(txtFMed.getText()), 
    			k));
    	
    
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxRiver != null : "fx:id=\"boxRiver\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStartDate != null : "fx:id=\"txtStartDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtEndDate != null : "fx:id=\"txtEndDate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumMeasurements != null : "fx:id=\"txtNumMeasurements\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtFMed != null : "fx:id=\"txtFMed\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtK != null : "fx:id=\"txtK\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	
    	for (River r : this.model.TuttiFiumi()) {
    		boxRiver.getItems().add(r);
    	}
    	
    }
}
