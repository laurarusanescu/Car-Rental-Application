package repository;

import domain.Identifiable;

public interface IRepository<T extends Identifiable<U>, U> {
    public void addItem(T item);
    public void removeItemByID(U id);
    public T getItemById(U id);
    public Iterable<T> getAllItems();

}
