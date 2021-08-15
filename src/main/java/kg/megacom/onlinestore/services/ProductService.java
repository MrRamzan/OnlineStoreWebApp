package kg.megacom.onlinestore.services;

import kg.megacom.onlinestore.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    void saveProductToDB(String name, String description, double price);

    List<Product> getAllProduct();

    void deleteProductById(Long id);

    void changeProductName(Long id, String name);

    void changeProductDescription(Long id, String description);

    void changeProductPrice(Long id, double price);
}
