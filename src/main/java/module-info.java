module testplatform {
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.graphics;

    opens testplatform to javafx.fxml,javafx.graphics;
}