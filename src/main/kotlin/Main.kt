package org.example

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import java.net.http.HttpClient
import java.nio.file.Paths

const val baseUrl = "https://dash.akamaized.net/akamai/bbb_30fps/"
@OptIn(ExperimentalStdlibApi::class)
suspend fun main() {
    println("Hello World!")
    val client = HttpClient(CIO)

    val fileName = "bbb_30fps.mpd"
    val resourceFile = Paths.get("src/main/resources", fileName).toString()
    println(resourceFile)
    val dashlArray =  parser(resourceFile, ".mpd")
    if(dashlArray.isNotEmpty()) {
        val response: HttpResponse = client.get(dashlArray[0])
        val body = response.body<String>()
        val bodyToArray = body.split("\n")
        val filterVideoArray = bodyToArray.filter { Regex(".m4v").find(it) != null }
        val filterAudioArray = bodyToArray.filter { Regex(".m4a").find(it) != null }
        if(filterVideoArray.isNotEmpty()) {
            val videoResponse = client.get("${baseUrl}${filterVideoArray[0]}")
            val videoBody = videoResponse.body<ByteArray>().toHexString()
            println(videoBody)
        }
        if(filterAudioArray.isNotEmpty()) {
            val audioResponse = client.get("${baseUrl}${filterAudioArray[0]}")
            val audioBody = audioResponse.body<ByteArray>().toHexString()
            println(audioBody)
        }
    }

    client.close()
}