package com.example.officialproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolscientistsexample.ServerCommand
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {
    private lateinit var rvMenuFood: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        rvMenuFood = findViewById(R.id.rvFoodMenu)
        rvMenuFood.layoutManager = LinearLayoutManager(this)
        rvMenuFood.adapter = FoodAdapter(listOf())
        lifecycleScope.launch {
            rvMenuFood.adapter = FoodAdapter(ServerCommand().getFoodList().nameoffood_list)
            ServerCommand().makeOrder(ServerCommand().getFoodList().nameoffood_list)
            lifecycleScope.launch(Dispatchers.IO) {
            }

        }
    }

    class FoodAdapter(val foodList: List<Food>) :
        RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

        // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder.
// Each data item is just a string in this case that is shown in a TextView.
        inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val foodTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val switch = itemView.findViewById<Switch>(R.id.switchFood)

            public fun bind(menu: Food) {
                foodTitle.text = menu.name
                switch.isChecked = false
            }
        }


        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): FoodAdapter.FoodViewHolder {
// create a new view
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
            return FoodViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
// - get element from your dataset at this position
// - replace the contents of the view with that element
            holder.bind(foodList[position])
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = foodList.size
    }
}
