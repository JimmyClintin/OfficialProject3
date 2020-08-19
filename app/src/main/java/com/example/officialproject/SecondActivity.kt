package com.example.officialproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolscientistsexample.ServerCommand

import kotlinx.coroutines.launch


class SecondActivity : AppCompatActivity() {
    private lateinit var rvMenuFood: RecyclerView
    private val serverCommand = ServerCommand()
    private lateinit var adapter: FoodAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        rvMenuFood = findViewById(R.id.rvFoodMenu)
        rvMenuFood.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            val list = serverCommand.getFoodList()
            Log.i("FoodList", list.toString())
            adapter = FoodAdapter(list.nameoffood_list)
            rvMenuFood.adapter = adapter
        }
        val makeOrder = findViewById<Button>(R.id.makeOrder)
        makeOrder.setOnClickListener {
            lifecycleScope.launch {
                ServerCommand().makeOrder(adapter.order)
            }
        }
    }

    inner class FoodAdapter(val foodList: List<Food>) :
        RecyclerView.Adapter<FoodAdapter.FoodViewHolder>(){
        public val order: MutableSet< OrderFood> = mutableSetOf()
        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val foodTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            val switch = itemView.findViewById<Switch>(R.id.switchFood)
            var item: Food? = null

            init {
                switch.setOnCheckedChangeListener { view, b ->
                    // Делаем запрос. В item хранится актуальный элемент списка.
                    val item = this.item?:return@setOnCheckedChangeListener
                    if(b) {
                        order.add(OrderFood(item.id))
                    }
                    else {
                        order.remove(OrderFood(item.id))
                    }
                }
            }

            public fun bind(menu: Food) {
                item = menu
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

