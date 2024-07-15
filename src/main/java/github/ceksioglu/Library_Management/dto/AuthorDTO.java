package github.ceksioglu.Library_Management.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * Yazar verilerini taşıyan DTO sınıfı.
 */
@Data
public class AuthorDTO {

    /**
     * Yazar kimliği.
     */
    private int id;

    /**
     * Yazarın adı.
     */
    private String name;

    /**
     * Yazarın doğum tarihi.
     */
    private LocalDate birthDate;

    /**
     * Yazarın ülkesi.
     */
    private String country;

    /**
     * Yazara ait kitap listesi.
     */
    private List<BookDTO> bookList;
}
