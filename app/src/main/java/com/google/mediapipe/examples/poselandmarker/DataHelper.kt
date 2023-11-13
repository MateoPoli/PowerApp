package com.google.mediapipe.examples.poselandmarker

import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult

class DataHelper {
    companion object {
        var lankMarksList: MutableList<PoseLandmarkerResult> = mutableListOf()
        var infTime: MutableList<Long> = mutableListOf()
        var thighSize: Double = 0.0
    }
}