package repository;

import domain.Identifiable;

import java.util.ArrayList;

public class MemoryRepository<T extends Identifiable<U>, U> implements IRepository<T,U>  {
    protected ArrayList<T> listOfItems = new ArrayList<T>();

    @Override
    public void addItem(T item){
        listOfItems.add(item);
    }

    @Override
    public void removeItemByID(U id) {
        T c = listOfItems.get((Integer) id);
        listOfItems.remove(c);
    }


    @Override
    public T getItemById(U id){
        return listOfItems.get((Integer) id);
    }



    @Override
    public Iterable<T> getAllItems(){return listOfItems;}

}
