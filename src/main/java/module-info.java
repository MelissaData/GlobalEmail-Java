module com.melissadata.globalemail {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.google.gson;
    requires jdk.crypto.ec;
    requires java.xml;

    opens com.melissadata.globalemail to javafx.fxml;
    opens com.melissadata.globalemail.model to javafx.fxml;
    opens com.melissadata.globalemail.view to javafx.fxml;
    exports com.melissadata.globalemail;
}
