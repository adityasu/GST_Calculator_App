package com.aditya.gst_calculator
import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Product::class), version = 1,exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDAO(): ProductDAO

    companion object {
         var INSTANCE: ProductDatabase? = null
        fun getInMemoryDatabase(context: Context): ProductDatabase  {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context,
                    ProductDatabase::class.java, "Product")
                    .addCallback(CALLBACK)
                    .build()
            }

            return INSTANCE!!
        }

        private val CALLBACK = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDBAsyncTask(INSTANCE).execute()
            }
        }
        @Suppress("DEPRECATION")
        class PopulateDBAsyncTask(productDatabase: ProductDatabase?) : AsyncTask<Void, Void, Void>() {
            private var productDAO:ProductDAO? = productDatabase?.productDAO()

            override fun doInBackground(vararg p0: Void?): Void? {
                productDAO?.insert(Product(1,"Air Conditioner",9.00,9.00,18.00,30000.00))
                productDAO?.insert(Product(2,"Fridge",9.00,9.00,18.00,25000.00))
                productDAO?.insert(Product(3,"Motor Cycle",14.00,14.00,28.00,100000.00))
                productDAO?.insert(Product(4,"Wooden Painting Frame",6.00,6.00,12.00,700.00))
                productDAO?.insert(Product(5,"Aircraft Seats",2.50,2.50,5.00,12000.00))
                return null
            }
        }
    }
}
