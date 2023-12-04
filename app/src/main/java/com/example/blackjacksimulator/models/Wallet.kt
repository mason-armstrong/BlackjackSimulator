package com.example.blackjacksimulator.models

class Wallet {
    private var total = 1000
    private var money = 0


    fun getMoney(): Int = money

    fun addMoney(amount: Int) {
        money += amount
    }

    fun subtractMoney(amount: Int) {
        if(amount > money) {
            throw Exception("Not enough money")
        }
        money -= amount
    }

    fun getTotal(): Int = total

    fun setTotal(amount: Int) {
        total = amount
    }

    fun reset() {
        money = total
    }


}