package my.project.sympla_ticket_simple.shared;

import my.project.sympla_ticket_simple.event.EventException;

public class BusinessException extends EventException {
    public BusinessException(String message) {
        super(message);
    }
}
