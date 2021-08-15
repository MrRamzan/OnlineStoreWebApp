package kg.megacom.onlinestore.controllers;

import kg.megacom.onlinestore.models.Product;
import kg.megacom.onlinestore.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listProducts.html")
    public String showExampleView(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "/listProducts.html";
    }

    @GetMapping("/")
    public String showAddProduct() {
        return "/addProduct.html";
    }

    @PostMapping("/addP")
    public String saveProduct(@RequestParam("pname") String name,
                              @RequestParam("price") double price,
                              @RequestParam("desc") String desc) {
        productService.saveProductToDB(name,desc,price);
        return "redirect:/listProducts.html";
    }

    @GetMapping("/deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/listProducts.html";
    }

    @PostMapping("/changeName")
    public String changePname(@RequestParam("id") Long id, @RequestParam("newPname") String name) {
        productService.changeProductName(id, name);
        return "redirect:/listProducts.html";
    }

    @PostMapping("/changePrice")
    public String changePrice(@RequestParam("id") Long id, @RequestParam("newPrice") double price) {
        productService.changeProductPrice(id, price);
        return "redirect:/listProducts.html";
    }

    @PostMapping("/changeDescription")
    public String changeDescription(@RequestParam("id") Long id, @RequestParam("newDescription") String description) {
        productService.changeProductDescription(id, description);
        return "redirect:/listProducts.html";
    }
}