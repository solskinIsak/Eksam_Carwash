package dtos;

import entities.Booking;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CarDTO implements Serializable {
    @NotNull
    private String id;
    private String brand;
    private String make;
    private Integer year;
    private List<Booking> bookings;

    public CarDTO() {
    }

    public CarDTO(String id, String brand, String make, Integer year, List<Booking> bookings) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.year = year;
        this.bookings = bookings;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getMake() {
        return make;
    }

    public Integer getYear() {
        return year;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDTO entity = (CarDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.brand, entity.brand) &&
                Objects.equals(this.make, entity.make) &&
                Objects.equals(this.year, entity.year) &&
                Objects.equals(this.bookings, entity.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, make, year, bookings);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "brand = " + brand + ", " +
                "make = " + make + ", " +
                "year = " + year + ", " +
                "bookings = " + bookings + ")";
    }
}
