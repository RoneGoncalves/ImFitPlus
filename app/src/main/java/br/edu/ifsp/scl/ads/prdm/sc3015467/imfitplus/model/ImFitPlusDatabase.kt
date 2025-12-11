package br.edu.ifsp.scl.ads.prdm.sc3015467.imfitplus.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PersonalData::class, Calculation::class],
    version = 1,
    exportSchema = false
)
abstract class ImFitPlusDatabase : RoomDatabase() {

    abstract fun personalDataDao(): PersonalDataDao
    abstract fun calculationDao(): CalculationDao

    companion object {
        @Volatile
        private var INSTANCE: ImFitPlusDatabase? = null

        fun getDatabase(context: Context): ImFitPlusDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ImFitPlusDatabase::class.java,
                    "imfitplus.db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}
