package github.ceksioglu.Library_Management.service.abstracts;

import github.ceksioglu.Library_Management.dto.BookBorrowingDTO;
import java.util.List;

/**
 * Kitap ödünç alma servisi için arayüz.
 */
public interface BookBorrowingService {

    /**
     * Tüm ödünç alma işlemlerini getirir.
     * @return Ödünç alma işlemlerinin listesi.
     */
    List<BookBorrowingDTO> getAllBookBorrowings();

    /**
     * Kimliğe göre ödünç alma işlemini getirir.
     * @param id Ödünç alma işlemi kimliği.
     * @return Ödünç alma işlemi DTO.
     */
    BookBorrowingDTO getBookBorrowingById(int id);

    /**
     * Yeni ödünç alma işlemi oluşturur.
     * @param bookBorrowingDTO Ödünç alma işlemi DTO.
     * @return Oluşturulan ödünç alma işlemi DTO.
     */
    BookBorrowingDTO createBookBorrowing(BookBorrowingDTO bookBorrowingDTO);

    /**
     * Ödünç alma işlemini günceller.
     * @param id Ödünç alma işlemi kimliği.
     * @param bookBorrowingDTO Güncellenmiş ödünç alma işlemi DTO.
     * @return Güncellenmiş ödünç alma işlemi DTO.
     */
    BookBorrowingDTO updateBookBorrowing(int id, BookBorrowingDTO bookBorrowingDTO);

    /**
     * Ödünç alma işlemini siler.
     * @param id Ödünç alma işlemi kimliği.
     */
    void deleteBookBorrowing(int id);
}
