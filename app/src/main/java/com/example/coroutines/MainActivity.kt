package com.example.coroutines

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// COROUTINES SCOPE INTERFACE DE LA LIBRAIRIE IL YA AUSSI GLOBALSCOPE
/*
Dispatchers.Main : cette coroutine s'execute dans le Main Thread UI
Dispatchers.Default : tache intensive comme trier une large liste
Dispatchers.IO : cette coroutine s'execute dans le Background threade network service API
 */

/*
4 principaux Coroutine Builder (Pattern) de Coroutine scope

Launch : lance une coroutine sans blocker le thread courant return un job
utilisé quand il y a pas de valeur de retour

Async : Lance une coroutine sans blocker le thread courant , retourne
une instance de Deferred , on doit invoquer await() pour avoir une valeur.
il est utilisé pour avoir une valeur de retour .

Produce : Utilisé pour produire des streams , retourne une instance de ReceiveChannel
Runblocking : elle bloc le thread jusqu'à la fin de l'éxécution , utilisé pour les test unitaires ,
elle retourne un result de type T
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCount.setOnClickListener {
            binding.tvCount.text = count++.toString()
        }
        binding.btnDownloadUserData.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                downloadUserData()
            }
        }
    }

    private fun downloadUserData() {
        for (i in 1..200000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
        }
    }
}