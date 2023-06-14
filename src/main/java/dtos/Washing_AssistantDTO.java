package dtos;

import entities.Booking;
import entities.Washing_Assistant;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Washing_AssistantDTO implements Serializable {
    private Long id;
    private String name;
    private String primaryLanguage;
    private String years_of_experience;
    private Integer price_pr_hour;
    private List<Booking> bookings;

    public Washing_AssistantDTO() {
    }

    public Washing_AssistantDTO(Washing_Assistant washing_assistant){
        this.id = washing_assistant.getId();
        this.name = washing_assistant.getName();
        this.primaryLanguage = washing_assistant.getPrimaryLanguage();
        this.years_of_experience = washing_assistant.getYears_of_experience();
        this.price_pr_hour = washing_assistant.getPrice_pr_hour();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }

    public String getYears_of_experience() {
        return years_of_experience;
    }

    public Integer getPrice_pr_hour() {
        return price_pr_hour;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Washing_AssistantDTO entity = (Washing_AssistantDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.primaryLanguage, entity.primaryLanguage) &&
                Objects.equals(this.years_of_experience, entity.years_of_experience) &&
                Objects.equals(this.price_pr_hour, entity.price_pr_hour) &&
                Objects.equals(this.bookings, entity.bookings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, primaryLanguage, years_of_experience, price_pr_hour, bookings);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "primaryLanguage = " + primaryLanguage + ", " +
                "years_of_experience = " + years_of_experience + ", " +
                "price_pr_hour = " + price_pr_hour + ", " +
                "bookings = " + bookings + ")";
    }
}
