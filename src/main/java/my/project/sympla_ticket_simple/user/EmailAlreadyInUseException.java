package my.project.sympla_ticket_simple.user;

public class EmailAlreadyInUseException extends UserException {

    public EmailAlreadyInUseException(String message) {
        super(message);
    }
}
