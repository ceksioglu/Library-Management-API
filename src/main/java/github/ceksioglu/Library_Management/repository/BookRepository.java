package github.ceksioglu.Library_Management.repository;

import github.ceksioglu.Library_Management.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Kitap veritabanı işlemleri için repository.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}