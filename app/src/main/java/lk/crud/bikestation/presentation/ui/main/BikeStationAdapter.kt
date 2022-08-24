package lk.crud.bikestation.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import lk.crud.bikestation.BikeStationApp
import lk.crud.bikestation.R
import lk.crud.bikestation.common.Helpers.distance
import lk.crud.bikestation.databinding.ItemBikeBinding
import lk.crud.bikestation.domain.model.Feature
import kotlin.math.roundToInt


class BikeStationAdapter(
    val onItemClickListener: (feature: Feature) -> Unit
) :
    RecyclerView.Adapter<BikeStationAdapter.BikeStationViewHolder>() {

    private val featureList = ArrayList<Feature>()

    inner class BikeStationViewHolder(val binding: ItemBikeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BikeStationViewHolder {
        val view = ItemBikeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BikeStationViewHolder(view)
    }

    override fun onBindViewHolder(holder: BikeStationViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            onItemClickListener(featureList[position])
        }

        holder.binding.txtTitle.text = featureList[position].properties.label
        holder.binding.txtAvailableBikes.text = featureList[position].properties.bikes
        holder.binding.txtAvailablePlaces.text = featureList[position].properties.bike_racks
        val dist = distance(
            BikeStationApp.currentLat,
            BikeStationApp.currentLng,
            featureList[position].geometry.coordinates[0],
            featureList[position].geometry.coordinates[1]
        ).roundToInt()

        holder.binding.txtDistance.text =
            holder.itemView.context.getString(R.string.bike_station, dist)

    }

    override fun getItemCount(): Int {
        return featureList.size
    }

    fun submitList(list: List<Feature>) {
        featureList.clear()
        featureList.addAll(list)
        notifyDataSetChanged()
    }
}