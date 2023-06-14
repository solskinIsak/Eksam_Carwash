package dtos;

import entities.Car;
import entities.Washing_Assistant;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BookingDTO implements Serializable {
    private Long id;
    private LocalDateTime dateAndTime;
    private Integer duration;
    private List<Washing_Assistant> washing_Assistants;
    private Car car;


    public BookingDTO() {
    }


    public BookingDTO(Long id, LocalDateTime dateAndTime, Integer duration, List<Washing_Assistant> washing_Assistants, Car car) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.washing_Assistants = washing_Assistants;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public List<Washing_Assistant> getWashing_Assistants() {
        return washing_Assistants;
    }

    public Car getCar() {
        return car;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookingDTO entity = (BookingDTO) object;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.dateAndTime, entity.dateAndTime) &&
                Objects.equals(this.duration, entity.duration) &&
                Objects.equals(this.washing_Assistants, entity.washing_Assistants) &&
                Objects.equals(this.car, entity.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAndTime, duration, washing_Assistants, car);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "dateAndTime = " + dateAndTime + ", " +
                "duration = " + duration + ", " +
                "washing_Assistants = " + washing_Assistants + ", " +
                "car = " + car + ")";
    }
}
