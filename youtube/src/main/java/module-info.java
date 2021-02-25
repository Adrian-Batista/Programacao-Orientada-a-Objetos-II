module br.com.adrianbatista.youtube {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;

    opens br.com.adrianbatista.youtube to javafx.fxml;
    exports br.com.adrianbatista.youtube;
}