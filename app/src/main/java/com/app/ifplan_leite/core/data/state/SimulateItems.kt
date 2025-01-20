package com.app.ifplan_leite.core.data.state

import androidx.room.TypeConverters
import com.app.ifplan_leite.core.data.model.utils.IFPlanConverters
import kotlinx.serialization.Serializable
import java.sql.Date
@Serializable
data class SimulateItems(
    var id: Long,
    var title: String,
    var creationDate: String,
    var description: String
)
