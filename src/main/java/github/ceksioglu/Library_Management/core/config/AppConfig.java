package github.ceksioglu.Library_Management.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

/**
 * Uygulama genelinde kullanılacak konfigürasyon sınıfı.
 */
@Configuration
public class AppConfig {

    /**
     * ModelMapper bean'i oluşturur.
     *
     * @return ModelMapper.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
