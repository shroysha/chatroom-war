package dev.shroysha.chatroom.war

import dev.shroysha.chatroom.ejb.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping

@SpringBootApplication
@EntityScan(basePackageClasses = ChatroomEntityScanTag.class)
class App {

    static void main(String[] args) {
        SpringApplication.run(App.class, args)
    }

    private ChatroomMessageRepo chatroomMessageRepo

    @PostMapping(ChatroomEntityScanTag.MESSAGE_CREATE)
    ChatroomMessage createMessage(ChatroomUser user, String message) {
        ChatroomMessage newMessage = new ChatroomMessage(user, message)
        chatroomMessageRepo.save(newMessage)
        return newMessage
    }

    @GetMapping(ChatroomEntityScanTag.MESSAGE_ALL)
    Iterable<ChatroomMessage> getAllMessages() {
        return chatroomMessageRepo.findAll()
    }


    @Autowired
    private ChatroomUserRepo chatroomUserRepo

    @PostMapping(ChatroomEntityScanTag.USER_CREATE)
    ChatroomUser createUser(String username) {
        ChatroomUser newUser = new ChatroomUser(username)
        chatroomUserRepo.save(newUser)
        return newUser
    }

    @GetMapping(ChatroomEntityScanTag.USER_ALL)
    Iterable<ChatroomUser> getAllUsers() {
        return chatroomUserRepo.findAll()
    }

}




