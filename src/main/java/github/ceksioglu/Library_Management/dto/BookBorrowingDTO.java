package github.ceksioglu.Library_Management.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Kitap ödünç alma verilerini taşıyan DTO sınıfı.
 */
@Data
public class BookBorrowingDTO {

    /**
     * Ödünç alma işlemi kimliği.
     */
    private int id;

    /**
     * Kitap ödünç alan kişinin adı soyadı.
     */
    private String borrowerName;

    /**
     * Kitap ödünç alan kişinin e-posta adresi.
     */
    private String borrowerEmail;

    /**
     * Kitap ödünç alma tarihi.
     */
    private LocalDate borrowingDate;

    /**
     * Kitabın teslim tarihi (ilk başta null olacak).
     */
    private LocalDate returnDate;

    /**
     * Ödünç alınan kitap.
     */
    private BookDTO book;
}
