<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Details</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 30px;
        }
        .form-control[readonly] {
            background-color: #e9ecef;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Payment Details</h2>

    <form id="paymentForm" action="completePurchase" method="post">
        <!-- Hidden field for Product ID -->
        <input type="hidden" name="productId" value="${product.id}">

        <!-- Display product details -->
        <div class="form-group">
            <label for="productName">Product Name:</label>
            <input type="text" class="form-control" id="productName" name="productName" value="${product.name}" readonly>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" class="form-control" id="price" name="price" value="${product.price}" readonly>
        </div>
       

        <!-- Additional fields for payment -->
        <div class="form-group">
            <label for="paymentDate">Payment Date:</label>
            <input type="text" class="form-control" id="paymentDate" name="paymentDate" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>" readonly>
        </div>
        <div class="form-group">
            <label for="paymentMethod">Payment Method:</label>
            <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                <option value="Credit Card">Credit Card</option>
                <option value="Debit Card">Debit Card</option>
                <option value="PayPal">PayPal</option>
                <option value="Bank Transfer">Bank Transfer</option>
            </select>
        </div>

        <!-- Submit button -->
        <button type="submit" class="btn btn-primary">Submit Payment</button>
    </form>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
