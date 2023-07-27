package com.emrehmrc.validator

/**
 *  Rev           1.0
 *  Author        EmreHamurcu
 *  Date          9/17/2021
 *  FileName      ValidatorResolver
 */
class ValidatorResolver(private val validators: List<Validator<*>>) {

    fun isValid(): ValidationState {
        for (validator in validators) {
            when (val state = validator.isValid()) {
                is ValidationState.InValid -> return state
                is ValidationState.Valid -> Unit
            }
        }
        return ValidationState.Valid
    }

    fun inValidList(): List<ValidationState> {
        val invalidList = ArrayList<ValidationState>()

        for (validator in validators) {
            val state = validator.inValidList()
            for (i in state.indices) {
                invalidList.add(state[i])
            }
        }
        return invalidList
    }

    fun validList(): List<ValidationState> {
        val validList = ArrayList<ValidationState>()

        for (validator in validators) {
            when (val state = validator.isValid()) {
                is ValidationState.InValid -> Unit
                is ValidationState.Valid -> validList.add(state)
            }
        }
        return validList
    }
}