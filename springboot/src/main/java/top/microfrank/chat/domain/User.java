package top.microfrank.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Frank on 2017/11/25.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})

public class User {
    @Id
    @GeneratedValue
    private Integer userid;
    private String username;
    private String password;
    private Boolean online;
}
