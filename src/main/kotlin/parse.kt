package org.example

import java.io.File

fun tagParser(tagString: String, tagName: String, attr: String): String  {
    val matchTag = Regex(tagName).find(tagString)
    var result = ""
    if(matchTag != null) {
        for (index in tagString.indices step(1)) {
            println(tagString[index].toString())
            if(tagString[index].toString() == attr) {
                println(tagString[index].toString())
            }
        }
    }
    return result
}

fun parser(fileName: String, extension: String): ArrayList<String> {
    val urlArray = ArrayList<String>()
    File(fileName).forEachLine {
        val isAdaptationSet = Regex("AdaptationSet").find(it)
        val isSegmentTemplate = Regex("SegmentTemplate").find(it)
        val isRepresentation = Regex("Representation").find(it)
        if(isAdaptationSet != null) {
            tagParser(it, "AdaptationSet", "id")
//            println(it)
        }
        if(isSegmentTemplate != null) {
//            println(it)
        }
        if(isRepresentation != null) {
//            println(it)
        }
//        val matchResult = Regex("#").find(it)
//        if(matchResult != null) {
////            println("-------------------------------------------------------")
//            val nameAndTagList = it.split(":")
////            println(nameAndTagList)
//            if(nameAndTagList[0] == "#EXT-X-MEDIA") {
//                val tagList = nameAndTagList[1].split(",")
////                println(nameAndTagList[0])
//                tagList.forEach { tagItem ->
//                    val tag = tagItem.split("=")
//                    println("${tag[0]}: ${tag[1]}")
//                }
//            }
//        } else {
//            val result = Regex(extension).find(it)
//            if(result != null) {
////                println("url: https://test-streams.mux.dev/x36xhzz/${it}")
//                urlArray.add("${baseUrl}${it}")
//            }
//        }
    }
    return urlArray
}