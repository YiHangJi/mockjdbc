package no.maddin.mockjdbc;

/**
 * @author Han at 2022/7/5.
 * email:   lynn.jiang@ximalaya.com
 */
public class LogUnsupportedOperationException extends UnsupportedOperationException {
    public LogUnsupportedOperationException(String message) {
        super(message);
    }
}
