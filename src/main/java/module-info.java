module edu.co.uniquindio.poo.monedero {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens edu.co.uniquindio.poo.monedero.viewController to javafx.fxml;

    opens edu.co.uniquindio.poo.monedero.model to javafx.base;

    opens edu.co.uniquindio.poo.monedero.app to javafx.fxml;

    exports edu.co.uniquindio.poo.monedero.app;
    exports edu.co.uniquindio.poo.monedero.viewController;
    exports edu.co.uniquindio.poo.monedero.model;
    exports edu.co.uniquindio.poo.monedero.controller;
}
