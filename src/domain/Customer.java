package domain;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Identifiable<String>, Serializable {
    int id;
    int phoneNumber;
    String name;

    public Customer(int id, String name, int phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getID() {
        return null;
    }

    @Override
    public void setID(String id) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return phoneNumber == customer.phoneNumber && Objects.equals(id, customer.id) && Objects.equals(name, customer.name);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name +
                ", phone=" + phoneNumber + '\'' ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, name);
    }
}
