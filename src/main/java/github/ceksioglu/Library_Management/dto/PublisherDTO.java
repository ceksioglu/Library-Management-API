package github.ceksioglu.Library_Management.dto;

import lombok.Data;

import java.util.List;

/**
 * Yayınevi verilerini taşıyan DTO sınıfı.
 */
@Data
public class PublisherDTO {

    /**
     * Yayınevi kimliği.
     */
    private int id;

    /**
     * Yayınevi ismi.
     */
    private String name;

    /**
     * Yayınevinin kuruluş yılı.
     */
    private int establishmentYear;

    /**
     * Yayınevi adresi.
     */
    private String address;

    /**
     * Yayınevine ait kitaplar.
     */
    private List<BookDTO> bookList;
}
