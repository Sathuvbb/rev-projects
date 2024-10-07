package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import ch.qos.logback.core.model.Model;

import java.util.List;

@RestController
@RequestMapping("/product/")
public class ProductController {



    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/createProduct")
    public String showCreateProductForm() {
        return "CreateProduct";
    }

    @PostMapping("/createProduct")
    public String createProduct(@RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("price") Double price,
                                RedirectAttributes redirectAttributes) {
        // Assuming userId is from the logged-in session
        Long userId = 1L; // Placeholder, in real app fetch it from the session

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        
        // Assuming you have a method to set the User or userId
        product.setId(userId);  // OR product.setUser(userService.findById(userId));

        boolean isProductCreated = productService.saveProduct(product);

        if (isProductCreated) {
            redirectAttributes.addFlashAttribute("successMessage", "Product created successfully.");
            return "redirect:/admin_dashboard";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating product.");
            return "redirect:/createProduct";
        }
    }

    @GetMapping
    @ResponseBody
    public ProductResponse getProductById(@RequestParam Long id) {
        try {
            return productService.getProductById(id);
        } catch (Exception e) {
            return null;
        }
    }

    @PutMapping
    public boolean updateProductById(@RequestBody ProductRequest productRequest) {
        try {
            return productService.createProduct(productRequest);
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProducts(@RequestParam String query) {
        try {
            return productService.searchProducts(query);
        } catch (Exception e) {
            return null;
        }
    }
}