package com.app.ifplan_leite.core.data.repository

import com.app.ifplan_leite.core.data.dao.AreaDao
import com.app.ifplan_leite.core.data.entities.Area
import com.app.ifplan_leite.core.data.state.AreaState
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

    fun loadAreaData() {
        loadingJob?.cancel()
        coroutineScope.launch {
            try {
                _areaState.update { it.copy(isSaving = true) }

                getArea()
                    .catch { error ->
                        _areaState.update {
                            it.copy(
                                error = "Error ao carregar dados $error.message",
                                isSuccess = false,
                                isSaving = false
                            )
                        }
                    }
                    .collect { area ->
                        if (area != null) {
                            _areaState.update {
                                it.copy(
                                    area = area.area,
                                    picketsNumber = area.picketsNumber,
                                    isSuccess = true,
                                    error = null,
                                    isSaving = false
                                )
                            }
                        } else {
                            _areaState.update {
                                it.copy(
                                    isSuccess = true,
                                    isSaving = false
                                )
                            }
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

    suspend fun saveArea(areaValue: Area) {
        areaDao.insertOrUpdate(areaValue)
    }

    suspend fun clearArea() = areaDao.deleteArea()
}
