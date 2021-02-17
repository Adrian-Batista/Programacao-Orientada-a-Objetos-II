module br.com.adrianbatista.youtube {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;

    opens br.com.adrianbatista.youtube to javafx.fxml;
    exports br.com.adrianbatista.youtube;
}