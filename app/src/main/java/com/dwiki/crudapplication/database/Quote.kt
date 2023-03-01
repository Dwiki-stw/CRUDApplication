package com.dwiki.crudapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int,

    @ColumnInfo(name = "name")
    var name:String? = null,

    @ColumnInfo(name = "text")
    var text:String? = null
)
