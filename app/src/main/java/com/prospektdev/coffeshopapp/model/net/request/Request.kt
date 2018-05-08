package com.prospektdev.coffeshopapp.model.net.request

import java.net.URL

interface Request<M> {
    fun request(url: URL): String = url.readText()
    fun execute(callback: (M) -> Unit)
}