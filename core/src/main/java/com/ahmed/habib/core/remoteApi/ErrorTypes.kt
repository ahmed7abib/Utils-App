package com.ahmed.habib.core.remoteApi

sealed class ErrorTypes(open val errorMessage: UserMessage?) {

    class ConnectError(override val errorMessage: UserMessage? = UserMessage(strMessage = "Check your internet connection and try again!")) :
        ErrorTypes(errorMessage)

    class AuthenticationError(override val errorMessage: UserMessage? = UserMessage(strMessage = "Auth error happened, please logout and login agan!")) :
        ErrorTypes(errorMessage)

    class NoData(override val errorMessage: UserMessage? = UserMessage(strMessage = "No data found!")) :
        ErrorTypes(errorMessage)

    class GeneralError(override val errorMessage: UserMessage, val statusCode: String? = null) :
        ErrorTypes(errorMessage)

    class NetworkError(override val errorMessage: UserMessage, val statusCode: String? = null) :
        ErrorTypes(errorMessage)
}