package top.microfrank.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.microfrank.chat.domain.Room;
import top.microfrank.chat.domain.User;
import top.microfrank.chat.repertory.AuthRep;
import top.microfrank.chat.repertory.RoomRep;
import top.microfrank.chat.repertory.UserRep;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 2017/11/25.
 */
@Controller
@RequestMapping("/web")
@Transactional
public class WebController {
    @Autowired
    RoomRep roomRep;

    @Autowired
    UserRep userRep;

    @Autowired
    AuthRep authRep;

    @GetMapping("/rooms")
    public ResponseEntity<List<Room>> getRooms(){
        return new ResponseEntity<List<Room>>(roomRep.findAll(), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(String roomid){
        List<User> userList=new ArrayList<>();
        authRep.getAuthsByRoomid(Integer.parseInt(roomid)).forEach(x->userList.add(userRep.getOne(x.getUserid())));
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    @GetMapping("/access")
    public ResponseEntity getAccess(String username,String roomid){
        return
                authRep.getAuthsByUserid(userRep.getUserByUsername(username).getUserid()).stream().anyMatch(
                    x->x.getRoomid()==Integer.parseInt(roomid)
                )?new ResponseEntity(HttpStatus.OK):new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

}
