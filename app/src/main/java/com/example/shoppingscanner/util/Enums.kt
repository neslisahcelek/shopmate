package com.example.shoppingscanner.util

enum class ProductCategory (
    val categoryName: String,
    val searchName: String
){
    
    OFFICE("Ofis", "Office Supplies > Book Accessories"),
    DECORATION("Dekorasyon", "home decor decals"),
    JACKET("Mont", "Clothing > Outerwear > Coats & Jackets"),
    LUGGAGE("Çanta", "Handbags"),
    CHAIR("Koltuk", "chair"),
    DRESS("Elbise", "Clothing > Dresses"),
    SHOE("Ayakkabı", "sneakers")

}