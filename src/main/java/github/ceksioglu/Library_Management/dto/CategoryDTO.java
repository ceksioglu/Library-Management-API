package github.ceksioglu.Library_Management.dto;

import lombok.Data;

import java.util.Set;

/**
 * Kategori verilerini taşıyan DTO sınıfı.
 */
@Data
public class CategoryDTO {

    /**
     * Kategori kimliği.
     */
    private int id;

    /**
     * Kategori adı.
     */
    private String name;

    /**
     * Kategori açıklaması.
     */
    private String description;

    /**
     * Kategoriye ait kitaplar.
     */
    private Set<BookDTO> books;
}
