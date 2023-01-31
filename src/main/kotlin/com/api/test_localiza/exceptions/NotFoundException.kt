package com.api.test_localiza.exceptions

import java.lang.RuntimeException

class NotFoundException(message: String? = "Not found"): RuntimeException(message) {

}