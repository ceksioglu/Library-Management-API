package github.ceksioglu.Library_Management.controller;

import github.ceksioglu.Library_Management.dto.PublisherDTO;
import github.ceksioglu.Library_Management.service.concretes.PublisherManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Yayınevleri için CRUD işlemlerini yöneten controller sınıfı.
 */
@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private final PublisherManager publisherManager;

    @Autowired
    public PublisherController(PublisherManager publisherManager) {
        this.publisherManager = publisherManager;
    }

    /**
     * Tüm yayınevlerini getirir.
     *
     * @return Yayınevlerinin listesi.
     */
    @GetMapping
    public ResponseEntity<List<PublisherDTO>> getAllPublishers() {
        return ResponseEntity.ok(publisherManager.getAllPublishers());
    }

    /**
     * Kimliğe göre yayınevi getirir.
     *
     * @param id Yayınevi kimliği.
     * @return Yayınevi DTO.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PublisherDTO> getPublisherById(@PathVariable int id) {
        return ResponseEntity.ok(publisherManager.getPublisherById(id));
    }

    /**
     * Yeni yayınevi oluşturur.
     *
     * @param publisherDTO Yeni yayınevi verileri.
     * @return Oluşturulan yayınevi DTO.
     */
    @PostMapping
    public ResponseEntity<PublisherDTO> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        return new ResponseEntity<>(publisherManager.createPublisher(publisherDTO), HttpStatus.CREATED);
    }

    /**
     * Yayınevini günceller.
     *
     * @param id Yayınevi kimliği.
     * @param publisherDTO Güncellenmiş yayınevi verileri.
     * @return Güncellenmiş yayınevi DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable int id, @RequestBody PublisherDTO publisherDTO) {
        return ResponseEntity.ok(publisherManager.updatePublisher(id, publisherDTO));
    }

    /**
     * Yayınevini siler.
     *
     * @param id Yayınevi kimliği.
     * @return HTTP Durumu.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable int id) {
        publisherManager.deletePublisher(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
