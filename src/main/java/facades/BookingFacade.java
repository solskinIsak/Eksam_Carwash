package facades;

import dtos.BookingDTO;
import entities.Booking;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class BookingFacade {

    private static EntityManagerFactory entityManagerFactory;
    private static BookingFacade instance;

    private BookingFacade() {
    }

    public static BookingFacade getBookingFacade(EntityManagerFactory emf) {
        if (instance == null) {
            entityManagerFactory = emf;
            instance = new BookingFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    public Booking createBooking(LocalDateTime date, int duration, String regNum) {
        EntityManager entityManager = getEntityManager();
        Booking booking = null;
        try {
            booking = new Booking(date, duration, regNum);
            entityManager.getTransaction().begin();
            entityManager.persist(booking);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return booking;
    }

    public List<BookingDTO> getAllBookings() {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            TypedQuery<Booking> query = entityManager.createQuery("SELECT b FROM Booking b", Booking.class);
            List<Booking> bookings = query.getResultList();
            return BookingDTO.getBookingDTOS(bookings);
        }

        public void deleteBooking(Long id) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            try {
                entityManager.getTransaction().begin();
                Booking booking = entityManager.find(Booking.class, id);
                entityManager.remove(booking);
                entityManager.getTransaction().commit();
            } finally {
                entityManager.close();
            }
        }

}
