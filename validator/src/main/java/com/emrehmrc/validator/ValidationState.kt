package com.emrehmrc.validator

/**
 * To avoid using if and make it simple we will wrap it with this class
 */
sealed class ValidationState {
    object Valid : ValidationState()
    data class InValid(val errorMessage: String?) : ValidationState()
}