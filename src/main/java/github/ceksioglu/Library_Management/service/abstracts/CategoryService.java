package github.ceksioglu.Library_Management.service.abstracts;

import github.ceksioglu.Library_Management.dto.CategoryDTO;
import java.util.List;

/**
 * Kategori servisi için arayüz.
 */
public interface CategoryService {

    /**
     * Tüm kategorileri getirir.
     * @return Kategorilerin listesi.
     */
    List<CategoryDTO> getAllCategories();

    /**
     * Kimliğe göre kategoriyi getirir.
     * @param id Kategori kimliği.
     * @return Kategori DTO.
     */
    CategoryDTO getCategoryById(int id);

    /**
     * Yeni kategori oluşturur.
     * @param categoryDTO Kategori DTO.
     * @return Oluşturulan kategori DTO.
     */
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    /**
     * Kategoriyi günceller.
     * @param id Kategori kimliği.
     * @param categoryDTO Güncellenmiş kategori DTO.
     * @return Güncellenmiş kategori DTO.
     */
    CategoryDTO updateCategory(int id, CategoryDTO categoryDTO);

    /**
     * Kategoriyi siler.
     * @param id Kategori kimliği.
     */
    void deleteCategory(int id);
}
