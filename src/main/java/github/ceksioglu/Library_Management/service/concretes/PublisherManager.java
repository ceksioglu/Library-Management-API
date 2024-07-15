package github.ceksioglu.Library_Management.service.concretes;

import github.ceksioglu.Library_Management.dto.PublisherDTO;
import github.ceksioglu.Library_Management.dto.BookDTO;
import github.ceksioglu.Library_Management.dto.AuthorDTO;
import github.ceksioglu.Library_Management.dto.CategoryDTO;
import github.ceksioglu.Library_Management.entity.Publisher;
import github.ceksioglu.Library_Management.entity.Book;
import github.ceksioglu.Library_Management.entity.Author;
import github.ceksioglu.Library_Management.entity.Category;
import github.ceksioglu.Library_Management.core.exception.ResourceNotFoundException;
import github.ceksioglu.Library_Management.repository.PublisherRepository;
import github.ceksioglu.Library_Management.service.abstracts.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Yayınevi servisi için yönetici sınıfı.
 */
@Service
public class PublisherManager implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherManager(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    /**
     * Tüm yayınevlerini getirir.
     *
     * @return Yayınevlerinin listesi.
     */
    @Override
    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll().stream().map(this::convertPublisherToDTO).collect(Collectors.toList());
    }

    /**
     * Kimliğe göre yayınevini getirir.
     *
     * @param id Yayınevi kimliği.
     * @return Yayınevi DTO.
     */
    @Override
    public PublisherDTO getPublisherById(int id) {
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Yayınevi bulunamadı"));
        return convertPublisherToDTO(publisher);
    }

    /**
     * Yeni yayınevi oluşturur.
     *
     * @param publisherDTO Yayınevi DTO.
     * @return Oluşturulan yayınevi DTO.
     */
    @Override
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = convertPublisherToEntity(publisherDTO);
        publisher = publisherRepository.save(publisher);
        return convertPublisherToDTO(publisher);
    }

    /**
     * Yayınevini günceller.
     *
     * @param id Yayınevi kimliği.
     * @param publisherDTO Güncellenmiş yayınevi DTO.
     * @return Güncellenmiş yayınevi DTO.
     */
    @Override
    public PublisherDTO updatePublisher(int id, PublisherDTO publisherDTO) {
        Publisher existingPublisher = publisherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Yayınevi bulunamadı"));
        existingPublisher.setName(publisherDTO.getName());
        existingPublisher.setEstablishmentYear(publisherDTO.getEstablishmentYear());
        existingPublisher.setAddress(publisherDTO.getAddress());
        publisherRepository.save(existingPublisher);
        return convertPublisherToDTO(existingPublisher);
    }

    /**
     * Yayınevini siler.
     *
     * @param id Yayınevi kimliği.
     */
    @Override
    public void deletePublisher(int id) {
        publisherRepository.deleteById(id);
    }

    // Yardımcı yöntemler: DTO ve Entity arasında dönüştürme yapar

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
        publisherDTO.setBookList(publisher.getBookList().stream().map(this::convertBookToDTO).collect(Collectors.toList()));
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
        publisher.setBookList(publisherDTO.getBookList().stream().map(this::convertBookToEntity).collect(Collectors.toList()));
        return publisher;
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
