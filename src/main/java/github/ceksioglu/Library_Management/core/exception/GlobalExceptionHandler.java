package github.ceksioglu.Library_Management.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Uygulama genelinde istisnaları ele alan sınıf.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ResourceNotFoundException istisnalarını ele alır.
     *
     * @param ex ResourceNotFoundException istisnası.
     * @return Hata mesajı ve HTTP durumu içeren ResponseEntity nesnesi.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Diğer tüm istisnaları ele alır.
     *
     * @param ex Genel istisna.
     * @return Hata mesajı ve HTTP durumu içeren ResponseEntity nesnesi.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
