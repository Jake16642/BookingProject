module ProjectPhareII {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires java.sql;
	//requires java.mail;
	
	opens application to javafx.graphics, javafx.fxml;
}
