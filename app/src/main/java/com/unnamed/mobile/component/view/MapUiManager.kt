package com.unnamed.mobile.component.view

import com.unnamed.mobile.component.model.*
import com.unnamed.mobile.component.viewmodel.ComponentViewModel

object MapUiManager {
    //TODO 쓰이는 properties는 다 외부에서 정의할 수 있도록
    var mapSize: Pair<Int, Int> = Pair(7, 6)
    val componentViewModel = ComponentViewModel()

    var robot: Robot = Robot(Pair(4F, 5F))

    private val statics: MutableList<Static> = mutableListOf(
        Blob(Pair(0, 0)),
        Hazard(Pair(1, 1)),
        Blob(Pair(2, 2)),
        TargetPoint(Pair(4, 1)),
    )


    //TODO make change
    fun updateMap(addingStatics: List<Static>) {
        statics.addAll(addingStatics)
    }

    fun uploadMap() {
        componentViewModel.initComponent(statics, robot)
        robot.location = componentViewModel.getRobotLocation()
    }

    fun autoInit(){
        val map = MapDo(
            mapSize = Pair(5, 6),
            robot = Pair(0, 0),
            blob = listOf(Pair(1, 5), Pair(2, 2)),
            hazard = listOf(Pair(1, 1)),
            targetPoint = listOf(Pair(4, 2))
        )
        initMap(map)
    }

    fun initMap(map: MapDo) {
        mapSize = map.mapSize
        moveLocateInit(pairToFloat(map.robot))
        initStatics(map.blob, map.hazard, map.targetPoint)

        uploadMap()
    }

    fun initStatics(
        blobs: List<Pair<Int, Int>>,
        hazards: List<Pair<Int, Int>>,
        targetPoints: List<Pair<Int, Int>>
    ) {
        statics.clear()

        for (targetPoint in targetPoints) {
            addComponent(toTargetPoint(targetPoint))
        }
        for (blob in blobs) {
            addComponent(toBlob(blob))
        }
        for (hazard in hazards) {
            addComponent(toHazard(hazard))
        }

    }

    private fun toBlob(location: Pair<Int, Int>): Static{
        return Blob(location)
    }
    private fun toHazard(location: Pair<Int, Int>): Static{
        return Hazard(location)
    }
    private fun toTargetPoint(location: Pair<Int, Int>): Static{
        return TargetPoint(location)
    }


    private fun addComponent(static: Static) {
        statics.add(static)
        componentViewModel.addComponent(static)
    }

    fun clearMap() {}

    fun getRobotLocation(): Pair<Float, Float> {
        return robot.location
    }

    suspend fun moveRobot(next: Pair<Int, Int>) {
        componentViewModel.moveRobotTo(next)
        robot.location = Pair(next.first.toFloat(), next.second.toFloat())
    }

    private fun moveLocateInit(location: Pair<Float, Float>) {
        robot.location = location
    }

    private fun pairToFloat(pair: Pair<Int, Int>): Pair<Float, Float> {
        return Pair(pair.first.toFloat(), pair.second.toFloat())
    }


}
