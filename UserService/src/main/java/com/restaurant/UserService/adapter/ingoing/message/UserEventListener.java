package com.restaurant.UserService.adapter.ingoing.message;

import com.restaurant.UserService.adapter.ingoing.message.event.UserDeleteEvent;
import com.restaurant.UserService.core.application.UserCommandService;
import com.restaurant.UserService.core.application.command.DeleteUserCommand;
import com.restaurant.UserService.adapter.ingoing.message.event.UserEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {
    private final UserCommandService commandService;

    public UserEventListener(UserCommandService commandService) {
        this.commandService = commandService;
    }

    @RabbitListener(queues="user-commands")
    public void listen(UserEvent event) {
        switch(event.getEventKey()) {
            case UserDeleteEvent.KEY:
                this.commandService.handle(new DeleteUserCommand(((UserDeleteEvent)event).getUsername()));
                break;
        }
    }
}
