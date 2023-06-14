package dtos;

import entities.Booking;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class BookingDTO implements Serializable {
    private Long id;
    private LocalDateTime dateAndTime;
    private Integer duration;
    private String regNum;


    public BookingDTO() {
    }


    public BookingDTO(Long id,LocalDateTime dateTime, int duration, String regNum) {
        this.id = id;
        this.dateAndTime = dateTime;
        this.duration = duration;
        this.regNum = regNum;
    }

    public static List<BookingDTO> getBookingDTOS(List<Booking> bookings) {
        List<BookingDTO> bookingDTOS = new java.util.ArrayList<>();
        for(Booking booking : bookings) {
            bookingDTOS.add(new BookingDTO(booking.getId(),booking.getDateAndTime(), booking.getDuration(), booking.getRegNum()));
        }
        return bookingDTOS;
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

    public String getRegNum() {
        return regNum;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        BookingDTO entity = (BookingDTO) object;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.dateAndTime, entity.dateAndTime) &&
                Objects.equals(this.duration, entity.duration) &&
                Objects.equals(this.regNum, entity.regNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateAndTime, duration, regNum);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "dateAndTime = " + dateAndTime + ", " +
                "duration = " + duration + ", " +
                "Registration Number = " + regNum + ")";
    }
}
