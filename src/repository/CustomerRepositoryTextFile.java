package repository;

import domain.Customer;

import java.io.*;

public class CustomerRepositoryTextFile extends FileRepository<Customer, String> {
    public CustomerRepositoryTextFile(String fileName) {
        super(fileName);
    }

    @Override
    protected void readFromFile() {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line = null;
            while((line = reader.readLine())!=null){
                String[] strings = line.split(",");
                if(strings.length != 3){
                    continue;
                }else{
                    Customer c = new Customer(Integer.parseInt(strings[0].trim()), strings[1].trim(), Integer.parseInt(strings[2].trim()));
                    this.listOfItems.add(c);
                }
            }
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for(Customer c:getAllItems()){
                writer.write(c.getID()+ ","+
                        c.getName()+ "," +
                        c.getPhoneNumber()+".\n");
            }
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
