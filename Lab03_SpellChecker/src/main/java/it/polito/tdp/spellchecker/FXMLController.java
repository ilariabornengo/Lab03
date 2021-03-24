
package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.spellchecker.model.Dictionary;
import it.polito.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class FXMLController {

	Dictionary model;
	String lingua;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem itemIta;

    @FXML
    private MenuItem itemEng;

    @FXML
    private TextArea txtImput;

    @FXML
    private Button btnSpell;

    @FXML
    private Label txtWrongWords;

    @FXML
    private TextArea txtResult;

    @FXML
    private Label txtNumeroErrori;

    @FXML
    private Button btnClear;

    @FXML
    private Label txtTempo;

    @FXML
    void doClear(ActionEvent event) {
    	this.txtImput.clear();
    	this.txtResult.clear();
    	this.txtImput.setDisable(true);
    	this.btnSpell.setDisable(true);
    	

    }

    @FXML
    void doSpell(ActionEvent event) {
    	
    	List<RichWord> paroleR=new LinkedList<RichWord>();
    	List<String> finale=new LinkedList<String>();
    	String risultato="";
    	String inserito=txtImput.getText();
    	inserito=inserito.toLowerCase();
    	inserito=inserito.replaceAll("\\p{Punct}", "");
    	List<String> listaInserito=new LinkedList<String>();
    	
    	this.model.loadDictionary(lingua);
    	
    	
    	String[] campi=inserito.split(" ");
    	for(int i=0;i<campi.length;i++)
    	{
    		listaInserito.add(campi[i]);
    	}
    	paroleR=this.model.spellCheckTestDichotomic(listaInserito);
    	finale=this.model.paroleSbagliate(paroleR);
    	
    	this.txtNumeroErrori.setText("Ci sono "+finale.size()+" parole sbagliate");
    	this.txtTempo.setText("Spell check completed in: "+System.currentTimeMillis());
    	for(String s:finale)
    	{
    		risultato+=s+"\n";
    	}
    	this.txtResult.setText(risultato);
   	
    }

    @FXML
    void handleEng(ActionEvent event) {
    	lingua="English";
    	this.txtImput.setDisable(false);
    	this.btnSpell.setDisable(false);

    }

    @FXML
    void handleIta(ActionEvent event) {
    	lingua="Italian";
    	this.txtImput.setDisable(false);
    	this.btnSpell.setDisable(false);

    }
    
    public void setModel(Dictionary model)
    {
    	this.model=model;
    }
 

    @FXML
    void initialize() {
        assert itemIta != null : "fx:id=\"itemIta\" was not injected: check your FXML file 'Scene.fxml'.";
        assert itemEng != null : "fx:id=\"itemEng\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtImput != null : "fx:id=\"txtImput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpell != null : "fx:id=\"btnSpell\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtWrongWords != null : "fx:id=\"txtWrongWords\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNumeroErrori != null : "fx:id=\"txtNumeroErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
 


