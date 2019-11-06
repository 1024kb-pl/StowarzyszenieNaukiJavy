package pl._1024kb.stowarzyszenienaukijavy.simpletodo.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import pl._1024kb.stowarzyszenienaukijavy.simpletodo.model.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Configuration
    class BeanConfig{
        @Autowired
        EntityManagerFactory emf;

        @Bean
        public TestEntityManager sessionFactory() {
            return new TestEntityManagerAutoConfiguration().testEntityManager(emf);
        }
    }


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp(){
        // given
        User user = User.builder()
                .id(1L)
                .username("Pablo")
                .password("pass")
                .repeatedPassword("passRepeat")
                .email("name@g.com")
                .build();

        testEntityManager.persist(user);
    }

    @Test
    public void whenFindByName_thenReturnUser() {
        // when
        User user = userRepository.findUserByUsername("Pablo");

        // then
        assertThat(user.getEmail()).isEqualTo("name@g.com");
    }

    @Test
    public void whenFindAll_thenReturnUserList() {
        // when
        List<User> users = userRepository.findAll();

        // then
        assertThat(users).hasSize(1);
    }
}