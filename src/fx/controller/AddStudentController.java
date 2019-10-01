/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.tools.Tool;

import entity.Student;
import service.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Meng
 */
public class AddStudentController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private Button back;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField age;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCourse(ActionEvent event) {
        if(firstName.getText()==null || "".equals(firstName.getText())){
        	util.Tool.alert("First name is required!");
        	return;
        }else if(firstName.getText().length()<=1 || !(firstName.getText().matches( "^[a-zA-Z]+" ))){
        	util.Tool.alert("First name is invalid!");
        	return;
        }else if(lastName.getText()==null || "".equals(lastName.getText())){
        	util.Tool.alert("Last name is required!");
        	return;
        }else if(lastName.getText().length()<=1 || !(lastName.getText().matches( "^[a-zA-Z]+" ))){
        	util.Tool.alert("Last name is invalid!");
        	return;
        }else if(age.getText()==null || "".equals(age.getText())){
        	util.Tool.alert("Age is required!");
        	return;
        }else{
            if(Integer.parseInt(age.getText())>100){
            	age.setText("");
            	util.Tool.alert("Age is invalid!");
            	return;
            }
        }
        int update = DataService.addStudent(new Student(0, firstName.getText(), lastName.getText(), Integer.parseInt(age.getText())));
        if(update==1){
        	firstName.setText("");
        	lastName.setText("");
        	age.setText("");
        	util.Tool.alert("Successful !");
        }else{
           	firstName.setText("");
        	lastName.setText("");
        	age.setText("");
        	util.Tool.alert("Fail !");
        }

    }

    @FXML
    private void back(ActionEvent event) throws FileNotFoundException, IOException {
        Parent root = new FXMLLoader().load(new FileInputStream("src/fx/FXML/MainFXML.fxml"));
        Scene scene = new Scene(root);
        ManuController.stage.close();
        ManuController.stage = new Stage();
        ManuController.stage.setTitle("Hot School System");
        ManuController.stage.setScene(scene);
        ManuController.stage.show();
    }
    
}
