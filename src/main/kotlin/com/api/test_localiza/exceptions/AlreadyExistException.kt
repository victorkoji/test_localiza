package com.api.test_localiza.exceptions

import java.lang.RuntimeException

class AlreadyExistException (message: String? = "Already Exist"): RuntimeException(message) {
}