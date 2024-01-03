package repository;
import domain.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarRepository extends MemoryRepository<Car, Integer>{
    public ArrayList<Car> cars = new ArrayList<>();

    public void addCar1(Car c){
        cars.add(c);
    }
    public void deleteCarById(int id){
        for (Car c :cars){
            if (c.getID() ==id){
                cars.remove(c);
            }
        }
    }

    public ArrayList<Car> getAllCars(){
        return this.cars;
    }
    public String getCheapestCar(){
        int minPricePerHour = 5000000;
        for (Car car:cars){
            if(minPricePerHour > car.getPricePerDay()){
                minPricePerHour = car.getPricePerDay();
            }
        }
        for(Car car:cars){
            if(minPricePerHour == car.getPricePerDay())
                return car.toString();
        }
        return null;
    }

    public String getMostExpensiveCar(){
        int maxPricePerHour = 0;
        for (Car car:cars){
            if(maxPricePerHour < car.getPricePerDay()){
                maxPricePerHour = car.getPricePerDay();
            }
        }
        for(Car car:cars){
            if(maxPricePerHour == car.getPricePerDay())
                return car.toString();
        }
        return null;
    }

    public ArrayList<Car> getCarsFabricatedYear(int year){
        List<Car> fabricatedInYear = new ArrayList<>();
        for (Car car:cars){
            if(car.getYearOfFabrication()==year){
                fabricatedInYear.add(car);
            }
        }
        return (ArrayList<Car>) fabricatedInYear;
    }

    public int getMaxId() {
        int maxId = 0;
        for (Car car : cars) { // Assuming cars is the collection of cars in your repository.
            if (car.getID() > maxId) {
                maxId = car.getID();
            }
        }
        return maxId;
    }

    public List<Car> getCarsBiggerPriceAlphabetically(int price) {
        return cars.stream()
                .filter(car -> car.getPricePerDay() > price)
                .sorted(Comparator.comparing(Car::getName))
                .collect(Collectors.toList());
    }

    public List<Car> getNCheapestCarsOlderThanYear(int n, int year){
        return cars.stream()
                .filter(car -> car.getYearOfFabrication() < year)
                .sorted(Comparator.comparingInt(Car::getPricePerDay))
                .limit(n)
                .collect(Collectors.toList());
    }
    public List<Car> getCarsWithNStarsSortedByYear(int n){
        return cars.stream()
                .filter(car -> car.getNumberStars() == n)
                .sorted(Comparator.comparingInt(Car::getYearOfFabrication))
                .collect(Collectors.toList());
    }
}
