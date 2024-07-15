package github.ceksioglu.Library_Management.service.abstracts;


import github.ceksioglu.Library_Management.dto.PublisherDTO;

import java.util.List;

/**
 * Yayınevi servisi için arayüz.
 */
public interface PublisherService {

    /**
     * Tüm yayınevlerini getirir.
     * @return Yayınevlerinin listesi.
     */
    List<PublisherDTO> getAllPublishers();

    /**
     * Kimliğe göre yayınevini getirir.
     * @param id Yayınevi kimliği.
     * @return Yayınevi DTO.
     */
    PublisherDTO getPublisherById(int id);

    /**
     * Yeni yayınevi oluşturur.
     * @param publisherDTO Yayınevi DTO.
     * @return Oluşturulan yayınevi DTO.
     */
    PublisherDTO createPublisher(PublisherDTO publisherDTO);

    /**
     * Yayınevini günceller.
     * @param id Yayınevi kimliği.
     * @param publisherDTO Güncellenmiş yayınevi DTO.
     * @return Güncellenmiş yayınevi DTO.
     */
    PublisherDTO updatePublisher(int id, PublisherDTO publisherDTO);

    /**
     * Yayınevini siler.
     * @param id Yayınevi kimliği.
     */
    void deletePublisher(int id);
}
