module fr.eseo.lpjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens fr.eseo.lpjfx to javafx.fxml;
    exports fr.eseo.lpjfx;
    exports fr.eseo.lpjfx.controller;
    opens fr.eseo.lpjfx.controller to javafx.fxml;
}