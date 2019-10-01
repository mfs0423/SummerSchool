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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Meng
 */
public class EnrollController implements Initializable {

    @FXML
    private ScrollPane outputPane;
    @FXML
    private Button back;
    @FXML
    private Button btn;
    @FXML
    private Button enroll;
    @FXML
    private TableView table;
    
    private Student s;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	ArrayList<Student> students = DataService.getAllStudents();
        TableColumn<String,Student> column1 = new TableColumn<>(" ID ");
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<String,Student> column2 = new TableColumn<>(" First Name ");
        column2.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<String,Student> column3 = new TableColumn<>(" Last Name ");
        column3.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        TableColumn<String,Student> column4 = new TableColumn<>(" Age ");
        column4.setCellValueFactory(new PropertyValueFactory<>("age"));
        table.getColumns().add(column1);
        table.getColumns().add(column2);
        table.getColumns().add(column3);
        table.getColumns().add(column4);
    	for(int i=0;i<students.size();i++){
    		table.getItems().add(students.get(i));
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

    @FXML
    private void showList(ActionEvent event) {
    	String btnStr = btn.getText();
    	if("Confirm".equals(btnStr)){
    		ObservableList selectedItems = table.getSelectionModel().getSelectedItems();
    		s = (Student)(selectedItems.get(0));
    		if(s==null){
    			util.Tool.alert("You have to choose a student first!");
    			return;
    		}
    		table.getItems().clear();
    		table.getColumns().clear();
            TableColumn<String,Course> column1 = new TableColumn<>(" ID ");
            column1.setCellValueFactory(new PropertyValueFactory<>("id"));
            
            TableColumn<String,Course> column2 = new TableColumn<>(" Course Name ");
            column2.setCellValueFactory(new PropertyValueFactory<>("name"));
            
            TableColumn<String,Course> column3 = new TableColumn<>(" Start Time ");
            column3.setCellValueFactory(new PropertyValueFactory<>("startTime"));
            table.getColumns().add(column1);
            table.getColumns().add(column2);
            table.getColumns().add(column3);
           
            ArrayList<Course> courses = new ArrayList<>();
            courses = DataService.getUnEnrollCourses(s.getId());
            if(courses.size()==0){
            	util.Tool.alert("This student already enrolled all courses!");
            	return;
            }
        	for(int i=0;i<courses.size();i++){
        		table.getItems().add(courses.get(i));
        	}
        	btn.setText("Back");
    	}else{
            refresh();
    	}
    	
    }

    @FXML
    private void enroll(ActionEvent event) {
    	String btnStr = btn.getText();
    	if("Confirm".equals(btnStr)){
    		util.Tool.alert("You have to confirm a student first!");
			return;
    	}
		ObservableList selectedItems = table.getSelectionModel().getSelectedItems();
		Course course = (Course)(selectedItems.get(0));
		if(course==null){
			util.Tool.alert("You have to choose a course first!");
			return;
		}
		int update = DataService.addStudentCourse(s.getId(),course.getId());
        if(update==1){
        	util.Tool.alert("Successful !");
        }else{
        	util.Tool.alert("Fail !");
        	return;
        }
        refresh();
    }
    private void refresh(){
		btn.setText("Confirm");
		table.getItems().clear();
		table.getColumns().clear();
		initialize(null,null);
    }
    
}
