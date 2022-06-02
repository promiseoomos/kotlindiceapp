package com.example.dice

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.dice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        var rollButton : Button = findViewById(R.id.dicer)
        rollButton.setOnClickListener { rollDice() }

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    private fun rollDice(){

        var numSides = 6
        var dice = Dice(numSides)
        var currentRoll1 = dice.roll()
        var currentRoll2 = dice.roll()

        var diceNum : TextView = findViewById(R.id.dice_num)
        var diceNum2 : TextView = findViewById(R.id.dice_num2)

        var resultText : TextView = findViewById(R.id.resulttext)

        diceNum.text = currentRoll1.toString()
        diceNum2.text = currentRoll2.toString()

        resultText.text = "Dice has been rolled successfully."
        if(currentRoll1 == numSides && currentRoll2 == numSides){
            resultText.text = "Magical! You obtained a perfect 6 on both dice!"
        }

        Toast.makeText(this, "Dice has just been rolled", Toast.LENGTH_SHORT).show()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

class Dice (val numSides : Int){

    fun roll() : Int {
        return (1..numSides).random()
    }
}