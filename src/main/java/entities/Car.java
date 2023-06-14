package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "registration_number", nullable = false)
    private String id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "make")
    private String make;

    @Column(name = "year")
    private Integer year;

    @OneToMany(mappedBy = "car", orphanRemoval = true)
    private List<Booking> bookings = new ArrayList<>();

    public Car(String id, String brand, String make, Integer year) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.year = year;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Car(String id) {
        this.id = id;
    }

    public Car() {
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}