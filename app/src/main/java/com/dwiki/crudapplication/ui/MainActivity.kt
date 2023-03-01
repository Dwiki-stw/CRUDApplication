package com.dwiki.crudapplication.ui

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwiki.crudapplication.R
import com.dwiki.crudapplication.adapter.ListQuoteAdapter
import com.dwiki.crudapplication.database.Quote
import com.dwiki.crudapplication.databinding.ActivityMainBinding
import com.dwiki.crudapplication.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getAllQuote().observe(this){
            if (it.isEmpty()){
                hideUI(binding.tvNotFound, false)
            }else{
                hideUI(binding.tvNotFound, true)
            }
                setupRecyclerview(it)
        }

        binding.btnSave.setOnClickListener {
            saveQuote(createQuote())
        }

    }

    private fun setupRecyclerview(listQuote : List<Quote>){
        binding.rvQuote.layoutManager = LinearLayoutManager(this)
        val adapter = ListQuoteAdapter(listQuote)
        binding.rvQuote.adapter = adapter

        adapter.setOnItemClickedCallback(object: ListQuoteAdapter.OnItemClickCallback{
            override fun buttonDelete(quote: Quote) {
                deleteQuote(quote)
            }

            override fun buttonEdit(quote: Quote) {
                setupDialogUpdate(quote)
                Log.d("quote", "${quote.name}")
            }

        })
    }

    private fun setupDialogUpdate(quote:Quote){
        val dialog = Dialog(this)

        dialog.setTitle(null)

        dialog.setContentView(R.layout.dialog_update)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val inputName = dialog.findViewById<EditText>(R.id.inputName)
        val inputQuote = dialog.findViewById<EditText>(R.id.inputQuote)
        val buttonUpdate = dialog.findViewById<Button>(R.id.btnUpdate)


        dialog.show()

        buttonUpdate.setOnClickListener {
            val quoteUpdate = Quote(name = inputName.text.toString(), text = inputQuote.text.toString(), id = quote.id)
            Log.i("cekquote", quoteUpdate.name.toString())
            updateQuote(quoteUpdate)
            dialog.cancel()
        }

        inputName.setText(quote.name, TextView.BufferType.EDITABLE)
        inputQuote.setText(quote.text, TextView.BufferType.EDITABLE)
    }

    private fun createQuote(): Quote{
        val name = binding.inputName.text.toString()
        val text = binding.inputQuote.text.toString()

        return Quote(name = name, text = text, id = 0)
    }

    private fun saveQuote(quote: Quote){
        mainViewModel.upsert(quote)
    }

    private fun deleteQuote(quote: Quote){
        mainViewModel.delete(quote)
    }

    private fun updateQuote(quote: Quote){
        mainViewModel.upsert(quote)
    }

    private fun hideUI(v:View, condition: Boolean){
        v.visibility = if (condition) GONE else VISIBLE
    }
}