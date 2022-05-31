package com.example.exceptions

class ProductNotFoundException(id: Long) : BookShopException("Product with id $id not found")