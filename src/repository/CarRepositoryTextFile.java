package repository;

import domain.Car;

import java.io.*;

public class CarRepositoryTextFile extends FileRepository<Car, Integer>{
    private CarRepository carRepo;
    public CarRepositoryTextFile(String fileName) {
        super(fileName);
    }
    @Override
    protected void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = null;
            while ((line = reader.readLine())!=null){
                String[] stringArray = line.split(",");
                if(stringArray.length!=5){
                    continue;
                }else{
                    Car c = new Car(Integer.parseInt(stringArray[0].trim()), stringArray[1].trim(), Integer.parseInt(stringArray[2].trim()),Integer.parseInt(stringArray[3].trim()), Integer.parseInt(stringArray[4].trim()));
                    listOfItems.add(c);
                }
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for (Car c:getAllItems()){
                writer.write(c.getID() + ","+
                            c.getName() + ","+
                            c.getNumberStars() + ","+
                            c.getYearOfFabrication() + ","+
                            c.getPricePerDay() + "\n");
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
