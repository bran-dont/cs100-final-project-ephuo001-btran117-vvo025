package com.example.cookingbythebook

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.example.cookingbythebook.compositepackage.Category
import java.util.*

class MainActivity : AppCompatActivity() {
    var titlePage: Category = Category("")

    //@SuppressLint("ResourceType")
    //@RequiresApi(Build.VERSION_CODES.Q)
    @SuppressLint("ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle: Bundle? = intent.extras

        var categoryInput: String? = bundle?.getString("categoryInput")
        var addCategoryToInput: String? = bundle?.getString("addCategoryTo")

        var mainLinearLayout: LinearLayout? = findViewById(R.id.mainLinearLayout)
        var categoryLinearLayout: LinearLayout? = findCategoryLayout(mainLinearLayout, addCategoryToInput)

        categoryInput = categoryInput?.toLowerCase()
        categoryInput = categoryInput?.capitalize()

        var categoryBtn = Button(applicationContext)
        categoryBtn.setText(categoryInput)
        categoryBtn.setTextColor(resources.getColor(R.color.dark_aqua_blue))
        categoryBtn.setTypeface(ResourcesCompat.getFont(this, R.font.baloo))
        categoryBtn.setTextSize(16F)
        categoryBtn.setBackgroundColor(resources.getColor(R.color.aqua_blue))
        categoryLinearLayout?.addView(categoryBtn)
        categoryBtn.setOnClickListener {
            val intent = Intent(this, CategoryPageActivity::class.java)

            var bundle: Bundle = Bundle()
            val activityCameFrom: String = "Main Activity"

            bundle.putString("activityCameFrom", activityCameFrom)
            bundle.putString("categoryName", categoryInput)
            intent.putExtras(bundle)
            startActivity(intent)
        }

    }

    fun findCategoryLayout(linearLayout: LinearLayout?, _addCategoryInput: String?): LinearLayout? {
            var layout: LinearLayout? = findViewById(R.id.mainLinearLayout)

            for(i in 0 until linearLayout?.childCount!!) {
                var view: View = linearLayout.getChildAt(i)
                if (view is TextView) {
                    var addCategoryInput = _addCategoryInput?.toLowerCase()
                    var categoryName = view.text.toString().toLowerCase()

                    if (categoryName == addCategoryInput) {
                        if (linearLayout.getChildAt(i+1) is ScrollView) {
                            val categoryScrollView: ScrollView = linearLayout.getChildAt(i + 1) as ScrollView
                            layout = categoryScrollView.getChildAt(0) as LinearLayout
                            return layout
                        }
                    }
                }
            }

            return null
    }

    //val button = findViewById<Button>(R.id.addRecipeButton)
    fun goToAddRecipePage(view: View){
        val intent = Intent(this, AddRecipeActivity::class.java)
        startActivity(intent)

    }

    fun goToAddCategoryPage(view: View){
        val intent = Intent(this, AddCategoryActivity::class.java)

        var bundle: Bundle = Bundle()
        val activityCameFrom: String = "Main Activity"
        val addCategoryTo: String = ""
        bundle.putString("activityCameFrom", activityCameFrom)
        bundle.putString("addCategoryTo", addCategoryTo)
        intent.putExtras(bundle)

        startActivity(intent)

    }

    fun goToSearchPage(view: View) {
        val intent = Intent(this, SearchRecipeActivity::class.java)

        var bundle: Bundle = Bundle()
        val activityCameFrom: String = "Main Activity"
        bundle.putString("activityCameFrom", activityCameFrom)

        intent.putExtras(bundle)

        startActivity(intent)
    }
}