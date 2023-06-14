package facades;

import entities.Washing_Assistant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WashAssFacadeTest {

    private static EntityManagerFactory emf;
    private static WashAssFacade facade;

    public WashAssFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = WashAssFacade.getWashAssFacade(emf);
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();
        Washing_Assistant washing_assistant = new Washing_Assistant("isak","test1","LOOOTS",19999);
        Washing_Assistant washing_assistant2 = new Washing_Assistant("poul","agger-neese","LOOOTS",19999);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("washing_assistant.deleteAllRows").executeUpdate();
            em.persist(washing_assistant);
            em.persist(washing_assistant2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    void createWashingAssistant() {
        System.out.println("testing create washing assistant");
        Washing_Assistant exAssistant = new Washing_Assistant("isak","test1","LOOOTS",19999);
        Washing_Assistant acAssistant = facade.createWashingAssistant(exAssistant.getName(), exAssistant.getPrimaryLanguage(),exAssistant.getYears_of_experience(),exAssistant.getPrice_pr_hour());
        assertEquals(exAssistant.getName(),acAssistant.getName());
    }

    @Test
    void getAllWashingAssistants() {
        System.out.println("testing get all assistants");
        assertEquals(2,facade.getAllWashingAssistants().size());
    }
}