package com.example.api.Models
import org.json.JSONObject
import java.net.URLDecoder
import java.net.URLEncoder

data class  Recipe(
    var id : String? = null,
    var name : String? = null,
    var servings : String? = null,
    var prepTimeMinutes: String? = null,
    var difficulty : String? = null,
    var image  : String? = null,
){
    companion object{
        fun fromJson(json: JSONObject) : Recipe{
            return Recipe(
                json.getString("id"),
                json.getString("name"),
                json.getString("servings"),
                json.getString("prepTimeMinutes"),
                json.getString("difficulty"),
                json.getString("image"),
            )
        }
    }
}

fun String.encodeUrl() : String {
    return URLEncoder.encode(this, "UTF-8")
}

fun String.decodeUrl() : String {
    return URLDecoder.decode(this, "UTF-8")
}