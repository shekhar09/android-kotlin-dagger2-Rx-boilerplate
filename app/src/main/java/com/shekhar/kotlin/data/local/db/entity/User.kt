package com.shekhar.kotlin.data.local.db.entity

import androidx.room.*
import java.util.*

@Entity (tableName = "users")
data class User(

        @PrimaryKey(autoGenerate = true)
    var id:Long = 0,

        @ColumnInfo(name = "name")
    var name:String,

        @ColumnInfo(name = "addressId")
    var addressId: Long = 0,

        @ColumnInfo(name = "date_of_birth")
    var date_of_birth: Date,

        @Ignore
    var selected: Boolean = false

)

{
    constructor(): this(0,"",0,Date(),false)
}