package com.example.randomgenerator

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var btmNavigationView : BottomNavigationView
    private var concat: Set<Int> = emptySet()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainFragment=MainFragment()
        val favFragment=FavFragment()

        //setCurrentFragment(mainFragment)

        btmNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)!!
        btmNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> setCurrentFragment(mainFragment)
                R.id.favorites -> setCurrentFragment(favFragment)

            }
            true
        }


        val generateButton: Button = findViewById(R.id.buttonGenerate)
        val selNumbers: Button = findViewById(R.id.selectNumbers)

        val numberGenerated: TextView = findViewById(R.id.numberGenerated)
        val numberGenerated2: TextView = findViewById(R.id.numberGenerated2)
        val numberGenerated3: TextView = findViewById(R.id.numberGenerated3)
        val numberGenerated4: TextView = findViewById(R.id.numberGenerated4)
        val numberGenerated5: TextView = findViewById(R.id.numberGenerated5)
        val collectedNumbers: TextView = findViewById(R.id.collectedNumbers)


        generateButton.setOnClickListener {
            val randomNumbers = generateSequence {
                Random.nextInt(1..70)
            }.distinct().take(5).sorted().toSet()
            val randomNumbers2 = generateSequence {
                Random.nextInt(1..70)
            }.distinct().take(5).sorted().toSet()
            val randomNumbers3 = generateSequence {
                Random.nextInt(1..70)
            }.distinct().take(5).sorted().toSet()
            val randomNumbers4 = generateSequence {
                Random.nextInt(1..70)
            }.distinct().take(5).sorted().toSet()
            val randomNumbers5 = generateSequence {
                Random.nextInt(1..70)
            }.distinct().take(5).sorted().toSet()

            concat = concatenate(
                randomNumbers,
                randomNumbers2,
                randomNumbers3,
                randomNumbers4,
                randomNumbers5
            ) //test


            numberGenerated.text = randomNumbers.toString()
            numberGenerated2.text = randomNumbers2.toString()
            numberGenerated3.text = randomNumbers3.toString()
            numberGenerated4.text = randomNumbers4.toString()
            numberGenerated5.text = randomNumbers5.toString()
        }

        selNumbers.setOnClickListener {
            numberGenerated.text = "none"
            numberGenerated2.text = "none"
            numberGenerated3.text = "none"
            numberGenerated4.text = "none"
            numberGenerated5.text = "none"
            collectedNumbers.text = concat.toString()
            createAndAddView()

        }



    }

    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }


    private fun createAndAddView()
    {
        val root = findViewById<ConstraintLayout>(R.id.root)
        lateinit var button: Button

        val array: IntArray = concat.toIntArray()

        for ((index, i) in array.withIndex()) {
                button = Button(this).apply {
                layoutParams = ViewGroup.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)
                id = index + 1
                text = i.toString()
                    setOnClickListener {
                        Toast.makeText(this@MainActivity, "Clicked $i", Toast.LENGTH_SHORT).show()
                    }

            }
            root.addView(button)

        }
        val flow = findViewById<Flow>(R.id.flow)
        flow.referencedIds = (1 .. array.size).toList().toIntArray()


    }


}

private fun Random.nextInt(range: IntRange): Int {
    return range.first + nextInt(range.last - range.first)
}

private fun concatenate(
    randomNumbers: Set<Int>,
    randomNumbers2: Set<Int>,
    randomNumbers3: Set<Int>,
    randomNumbers4: Set<Int>,
    randomNumbers5: Set<Int>
): Set<Int> {
    return randomNumbers + randomNumbers2 + randomNumbers3 + randomNumbers4 + randomNumbers5
}


