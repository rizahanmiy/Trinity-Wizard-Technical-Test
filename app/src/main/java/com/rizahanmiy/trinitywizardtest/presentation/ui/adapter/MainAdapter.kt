package com.rizahanmiy.trinitywizardtest.presentation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizahanmiy.trinitywizardtest.databinding.ItemContactListingBinding
import com.rizahanmiy.trinitywizardtest.data.entities.Contacts


class MainAdapter(
    private val contactList : List<Contacts>,
    private val onItemClick: (Contacts) -> Unit
    ) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var binding: ItemContactListingBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        binding = ItemContactListingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding){
            onItemClick(contactList[it])
        }
    }

    override fun getItemCount(): Int = contactList.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = contactList[position]
        holder.bind(movie)
    }

    class MainViewHolder(
        private val binding: ItemContactListingBinding,
        onItemClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        val sharedPref = itemView.context.getSharedPreferences("SHARED_PREFS", Context.MODE_PRIVATE)

        init {
            itemView.setOnClickListener {
                // this will be called only once.

                with (sharedPref.edit()) {
                    putInt("position", adapterPosition)
                    apply()
                }


                onItemClicked(adapterPosition)
            }
        }

        fun bind(dataResponse: Contacts) {
            binding.data = dataResponse
        }
    }

    class OnClickListener(val clickListener: (data: Contacts) -> Unit) {
        fun onClick(data: Contacts) = clickListener(data)
    }

}



