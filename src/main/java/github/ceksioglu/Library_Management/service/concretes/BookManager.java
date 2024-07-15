package github.ceksioglu.Library_Management.service.concretes;

import github.ceksioglu.Library_Management.dto.BookDTO;
import github.ceksioglu.Library_Management.dto.AuthorDTO;
import github.ceksioglu.Library_Management.dto.PublisherDTO;
import github.ceksioglu.Library_Management.dto.CategoryDTO;
import github.ceksioglu.Library_Management.dto.BookBorrowingDTO;
import github.ceksioglu.Library_Management.entity.Book;
import github.ceksioglu.Library_Management.entity.Author;
import github.ceksioglu.Library_Management.entity.Publisher;
import github.ceksioglu.Library_Management.entity.Category;
import github.ceksioglu.Library_Management.entity.BookBorrowing;
import github.ceksioglu.Library_Management.core.exception.ResourceNotFoundException;
import github.ceksioglu.Library_Management.repository.BookRepository;
import github.ceksioglu.Library_Management.service.abstracts.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Kitap servisi için yönetici sınıfı.
 */
@Service
public class BookManager implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookManager(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Tüm kitapları getirir.
     *
     * @return Kitapların listesi.
     */
    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(this::convertBookToDTO).collect(Collectors.toList());
    }

    /**
     * Kimliğe göre kitabı getirir.
     *
     * @param id Kitap kimliği.
     * @return Kitap DTO.
     */
    @Override
    public BookDTO getBookById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kitap bulunamadı"));
        return convertBookToDTO(book);
    }

    /**
     * Yeni kitap oluşturur.
     *
     * @param bookDTO Kitap DTO.
     * @return Oluşturulan kitap DTO.
     */
    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        Book book = convertBookToEntity(bookDTO);
        book = bookRepository.save(book);
        return convertBookToDTO(book);
    }

    /**
     * Kitabı günceller.
     *
     * @param id Kitap kimliği.
     * @param bookDTO Güncellenmiş kitap DTO.
     * @return Güncellenmiş kitap DTO.
     */
    @Override
    public BookDTO updateBook(int id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kitap bulunamadı"));
        existingBook.setName(bookDTO.getName());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());
        existingBook.setStock(bookDTO.getStock());
        existingBook.setAuthor(convertAuthorToEntity(bookDTO.getAuthor()));
        existingBook.setPublisher(convertPublisherToEntity(bookDTO.getPublisher()));
        existingBook.setCategories(bookDTO.getCategories().stream().map(this::convertCategoryToEntity).collect(Collectors.toSet()));
        bookRepository.save(existingBook);
        return convertBookToDTO(existingBook);
    }

    /**
     * Kitabı siler.
     *
     * @param id Kitap kimliği.
     */
    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    // Yardımcı yöntemler: DTO ve Entity arasında dönüştürme yapar

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
}
