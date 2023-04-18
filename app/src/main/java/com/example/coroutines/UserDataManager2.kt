package com.example.coroutines

import kotlinx.coroutines.*

class UserDataManager2 {

    suspend fun getTotalUserCount():Int{
        var count = 0
        lateinit var deferred: Deferred<Int>
        //fonction enfant de l'interface avec un C maj , si on ne définit pas de dispatcher il prend le dispatcher du parent par défaut
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000)
                count = 50
            }
             deferred =  async(Dispatchers.IO) {
                delay(3000)
                return@async 70
            }
       }
        return count + deferred.await()

    }
}