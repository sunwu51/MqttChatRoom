-- 3 users
INSERT INTO user(userid,username,password,online) VALUES (1,'frank','12345',FALSE );
INSERT INTO user(userid,username,password,online)  VALUES (2,'david','12345',FALSE );
INSERT INTO user(userid,username,password,online)  VALUES (3,'claire','12345',FALSE );

-- 3 rooms
INSERT INTO room(roomid,roomname,issecret,password) VALUES (1,'Room1',FALSE,'');
INSERT INTO room(roomid,roomname,issecret,password) VALUES (2,'Room2',FALSE,'');
INSERT INTO room(roomid,roomname,issecret,password) VALUES (3,'Room3',FALSE,'');

-- user in room
INSERT INTO auth(id,userid,roomid) VALUES (1,1,1);
INSERT INTO auth(id,userid,roomid) VALUES (2,1,2);
INSERT INTO auth(id,userid,roomid) VALUES (3,1,3);

INSERT INTO auth(id,userid,roomid) VALUES (4,2,1);
INSERT INTO auth(id,userid,roomid) VALUES (5,2,2);

INSERT INTO auth(id,userid,roomid) VALUES (6,3,1);
INSERT INTO auth(id,userid,roomid) VALUES (7,3,3);