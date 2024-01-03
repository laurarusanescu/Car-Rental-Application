package main;

import GUI.CarRentalAppController;
import domain.Car;
import domain.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.CarRepository;
import repository.CustomerRepository;
import repository.IRepository;
import service.Service;

public class main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        IRepository<Car, Integer> carRepo = new CarRepository();
        IRepository<Customer, String> customerRepo = new CustomerRepository();
        Service service = new Service(carRepo, customerRepo);
        CarRentalAppController controller = new CarRentalAppController(service);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/GUI.fxml"));
        loader.setController(controller);
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}