module com.restaurant.restaurant_management_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires annotations;

    exports com.restaurant.restaurant_management_project;
    exports com.restaurant.restaurant_management_project.controller to javafx.fxml;
    opens com.restaurant.restaurant_management_project.controller to  javafx.fxml;
}
