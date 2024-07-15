package github.ceksioglu.Library_Management.service.abstracts;
import github.ceksioglu.Library_Management.dto.AuthorDTO;
import java.util.List;

/**
 * Yazar servisi için arayüz.
 */
public interface AuthorService {

    /**
     * Tüm yazarları getirir.
     * @return Yazarların listesi.
     */
    List<AuthorDTO> getAllAuthors();

    /**
     * Kimliğe göre yazarı getirir.
     * @param id Yazar kimliği.
     * @return Yazar DTO.
     */
    AuthorDTO getAuthorById(int id);

    /**
     * Yeni yazar oluşturur.
     * @param authorDTO Yazar DTO.
     * @return Oluşturulan yazar DTO.
     */
    AuthorDTO createAuthor(AuthorDTO authorDTO);

    /**
     * Yazarı günceller.
     * @param id Yazar kimliği.
     * @param authorDTO Güncellenmiş yazar DTO.
     * @return Güncellenmiş yazar DTO.
     */
    AuthorDTO updateAuthor(int id, AuthorDTO authorDTO);

    /**
     * Yazarı siler.
     * @param id Yazar kimliği.
     */
    void deleteAuthor(int id);
}
