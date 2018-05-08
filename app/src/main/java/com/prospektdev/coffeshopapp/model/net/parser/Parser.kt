package com.prospektdev.coffeshopapp.model.net.parser

interface Parser<Result> {
    fun parse(json: String): Result
}