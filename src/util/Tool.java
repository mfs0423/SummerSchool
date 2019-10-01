package util;

import javafx.scene.control.Alert;

public class Tool {

	public static void alert(String msg){
    	Alert information = new Alert(Alert.AlertType.INFORMATION,msg);
    	information.setTitle("information"); 
    	information.setHeaderText("Information");
    	information.showAndWait();
	    
	}
}
