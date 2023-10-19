package com.google.mediapipe.examples.poselandmarker

import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult

class DataHelper {
    companion object {
        var lankMarksList: MutableList<PoseLandmarkerResult> = mutableListOf()
        var infTime: MutableList<Long> = mutableListOf()
        var timeTest: MutableList<Long> = mutableListOf()
        var personHeight: Double = 0.0
        var barbellWeight: Double = 0.0
    }

}