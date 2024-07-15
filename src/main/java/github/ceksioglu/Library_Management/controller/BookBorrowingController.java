package github.ceksioglu.Library_Management.controller;

import github.ceksioglu.Library_Management.dto.BookBorrowingDTO;
import github.ceksioglu.Library_Management.service.concretes.BookBorrowingManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kitap ödünç alma işlemleri için CRUD işlemlerini yöneten controller sınıfı.
 */
@RestController
@RequestMapping("/api/book-borrowings")
public class BookBorrowingController {

    private final BookBorrowingManager bookBorrowingManager;

    @Autowired
    public BookBorrowingController(BookBorrowingManager bookBorrowingManager) {
        this.bookBorrowingManager = bookBorrowingManager;
    }

    /**
     * Tüm ödünç alma işlemlerini getirir.
     *
     * @return Ödünç alma işlemlerinin listesi.
     */
    @GetMapping
    public ResponseEntity<List<BookBorrowingDTO>> getAllBookBorrowings() {
        return ResponseEntity.ok(bookBorrowingManager.getAllBookBorrowings());
    }

    /**
     * Kimliğe göre ödünç alma işlemini getirir.
     *
     * @param id Ödünç alma işlemi kimliği.
     * @return Ödünç alma işlemi DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookBorrowingDTO> getBookBorrowingById(@PathVariable int id) {
        return ResponseEntity.ok(bookBorrowingManager.getBookBorrowingById(id));
    }

    /**
     * Yeni ödünç alma işlemi oluşturur.
     *
     * @param bookBorrowingDTO Yeni ödünç alma işlemi verileri.
     * @return Oluşturulan ödünç alma işlemi DTO.
     */
    @PostMapping
    public ResponseEntity<BookBorrowingDTO> createBookBorrowing(@RequestBody BookBorrowingDTO bookBorrowingDTO) {
        return new ResponseEntity<>(bookBorrowingManager.createBookBorrowing(bookBorrowingDTO), HttpStatus.CREATED);
    }

    /**
     * Ödünç alma işlemini günceller.
     *
     * @param id Ödünç alma işlemi kimliği.
     * @param bookBorrowingDTO Güncellenmiş ödünç alma işlemi verileri.
     * @return Güncellenmiş ödünç alma işlemi DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookBorrowingDTO> updateBookBorrowing(@PathVariable int id, @RequestBody BookBorrowingDTO bookBorrowingDTO) {
        return ResponseEntity.ok(bookBorrowingManager.updateBookBorrowing(id, bookBorrowingDTO));
    }

    /**
     * Ödünç alma işlemini siler.
     *
     * @param id Ödünç alma işlemi kimliği.
     * @return HTTP Durumu.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookBorrowing(@PathVariable int id) {
        bookBorrowingManager.deleteBookBorrowing(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
