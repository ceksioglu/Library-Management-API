package github.ceksioglu.Library_Management.controller;

import github.ceksioglu.Library_Management.dto.BookDTO;
import github.ceksioglu.Library_Management.service.concretes.BookManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kitaplar için CRUD işlemlerini yöneten controller sınıfı.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookManager bookManager;

    @Autowired
    public BookController(BookManager bookManager) {
        this.bookManager = bookManager;
    }

    /**
     * Tüm kitapları getirir.
     *
     * @return Kitapların listesi.
     */
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        return ResponseEntity.ok(bookManager.getAllBooks());
    }

    /**
     * Kimliğe göre kitap getirir.
     *
     * @param id Kitap kimliği.
     * @return Kitap DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable int id) {
        return ResponseEntity.ok(bookManager.getBookById(id));
    }

    /**
     * Yeni kitap oluşturur.
     *
     * @param bookDTO Yeni kitap verileri.
     * @return Oluşturulan kitap DTO.
     */
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookManager.createBook(bookDTO), HttpStatus.CREATED);
    }

    /**
     * Kitabı günceller.
     *
     * @param id Kitap kimliği.
     * @param bookDTO Güncellenmiş kitap verileri.
     * @return Güncellenmiş kitap DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable int id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookManager.updateBook(id, bookDTO));
    }

    /**
     * Kitabı siler.
     *
     * @param id Kitap kimliği.
     * @return HTTP Durumu.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookManager.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
