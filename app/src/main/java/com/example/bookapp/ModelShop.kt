package com.example.bookapp

class ModelShop {

    var shopID: Int = 0
    var shopName: String = ""
    var shopCity: String = ""
    var shopAddress: String = ""
    var shopLocation: String = ""

    constructor()

    //constructor
    constructor(
        shopID: Int,
        shopName: String,
        shopCity: String,
        shopAddress: String,
        shopLocation: String
    ) {
        this.shopID = shopID
        this.shopName = shopName
        this.shopCity = shopCity
        this.shopAddress = shopAddress
        this.shopLocation = shopLocation
    }


}