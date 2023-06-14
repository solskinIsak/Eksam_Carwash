package entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_and_time")
    private LocalDateTime dateAndTime;

    @Column(name = "duration")
    private Integer duration;

    @ManyToMany(mappedBy = "bookings")
    private List<Washing_Assistant> washing_Assistants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "car_registration_number")
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Washing_Assistant> getWashing_Assistants() {
        return washing_Assistants;
    }

    public void setWashing_Assistants(List<Washing_Assistant> washing_Assistants) {
        this.washing_Assistants = washing_Assistants;
    }

    public Booking() {
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }


    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}