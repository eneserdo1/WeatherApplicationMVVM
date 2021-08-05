package com.app.weatherapplicationmvvm.ui.MainActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.weatherapplicationmvvm.model.Hourly
import com.app.weatherapplicationmvvm.databinding.ItemHourlyRecyclerviewBinding
import com.app.weatherapplicationmvvm.utils.Constants
import com.app.weatherapplicationmvvm.utils.calculatCelcius
import com.app.weatherapplicationmvvm.utils.getDateTime
import com.bumptech.glide.Glide

class HourlyRecylerviewAdapter : RecyclerView.Adapter<HourlyRecylerviewAdapter.MyHolder>() {
    lateinit var binding: ItemHourlyRecyclerviewBinding
    var originalList:ArrayList<Hourly> = arrayListOf()

    fun setList(newList: List<Hourly>?){
        originalList = newList as ArrayList<Hourly>
        notifyDataSetChanged()
    }

    class MyHolder(val binding: ItemHourlyRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data:Hourly) = with(itemView){
            binding.apply {
                itemHour.text = getDateTime(data.dt,"hh:mm")
                itemTemp.text = "${calculatCelcius(data.temp)}°C"
                itemWind.text =" ${data.wind_speed.toString()}km/s"
                Glide.with(itemView).load("${Constants.IMAGE_URL}${data.weather[0].icon}.png").into(itemImage)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        binding = ItemHourlyRecyclerviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(originalList[position])
    }

    override fun getItemCount(): Int {
        return originalList.size
    }
}