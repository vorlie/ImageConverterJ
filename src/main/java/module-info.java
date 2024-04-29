module com.vorlie.testapp.testapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;

    opens com.vorlie.ImageConverter to javafx.fxml;
    exports com.vorlie.ImageConverter;
}