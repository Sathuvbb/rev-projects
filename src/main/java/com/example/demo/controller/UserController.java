package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.UserDaoimp;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;
    @Autowired
    private UserDaoimp userdaoimp;

    // Show registration form
    @GetMapping("/register")
   
    public String showRegistrationForm() {
        return "user_register"; // Points to user_register.jsp
    }

    // Handle user registration
    @PostMapping("/register")
    public ModelAndView registerUser(UserRequest userRequest) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            boolean isCreated = userService.createUser(userRequest);
            System.out.println(userRequest);
            if (isCreated) {
                // Redirect to login page after successful registration
                modelAndView.setViewName("redirect:/user/login");
            } else {
                // Registration failed, return to the registration form with an error message
                modelAndView.setViewName("user_register");
                modelAndView.addObject("error", "Your Registration is Successful. Please Login.");
            }
        } catch (Exception e) {
            // Handle exception and return error message
            modelAndView.setViewName("user_register");
            modelAndView.addObject("error", "An error occurred. Please try again.");
        }
        return modelAndView;
    }

    // Show login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Points to login.jsp
    }

    // Handle user/admin login
    @PostMapping("/login")
    public ModelAndView loginUser(@RequestParam String email, @RequestParam String password,
                                  @RequestParam String role, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            if ("admin".equals(role)) {
                // Handle admin login
                boolean isAdminValid = userService.validateAdmin(email, password);
                if (isAdminValid) {
                    session.setAttribute("admin", email);
                    modelAndView.setViewName("redirect:/user/admin/dashboard");
                } else {
                    modelAndView.setViewName("login");
                    modelAndView.addObject("error", "Invalid admin credentials. Please try again.");
                }
            } else if ("user".equals(role)) {
                // Handle user login
                boolean isUserValid = userService.validateUser(email, password);
                if (isUserValid) {
                    session.setAttribute("username", email);
                    modelAndView.setViewName("redirect:/user/dashboard");
                } else {
                    modelAndView.setViewName("login");
                    modelAndView.addObject("error", "Invalid user credentials. Please try again.");
                }
            } else {
                // Invalid role selected
                modelAndView.setViewName("login");
                modelAndView.addObject("error", "Invalid role selected. Please try again.");
            }
        } catch (Exception e) {
            modelAndView.setViewName("login");
            modelAndView.addObject("success-message", "An error occurred. Please try again.");
        }
        return modelAndView;
    }

    // Show the user dashboard (dashboard.jsp)
    @GetMapping("/dashboard")
    public String showDashboardPage(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "dashboard"; // Points to dashboard.jsp
        }
        return "redirect:/user/login"; // Redirect to login if not logged in
    }

    // Handle user logout
    @GetMapping("/UserLogout")
    public String logoutUser(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "UserLogout"; // Redirect to login page after logout
    }

    // ====================== Admin Login Code =======================

    // Show the admin dashboard (admin_dashboard.jsp)
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(HttpSession session) {
        if (session.getAttribute("admin") != null) {
            return "admin_dashboard"; // Points to admin_dashboard.jsp
        }
        return "redirect:/user/admin/login"; // Redirect to admin login if not logged in
    }

    // Handle admin logout
    @GetMapping("admin/AdminLogout")
    public String logoutAdmin(HttpSession session) {
        session.invalidate(); // Invalidate the session
        return "AdminLogout"; // Redirect to admin login page after logout
    }

    // ====================== Product List Code =======================

    // Show the product list page (productList.jsp)
    @GetMapping("/productList")
    public String showProductsPage(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "productList"; // Points to productList.jsp (product listing page)
    }
    @GetMapping("admin/createProduct")
    public String showCreateProductForm() {
        return "createProduct"; // Points to createProduct.jsp
    }

    // Handle Create Product submission
    @PostMapping("admin/createProduct")
    public String createProduct(@RequestParam("name") String name,
                                @RequestParam("description") String description,
                                @RequestParam("price") Double price,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        // Assuming userId is retrieved from the logged-in session
        String email = (String) session.getAttribute("username"); 
        Long userId = 1L; // Use a method like userService.findUserIdByEmail(email) to fetch userId in the real application

        // Create a ProductRequest DTO object
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(name);
        productRequest.setDescription(description);
        productRequest.setPrice(price);
        productRequest.setUserId(userId);

        // Call the ProductService to create the product
        boolean isProductCreated = productService.createProduct(productRequest);

        if (isProductCreated) {
            redirectAttributes.addFlashAttribute("successMessage", "Product created successfully.");
            return "	dashboard";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating product.");
            return "createProduct";
        }
    }

    // Display the list of products
  
   
    // Search for products
    @GetMapping("searchProduct")
    public String searchProducts(@RequestParam String query, Model model) {
        model.addAttribute("products", productService.searchProducts(query));
        return "productList"; // Points to productList.jsp with search results
    }
    
    @GetMapping("/BuyNow")
    public String buyNow(@RequestParam("productId") Long productId, Model model) {
        if (productId == null) {
            model.addAttribute("error", "Invalid Product ID.");
            return "error";  // Return an error page if productId is null
        }

        ProductResponse product = productService.getProductById(productId);
        if (product == null) {
            model.addAttribute("error", "Product not found.");
            return "error";  // Return an error page if the product is not found
        }

        model.addAttribute("product", product);
        return "BuyNow";  // Return the Buy Now page
    }

    @PostMapping("/processPayment")
    public String processPayment(
            @RequestParam("productId") Long productId,
            @RequestParam("productPrice") Double productPrice,
            @RequestParam("paymentMethod") String paymentMethod,
            @RequestParam("address") String address,
            Model model) {

        // Here you would process the payment logic
        // For example, save the payment details in the database
        
        // Add product details to model for confirmation page
        model.addAttribute("productId", productId);
        model.addAttribute("productPrice", productPrice);
        model.addAttribute("paymentMethod", paymentMethod);
        model.addAttribute("address", address);

        // Redirect to confirmation page
        return "paymentConfirmation"; // JSP page to show payment confirmation
    }
 

}