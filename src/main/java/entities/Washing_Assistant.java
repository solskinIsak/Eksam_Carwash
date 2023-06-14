package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "washing_assistant")
public class Washing_Assistant {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

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