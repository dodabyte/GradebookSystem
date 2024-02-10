module com.example.lab2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.hibernate.orm.hikaricp;
    requires com.zaxxer.hikari;

    opens com.example.lab2.objects;
    opens com.example.lab2.callbacks;
    opens com.example.lab2.cells;

    opens com.example.lab2 to javafx.fxml;
    exports com.example.lab2;
    exports com.example.lab2.controllers;
    opens com.example.lab2.controllers to javafx.fxml;
}