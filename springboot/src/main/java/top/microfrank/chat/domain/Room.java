package top.microfrank.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Frank on 2017/11/25.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})

public class Room {
    @Id
    @GeneratedValue
    private Integer roomid;
    private String roomname;
    private Boolean issecret;
    private String password;
}
