package dev.mitigate.dogapp.utility

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WikiTitle

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class WikiExtract

@FromJson
@WikiTitle
fun titleFromJson(json: Map<String, Any?>): String {
    return json.getValue("title") as String
}

@FromJson
@WikiExtract
fun extractFromJson(json: Map<String, Any?>): String {
    return json.getValue("extract") as String
}