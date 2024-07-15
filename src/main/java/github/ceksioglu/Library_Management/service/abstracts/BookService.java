package github.ceksioglu.Library_Management.service.abstracts;


import github.ceksioglu.Library_Management.dto.BookDTO;

import java.util.List;

/**
 * Kitap servisi için arayüz.
 */
public interface BookService {

    /**
     * Tüm kitapları getirir.
     * @return Kitapların listesi.
     */
    List<BookDTO> getAllBooks();

    /**
     * Kimliğe göre kitabı getirir.
     * @param id Kitap kimliği.
     * @return Kitap DTO.
     */
    BookDTO getBookById(int id);

    /**
     * Yeni kitap oluşturur.
     * @param bookDTO Kitap DTO.
     * @return Oluşturulan kitap DTO.
     */
    BookDTO createBook(BookDTO bookDTO);

    /**
     * Kitabı günceller.
     * @param id Kitap kimliği.
     * @param bookDTO Güncellenmiş kitap DTO.
     * @return Güncellenmiş kitap DTO.
     */
    BookDTO updateBook(int id, BookDTO bookDTO);

    /**
     * Kitabı siler.
     * @param id Kitap kimliği.
     */
    void deleteBook(int id);
}
