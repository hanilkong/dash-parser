package org.example

import java.io.File

fun attrParser(attr: String): String {
    return attr.split("\"")[1]
}

fun tagParser(allString: String, tagName: String, attr: String): ArrayList<String> {
    val matchTag = Regex(tagName).find(allString)
    val tagArray = allString.split(" ")

    var result: ArrayList<String> = arrayListOf()
    if(matchTag != null) {
        tagArray.forEach { attrValue ->
            val matchAttr = Regex("${attr}=").find(attrValue)
            if(matchAttr != null) {
                val resultAttr = attrParser(attrValue)
                println("${baseUrl}${resultAttr}.m4v")
                result.add(resultAttr)
            }
        }
    }
    return result
}

fun parser(fileName: String, extension: String): ArrayList<String> {
    val urlArray = ArrayList<String>()
    File(fileName).forEachLine {
        tagParser(it, "Representation", "id")
        tagParser(it, "AudioChannelConfiguration", "id")
    }
    return urlArray
}