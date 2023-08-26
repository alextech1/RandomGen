package com.example.randomgenerator

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout

class SelectNumbers : ComponentActivity() {
    var activity: MainActivity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.select_numbers)
        createAndAddView()
    }

    private fun createAndAddView()
    {
        val root = findViewById<ConstraintLayout>(R.id.root)

        val size = 14
        //val array: IntArray = activity?.concat!!.toIntArray()
        val array = IntArray(size)

        for (i in 0 until size) {
            val button = Button(this).apply {
                layoutParams = ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
                id = i + 1
                text = "${i + 1}"
            }
            root.addView(button)
        }
        val flow = findViewById<Flow>(R.id.flow)
        flow.referencedIds = array
    }

}