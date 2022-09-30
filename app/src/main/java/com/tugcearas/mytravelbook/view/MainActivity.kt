package com.tugcearas.mytravelbook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.room.Room
import com.tugcearas.mytravelbook.R
import com.tugcearas.mytravelbook.adapter.PlaceAdapter
import com.tugcearas.mytravelbook.databinding.ActivityMainBinding
import com.tugcearas.mytravelbook.db.PlaceDatabase
import com.tugcearas.mytravelbook.model.PlaceModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val db = Room.databaseBuilder(applicationContext,PlaceDatabase::class.java,"Places").build()
        val placeDao = db.placeDao()

        //get data
        compositeDisposable.add(
            placeDao.getAllPlace()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(placelist:List<PlaceModel>){
        val adapter = PlaceAdapter(placelist)
        binding.recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.place_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.addPlace){
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("info","new")
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}