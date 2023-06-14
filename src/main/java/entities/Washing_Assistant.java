package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "washing_assistant")
@NamedQuery(name = "washing_assistant.deleteAllRows", query = "DELETE from Washing_Assistant")
public class Washing_Assistant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "primary_language")
    private String primaryLanguage;

    @Column(name = "years_of_experience")
    private String years_of_experience;

    @Column(name = "price_pr_hour")
    private Integer price_pr_hour;

    @ManyToMany
    @JoinTable(name = "washing_assistant_bookings",
            joinColumns = @JoinColumn(name = "washing_Assistant_id"),
            inverseJoinColumns = @JoinColumn(name = "bookings_id"))
    private List<Booking> bookings = new ArrayList<>();

    public Washing_Assistant() {
    }

    public Washing_Assistant(String name, String primaryLanguage, String years_of_experience, Integer price_pr_hour) {
        this.name = name;
        this.primaryLanguage = primaryLanguage;
        this.years_of_experience = years_of_experience;
        this.price_pr_hour = price_pr_hour;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public Integer getPrice_pr_hour() {
        return price_pr_hour;
    }

    public void setPrice_pr_hour(Integer price_pr_hour) {
        this.price_pr_hour = price_pr_hour;
    }

    public String getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(String years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public void setPrimaryLanguage(String primaryLanguage) {
        this.primaryLanguage = primaryLanguage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}