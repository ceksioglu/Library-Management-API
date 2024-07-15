package github.ceksioglu.Library_Management.repository;

import github.ceksioglu.Library_Management.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Kategori veritabanı işlemleri için repository.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}