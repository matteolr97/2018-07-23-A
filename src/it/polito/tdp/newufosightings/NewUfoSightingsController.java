/**
 * Sample Skeleton for 'NewUfoSightings.fxml' Controller Class
 */

package it.polito.tdp.newufosightings;

import it.polito.tdp.newufosightings.model.*;
import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.newufosightings.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class NewUfoSightingsController {

	private Model model;


	
	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="btnSelezionaAnno"
	private Button btnSelezionaAnno; // Value injected by FXMLLoader

	@FXML // fx:id="cmbBoxForma"
	private ComboBox<String> cmbBoxForma; // Value injected by FXMLLoader

	@FXML // fx:id="btnCreaGrafo"
	private Button btnCreaGrafo; // Value injected by FXMLLoader

	@FXML // fx:id="txtT1"
	private TextField txtT1; // Value injected by FXMLLoader

	@FXML // fx:id="txtAlfa"
	private TextField txtAlfa; // Value injected by FXMLLoader

	@FXML // fx:id="btnSimula"
	private Button btnSimula; // Value injected by FXMLLoader

	@FXML
	void doCreaGrafo(ActionEvent event) {
		txtResult.clear();
		int anno = 0;
		try{
			anno = Integer.parseInt(txtAnno.getText());
		} catch(NumberFormatException e) {
			txtResult.setText("Inserire un anno tra 1910 e 2014");
			return;
		}
		if(anno<1910 || anno >2014) {
			txtResult.setText("Inserire un anno tra 1910 e 2014");
			return;
		}
		String forma = cmbBoxForma.getValue();
		if(forma == null) {
			txtResult.setText("Selezionare una forma");
			return;
		}
		model.creaGrafo(forma, anno);
		txtResult.appendText("Grafo creato con"+model.getVertexSize()+" vertici e "+model.getEdgeSize()+" archi\n");
		model.calcolaVicini();
		for(State s : model.getStati()) {
			txtResult.appendText(s.getName()+": "+s.getSomma()+"\n");
		};
		
	}

	@FXML
	void doSelezionaAnno(ActionEvent event) {
		txtResult.clear();
		int anno=0;
		try {
			anno = Integer.parseInt(txtAnno.getText());
		} catch(NumberFormatException e) {
			txtResult.setText("Inserire anno, t1 e alfa validi");
			return;
		}
		if(anno<1910 || anno >2014) {
			txtResult.setText("Inserire un anno tra 1910 e 2014");
			return;
		}
		
		cmbBoxForma.getItems().addAll(model.getListaForme(anno));
		
	}

	@FXML
	void doSimula(ActionEvent event) {

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert btnSelezionaAnno != null : "fx:id=\"btnSelezionaAnno\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert cmbBoxForma != null : "fx:id=\"cmbBoxForma\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert txtT1 != null : "fx:id=\"txtT1\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert txtAlfa != null : "fx:id=\"txtAlfa\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";
		assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'NewUfoSightings.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;

	}
}
