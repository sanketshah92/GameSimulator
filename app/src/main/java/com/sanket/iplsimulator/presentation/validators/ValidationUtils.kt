package com.sanket.iplsimulator.presentation.validators

class ValidationUtils {
    private fun isValidIplTeamName(name: String): Boolean {
        return name.isNotBlank()
    }

    fun validateTwoTeams(name: String, name2: String): Boolean {
        val isName1Valid = isValidIplTeamName(name.trim())
        val isName2Valid = isValidIplTeamName(name2.trim())
        return isName1Valid && isName2Valid
    }
}