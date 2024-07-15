package github.ceksioglu.Library_Management.repository;

import github.ceksioglu.Library_Management.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Yayınevi veritabanı işlemleri için repository.
 */
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}