package com.unnamed.mobile.api

import com.unnamed.mobile.component.model.Blob
import com.unnamed.mobile.component.model.Hazard
import com.unnamed.mobile.component.model.Static
import com.unnamed.mobile.component.model.TargetPoint
import com.unnamed.mobile.component.view.MapUiManager

object TokenEncoder {
    fun tokenPause(): String {
        return "PSR/"
    }
    fun tokenResume(): String {
        return "RSR/"
    }
    //TODO NLP input format 파싱
    fun tokenStaticUpdated(statics: List<String>): String {
        var token = "UDM/"
        for (static in statics) {
            token += static
        }
        return token
    }
    fun tokenMapInit(): String {
        var token: String = "UMG/"
        MapUiManager.mapSize.let {
            token += "m${it.first},${it.second}/"
        }
        MapUiManager.getRobotLocation().let {
            token += "r${it.first.toInt()},${it.second.toInt()}/"
        }
        MapUiManager.getStatics().let {
            for (static in it) {
                token += staticToToken(static)
            }
        }
        return token
    }
    private fun staticToToken(static: Static): String {
        return when(static){
            is Blob -> {
                "b${static.location.first},${static.location.second}/"
            }
            is Hazard -> {
                "h${static.location.first},${static.location.second}/"
            }
            is TargetPoint -> {
                "t${static.location.first},${static.location.second}/"
            }
            else -> {
                ""
            }
        }
    }
}