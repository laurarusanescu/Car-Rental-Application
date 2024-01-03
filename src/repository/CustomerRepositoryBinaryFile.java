package repository;

import domain.Customer;

import java.io.*;
import java.util.ArrayList;

public class CustomerRepositoryBinaryFile extends FileRepository<Customer, String>{
    public CustomerRepositoryBinaryFile(String fileName) {
        super(fileName);
    }

    @Override
    protected void readFromFile() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            this.listOfItems = (ArrayList<Customer>) ois.readObject();
        }catch (IOException e){
            throw new RuntimeException(e);
        }catch (ClassNotFoundException c){
            throw new RuntimeException(c);
        }
    }

    @Override
    protected void writeToFile() {
        try(ObjectOutputStream oos= new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(this.listOfItems);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }
}
