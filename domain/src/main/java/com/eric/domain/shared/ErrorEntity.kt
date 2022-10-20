package com.eric.domain.shared

sealed class ErrorEntity(val message: String) {

    object NoError : ErrorEntity("") {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class NoResponse(message: String) : ErrorEntity(message) {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

    class Unknown(message: String) : ErrorEntity(message) {
        override fun toString(): String {
            return this.javaClass.canonicalName ?: ""
        }
    }

}