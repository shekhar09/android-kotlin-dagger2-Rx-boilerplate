package com.shekhar.kotlin.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shekhar.kotlin.data.local.db.dao.AddressDao
import com.shekhar.kotlin.data.local.db.dao.UserDao
import com.shekhar.kotlin.data.local.db.entity.Address
import com.shekhar.kotlin.data.local.db.entity.User



@Database(entities = [
    User::class,
    Address::class],
    version = 1,
    exportSchema = false)

@TypeConverters(Converter::class)
abstract class DatabaseService : RoomDatabase(){

    abstract fun userDao():UserDao

    abstract fun addressDao():AddressDao

}

