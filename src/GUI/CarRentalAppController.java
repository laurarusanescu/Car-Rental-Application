package GUI;

import domain.Car;
import domain.Customer;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import service.Service;

import java.util.Collection;

public class CarRentalAppController {
    public TextField nameCar;
    public TextField starsCar;
    public TextField yearCar;
    public TextField priceCar;
    public TextField nameCustomer;
    public TextField phoneCustomer;
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
    private ListView<Customer> customerList= new ListView<>();
    @FXML
    private ListView<Car> carChoosen = new ListView<>();
    @FXML
    private ListView<Customer> customerChoosen = new ListView<>();

    @FXML
    private Button addCarButton;

    @FXML
    private Button updateCarButton;

    @FXML
    private Button deleteCarButton;

    @FXML
    private Button addCustomerButton;

    @FXML
    private Button updateCustomerButton;

    @FXML
    private Button deleteCustomerButton;
    @FXML
    private TextField idCarDelete;

    @FXML
    private TextField idCustomerDelete;

    @FXML
    private Button getList;

    @FXML
    private TextField priceBigger;

    @FXML
    private TextField price2;

    @FXML
    private TextField stars3;

    @FXML
    private TextField id4;

    @FXML
    private TextField name5;

    @FXML
    private TextField year2;
    @FXML
    private TextField price1;

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
    void populateCustomerList() {
        Collection<Customer> customerR = service.getAllCustomers();
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(customerR);
        customerList.setItems(customerObservableList);

    }

    public void getListChoosen(MouseEvent event){
        if(price1!=null){
            Collection<Car> carR = service.getCarsBiggerPriceAlphabetically(Integer.parseInt(price1.getText()));
            ObservableList<Car> carObservableList = FXCollections.observableArrayList(carR);
            carChoosen.setItems(carObservableList);
        } else if (price2!=null) {
            Collection<Car> carR = service.getNCheapestCarsOlderThanYear(Integer.parseInt(price2.getText()), Integer.parseInt(year2.getText()));
            ObservableList<Car> carObservableList = FXCollections.observableArrayList(carR);
            carChoosen.setItems(carObservableList);
        } else if (stars3!=null) {
            Collection<Car> carR = service.getCarsWithNStarsSortedByYear(Integer.parseInt(stars3.getText()));
            ObservableList<Car> carObservableList = FXCollections.observableArrayList(carR);
            carChoosen.setItems(carObservableList);
        } else if (id4!=null){
            Collection<Customer> customerR = service.getCustomersAlphabeticallyWithIdBiggerN(Integer.parseInt(id4.getText()));
            ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(customerR);
            customerChoosen.setItems(customerObservableList);
        }else if(name5!=null){
            Collection<Customer> customerR = service.getCustomersNameEndingInSOrderedByID(name5.getText());
            ObservableList<Customer> customerObservableList = FXCollections.observableArrayList(customerR);
            customerChoosen.setItems(customerObservableList);
        }
    }

    public void initialize() {
        populateCarList();
        populateCustomerList();
        SelectionMode modeCar = carsList.getSelectionModel().getSelectionMode();
        SelectionMode modeCustomer = customerList.getSelectionModel().getSelectionMode();
    }

    @FXML
    void onClickList(javafx.scene.input.MouseEvent event) {
        int idx = carsList.getSelectionModel().getSelectedIndex();
        ObservableList<Car> cars = carsList.getItems();

        if (idx < 0 || idx >= cars.size()) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setContentText("Invalid selection!");
            dialog.show();
        } else {
            Car selectedCar = cars.get(idx);
            nameCar.setText(selectedCar.getName());
            starsCar.setText(String.valueOf(selectedCar.getNumberStars()));
            yearCar.setText(String.valueOf(selectedCar.getYearOfFabrication()));
            priceCar.setText(String.valueOf(selectedCar.getPricePerDay()));
        }
    }

    @FXML
    public void addCar(MouseEvent event) throws DuplicateItemException {
        service.addCar(nameCar.getText(), Integer.parseInt(starsCar.getText()), Integer.parseInt(yearCar.getText()), Integer.parseInt(priceCar.getText()));
        clearCarFields();
        populateCarList();
    }

    @FXML
    public void updateCar(MouseEvent event) throws ItemNotFound, DuplicateItemException {
        Car selectedCar = carsList.getSelectionModel().getSelectedItem();
        nameCar.setText(selectedCar.getName());
        starsCar.setText(String.valueOf(selectedCar.getNumberStars()));
        yearCar.setText(String.valueOf(selectedCar.getYearOfFabrication()));
        priceCar.setText(String.valueOf(selectedCar.getPricePerDay()));
        service.deleteCarByID(selectedCar.getID());
        service.addCar(nameCar.getText(), Integer.parseInt(starsCar.getText()), Integer.parseInt(yearCar.getText()), Integer.parseInt(priceCar.getText()));
        populateCarList();
        clearCarFields();
    }

    @FXML
    private void deleteCar(MouseEvent event) throws ItemNotFound {
        String idCarText = idCarDelete.getText();
        int idCar = Integer.parseInt(idCarText);
        service.deleteCarByID(idCar);
        populateCarList();
        clearCarFields();
    }


    @FXML
    private void addCustomer(MouseEvent event) {
        service.addCustomer(nameCustomer.getText(), Integer.parseInt(phoneCustomer.getText()));
        clearCustomerFields();
        populateCustomerList();
    }

    @FXML
    private void updateCustomer(MouseEvent event) throws ItemNotFound {
        Customer customerSelected = customerList.getSelectionModel().getSelectedItem();
        nameCustomer.setText(customerSelected.getName());
        phoneCustomer.setText(String.valueOf(customerSelected.getPhoneNumber()));
        service.deleteCustomerByID(customerSelected.getId());
    }

    @FXML
    private void deleteCustomer(MouseEvent event) throws ItemNotFound {
        service.deleteCustomerByID(Integer.parseInt(idCustomerDelete.getText()));
        clearCustomerFields();
        populateCustomerList();
    }

    private void clearCarFields() {
        nameCar.clear();
        starsCar.clear();
        yearCar.clear();
        priceCar.clear();
        idCarDelete.clear();
    }

    private void clearCustomerFields() {
        nameCustomer.clear();
        phoneCustomer.clear();
        idCustomerDelete.clear();
    }
}
