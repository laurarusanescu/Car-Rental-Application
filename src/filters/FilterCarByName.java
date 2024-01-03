package filters;

import domain.Car;

public class FilterCarByName {
    private String name;

    public FilterCarByName(String name) {
        this.name = name;
    }

    public boolean accept(Car c){return c.getName().equals(name);}
}
