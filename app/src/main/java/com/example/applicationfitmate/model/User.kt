package com.example.applicationfitmate.model

data class User(var id: Int, var fName: String, var lName: String, var email: String,
                var loginName: String, var password: String, var age: Int, var height: Int, var weight: Int) {

    override fun toString(): String {
        return "User:[Id: $id, First Name: $fName, Login Name: $loginName, Password: $password]"
    }

}