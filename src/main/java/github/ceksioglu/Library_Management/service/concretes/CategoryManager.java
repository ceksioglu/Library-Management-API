package github.ceksioglu.Library_Management.service.concretes;

import github.ceksioglu.Library_Management.dto.CategoryDTO;
import github.ceksioglu.Library_Management.dto.BookDTO;
import github.ceksioglu.Library_Management.dto.AuthorDTO;
import github.ceksioglu.Library_Management.dto.PublisherDTO;
import github.ceksioglu.Library_Management.entity.Category;
import github.ceksioglu.Library_Management.entity.Book;
import github.ceksioglu.Library_Management.entity.Author;
import github.ceksioglu.Library_Management.entity.Publisher;
import github.ceksioglu.Library_Management.core.exception.ResourceNotFoundException;
import github.ceksioglu.Library_Management.repository.CategoryRepository;
import github.ceksioglu.Library_Management.service.abstracts.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Kategori servisi için yönetici sınıfı.
 */
@Service
public class CategoryManager implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Tüm kategorileri getirir.
     *
     * @return Kategorilerin listesi.
     */
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(this::convertCategoryToDTO).collect(Collectors.toList());
    }

    /**
     * Kimliğe göre kategoriyi getirir.
     *
     * @param id Kategori kimliği.
     * @return Kategori DTO.
     */
    @Override
    public CategoryDTO getCategoryById(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kategori bulunamadı"));
        return convertCategoryToDTO(category);
    }

    /**
     * Yeni kategori oluşturur.
     *
     * @param categoryDTO Kategori DTO.
     * @return Oluşturulan kategori DTO.
     */
    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = convertCategoryToEntity(categoryDTO);
        category = categoryRepository.save(category);
        return convertCategoryToDTO(category);
    }

    /**
     * Kategoriyi günceller.
     *
     * @param id Kategori kimliği.
     * @param categoryDTO Güncellenmiş kategori DTO.
     * @return Güncellenmiş kategori DTO.
     */
    @Override
    public CategoryDTO updateCategory(int id, CategoryDTO categoryDTO) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kategori bulunamadı"));
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setDescription(categoryDTO.getDescription());
        categoryRepository.save(existingCategory);
        return convertCategoryToDTO(existingCategory);
    }

    /**
     * Kategoriyi siler.
     *
     * @param id Kategori kimliği.
     */
    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

    // Yardımcı yöntemler: DTO ve Entity arasında dönüştürme yapar

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
        categoryDTO.setBooks(category.getBooks().stream().map(this::convertBookToDTO).collect(Collectors.toSet()));
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
        category.setBooks(categoryDTO.getBooks().stream().map(this::convertBookToEntity).collect(Collectors.toSet()));
        return category;
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
}
