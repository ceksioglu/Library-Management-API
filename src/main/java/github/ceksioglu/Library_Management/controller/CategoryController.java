package github.ceksioglu.Library_Management.controller;

import github.ceksioglu.Library_Management.dto.CategoryDTO;
import github.ceksioglu.Library_Management.service.concretes.CategoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Kategoriler için CRUD işlemlerini yöneten controller sınıfı.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryManager categoryManager;

    @Autowired
    public CategoryController(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    /**
     * Tüm kategorileri getirir.
     *
     * @return Kategorilerin listesi.
     */
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryManager.getAllCategories());
    }

    /**
     * Kimliğe göre kategori getirir.
     *
     * @param id Kategori kimliği.
     * @return Kategori DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable int id) {
        return ResponseEntity.ok(categoryManager.getCategoryById(id));
    }

    /**
     * Yeni kategori oluşturur.
     *
     * @param categoryDTO Yeni kategori verileri.
     * @return Oluşturulan kategori DTO.
     */
    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryManager.createCategory(categoryDTO), HttpStatus.CREATED);
    }

    /**
     * Kategoriyi günceller.
     *
     * @param id Kategori kimliği.
     * @param categoryDTO Güncellenmiş kategori verileri.
     * @return Güncellenmiş kategori DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(categoryManager.updateCategory(id, categoryDTO));
    }

    /**
     * Kategoriyi siler.
     *
     * @param id Kategori kimliği.
     * @return HTTP Durumu.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryManager.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
