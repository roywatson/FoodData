package com.delasystems.fooddata.presentation.search

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun FoodSearchScreen(
    modifier: Modifier = Modifier,
    state: FoodSearchScreenState,
    onSearch: (anyWords: String, allWords: String, noneWords: String) -> Unit,
    onClear: () -> Unit
) {
    Surface(
        modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = modifier
            .padding(5.dp)
        ) {
            val anyWords = rememberSaveable { mutableStateOf(state.anyWords) }
            val allWords = rememberSaveable { mutableStateOf(state.allWords) }
            val noneWords = rememberSaveable { mutableStateOf(state.noneWords) }
            val context = LocalContext.current
            Column() {
                Text("Contains ANY of these words:")
                TextField(
                    value = anyWords.value,
                    onValueChange = { newValue: String -> anyWords.value = newValue },
                    label = { Text("comma or space separated words") },
                    singleLine = false,
                )
                Text("Contains ALL of these words:")
                TextField(
                    value = allWords.value,
                    onValueChange = { newValue: String -> allWords.value = newValue },
                    label = { Text("comma or space separated words") },
                    singleLine = false,
                )
                Text("Contains NONE of these words:")
                TextField(
                    value = noneWords.value,
                    onValueChange = { newValue: String -> noneWords.value = newValue },
                    label = { Text("comma or space separated words") },
                    singleLine = false,
                )
                Row() {
                    Button(
                        onClick = {
                            if(anyWords.value.isNotBlank() || allWords.value.isNotBlank() || noneWords.value.isNotBlank()) {
                                onSearch(anyWords.value, allWords.value, noneWords.value)
                            } else {
                                val toast = Toast.makeText( context,"You must enter some search parameters first.", Toast.LENGTH_LONG)
                                toast.setGravity(Gravity.CENTER, 0, 0)
                                toast.show()
                            }
                        }
                    ) {
                        Text("Search")
                    }
                    Button(
                        onClick = {
                            onClear()
                            anyWords.value = ""
                            allWords.value = ""
                            noneWords.value = ""
                        }
                    ) {
                        Text("Clear")
                    }
                }
            }
        }
    }
}

