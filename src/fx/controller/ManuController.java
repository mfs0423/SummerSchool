/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controller;

import fx.applications.HotSchool;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *
 * @author Meng
 */
public class ManuController implements Initializable {

    @FXML
    private Button addCourse;
    @FXML
    private Button removeCourse;
    @FXML
    private Button addStudent;
    @FXML
    private Button enroll;
    @FXML
    private Button unEnroll;
    @FXML
    private Button listCourses;
    @FXML
    private Button listStudents;
    @FXML
    private Button start;
    @FXML
    private Label label;
    
    public static Stage stage = new Stage();
    
    @FXML
    private void start(ActionEvent event) throws IOException {
        
        Parent root = new FXMLLoader().load(new FileInputStream("src/fx/FXML/MainFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Hot School System");
        stage.setScene(scene);
        stage.show();
        HotSchool.mystage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    @FXML
    private void addCourse(ActionEvent event) throws FileNotFoundException, IOException {
    	showStage("src/fx/FXML/AddCourseFXML.fxml");
    }

    @FXML
    private void removeCourse(ActionEvent event) throws FileNotFoundException, IOException {
    	showStage("src/fx/FXML/RemoveCourseFXML.fxml");
    }

    @FXML
    private void addStudent(ActionEvent event) throws FileNotFoundException, IOException {
    	showStage("src/fx/FXML/AddStudentFXML.fxml");
    }

    @FXML
    private void enroll(ActionEvent event) throws FileNotFoundException, IOException {
    	showStage("src/fx/FXML/EnrollFXML.fxml");
    }

    @FXML
    private void unEnroll(ActionEvent event) throws FileNotFoundException, IOException {
    	showStage("src/fx/FXML/UnEnrollFXML.fxml");
    }

    @FXML
    private void listCourses(ActionEvent event) throws FileNotFoundException, IOException {
        showStage("src/fx/FXML/ShowCoursesFXML.fxml");
    }

    @FXML
    private void listStudents(ActionEvent event) throws FileNotFoundException, IOException {
        showStage("src/fx/FXML/ShowStudentsFXML.fxml");
    }
    
    private void showStage(String url) throws FileNotFoundException, IOException{
    	Parent root = new FXMLLoader().load(new FileInputStream(url));
        Scene scene = new Scene(root);
        stage.setTitle("Hot School System");
        stage.setScene(scene);
        stage.show();
    }

    
}
