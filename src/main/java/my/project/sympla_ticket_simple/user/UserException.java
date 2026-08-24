package my.project.sympla_ticket_simple.user;

public abstract class UserException extends RuntimeException {
    public UserException(String message){
        super(message);
    }
}
