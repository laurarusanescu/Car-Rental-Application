package repository;

import domain.Identifiable;

public abstract class FileRepository <T extends Identifiable<U> , U> extends MemoryRepository<T,U>{
    protected String fileName;

    public FileRepository(String fileName){
        this.fileName = fileName;
        readFromFile();
    }
    protected abstract void readFromFile();
    protected abstract void writeToFile();

    @Override
    public void addItem(T item){
        super.addItem(item);
        writeToFile();
    }

    @Override
    public void removeItemByID(U id){
        super.removeItemByID(id);
        writeToFile();
    }
}
