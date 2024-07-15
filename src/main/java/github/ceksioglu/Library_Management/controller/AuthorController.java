package github.ceksioglu.Library_Management.controller;

import github.ceksioglu.Library_Management.dto.AuthorDTO;
import github.ceksioglu.Library_Management.service.concretes.AuthorManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Yazarlar için CRUD işlemlerini yöneten controller sınıfı.
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorManager authorManager;

    @Autowired
    public AuthorController(AuthorManager authorManager) {
        this.authorManager = authorManager;
    }

    /**
     * Tüm yazarları getirir.
     *
     * @return Yazarların listesi.
     */
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorManager.getAllAuthors());
    }

    /**
     * Kimliğe göre yazar getirir.
     *
     * @param id Yazar kimliği.
     * @return Yazar DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable int id) {
        return ResponseEntity.ok(authorManager.getAuthorById(id));
    }

    /**
     * Yeni yazar oluşturur.
     *
     * @param authorDTO Yeni yazar verileri.
     * @return Oluşturulan yazar DTO.
     */
    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO) {
        return new ResponseEntity<>(authorManager.createAuthor(authorDTO), HttpStatus.CREATED);
    }

    /**
     * Yazarı günceller.
     *
     * @param id Yazar kimliği.
     * @param authorDTO Güncellenmiş yazar verileri.
     * @return Güncellenmiş yazar DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable int id, @RequestBody AuthorDTO authorDTO) {
        return ResponseEntity.ok(authorManager.updateAuthor(id, authorDTO));
    }

    /**
     * Yazarı siler.
     *
     * @param id Yazar kimliği.
     * @return HTTP Durumu.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        authorManager.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
