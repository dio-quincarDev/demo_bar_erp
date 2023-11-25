package pa.com.erpbar.exceptions;

public class RoleAlReadyExistException extends RuntimeException {
    public RoleAlReadyExistException(String message) {
        super(message);
    }
}
