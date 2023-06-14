package entities;

import dtos.CarDTO;
import dtos.Washing_AssistantDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "booking")
@NamedQuery(name = "booking.deleteAllRows", query = "DELETE from Booking")
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

    @ManyToMany(mappedBy = "userBookings")
    private List<User> userList;


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

    public Booking(LocalDateTime dateAndTime, Integer duration) {
        this.dateAndTime = dateAndTime;
        this.duration = duration;
    }

    public Booking(LocalDateTime dateAndTime, Integer duration, String car) {
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.car = new Car(car); //TODO: skal være bare Registreringsnummeret og ikke en entitet KUN med registreringsnummeret FIX hvis tid
    }

    public Booking(Long id, LocalDateTime dateAndTime, Integer duration, Washing_AssistantDTO washing_assistants, CarDTO carDTO) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
        this.washing_Assistants.add(new Washing_Assistant(washing_assistants.getId()));
        this.car = new Car(carDTO.getId(), carDTO.getBrand(), carDTO.getMake(), carDTO.getYear());
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

    public List<User> getUserList() {
        return userList;
    }
    public void setUserList(List<User> userList) {
        this.userList = userList;
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

    public Washing_Assistant getWashing_Assistant() {
        return washing_Assistants.get(0);
    }

    public String getRegNum() {
        return car.getId();
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", dateAndTime=" + dateAndTime +
                ", duration=" + duration +
                ", washing_Assistants=" + washing_Assistants +
                ", userList=" + userList +
                ", car=" + car +
                '}';
    }
}