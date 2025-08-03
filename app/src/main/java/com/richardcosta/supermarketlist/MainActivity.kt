package com.richardcosta.supermarketlist

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.lifecycle.lifecycleScope
import com.richardcosta.supermarketlist.data.AppDatabase
import com.richardcosta.supermarketlist.data.Item
import com.richardcosta.supermarketlist.data.ItemRepository
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = AppDatabase.getDatabase(this).itemDao()
        val repository = ItemRepository(dao)

        lifecycleScope.launch {
            repository.addItem(Item(name = "Maçã", quantity = 3))

            repository.allItems.collect { items ->
                Log.d("TEST_DB", "Itens no banco: ${items.joinToString()}")
            }
        }

        setContent {
            Text("Teste do Repository - Verifique o Logcat!")
        }
    }
}
