package top.microfrank.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import top.microfrank.chat.domain.MqttAction;
import top.microfrank.chat.domain.User;
import top.microfrank.chat.repertory.AuthRep;
import top.microfrank.chat.repertory.RoomRep;
import top.microfrank.chat.repertory.UserRep;

/**
 * Created by Frank on 2017/11/25.
 */
@Controller
@RequestMapping("/mqtt")
@Transactional
public class MqttController {
    @Autowired
    UserRep userRep;
    @Autowired
    RoomRep roomRep;
    @Autowired
    AuthRep authRep;

    @PostMapping("/auth")
    public ResponseEntity auth(String username,String password){
        User user = userRep.getUserByUsername(username);
        if(user!=null && user.getPassword().equals(password)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }


    @GetMapping("/acl")
    public ResponseEntity acl(String access,String username,String clientid,String ipaddr,String topic){

        User user = userRep.getUserByUsername(username);
        //topic=/chat/roomname
        long count = authRep.getAuthsByUserid(user.getUserid()).stream()
                .filter(x->roomRep.getOne(x.getRoomid()).getRoomname().equals(topic.replace("/chat","")))
                .count();
        System.out.println(username+topic+count);

        ResponseEntity res = count>=1?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.UNAUTHORIZED);

        System.out.println(res.getStatusCode());

        return res;

    }

    @PostMapping("/hook")
    public ResponseEntity hook(@RequestBody MqttAction mqttAction){
        String username = mqttAction.getClient_id();
        String action = mqttAction.getAction();
        String topic = mqttAction.getTopic();
        User user = userRep.getUserByUsername(username);
        System.out.println(action+username+topic);
        switch (action){
            //用户上线和下线
            case "client_connected":
                user.setOnline(true);
                userRep.save(user);
                break;
            case "client_disconnected":
                user.setOnline(false);
                userRep.save(user);
                break;
            default:
                break;
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
