package com.emrehmrc.validator

import java.util.regex.Pattern

/**
 *  Rev           1.0
 *  Author        EmreHamurcu
 *  Date          9/17/2021
 *  FileName      ValidationUtil
 */

/*  Some Pattern Sample
        ^                 # start-of-string
        (?=.*[0-9])       # a digit must occur at least once
        (?=.*[a-z])       # a lower case letter must occur at least once
        (?=.*[A-Z])       # an upper case letter must occur at least once
        (?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
        (?=\\S+$)          # no whitespace allowed in the entire string
        .{4,}             # anything, at least six places though
        $                 # end-of-string
*/

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

/**
 * Checks if given string is valid email format
 */
fun String?.isValidEmail(): Boolean {
    return isValidPattern(EMAIL_ADDRESS_PATTERN)
}

/**
 * Can be used in any pattern
 */
fun String?.isValidPattern(pattern: Pattern): Boolean {
    this?.let {
        return pattern.matcher(this).matches()
    }
    return false
}

/**
 * Given Int is in between min and max values, not equals to them!
 * @param min minimum value
 * @param max maximum value
 *
 * @sample 23.isBetween(23,30) should return false
 */
fun Int.isBetween(min: Int, max: Int): Boolean {
    return this in (min + 1) until max
}