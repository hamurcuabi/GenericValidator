package com.emrehmrc.validator

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.emrehmrc.validator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private  val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        user = User()
        val userValidator = Validator(user).apply {
            addRule("Name Cannot be empty") { it?.name.isNullOrEmpty() }
            addRule("Username lenght min 5 character") { it?.name!!.length < 5 }
            addRule("Email cannot be empty") { it?.email.isNullOrEmpty() }
            addRule("Email format is wrong") { it?.email.isValidEmail() }
            addRule("Age min 18") { it?.age!! < 18 }
        }

        binding.btnValidate.setOnClickListener {
            Toast.makeText(this,"${ValidatorResolver(listOf(userValidator)).inValidList()}",Toast.LENGTH_LONG).show()
            Log.e("RULE",ValidatorResolver(listOf(userValidator)).inValidList().toString())
        }
    }

    private fun validateAll(userValidator: Validator<User>) {
        val validateResolver = ValidatorResolver(listOf(userValidator))
        when (val state = validateResolver.isValid()) {
            is ValidationState.Valid -> "Valid Do Something"
            is ValidationState.InValid -> "Not Valid ${state.errorMessage}"
        }
    }

    private fun validateList(userValidator: Validator<User>) {
        ValidatorResolver(listOf(userValidator)).inValidList().toString()
    }

    private fun validateOldWay() {
        val pair = if (user.name.isNullOrEmpty()) {
            Pair(false, "Username cannot be empty")
        } else if (user.name.length < 5) {
            Pair(false, "Username lenght min 5 character")
        } else if (user.email.isNullOrEmpty()) {
            Pair(false, "Email cannot be empty")
        } else if (user.email.isValidEmail().not()) {
            Pair(false, "Email format is wrong")
        } else if (user.age < 18) {
            Pair(false, "Age min 18")
        } else Pair(true, "VALID")

        if (pair.first) {
            // VALID DO SOMETHING
        } else {
            //NOT VALID !!
        }
    }

    data class User(
        var name: String = "", // No empty, min 5 character
        var surname: String = "", //Not empty
        var email: String = "", // Not empty, Email format
        var age: Int = 0, // min age 18
    )
}