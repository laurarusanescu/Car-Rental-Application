package UI;

import exceptions.DuplicateItemException;
import exceptions.ItemNotFound;
import service.Service;

import java.util.Scanner;

public class UI {
    private final Service service;

    public UI(Service service){
        this.service = service;
    }

    public void printMenu(){
        System.out.println("Welcome to out car rental!");
        System.out.println("0. Exit");
        System.out.println("1. Make actions on cars");
        System.out.println("2. Make actions on customers");
    }
    public void run() throws DuplicateItemException, ItemNotFound {
        System.out.println("Please input the number of your command: ");
        Scanner scan = new Scanner(System.in);
        int command = scan.nextInt();
        switch (command){
            case 0:
                return;
            case 1:
                System.out.println("0. Exit");
                System.out.println("1. List all cars");
                System.out.println("2. Add a car");
                System.out.println("3. See a car by name");
                System.out.println("4. See all the cars");
                System.out.println("5. Update car ");
                System.out.println("6. Delete a car by name");
                System.out.println("7. Get the cheapest car");
                System.out.println("8. Get the most expensive car");
                System.out.println("9. Get the cars that were fabricated in a desired year");
                System.out.println("10. See a car by id");
                System.out.println("11. Delete a car by id");
                System.out.println("12. Get cars with bigger price alphabetically");
                System.out.println("13. Get cars cheaper and Older Than Year");
                System.out.println("14. Gen cars with chosen number of stars sorted by the year");

                System.out.println("Please input the number of your command: ");
                Scanner scan1 = new Scanner(System.in);
                int command1 = scan1.nextInt();
                switch (command1){
                    case 0:
                        return;
                    case 1:
                        service.getAllCars();
                        run();
                    case 2:
                        System.out.println("Write the details of the car that you want to add.");

                        System.out.println("Enter the name of the car:");
                        Scanner scanNameAdd = new Scanner(System.in);
                        String nameAddedCar = String.valueOf(scanNameAdd.nextLine());

                        System.out.println("Enter the number of stars of the car:");
                        Scanner scanStarsAdd = new Scanner(System.in);
                        int numberOfStarsAddedCar = scanStarsAdd.nextInt();

                        System.out.println("Enter the year of fabrication of the car:");
                        Scanner scanYearAdd = new Scanner(System.in);
                        int yearOfFabricationAddedCar = scanYearAdd.nextInt();

                        System.out.println("Enter the price per day of the added car:");
                        Scanner scanPirceAdd= new Scanner(System.in);
                        int pricePerHourAddedCar = scanPirceAdd.nextInt();
                        int idAdd= service.generateIdCars();
                        try {
                            service.addCar(nameAddedCar, numberOfStarsAddedCar, yearOfFabricationAddedCar, pricePerHourAddedCar);
                        }catch(DuplicateItemException e) {
                            System.out.println(e.getMessage());
                        }
                        run();
                    case 3:
                        System.out.println("Write the name of the car that you want to see:");
                        Scanner scanNameGetName = new Scanner(System.in);
                        String nameCarGetByName = String.valueOf(scanNameGetName.nextLine());

                        service.getCarByName(nameCarGetByName);
                        run();
                    case 4:
                        System.out.println(service.getAllCars());
                        run();
                    case 5:
                        System.out.println("Enter the name of the car that you want to update the data:");
                        Scanner scanName = new Scanner(System.in);
                        String name = String.valueOf(scanName.nextLine());

                        System.out.println("Enter the new name of the car:");
                        Scanner scanNameUpdate = new Scanner(System.in);
                        String nameUpdate = String.valueOf(scanNameUpdate.nextLine());

                        System.out.println("Enter the new number of stars:");
                        Scanner scanStarsUpdate = new Scanner(System.in);
                        int starsUpdate = scanStarsUpdate.nextInt();

                        System.out.println("Enter the new year of fabrication:");
                        Scanner scanYearUpdate = new Scanner(System.in);
                        int yearUpdate = scanYearUpdate.nextInt();

                        System.out.println("Enter the new price per day:");
                        Scanner scanPriceUpdate = new Scanner(System.in);
                        int priceUpdate = scanPriceUpdate.nextInt();
                        int idUpdate = service.generateIdCars();

                        service.updateCar(idUpdate ,name, nameUpdate, starsUpdate, yearUpdate, priceUpdate);
                        run();
                    case 6:
                        System.out.println("Write the name of the car that you want to delete:");
                        Scanner scanNameDelete = new Scanner(System.in);
                        String nameDelete = String.valueOf(scanNameDelete.nextLine());

                        service.deleteCarByName(nameDelete);
                        run();
                    case 7:
                        service.returnCheapestCar();
                        run();
                    case 8:
                        service.returnExpensiveCar();
                        run();
                    case 9:
                        System.out.println("Write the year that you want:");
                        Scanner scanYear = new Scanner(System.in);
                        int yearChoosen = scanYear.nextInt();
                        service.returnFabricationCars(yearChoosen);
                        run();
                    case 10:
                        System.out.println("Write the id of the cer:");
                        Scanner scanId = new Scanner(System.in);
                        int idSearched = scanId.nextInt();

                        try {
                            service.getCarByID(idSearched);
                        }catch(ItemNotFound i){
                            System.out.println(i.getMessage());
                        }
                        run();
                    case 11:
                        System.out.println("Write the id of the car that you want to delete:");
                        Scanner scanIdDel = new Scanner(System.in);
                        int idDelete = scanIdDel.nextInt();

                        try {
                            service.deleteCarByID(idDelete);
                        }catch(ItemNotFound i){
                            System.out.println(i.getMessage());
                        }
                        run();
                    case 12:
                        System.out.println("Enter the price per day car:");
                        Scanner scanPirceSorting= new Scanner(System.in);
                        int pricePerHourSort = scanPirceSorting.nextInt();
                        service.getCarsBiggerPriceAlphabetically(pricePerHourSort);
                    case 13:
                        System.out.print("Enter the number of cars (N): ");
                        Scanner n = new Scanner(System.in);
                        int number = n.nextInt();
                        System.out.print("Enter the year threshold: ");
                        Scanner year = new Scanner(System.in);
                        int yearC = year.nextInt();
                        service.getNCheapestCarsOlderThanYear(number, yearC);
                    case 14:
                        System.out.println("Enter the new number of stars:");
                        Scanner scanStars = new Scanner(System.in);
                        int stars = scanStars.nextInt();
                        service.getCarsWithNStarsSortedByYear(stars);
                    default:
                        System.out.println("The command is invalid.");
                        break;
                }
            case 2:
                System.out.println("0. Exit");
                System.out.println("1.See all the customers");
                System.out.println("2.See a customer by id");
                System.out.println("3.Update a costumer's information");
                System.out.println("4.Delete a customer by id");
                System.out.println("5. Add a customer");
                System.out.println("6. get Customers Alphabetically With Id Bigger N");
                System.out.println("7. get Customers Name Ending In S Ordered By ID");
                System.out.println("Write the comand that you want to execute");
                Scanner scanCustomer = new Scanner(System.in);
                int command2 = scanCustomer.nextInt();
                switch(command2){
                    case 0:
                        return;
                    case 1:
                        service.getAllCustomers();
                        run();
                    case 2:
                        System.out.println("Write the id of the customer that you want ot see:");
                        Scanner scanIdCustomerGet = new Scanner(System.in);
                        int idGetCustomer = scanIdCustomerGet.nextInt();
                        try {
                            service.getCustomerById(idGetCustomer);
                        }catch(ItemNotFound i){
                            System.out.println(i.getMessage());
                        }
                        run();
                    case 3:
                        System.out.println("Write the id of the customer that you want to update:");
                        Scanner scanIDCustomerUpdate = new Scanner(System.in);
                        int idCustomerUpdate = scanIDCustomerUpdate.nextInt();

                        System.out.println("Write the name of the car that you want to update the data:");
                        Scanner scanNameCustomer = new Scanner(System.in);
                        String nameCustomerUpdate = String.valueOf(scanNameCustomer.nextLine());

                        System.out.println("Write the phone number that you want tot update:");
                        Scanner scanPhoneNumber = new Scanner(System.in);
                        int phoneNumberUpdate = scanPhoneNumber.nextInt();
                        try {
                            service.updateCustomer(idCustomerUpdate, nameCustomerUpdate, phoneNumberUpdate);
                        }catch(ItemNotFound i){
                            System.out.println(i.getMessage());
                        }
                        run();
                    case 4:
                        System.out.println("Write  the id of the customer that you want to delete:");
                        Scanner scanIdCustomerDel = new Scanner(System.in);
                        int idCustomerDel = scanIdCustomerDel.nextInt();
                        try {
                            service.deleteCustomerByID(idCustomerDel);
                        }catch(ItemNotFound i){
                            System.out.println(i.getMessage());
                        }
                        run();
                    case 5:
                        System.out.println("Write the name of the car that you want to add the data:");
                        Scanner scanNameCustomerAdd = new Scanner(System.in);
                        String nameCustomerADD = String.valueOf(scanNameCustomerAdd.nextLine());

                        System.out.println("Write the phone number that you want tot add:");
                        Scanner scanPhoneNumberADD = new Scanner(System.in);
                        int phoneNumberAdd = scanPhoneNumberADD.nextInt();
                        service.addCustomer(nameCustomerADD, phoneNumberAdd);
                    case 6:
                        System.out.println("Write the id(n):");
                        Scanner scanIDCustomer = new Scanner(System.in);
                        int idCustomer = scanIDCustomer.nextInt();
                        service.getCustomersAlphabeticallyWithIdBiggerN(idCustomer);
                    case 7:
                        System.out.println("Write the character:");
                        Scanner scanChar = new Scanner(System.in);
                        String c = String.valueOf(scanChar.nextLine());
                        service.getCustomersNameEndingInSOrderedByID(c);
                }
        }


    }

}
