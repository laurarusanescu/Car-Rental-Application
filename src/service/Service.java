package service;

import domain.Car;
import domain.Customer;
import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import repository.CarRepository;
import repository.CustomerRepository;
import repository.IRepository;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private final CarRepository repositoryCars;
    private final CustomerRepository repositoryCustomers;

    public Service(IRepository<Car, Integer> repositoryCars, IRepository<Customer, String> repositoryCustomers){
        this.repositoryCars = (CarRepository) repositoryCars;
        this.repositoryCustomers = (CustomerRepository) repositoryCustomers;
    }

    public int generateIdCars() {
        int maxId = repositoryCars.getMaxId();
        return maxId + 1;
    }
    public int getMaxIdCars(){
        int maxId1 = 1;

        for (Car car : repositoryCars.getAllCars()) {
            if (car.getID() > maxId1) {
                maxId1 = car.getID();
            }
        }

        return maxId1;
    }
    public void addCar(String name, int numberOfStars, int yearOfFabrication, int priceperHour) throws DuplicateItemException {
        int idGenerated = generateIdCars();
        this.repositoryCars.addCar1(new Car(idGenerated, name, numberOfStars, yearOfFabrication,priceperHour));
    }

    public void getCarByName(String name) {
        List<Car> cars = repositoryCars.getAllCars();

        for (Car car:cars){
            if(car.getName().equals(name)) {
                car.getCar();
                return;
            }
        }
    }

    public void getCarByID(int id) throws ItemNotFound{
        repositoryCars.getItemById(id);
    }

    public void updateCar(Integer id, String nameBefore, String nameUpdate, int numberOfStars, int yearOfFabrication, int priceperHour) {
        List<Car> cars = repositoryCars.getAllCars();
        for (Car car:cars){
            if(car.getName().equals(nameBefore)){
                car.setName(nameUpdate);
                car.setNumberStars(numberOfStars);
                car.setYearOfFabrication(yearOfFabrication);
                car.setPricePerDay(priceperHour);
                return;
            }
        }
    }
    public ArrayList<Car> getAllCars() {
        return repositoryCars.getAllCars();
    }


    public void deleteCarByName(String name) {
        List<Car> cars = repositoryCars.getAllCars();
        List<Car> carsToKeep = new ArrayList<>();

        for (Car car : cars) {
            if (!car.getName().equals(name)) {
                carsToKeep.add(car);
            }
        }

        // Replace the original list with the new list
        cars.clear();
        cars.addAll(carsToKeep);

    }
    public void deleteCarByID(int id) throws ItemNotFound{
        repositoryCars.deleteCarById(id);
    }

    public void returnCheapestCar(){
        repositoryCars.getCheapestCar();
    }

    public void returnExpensiveCar(){
        repositoryCars.getMostExpensiveCar();
    }

    public void returnFabricationCars(int year){
        if(year>1900 && year <2024) {
            repositoryCars.getCarsFabricatedYear(year);
        }
    }

    public int generateIdCustomers() {
        int maxId = getMaxIdCustomers();
        return maxId + 1;
    }
    public int getMaxIdCustomers(){
        int maxId1 = 1;

        for (Customer customer : repositoryCustomers.getAllCustomers()) {
            if (customer.getId() > maxId1) {
                maxId1 = customer.getId();
            }
        }

        return maxId1;
    }
    public void addCustomer( String Name, int PhoneNumber){
        int id = generateIdCustomers();
        repositoryCustomers.addCustomer(new Customer(id, Name, PhoneNumber));
    }

    public ArrayList<Customer> getAllCustomers(){
        return repositoryCustomers.getAllCustomers();
    }
    public void getCustomerById(int id) throws ItemNotFound{
        this.repositoryCustomers.getCustomerByID(id);
    }


    public void updateCustomer(int idUpdate, String nameUpdate, int phoneNumeberUpdate) throws ItemNotFound{
        Customer customerToUpdate = repositoryCustomers.getCustomerByID(idUpdate);
        if (!nameUpdate.equals(customerToUpdate.getName()))
            customerToUpdate.setName(nameUpdate);
        if(phoneNumeberUpdate != customerToUpdate.getPhoneNumber())
            customerToUpdate.setPhoneNumber(phoneNumeberUpdate);
        this.repositoryCustomers.updateCustomer( customerToUpdate);
    }

    public void deleteCustomerByID(int idDelete) throws ItemNotFound{
        Customer customerToDelete = repositoryCustomers.getCustomerByID(idDelete);
        this.repositoryCustomers.deleteCustomerByID(idDelete);
    }

    public void deleteAllCars(){
        List<Car> cars1 = repositoryCars.getAllCars();
        List<Car> carsToKeep1 = new ArrayList<>();
        cars1.clear();
    }

    public List<Car> getCarsBiggerPriceAlphabetically(int price) {
        return repositoryCars.getCarsBiggerPriceAlphabetically(price);
    }

    public List<Car> getNCheapestCarsOlderThanYear(int n, int year) {
        return repositoryCars.getNCheapestCarsOlderThanYear(n, year);
    }

    public List<Car> getCarsWithNStarsSortedByYear(int n) {
        return repositoryCars.getCarsWithNStarsSortedByYear(n);
    }

    public List<Customer> getCustomersAlphabeticallyWithIdBiggerN(int n) {
        return repositoryCustomers.getCustomersAlphabeticallyWithIdBiggerN(n);
    }

    public ArrayList<Customer> getCustomersNameEndingInSOrderedByID(String s) {
        return repositoryCustomers.getCustomersNameEndingInSOrderedByID(s);
    }

    public void deleteCar(Car c){
        repositoryCars.removeItemByID(c.getID());
    }

}
