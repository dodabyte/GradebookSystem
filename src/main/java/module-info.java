module com.example.lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.hibernate.orm.hikaricp;
    requires com.zaxxer.hikari;
    requires spring.security.crypto;
    requires mail;
    requires aspose.cells;

    opens com.example.lab2.callbacks;
    opens com.example.lab2.cells;

    opens com.example.lab2 to javafx.fxml;
    exports com.example.lab2;
    exports com.example.lab2.controllers;
    opens com.example.lab2.controllers to javafx.fxml;
    opens com.example.lab2.objects.main;
    opens com.example.lab2.objects.references;
    opens com.example.lab2.controls;
    opens com.example.lab2.controls.global;
}