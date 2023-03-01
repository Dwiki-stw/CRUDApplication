package com.dwiki.crudapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dwiki.crudapplication.database.Quote
import com.dwiki.crudapplication.databinding.ItemRowBinding

class ListQuoteAdapter(private val listQuote: List<Quote>) : RecyclerView.Adapter<ListQuoteAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickedCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    class ViewHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        val buttonDelete = binding.btnDelete
        val buttonEdit = binding.btnEdit

        fun bind(quote: Quote){
            binding.apply {
                tvQuote.text = "\"${quote.text}\""
                tvName.text = "- ${quote.name} -"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listQuote[position])

        holder.buttonDelete.setOnClickListener {
            onItemClickCallback.buttonDelete(listQuote[position])
        }
        holder.buttonEdit.setOnClickListener {
            onItemClickCallback.buttonEdit(listQuote[position])
            Log.d("id quote", "${listQuote[position].id}")
        }
    }

    override fun getItemCount(): Int {
        return listQuote.size
    }

    interface OnItemClickCallback{
        fun buttonDelete(quote: Quote)
        fun buttonEdit(quote: Quote)
    }

}