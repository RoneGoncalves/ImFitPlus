package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PersonalDataEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personalDataDao(): PersonalDataDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "imfitplus_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

