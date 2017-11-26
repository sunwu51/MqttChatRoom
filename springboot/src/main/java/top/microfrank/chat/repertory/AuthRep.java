package top.microfrank.chat.repertory;

import org.springframework.data.jpa.repository.JpaRepository;
import top.microfrank.chat.domain.Auth;
import top.microfrank.chat.domain.Room;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by Frank on 2017/11/25.
 */
public interface AuthRep extends JpaRepository<Auth,Integer> {
    List<Auth> getAuthsByUserid(Integer userid);
    List<Auth> getAuthsByRoomid(Integer roomid);
}
