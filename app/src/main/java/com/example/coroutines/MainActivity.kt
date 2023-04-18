package com.example.coroutines

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

/*
Fonctions Suspendu

    WithContext: permet de changer le contexte d'exécution d'une coroutine en spécifiant un contexte différent.
    WithTimeout: permet de définir une durée limite pour l'exécution d'une coroutine. Si la durée limite est dépassée, une exception est lancée.
    WithTimeoutOrNull: similaire à WithTimeout, mais ne lance pas d'exception en cas de dépassement de la durée limite. Au lieu de cela, il retourne null.
    join: permet de bloquer l'exécution du programme jusqu'à ce que la coroutine soit terminée.
    delay: suspend l'exécution de la coroutine pendant une durée spécifiée sans bloquer le fil d'exécution.
    await: permet d'attendre la fin d'une tâche asynchrone et récupérer son résultat.
    supervisorScope: permet de lancer une nouvelle hiérarchie de coroutines indépendante de la hiérarchie parente.
    coroutineScope: permet de lancer une nouvelle portée de coroutines imbriquée à la portée parente,
    tout en attendant que toutes les coroutines lancées à l'intérieur de cette portée soient terminées avant de continuer l'exécution de la coroutine parente.
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
            CoroutineScope(Dispatchers.Main).launch {
                //binding.tvUserMessage.text = UserDataManager().getTotalUserCount().toString()
                binding.tvUserMessage.text = UserDataManager2().getTotalUserCount().toString()
            }
        }
    }

    private suspend fun downloadUserData() {
        for (i in 1..200000) {
            withContext(Dispatchers.Main){
                binding.tvUserMessage.text = "Downloading user $i in ${Thread.currentThread().name}"

            }
        }
    }
}