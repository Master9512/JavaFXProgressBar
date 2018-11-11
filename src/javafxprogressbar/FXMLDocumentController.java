/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxprogressbar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 *
 * @author Cool IT Help
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML 
    private ProgressBar progressBar;
    
    Task copyWorker;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {        
       
        
        progressBar.setProgress(0.0);


        copyWorker = createWorker();
        
        progressBar.progressProperty().unbind();
        
        progressBar.progressProperty().bind(copyWorker.progressProperty());

        copyWorker.messageProperty().addListener(new ChangeListener<String>() {
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        System.out.println(newValue);
                        label.setText(newValue);
                    }
                });
        
        
        new Thread(copyWorker).start();
        
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(2000);
                    updateMessage("Task Completed : " + ((i*10)+10)  + "%");
                    updateProgress(i + 1, 10);
                }
                return true;
            }
        };
   
    
}

}