<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Payment Confirmation</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f7f7f7;
        }
        .confirmation-container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        .confirmation-header {
            text-align: center;
            margin-bottom: 20px;
        }
        .alert-success-custom {
            background-color: #28a745;
            color: #fff;
            border-radius: 10px;
            padding: 20px;
            font-size: 1.1rem;
        }
        .details-container {
            margin-top: 20px;
        }
        .details-item {
            margin-bottom: 15px;
        }
        .details-item strong {
            display: inline-block;
            width: 150px;
        }
        .estimated-delivery {
            text-align: center;
            margin-top: 30px;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
            border-radius: 20px;
            padding: 10px 30px;
            border: none;
            text-align: center;
        }
        .btn-custom:hover {
            background-color: #0056b3;
        }
        .footer-note {
            text-align: center;
            margin-top: 40px;
            font-size: 0.9rem;
            color: #6c757d;
        }
    </style>
</head>
<body>
<div class="container confirmation-container">
    <h1 class="confirmation-header">Payment Successful!</h1>
    <div class="alert alert-success-custom text-center">
        <p>Your purchase has been successfully processed!</p>
    </div>

    <div class="details-container">
        
        <div class="details-item">
            <strong>Amount Paid:</strong>
            $<%= request.getAttribute("productPrice") %>
        </div>
        <div class="details-item">
            <strong>Payment Method:</strong>
            <%= request.getAttribute("paymentMethod") %>
        </div>
        <div class="details-item">
            <strong>Shipping Address:</strong>
            <%= request.getAttribute("address") %>
        </div>
    </div>

    <div class="estimated-delivery">
        <h5>Estimated Delivery Date:</h5>
        <p>Your product is expected to arrive by <strong><%= new java.text.SimpleDateFormat("MMMM dd, yyyy").format(new java.util.Date(System.currentTimeMillis() + 5 * 24 * 60 * 60 * 1000)) %></strong> (within 5 business days).</p>
    </div>

    <div class="text-center mt-4">
        <a href="productList" class="btn btn-custom">Continue Shopping</a>
    </div>

    <div class="footer-note">
        <p>Thank you for shopping with us! For any queries, feel free to <a href="mailto:saik62108@gmail.com">contact us</a> at <strong>saik62108@gmail.com</strong> or call <strong>630-449-1650</strong>.</p>
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
