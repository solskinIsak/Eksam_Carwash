package facades;

import entities.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CarFacade {

    private static CarFacade instance;
    private static EntityManagerFactory entityManagerFactory;

    private CarFacade(){}

    // Method returns an instance of the FacadeExample class
    public static CarFacade getCarFacade(EntityManagerFactory entityManagerFactory1){
        if( instance == null) {
            entityManagerFactory = entityManagerFactory1;
            instance = new CarFacade();
        }
        return instance;
    }

    private EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    public Car createCar(String regNum, String brand, String make, int year) {
        EntityManager em = getEntityManagerFactory().createEntityManager();
        Car car = null;
        try {
            car = new Car(regNum, brand, make, year);
            em.getTransaction().begin();
            em.persist(car);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return car;
    }
}
