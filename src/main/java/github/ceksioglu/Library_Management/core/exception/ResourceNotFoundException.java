package github.ceksioglu.Library_Management.core.exception;

/**
 * Kaynak bulunamadığında fırlatılan özel istisna sınıfı.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Yeni bir ResourceNotFoundException nesnesi oluşturur.
     *
     * @param message Hata mesajı.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
