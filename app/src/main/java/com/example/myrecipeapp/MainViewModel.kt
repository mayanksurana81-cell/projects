package com.example.myrecipeapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImagePainter
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val  _categoryState = mutableStateOf(RecipeState())
    val categoriesState: State<RecipeState> = _categoryState

    init {
        fetchCategories() // ✅ Call it here
    }
    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeService.getCategories()
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    list = response.categories,
                    error = null
                )

            }catch (e: Exception){
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Error fetching catogries ${e.message}"
                )
            }
        }
    }
    data class RecipeState(
        val loading: Boolean = true,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}

