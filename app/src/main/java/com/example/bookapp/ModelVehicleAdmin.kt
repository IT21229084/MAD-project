package com.example.bookapp

class ModelVehicleAdmin {

    var vehicleID: String = ""
    var vehicleName: String = ""
    var vehicleDescription: String = ""
    var vehicleCount: String = ""
    var vehicleTypeID: String = ""
    var imagePath: String = ""
    var shopID: String = ""

    constructor()

    constructor(
        vehicleID: String,
        vehicleName: String,
        vehicleDescription: String,
        vehicleCount: String,
        vehicleTypeID: String,
        imagePath: String,
        shopID: String
    ) {
        this.vehicleID = vehicleID
        this.vehicleName = vehicleName
        this.vehicleDescription = vehicleDescription
        this.vehicleCount = vehicleCount
        this.vehicleTypeID = vehicleTypeID
        this.imagePath = imagePath
        this.shopID = shopID
    }
}