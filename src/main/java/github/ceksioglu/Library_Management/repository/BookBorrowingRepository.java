package github.ceksioglu.Library_Management.repository;


import github.ceksioglu.Library_Management.entity.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Kitap ödünç alma veritabanı işlemleri için repository.
 */
@Repository
public interface BookBorrowingRepository extends JpaRepository<BookBorrowing, Integer> {
}