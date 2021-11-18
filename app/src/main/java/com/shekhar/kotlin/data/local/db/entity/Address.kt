package com.shekhar.kotlin.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "address")
data class Address (


        @PrimaryKey(autoGenerate = true)
        var id : Long = 0,

        @ColumnInfo(name = "city")
        var city:String,

        @ColumnInfo(name = "country")
        var country:String
)

{
        constructor(): this(0,"","")
}