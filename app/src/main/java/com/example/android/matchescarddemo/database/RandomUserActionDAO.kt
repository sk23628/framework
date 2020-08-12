package com.example.android.matchescarddemo.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.android.matchescarddemo.data.Result

@Dao
interface RandomUserActionDAO {

//    @Insert
//    fun insert(result: Result)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(results : MutableList<Result>)

//    @Update
//    fun update(action: RandomUserAction)

    @Query("SELECT * from random_user_table order by userId ASC LIMIT 10")
    fun getRandomUsers(): LiveData<MutableList<Result>>


    @Query("DELETE FROM random_user_table")
    suspend fun deleteAll()

    @Query("UPDATE random_user_table SET random_user_status = :userStatusVal WHERE userId = :userIdVal AND random_user_email = :userEmailVal")
    fun updateStatus(userIdVal: Int, userEmailVal: String, userStatusVal: String)

}

