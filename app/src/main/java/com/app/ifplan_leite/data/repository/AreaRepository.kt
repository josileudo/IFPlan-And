package com.app.ifplan_leite.data.repository

import com.app.ifplan_leite.data.dao.AreaDao
import com.app.ifplan_leite.data.entities.Area
import com.app.ifplan_leite.data.state.AreaState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AreaRepository @Inject constructor(
    private val areaDao: AreaDao
) {
    val _areaState = MutableStateFlow(AreaState())
    val areaState: StateFlow<AreaState> = _areaState.asStateFlow()
    val loadingJob: Job? = null
    val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun getArea() = areaDao.getArea()

    init {
        loadAreaData()
    }

    fun loadAreaData() {
        loadingJob?.cancel()
        coroutineScope.launch {
            try {
                _areaState.update { it.copy( isSaving = true) }

                getArea()
                    .catch { error ->
                        _areaState.update { it.copy(
                            error = "Error ao carregar dados $error.message",
                            isSuccess = false,
                            isSaving = false
                        ) }
                    }
                    .collect { area ->
                        if(area != null) {
                            _areaState.update { it.copy(
                                area = area.area,
                                picketsNumber = area.picketsNumber,
                                isSuccess = true,
                                error = null,
                                isSaving = false
                            )
                            }
                        } else {
                            _areaState.update { it.copy(
                                isSuccess = true,
                                isSaving = false
                            ) }
                        }
                    }
            } catch (error: Exception) {
                _areaState.update { area ->
                    area.copy(
                        error = "Error ao carregar dados $error.message",
                        isSuccess = false,
                        isSaving = false
                    )
                }
            }
        }
    }

    fun saveArea() {
        coroutineScope.launch {
            _areaState.update { it.copy( isSaving = true ) }
            try {
                val currState = _areaState.value
                with(currState) {
                    val areaValue = Area(
                        area = currState.area,
                        picketsNumber = currState.picketsNumber,
                    )
                    areaDao.insertOrUpdate(areaValue)
                }

                _areaState.update {
                    it.copy(
                        isSuccess = true,
                        error = null
                    )
                }
            } catch(error: Exception) {
                _areaState.update {it.copy(
                    error = "Error ao salvar dados $error.message",
                    isSuccess = false,
                    isSaving = false
                )}
            }
        }
    }

    suspend fun clearArea() = areaDao.deleteArea()
}
