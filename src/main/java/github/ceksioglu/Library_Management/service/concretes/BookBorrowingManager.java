package github.ceksioglu.Library_Management.service.concretes;

import github.ceksioglu.Library_Management.dto.BookBorrowingDTO;
import github.ceksioglu.Library_Management.dto.BookDTO;
import github.ceksioglu.Library_Management.dto.AuthorDTO;
import github.ceksioglu.Library_Management.dto.PublisherDTO;
import github.ceksioglu.Library_Management.dto.CategoryDTO;
import github.ceksioglu.Library_Management.entity.BookBorrowing;
import github.ceksioglu.Library_Management.entity.Book;
import github.ceksioglu.Library_Management.entity.Author;
import github.ceksioglu.Library_Management.entity.Publisher;
import github.ceksioglu.Library_Management.entity.Category;
import github.ceksioglu.Library_Management.core.exception.ResourceNotFoundException;
import github.ceksioglu.Library_Management.repository.BookBorrowingRepository;
import github.ceksioglu.Library_Management.service.abstracts.BookBorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Kitap ödünç alma servisi için yönetici sınıfı.
 */
@Service
public class BookBorrowingManager implements BookBorrowingService {

    private final BookBorrowingRepository bookBorrowingRepository;

    @Autowired
    public BookBorrowingManager(BookBorrowingRepository bookBorrowingRepository) {
        this.bookBorrowingRepository = bookBorrowingRepository;
    }

    /**
     * Tüm ödünç alma işlemlerini getirir.
     *
     * @return Ödünç alma işlemlerinin listesi.
     */
    @Override
    public List<BookBorrowingDTO> getAllBookBorrowings() {
        return bookBorrowingRepository.findAll().stream().map(this::convertBookBorrowingToDTO).collect(Collectors.toList());
    }

    /**
     * Kimliğe göre ödünç alma işlemini getirir.
     *
     * @param id Ödünç alma işlemi kimliği.
     * @return Ödünç alma işlemi DTO.
     */
    @Override
    public BookBorrowingDTO getBookBorrowingById(int id) {
        BookBorrowing bookBorrowing = bookBorrowingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ödünç alma işlemi bulunamadı"));
        return convertBookBorrowingToDTO(bookBorrowing);
    }

    /**
     * Yeni ödünç alma işlemi oluşturur.
     *
     * @param bookBorrowingDTO Ödünç alma işlemi DTO.
     * @return Oluşturulan ödünç alma işlemi DTO.
     */
    @Override
    public BookBorrowingDTO createBookBorrowing(BookBorrowingDTO bookBorrowingDTO) {
        BookBorrowing bookBorrowing = convertBookBorrowingToEntity(bookBorrowingDTO);
        bookBorrowing = bookBorrowingRepository.save(bookBorrowing);
        return convertBookBorrowingToDTO(bookBorrowing);
    }

    /**
     * Ödünç alma işlemini günceller.
     *
     * @param id Ödünç alma işlemi kimliği.
     * @param bookBorrowingDTO Güncellenmiş ödünç alma işlemi DTO.
     * @return Güncellenmiş ödünç alma işlemi DTO.
     */
    @Override
    public BookBorrowingDTO updateBookBorrowing(int id, BookBorrowingDTO bookBorrowingDTO) {
        BookBorrowing existingBookBorrowing = bookBorrowingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ödünç alma işlemi bulunamadı"));
        existingBookBorrowing.setBorrowerName(bookBorrowingDTO.getBorrowerName());
        existingBookBorrowing.setBorrowerEmail(bookBorrowingDTO.getBorrowerEmail());
        existingBookBorrowing.setBorrowingDate(bookBorrowingDTO.getBorrowingDate());
        existingBookBorrowing.setReturnDate(bookBorrowingDTO.getReturnDate());
        bookBorrowingRepository.save(existingBookBorrowing);
        return convertBookBorrowingToDTO(existingBookBorrowing);
    }

    /**
     * Ödünç alma işlemini siler.
     *
     * @param id Ödünç alma işlemi kimliği.
     */
    @Override
    public void deleteBookBorrowing(int id) {
        bookBorrowingRepository.deleteById(id);
    }

    // Yardımcı yöntemler: DTO ve Entity arasında dönüştürme yapar

    /**
     * Kitap ödünç alma entity'sini BookBorrowingDTO'ya dönüştürür.
     *
     * @param bookBorrowing Kitap ödünç alma entity.
     * @return Kitap ödünç alma DTO.
     */
    private BookBorrowingDTO convertBookBorrowingToDTO(BookBorrowing bookBorrowing) {
        BookBorrowingDTO bookBorrowingDTO = new BookBorrowingDTO();
        bookBorrowingDTO.setId(bookBorrowing.getId());
        bookBorrowingDTO.setBorrowerName(bookBorrowing.getBorrowerName());
        bookBorrowingDTO.setBorrowerEmail(bookBorrowing.getBorrowerEmail());
        bookBorrowingDTO.setBorrowingDate(bookBorrowing.getBorrowingDate());
        bookBorrowingDTO.setReturnDate(bookBorrowing.getReturnDate());
        bookBorrowingDTO.setBook(convertBookToDTO(bookBorrowing.getBook()));
        return bookBorrowingDTO;
    }

    /**
     * BookBorrowingDTO'yu kitap ödünç alma entity'sine dönüştürür.
     *
     * @param bookBorrowingDTO Kitap ödünç alma DTO.
     * @return Kitap ödünç alma entity.
     */
    private BookBorrowing convertBookBorrowingToEntity(BookBorrowingDTO bookBorrowingDTO) {
        BookBorrowing bookBorrowing = new BookBorrowing();
        bookBorrowing.setId(bookBorrowingDTO.getId());
        bookBorrowing.setBorrowerName(bookBorrowingDTO.getBorrowerName());
        bookBorrowing.setBorrowerEmail(bookBorrowingDTO.getBorrowerEmail());
        bookBorrowing.setBorrowingDate(bookBorrowingDTO.getBorrowingDate());
        bookBorrowing.setReturnDate(bookBorrowingDTO.getReturnDate());
        bookBorrowing.setBook(convertBookToEntity(bookBorrowingDTO.getBook()));
        return bookBorrowing;
    }

    /**
     * Kitap entity'sini BookDTO'ya dönüştürür.
     *
     * @param book Kitap entity.
     * @return Kitap DTO.
     */
    private BookDTO convertBookToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setPublicationYear(book.getPublicationYear());
        bookDTO.setStock(book.getStock());
        bookDTO.setAuthor(convertAuthorToDTO(book.getAuthor()));
        bookDTO.setPublisher(convertPublisherToDTO(book.getPublisher()));
        bookDTO.setCategories(book.getCategories().stream().map(this::convertCategoryToDTO).collect(Collectors.toSet()));
        return bookDTO;
    }

    /**
     * BookDTO'yu kitap entity'sine dönüştürür.
     *
     * @param bookDTO Kitap DTO.
     * @return Kitap entity.
     */
    private Book convertBookToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setStock(bookDTO.getStock());
        book.setAuthor(convertAuthorToEntity(bookDTO.getAuthor()));
        book.setPublisher(convertPublisherToEntity(bookDTO.getPublisher()));
        book.setCategories(bookDTO.getCategories().stream().map(this::convertCategoryToEntity).collect(Collectors.toSet()));
        return book;
    }

    /**
     * Yazar entity'sini AuthorDTO'ya dönüştürür.
     *
     * @param author Yazar entity.
     * @return Yazar DTO.
     */
    private AuthorDTO convertAuthorToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setBirthDate(author.getBirthDate());
        authorDTO.setCountry(author.getCountry());
        return authorDTO;
    }

    /**
     * AuthorDTO'yu yazar entity'sine dönüştürür.
     *
     * @param authorDTO Yazar DTO.
     * @return Yazar entity.
     */
    private Author convertAuthorToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setBirthDate(authorDTO.getBirthDate());
        author.setCountry(authorDTO.getCountry());
        return author;
    }

    /**
     * Yayınevi entity'sini PublisherDTO'ya dönüştürür.
     *
     * @param publisher Yayınevi entity.
     * @return Yayınevi DTO.
     */
    private PublisherDTO convertPublisherToDTO(Publisher publisher) {
        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        publisherDTO.setEstablishmentYear(publisher.getEstablishmentYear());
        publisherDTO.setAddress(publisher.getAddress());
        return publisherDTO;
    }

    /**
     * PublisherDTO'yu yayınevi entity'sine dönüştürür.
     *
     * @param publisherDTO Yayınevi DTO.
     * @return Yayınevi entity.
     */
    private Publisher convertPublisherToEntity(PublisherDTO publisherDTO) {
        Publisher publisher = new Publisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        publisher.setEstablishmentYear(publisherDTO.getEstablishmentYear());
        publisher.setAddress(publisherDTO.getAddress());
        return publisher;
    }

    /**
     * Kategori entity'sini CategoryDTO'ya dönüştürür.
     *
     * @param category Kategori entity.
     * @return Kategori DTO.
     */
    private CategoryDTO convertCategoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    /**
     * CategoryDTO'yu kategori entity'sine dönüştürür.
     *
     * @param categoryDTO Kategori DTO.
     * @return Kategori entity.
     */
    private Category convertCategoryToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        return category;
    }
}
