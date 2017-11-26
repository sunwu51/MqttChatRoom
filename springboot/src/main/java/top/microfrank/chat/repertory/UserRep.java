package top.microfrank.chat.repertory;

import org.springframework.data.jpa.repository.JpaRepository;
import top.microfrank.chat.domain.User;

/**
 * Created by Frank on 2017/11/25.
 */
public interface UserRep extends JpaRepository<User,Integer> {
    User getUserByUsername(String username);
}
