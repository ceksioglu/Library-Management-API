package github.ceksioglu.Library_Management.dto;

import lombok.Data;

import java.util.Set;

/**
 * Kitap verilerini taşıyan DTO sınıfı.
 */
@Data
public class BookDTO {

    /**
     * Kitap kimliği.
     */
    private int id;

    /**
     * Kitap adı.
     */
    private String name;

    /**
     * Yayın yılı.
     */
    private int publicationYear;

    /**
     * Stok miktarı.
     */
    private int stock;

    /**
     * Kitap yazarı.
     */
    private AuthorDTO author;

    /**
     * Kitap yayınevi.
     */
    private PublisherDTO publisher;

    /**
     * Kitap kategorileri.
     */
    private Set<CategoryDTO> categories;
}
