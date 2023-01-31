package com.api.test_localiza.mapper

interface Mapper<T, U> {

    fun map(obj: T): U

}