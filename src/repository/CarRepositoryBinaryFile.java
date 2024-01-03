package repository;

import domain.Car;

import java.io.*;
import java.util.ArrayList;

public class CarRepositoryBinaryFile extends FileRepository<Car, Integer>{

    public CarRepositoryBinaryFile(String fileName) {
        super(fileName);
    }

    @Override
    protected void readFromFile() {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            this.listOfItems = (ArrayList<Car>) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(this.listOfItems);
        }catch (IOException e){
            throw  new RuntimeException(e);
        }
    }

}
