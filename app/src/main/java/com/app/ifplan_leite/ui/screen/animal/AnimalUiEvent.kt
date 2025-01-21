package com.app.ifplan_leite.ui.screen.animal

sealed class AnimalUiEvent {
    data object OnFetchAnimalResult: AnimalUiEvent()
    data object OnSaveAnimal: AnimalUiEvent()
    data class OnFetchAnimalById(var id: Long): AnimalUiEvent()
    data class OnUpdateAnimalFields(var field: String, var value: Double): AnimalUiEvent()
}
