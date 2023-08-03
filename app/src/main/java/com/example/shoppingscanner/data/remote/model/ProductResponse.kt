package com.example.shoppingscanner.data.remote.model

data class ProductResponse(
    val products: List<Product>?
)


data class Product(
    val barcode_number: String?,
    val barcode_formats: String?,
    val mpn: String?,
    val model: String?,
    val asin: String?,
    var title: String?,
    var category: String?,
    val manufacturer: String?,
    val brand: String?,
    val contributors: List<Contributor>?,
    val age_group: String?,
    val ingredients: String?,
    val nutrition_facts: String?,
    val energy_efficiency_class: String?,
    val color: String?,
    val gender: String?,
    val material: String?,
    val pattern: String?,
    val format: String?,
    val multipack: String?,
    val size: String?,
    val length: String?,
    val width: String?,
    val height: String?,
    val weight: String?,
    val release_date: String?,
    val description: String?,
    val features: List<String>?,
    val images: List<String>?,
    val last_update: String?,
    var stores: List<Store>?,
    val reviews: List<Review>?
)

data class Contributor(
    val role: String?,
    val name: String?
)

data class Store(
    val name: String?,
    val country: String?,
    val currency: String?,
    val currency_symbol: String?,
    val price: String?,
    val sale_price: String?,
    val tax: List<Tax>?,
    val link: String?,
    val item_group_id: String?,
    val availability: String?,
    val condition: String?,
    val shipping: List<Shipping>?,
    val last_update: String?
)

data class Tax(
    val country: String?,
    val region: String?,
    val rate: String?,
    val tax_ship: String?
)

data class Shipping(
    val country: String?,
    val region: String?,
    val service: String?,
    val price: String?
)

data class Review(
    val name: String?,
    val rating: String?,
    val title: String?,
    val review: String?,
    val date: String?
)
