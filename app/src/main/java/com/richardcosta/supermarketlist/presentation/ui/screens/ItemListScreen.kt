import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.richardcosta.supermarketlist.data.Item

@Composable
fun ItemListScreen() {
    var items by remember {
        mutableStateOf(
            listOf(
                Item(name = "Maçã", quantity = 3, category = "Frutas"),
                Item(name = "Pão", quantity = 1, category = "Padaria"),
                Item(name = "Leite", quantity = 2, category = "Laticínios")
            )
        )
    }

    var showDialog by remember { mutableStateOf(false) }
    var newItemName by remember { mutableStateOf("") }
    var newItemQuantity by remember { mutableStateOf("1") }
    var newItemCategory by remember { mutableStateOf("Outros") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Minha Lista",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(items) { item ->
                    ItemCard(item = item)
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = Color(0xFF4CAF50),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Adicionar item"
            )
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Adicionar novo item") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = newItemName,
                            onValueChange = { newItemName = it },
                            label = { Text("Nome do item") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = newItemQuantity,
                            onValueChange = { newItemQuantity = it },
                            label = { Text("Quantidade") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = newItemCategory,
                            onValueChange = { newItemCategory = it },
                            label = { Text("Categoria") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (newItemName.isNotBlank()) {
                                items = items + Item(
                                    name = newItemName,
                                    quantity = newItemQuantity.toIntOrNull() ?: 1,
                                    category = newItemCategory
                                )
                                newItemName = ""
                                newItemQuantity = "1"
                                newItemCategory = "Outros"
                                showDialog = false
                            }
                        }
                    ) {
                        Text("Adicionar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showDialog = false }
                    ) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}

@Composable
fun ItemCard(item: Item) {
    var checked by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (checked) Color(0xFFE8F5E9) else Color(0xFFE3F2FD)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { isChecked -> checked = isChecked },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF4CAF50),
                    uncheckedColor = Color(0xFF2196F3)
                )
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = item.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (checked) Color.Gray else Color.Black
                )
                Text(
                    text = item.category ?: "Sem categoria",
                    fontSize = 14.sp,
                    color = if (checked) Color.LightGray else Color.Gray
                )
            }

            Text(
                text = "Qtd: ${item.quantity}",
                fontSize = 16.sp,
                color = if (checked) Color(0xFF81C784) else Color(0xFF2196F3)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewItemListScreen() {
    ItemListScreen()
}