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
import java.util.ArrayList;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Meng
 */
public class ShowCoursesController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private ScrollPane outputPane;
    @FXML
    private WebView browser;
    @FXML
    private Button back;
    @FXML
    private Button search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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

    @FXML
    private void search(ActionEvent event) {
        String html = "<table width='560' border='1px'> <tr><td>Course ID</td><td>Course Name</td><td>Start Time</td></tr>";
        ArrayList<Course> courses = new ArrayList<>();
        if(firstName.getText()==null || "".equals(firstName.getText()) || lastName.getText()==null || "".equals(lastName.getText())){
        	util.Tool.alert("First name and last name are required!");
        	return;
        }else{
            courses = DataService.getCoursesByName(firstName.getText(), lastName.getText());
            if(courses.size()<=0){
            	firstName.setText("");
            	lastName.setText("");
            	util.Tool.alert("The student name could not be found");
            	return;
            }
            for(int i=0;i<=courses.size()-1;i++){
            	html += "<tr><td>"+courses.get(i).getId()+"</td><td>"+courses.get(i).getName()+"</td><td>"+courses.get(i).getStartTime()+"</td></tr>";
            }
            html+="</table>";
            final WebEngine webEngine = browser.getEngine();
            webEngine.loadContent(html);
        }
    }
    
}
