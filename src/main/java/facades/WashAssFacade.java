package facades;

import dtos.Washing_AssistantDTO;
import entities.User;
import entities.Washing_Assistant;
import security.errorhandling.AuthenticationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class WashAssFacade {

    private static EntityManagerFactory entityManagerFactory;
public static WashAssFacade instance;

private WashAssFacade() {}

public static WashAssFacade getWashAssFacade(EntityManagerFactory emf) {
        if (instance == null) {
        entityManagerFactory = emf;
        instance = new WashAssFacade();
        }
        return instance;
        }
    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

public User getVerifiedUser(String username, String password) throws AuthenticationException {
        return getUser(username, password, entityManagerFactory);
    }

    static User getUser(String username, String password, EntityManagerFactory entityManagerFactory) throws AuthenticationException {
        EntityManager em = entityManagerFactory.createEntityManager();
        User user;
        try {
            user = em.find(User.class, username);
            if (user == null || !user.verifyPassword(password)) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return user;
    }

    public Washing_Assistant createWashingAssistant(String name, String primary_language, String years_of_experience, Integer price_pr_hour){
        EntityManager entityManager = getEntityManager();
        Washing_Assistant washing_assistant = new Washing_Assistant(name, primary_language, years_of_experience, price_pr_hour);
        entityManager.getTransaction().begin();
        entityManager.persist(washing_assistant);
        entityManager.getTransaction().commit();
        entityManager.close();
        return washing_assistant;
}

public List<Washing_AssistantDTO> getAllWashingAssistants() {
    EntityManager entityManager = getEntityManager();
    List<Washing_AssistantDTO> washing_assistantDTOS = entityManager.createQuery("SELECT w FROM Washing_Assistant w", Washing_AssistantDTO.class).getResultList();
    entityManager.close();
    return washing_assistantDTOS;
}

}
