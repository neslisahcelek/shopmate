package com.example.shoppingscanner.util

enum class ProductCategory (
    val categoryName: String,
    val searchName: String
){
    DECORATION("Dekorasyon", "home"),
    JACKET("Mont", "coats jackets"),
    LUGGAGE("Çanta", "luggage"),
    CHAIR("Koltuk", "chair"),
    SHOE("Ayakkabı", "sneaker")
}