<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Your Cart</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            transition: transform 0.3s ease;
        }
        .card:hover {
            transform: scale(1.05);
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <h1 class="text-center mb-4">Your Cart</h1>
    <div class="row">
        <th:block th:if="${cartItems != null}">
            <th:block th:each="item : ${cartItems}">
                <div class="col-md-4 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Product Name</h5> <!-- Replace with actual product name -->
                            <p class="card-text">Quantity: <span th:text="${item.quantity}"></span></p>
                            <a th:href="@{/removeFromCart(id=${item.id})}" class="btn btn-danger">Remove</a>
                        </div>
                    </div>
                </div>
            </th:block>
        </th:block>
        <th:block th:if="${cartItems == null}">
            <div class="col-12 text-center">
                <p>Your cart is empty.</p>
            </div>
        </th:block>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
