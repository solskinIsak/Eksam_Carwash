package facades;

import dtos.Washing_AssistantDTO;
import entities.Washing_Assistant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import java.util.List;

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
        Washing_Assistant exAssistant = new Washing_Assistant("Yesman","test1","LOOOTS",19999);
        facade.createWashingAssistant(exAssistant.getName(), exAssistant.getPrimaryLanguage(),exAssistant.getYears_of_experience(),exAssistant.getPrice_pr_hour());
        List<Washing_AssistantDTO> washing_assistantDTOS = facade.getAllWashingAssistants();
        assertEquals(washing_assistantDTOS.size(),facade.getAllWashingAssistants().size());
    }
}