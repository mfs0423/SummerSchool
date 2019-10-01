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

import service.DataService;
import entity.Course;
import entity.Student;
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
public class ShowStudentsController implements Initializable {

    @FXML
    private ScrollPane outputPane;
    @FXML
    private WebView browser;
    @FXML
    private Button back;
    @FXML
    private Button search;
    @FXML
    private TextField courseName;

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
        String html = "<table width='560' border='1px'> <tr><td>Student ID</td>"
        				+ "<td>First Name</td><td>Last Name</td><td>Age</td></tr>";
        ArrayList<Student> students = new ArrayList<>();
        if(courseName.getText()==null || "".equals(courseName.getText())){
        	util.Tool.alert("Course name is required!");
        	return;
        }else{
        	students = DataService.getStudentsByCourse(courseName.getText());
            if(students.size()<=0){
            	courseName.setText("");
            	util.Tool.alert("The course name could not be found or no student join the course!");
            	return;
            }
            for(int i=0;i<=students.size()-1;i++){
            	html += "<tr><td>"+students.get(i).getId()+"</td><td>"+students.get(i).getFirstName()+"</td>"
            			+ "<td>"+students.get(i).getLastName()+"</td><td>"+students.get(i).getAge()+"</td></tr>";
            }
            html+="</table>";
            final WebEngine webEngine = browser.getEngine();
            webEngine.loadContent(html);
        }
    }
    
}
