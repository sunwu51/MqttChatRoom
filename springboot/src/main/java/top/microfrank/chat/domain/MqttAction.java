package top.microfrank.chat.domain;

import lombok.Data;

/**
 * Created by Frank on 2017/11/25.
 */
@Data
public class MqttAction {
    private String action;
    private String username;
    private String client_id;
    private String topic;
}
