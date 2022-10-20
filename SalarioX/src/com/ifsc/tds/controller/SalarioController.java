package com.ifsc.tds.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class SalarioController {

    @FXML
    private TextField txtHoras;

    @FXML
    private Label lblSalario;

    @FXML
    private Button btnCalcular;

    @FXML
    private Button btnLimpar;

    @FXML
    private TextField txtValorHora;

    @FXML
    void onClickBtnCalcular(ActionEvent event) {
    	try {
	    	Double horas = Double.parseDouble(txtHoras.getText());
	    	Double valorHora = Double.parseDouble(txtValorHora.getText());
	    	double horasExtra = 0;
	    	double salario = 0;
	    	
	    	if(horas > 160) {
	    		horasExtra = horas - 160;
	    		horas = horas - horasExtra;
	    		salario = salario + (horasExtra * valorHora + valorHora / 2);
	    	}
	    	
	    	salario = salario + horas * valorHora;
	    	
	    	lblSalario.setText(String.format("%.2f", salario));
	    	
    	} catch (NumberFormatException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);

			String textoErro = sw.toString();
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Aconteceu um erro de conversão numérica!");

			Label label = new Label("Segue a pilha de exceção: ");

			TextArea textArea = new TextArea(textoErro);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);

			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expConteudo = new GridPane();
			expConteudo.setMaxWidth(Double.MAX_VALUE);
			expConteudo.add(label, 0, 0);
			expConteudo.add(textArea, 0, 1);
			alerta.getDialogPane().setExpandableContent(expConteudo);
			alerta.showAndWait();
		}	
    }
    

    @FXML
    void onClickBtnLimpar(ActionEvent event) {
    	txtHoras.clear();
    	txtValorHora.clear();
    	lblSalario.setText("0");
    }
    
    public boolean onCloseQuery() {
    	Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
    			
    	alerta.setTitle("Pergunta");
    	alerta.setHeaderText("Deseja sair do sistema?");
    			
    	ButtonType botaoNao = ButtonType.NO;
    	ButtonType botaoSim = ButtonType.YES;
    			
    	alerta.getButtonTypes().setAll(botaoSim, botaoNao);
    			
    	Optional<ButtonType> opcaoClicada = alerta.showAndWait();
    			
    	return opcaoClicada.get() == botaoSim ? true:false;

    }
}
