package github.ceksioglu.Library_Management.repository;

import github.ceksioglu.Library_Management.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Yazar veritabanı işlemleri için repository.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
