package com.tugcearas.mytravelbook.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tugcearas.mytravelbook.databinding.RecyclerRowBinding
import com.tugcearas.mytravelbook.model.PlaceModel
import com.tugcearas.mytravelbook.view.MapsActivity

class PlaceAdapter(var placeList:List<PlaceModel>): RecyclerView.Adapter<PlaceAdapter.PlaceHolder>() {

    class PlaceHolder(val recyclerRowBinding: RecyclerRowBinding):RecyclerView.ViewHolder(recyclerRowBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceHolder {
        val recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlaceHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: PlaceHolder, position: Int) {
        holder.recyclerRowBinding.recyclerRow.text = placeList.get(position).name
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,MapsActivity::class.java)
            intent.putExtra("selectedPlace", placeList[position])
            intent.putExtra("info","old")
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = placeList.size

}