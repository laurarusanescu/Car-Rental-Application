package src.GUI;

import domain.Car;
import exceptions.DuplicateItemException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import service.Service;

import java.util.Collection;

public class CarRentalAppController {
    Service service ;


    private void addSampleCars() {
        try {
            service.addCar("Tesla", 4, 2022, 100);
            service.addCar("Seat", 5, 2021, 120);
            service.addCar("Reno", 3, 2020, 80);
        } catch (DuplicateItemException e) {
            // Handle exception if needed
            e.printStackTrace();
        }
    }

    private void addSampleCustomers() {
        service.addCustomer("Ana", 123456789);
        service.addCustomer("Launa", 987654321);
        service.addCustomer("Vald", 06543466555);
    }

    @FXML
    private ListView<Car> carsList;

    @FXML
    private ListView<String> customerList= new ListView<>();

    @FXML
    private TextField nameCar = new TextField();

    @FXML
    private TextField starsCar= new TextField();

    @FXML
    private TextField yearCar= new TextField();

    @FXML
    private TextField priceCar= new TextField();

    @FXML
    private Button addCarButton;

    @FXML
    private Button updateCarButton;

    @FXML
    private Button deleteCarButton;

    @FXML
    private TextField nameCustomer= new TextField();

    @FXML
    private TextField phoneCustomer= new TextField();

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button deleteCustomerButton;

    public CarRentalAppController(Service s) {
        this.service = s;

        // Add some sample data
        addSampleCars();
        addSampleCustomers();
    }


    void populateCarList(){
        Collection<Car> carR = service.getAllCars();
        ObservableList<Car> carObservableList = FXCollections.observableArrayList(carR);
        carsList.setItems(carObservableList);
    }

    public void initialize() {
        populateCarList();
        SelectionMode modeCar = carsList.getSelectionModel().getSelectionMode();
    }



    @FXML
    private void addCar(ActionEvent event) throws DuplicateItemException {
        String carDetails = String.format("%s - Stars: %s, Year: %s, Price: %s",
                nameCar.getText(), starsCar.getText(), yearCar.getText(), priceCar.getText());
        int len = service.getAllCars().size();
        service.addCar(nameCar.getText(), Integer.parseInt(starsCar.getText()), Integer.parseInt(yearCar.getText()), Integer.parseInt(priceCar.getText()));
        clearCarFields();
    }

    @FXML
    private void updateCar(ActionEvent event) {
        // Implement logic for updating a car
    }

    @FXML
    private void deleteCar(ActionEvent event) {
        // Implement logic for deleting a car
        int selectedIndex = carsList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            carsList.getItems().remove(selectedIndex);
        }
    }


    @FXML
    private void addCustomer(ActionEvent event) {
        // Implement logic for adding a customer
        String customerDetails = String.format("%s - Phone: %s",
                nameCustomer.getText(), phoneCustomer.getText());
        customerList.getItems().add(customerDetails);
        clearCustomerFields();
    }

    @FXML
    private void updateCustomer(ActionEvent event) {
        // Implement logic for updating a customer
    }

    @FXML
    private void deleteCustomer(ActionEvent event) {
        // Implement logic for deleting a customer
        int selectedIndex = customerList.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            customerList.getItems().remove(selectedIndex);
        }
    }

    private void clearCarFields() {
        nameCar.clear();
        starsCar.clear();
        yearCar.clear();
        priceCar.clear();
    }

    private void clearCustomerFields() {
        nameCustomer.clear();
        phoneCustomer.clear();
    }
}
