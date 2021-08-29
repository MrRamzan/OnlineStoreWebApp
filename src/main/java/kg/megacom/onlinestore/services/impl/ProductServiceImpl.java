package kg.megacom.onlinestore.services.impl;

import kg.megacom.onlinestore.dao.ProductRepo;
import kg.megacom.onlinestore.models.Product;
import kg.megacom.onlinestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public void saveProductToDB(MultipartFile file, String name, String description, int price) {
        Product newProduct = new Product();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if (fileName.contains("..")){
            System.out.println("not a valid file");
        }
        try {
            newProduct.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        newProduct.setDescription(description);
        newProduct.setName(name);
        newProduct.setPrice(price);
        productRepo.save(newProduct);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public void changeProductName(Long id, String name) {
        if (productRepo.existsById(id)) {
            Product newProduct = productRepo.getById(id);
            newProduct.setName(name);
            productRepo.save(newProduct);
        }
    }

    @Override
    public void changeProductDescription(Long id, String description) {
        if (productRepo.existsById(id)) {
            Product newProduct = productRepo.getById(id);
            newProduct.setDescription(description);
            productRepo.save(newProduct);
        }
    }

    @Override
    public void changeProductPrice(Long id, int price) {
        if (productRepo.existsById(id)) {
            Product newProduct = productRepo.getById(id);
            newProduct.setPrice(price);
            productRepo.save(newProduct);
        }
    }
}
