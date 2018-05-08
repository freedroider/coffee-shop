package com.prospektdev.coffeshopapp.model.net.request

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import com.prospektdev.coffeshopapp.model.net.parser.Parser
import java.net.URL

abstract class BaseRequest<M> : Request<M> {

    override fun execute(callback: (M) -> Unit) {
        AsyncTask.execute({
            val json: String = request(getUrl())
            Handler(Looper.getMainLooper()).post({
                callback.invoke(parser.parse(json))
            })
        })
    }

    abstract val parser: Parser<M>
    abstract fun getUrl(): URL
}