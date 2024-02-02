package com.rizahanmiy.trinitywizard.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizahanmiy.trinitywizard.data.entities.Contacts
import com.rizahanmiy.trinitywizard.databinding.ItemContactBinding
import com.rizahanmiy.trinitywizard.util.constant.AppConstants.POSITION_ID


class MainAdapter(
    private val contactList: List<Contacts>,
    private val onItemClick: (Contacts) -> Unit
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var binding: ItemContactBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding) {
            onItemClick(contactList[it])
        }
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = contactList[position]
        holder.bind(movie)
    }

    class MainViewHolder(
        private val binding: ItemContactBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        val sharedPref = itemView.context.getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

        init {
            itemView.setOnClickListener {

                with(sharedPref.edit()) {
                    putInt(POSITION_ID, adapterPosition)
                    apply()
                }


                onItemClicked(adapterPosition)
            }
        }

        fun bind(dataResponse: Contacts) {
            val name = dataResponse.firstName + " " + dataResponse.lastName
            binding.data = dataResponse
            binding.tvName.text = name
        }
    }

    class OnClickListener(val clickListener: (data: Contacts) -> Unit) {
        fun onClick(data: Contacts) = clickListener(data)
    }

}



