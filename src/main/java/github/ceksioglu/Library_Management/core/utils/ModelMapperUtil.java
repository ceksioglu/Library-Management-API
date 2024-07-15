package github.ceksioglu.Library_Management.core.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ModelMapper yardımcı sınıfı.
 */
@Component
public class ModelMapperUtil {

    private static ModelMapper modelMapper;

    @Autowired
    public ModelMapperUtil(ModelMapper modelMapper) {
        ModelMapperUtil.modelMapper = modelMapper;
    }

    public static ModelMapper getModelMapper() {
        return modelMapper;
    }
}
