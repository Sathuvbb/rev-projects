<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.example.demo.dto.ProductResponse" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buy Now</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card-custom {
            border: 1px solid #ddd;
            border-radius: 0.5rem;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .btn-custom {
            background-color: #28a745;
            color: #fff;
        }
        .btn-custom:hover {
            background-color: #218838;
        }
        .form-control:focus {
            box-shadow: none;
            border-color: #28a745;
        }
        .alert-custom {
            border-radius: 0.5rem;
        }
    </style>
</head>
<body>
<div class="container mt-5">
    <h1 class="text-center mb-4">Buy Now</h1>
    <%
        ProductResponse product = (ProductResponse) request.getAttribute("product");
        if (product != null) {
    %>
    <div class="card card-custom mb-4">
        <div class="card-body">
            <h5 class="card-title"><%= product.getName() %></h5>
            <p class="card-text"><strong>Description:</strong> <%= product.getDescription() %></p>
            <p class="card-text"><strong>Price:</strong> $<%= product.getPrice() %></p>
            
            <!-- Buy Now Form -->
            <form action="processPayment" method="post">
                <input type="hidden" name="productId" value="<%= product.getId() %>">
                <input type="hidden" name="productPrice" value="<%= product.getPrice() %>">

                <div class="form-group">
                    <label for="paymentMethod">Payment Method:</label>
                    <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                        <option value="Credit Card">Credit Card</option>
                        <option value="PayPal">PayPal</option>
                        <option value="Debit Card">Debit Card</option>
                        <option value="Cash on Delivery">Cash on Delivery</option> <!-- Added COD option -->
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="address">Shipping Address:</label>
                    <input type="text" class="form-control" id="address" name="address" placeholder="Enter your address" required>
                </div>

                <button type="submit" class="btn btn-custom">Confirm Purchase</button>
            </form>
        </div>
    </div>
    <%
        } else {
    %>
    <div class="alert alert-danger alert-custom">Product not found.</div>
    <%
        }
    %>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
