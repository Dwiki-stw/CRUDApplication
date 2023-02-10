package com.dwiki.crudapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:String,

    @ColumnInfo(name = "name")
    val name:String,

    @ColumnInfo(name = "quote")
    val quote:String
)
