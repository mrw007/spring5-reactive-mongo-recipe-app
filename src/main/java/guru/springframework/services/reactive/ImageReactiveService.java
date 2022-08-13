package guru.springframework.services.reactive;

import org.springframework.web.multipart.MultipartFile;

public interface ImageReactiveService {

    void saveImageFile(String recipeId, MultipartFile file);
}
