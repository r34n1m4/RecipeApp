import com.reanima.business.repository.UserRepository;
import com.reanima.business.repository.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUserEmail("testuser@gmail.com");
        user.setUserPassword("test123");
        user.setUserName("TestName");
        user.setUserSurname("TestSurname");

        User savedUser = userRepository.save(user);
        User existUser = entityManager.find(User.class, savedUser.getUserId());
        assertThat(existUser.getUserEmail()).isEqualTo(user.getUserEmail());
    }
}
