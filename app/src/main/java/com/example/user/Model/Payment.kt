package com.example.user.Model

class Payment(
    name: String,
    card: String,
    reason: String,
    ccv: Int,
    type: String,
    month: String,
    year: String,
    amount: Double
) {

    private var id: String? = null
    private var name: String? = name
    private var card: String? = card
    private var reason: String? = reason
    private var ccv: Int? = ccv
    private var month: String? = month
    private var year: String? = year
    private var amount = amount
    private var type: String? = type

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getCcv(): Int? {
        return ccv
    }

    fun setCcv(ccv: Int?) {
        this.ccv = ccv
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCard(): String? {
        return card
    }

    fun setCard(card: String?) {
        this.card = card
    }

    fun getReason(): String? {
        return reason
    }

    fun setReason(reason: String?) {
        this.reason = reason
    }

    fun getMonth(): String? {
        return month
    }

    fun setMonth(month: String?) {
        this.month = month
    }

    fun getYear(): String? {
        return year
    }

    fun setYear(year: String?) {
        this.year = year
    }

    fun getAmount(): Double {
        return amount
    }

    fun setAmount(amount: Double) {
        this.amount = amount
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

}

class Payment_all(
    id: String,
    name: String,
    card: String,
    reason: String,
    ccv: Int,
    type: String,
    month: String,
    year: String,
    amount: Double
) {

    private var id: String? = id
    private var name: String? = name
    private var card: String? = card
    private var reason: String? = reason
    private var ccv: Int? = ccv
    private var month: String? = month
    private var year: String? = year
    private var amount = amount
    private var type: String? = type

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }

    fun getCcv(): Int? {
        return ccv
    }

    fun setCcv(ccv: Int?) {
        this.ccv = ccv
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCard(): String? {
        return card
    }

    fun setCard(card: String?) {
        this.card = card
    }

    fun getReason(): String? {
        return reason
    }

    fun setReason(reason: String?) {
        this.reason = reason
    }

    fun getMonth(): String? {
        return month
    }

    fun setMonth(month: String?) {
        this.month = month
    }

    fun getYear(): String? {
        return year
    }

    fun setYear(year: String?) {
        this.year = year
    }

    fun getAmount(): Double {
        return amount
    }

    fun setAmount(amount: Double) {
        this.amount = amount
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

}