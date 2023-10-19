package com.google.mediapipe.examples.poselandmarker

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.*
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark

class SquatEvaluator : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.squat_evaluator)

        var sequenceResults : MutableList<List<String>> =
            mutableListOf(listOf("Rep", "Distance", "Velocity"))
        var landmarkResults = DataHelper.lankMarksList
        var inferenceResults = DataHelper.infTime
        if (landmarkResults.isEmpty()){
            createTable(sequenceResults)
            return
        }
        var DetectionQuantity = landmarkResults.size
        var squatStage: String = "up"
        var scuatCounter: Int = 0
        var lastAngle : Double = 360.0
        var timeSquat : Long = 0

        landmarkResults.forEachIndexed { index, poseLandmarkerResult ->
            val lnks = poseLandmarkerResult.landmarks()
            if (lnks.isNotEmpty()) {
                var hip = lnks.get(0).get(23)
                var knee = lnks.get(0).get(25)
                var ankle = lnks.get(0).get(27)
                var knee_angle = calculateAngle(hip, knee, ankle)

                if (knee_angle <= 90){
                    if (squatStage=="up"){
                        scuatCounter++
                        lastAngle = knee_angle
                        squatStage = "down"
                    }
                    if (squatStage=="down" && knee_angle < lastAngle) {
                        lastAngle = knee_angle
                        timeSquat = 0
                    }
                }

                if(squatStage=="down"){
                    timeSquat += inferenceResults.get(index)
                    if (knee_angle >= 170){
                        var segTimesquat = timeSquat.toFloat()/1000.0
                        var squatDistance = calculateDistance(DataHelper.personHeight, lastAngle)
                        var velocity = CalVelocity(squatDistance, segTimesquat)
                        var result: List<String> =
                            listOf("${scuatCounter}", "${String.format("%.2f", Mdistance)}", "${String.format("%.2f", velocity)}")
                        sequenceResults.add(result)
                        squatStage = "up"
                    }
                }
            }
        }
        createTable(sequenceResults)
    }

    fun calculateAngle(a: NormalizedLandmark, b: NormalizedLandmark, c: NormalizedLandmark): Double {
        val radians = atan2(c.y() - b.y(), c.x() - b.x()) - atan2(a.y() - b.y(), a.x() - b.x())
        var angle = Math.abs((radians * 180.0) / Math.PI)

        if (angle > 180.0) {
            angle = 360 - angle
        }
        return angle
    }
    
    fun calculateDistance(legHeight: Double, kneeAngle: Double): Double{
        val a = legHeight
        val c = legHeight
        val angleB = Math.toRadians(180 - kneeAngle)

        return sqrt((a * a) + (c * c) - (2 * a * c * Math.cos(angleB)))
    }

    fun CalVelocity(distance: Double, time: Double): Double{
        return distance / time
    }

    fun createTable(data: MutableList<List<String>>){
        val cardView: CardView = findViewById(R.id.cardView)
        val horizontalScrollView: HorizontalScrollView = cardView.findViewById(R.id.horizontalScrollView)
        val tableLayout = TableLayout(this)
        tableLayout.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        tableLayout.setBackgroundColor(Color.WHITE)
        // Ancho fijo para las columnas (en píxeles)
        val columnWidths = intArrayOf(300, 300, 300)
        for (i in data.indices) {
            val rowData = data[i]
            val tableRow = TableRow(this)
            tableRow.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )

            for (j in rowData.indices) {
                val textView = TextView(this)
                textView.text = rowData[j]
                textView.setPadding(8, 8, 8, 8)
                textView.gravity = Gravity.CENTER
                if (i == 0) {
                    textView.setTypeface(null, Typeface.BOLD)
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20f)
                }
                val params = TableRow.LayoutParams(columnWidths[j], TableRow.LayoutParams.WRAP_CONTENT)
                tableRow.addView(textView, params)
            }
            tableLayout.addView(tableRow)
        }
        horizontalScrollView.addView(tableLayout)
    }

    fun onCloseButtonClick(view: View) {
        DataHelper.personHeight = 0.0
        DataHelper.barbellWeight = 0.0
        DataHelper.lankMarksList = mutableListOf()
        DataHelper.infTime = mutableListOf()
        val intent = Intent(this, SquatForm::class.java)
        startActivity(intent)
    }
}