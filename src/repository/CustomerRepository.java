package repository;

import domain.Customer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CustomerRepository extends MemoryRepository<Customer, String> {
    public ArrayList<Customer> listOfCustomers = new ArrayList<>();

    public void addCustomer(Customer c){
        listOfCustomers.add(c);}

    public Customer getCustomerByID(int id){
        for(Customer customer: listOfCustomers){
            if(customer.getId()==id)
                return customer;
        }
        return null;
    }

    public void updateCustomer(Customer c){
        for(Customer customer: listOfCustomers){
            if(customer.getId()== c.getId()){
                listOfCustomers.set(listOfCustomers.indexOf(customer), c);
            }
        }
    }
    public ArrayList<Customer> getAllCustomers(){return this.listOfCustomers;}

    public void deleteCustomerByID(int id) {
        for (Customer customer : listOfCustomers) {
            if (customer.getId() == id) {
                listOfCustomers.remove(customer);
                break;
            }
        }
    }

    public ArrayList<Customer> getCustomersAlphabeticallyWithIdBiggerN(int n) {
        return (ArrayList<Customer>) listOfCustomers.stream()
                .filter(customer -> customer.getId() > n)
                .sorted(Comparator.comparing(Customer::getName))
                .collect(Collectors.toList());
    }

    public ArrayList<Customer> getCustomersNameEndingInSOrderedByID(String s){
        return listOfCustomers.stream()
                .filter(customer -> customer.getName().endsWith(String.valueOf(s)))
                .sorted(Comparator.comparingInt(Customer::getId))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
