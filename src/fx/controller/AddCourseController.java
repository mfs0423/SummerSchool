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

import entity.Course;
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
public class AddCourseController implements Initializable {

    @FXML
    private TextField courseName;
    @FXML
    private TextField time;
    @FXML
    private Button add;
    @FXML
    private Button back;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCourse(ActionEvent event) {
        if(courseName.getText()==null || "".equals(courseName.getText())){
        	util.Tool.alert("Course name is required!");
        	return;
        }else if(courseName.getText().length()<=1 || !courseName.getText().matches( "^[a-zA-Z]+" )){
        	util.Tool.alert("Course name is invalid!");
        	return;
        }else if(time.getText()==null || "".equals(time.getText())){
        	util.Tool.alert("Time is required!");
        	return;
        }else{
        	String courseTime = time.getText();
        	String[] strs = courseTime.split(":");
            if(strs.length!=2 || Integer.parseInt(strs[0])>23 ||Integer.parseInt(strs[0])<0 
            		|| Integer.parseInt(strs[1])>59 ||Integer.parseInt(strs[1])<0 ){
            	time.setText("");
            	util.Tool.alert("Time is invalid!");
            	return;
            }
        }
        int update = DataService.addCourse(new Course(0, courseName.getText(), time.getText()));
        if(update==1){
        	courseName.setText("");
        	time.setText("");
        	util.Tool.alert("Successful !");
        }else{
        	courseName.setText("");
        	time.setText("");
        	util.Tool.alert("Fail !");
        }

    }

    @FXML
    private void goBack(ActionEvent event) throws FileNotFoundException, IOException {
        Parent root = new FXMLLoader().load(new FileInputStream("src/fx/FXML/MainFXML.fxml"));
        Scene scene = new Scene(root);
        ManuController.stage.close();
        ManuController.stage = new Stage();
        ManuController.stage.setTitle("Hot School System");
        ManuController.stage.setScene(scene);
        ManuController.stage.show();
    }
    
}
