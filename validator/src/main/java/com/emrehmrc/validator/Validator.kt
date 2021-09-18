package com.emrehmrc.validator

/**
 *  Rev           1.0
 *  Author        EmreHamurcu
 *  Date          9/17/2021
 *  FileName      Validator
 */

/**
 * Rule is our business logic
 * @param T is a generic type of any
 */
typealias Rule<T> = (value: T?) -> Boolean

class Validator<T>(private val data: T) {

    private val validationRules = ArrayList<Rule<T>>()
    private var errorMessages = ArrayList<String?>()

    /**
     * @param errorMsg if condition is wrong should return this
     * @param rule business logic
     *
     * @sample addRule("Name Cannot be empty") { it?.name.isNullOrEmpty() }
     */
    fun addRule(errorMsg: String, rule: Rule<T>) {
        validationRules.add(rule)
        errorMessages.add(errorMsg)
    }

    /**
     * Returns ValidationState
     */
    fun isValid(): ValidationState {
        for (i in 0 until validationRules.size) {
            if (validationRules[i](data)) {
                val errorMsg = errorMessages[i]
                return ValidationState.InValid(errorMsg)
            }
        }
        return ValidationState.Valid
    }

    /**
     * Returns a list of invalid ValidationState
     */
    fun inValidList(): List<ValidationState> {
        val invalidStates = ArrayList<ValidationState>()

        for (i in 0 until validationRules.size) {
            if (validationRules[i](data)) {
                val errorMsg = errorMessages[i]
                invalidStates.add(ValidationState.InValid(errorMsg))
            }
        }
        return invalidStates
    }

    /**
     * Returns a list of invalid ValidationState
     */
    fun validList(): List<ValidationState> {
        val validStates = ArrayList<ValidationState>()

        for (i in 0 until validationRules.size) {
            if (validationRules[i](data).not()) {
                validStates.add(ValidationState.Valid)
            }
        }
        return validStates
    }
}

