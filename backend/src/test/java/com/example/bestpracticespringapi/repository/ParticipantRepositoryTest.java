package com.example.bestpracticespringapi.repository;

import com.example.bestpracticespringapi.customAnnotation.IntegrationTest;
import com.example.bestpracticespringapi.model.Participant;
import org.junit.Test;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class) // SpringRunner is an alias for the SpringJUnit4ClassRunner , which joins JUnit testing library with the Spring TestContext Framework
@IntegrationTest
@Tag("IntegrationTest") // can have different tag for UnitTest and IntegrationTest
//@ActiveProfiles("integration-test") // can use different profile and use application.properties
@DataJpaTest(showSql = true) // Mock JPA
//@SpringBootTest // if used same context will be used for all test, otherwise each run will have its own context
@Sql(statements = {
        "INSERT INTO participant(part_id, name, age) VALUES(1L, 'xyz', 5)",
        "INSERT INTO participant(part_id, name, age) VALUES(2L, 'abc', 10)",
})
//Using sql as TestEntityManager is not working
public class ParticipantRepositoryTest {

//    @Autowired
//    private TestEntityManager entityManager;

    @Autowired
    ParticipantRepository participantRepository;

    @Test
    public void testFindAll() {

        //Mock data
        Participant participant1 = new Participant(1L, "xyz", 5);
        Participant participant2 = new Participant(2L, "abc", 10);
//        this.entityManager.persist(participant1);
//        this.entityManager.persist(participant2);
//        entityManager.flush();

        //Call
        List<Participant> participants = participantRepository.findAll();

        //Assertion
        //AssertThat provides advance comparison
        assertThat(participantRepository.findAll())
                .extracting(Participant::getName)
                        .containsExactly("xyz", "abc");
        assertThat(participants.size()).isEqualTo(2);

        //traditional assert
        assertEquals(2, participants.size());
        assertEquals(participant1.getName(), participants.get(0).getName());
        assertEquals(participant2.getName(), participants.get(1).getName());
    }
}