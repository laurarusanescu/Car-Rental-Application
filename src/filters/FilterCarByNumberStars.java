package filters;

import domain.Car;

public class FilterCarByNumberStars implements Filter<Car> {
    private Double minNumberStars;

    public FilterCarByNumberStars(Double minNumberStars) {
        this.minNumberStars = minNumberStars;
    }

    public boolean accept(Car c){
        return c.getNumberStars()> minNumberStars;
    }
}
