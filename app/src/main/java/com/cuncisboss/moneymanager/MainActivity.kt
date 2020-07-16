package com.cuncisboss.moneymanager

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.cuncisboss.moneymanager.adapter.MyPagerAdapter
import com.cuncisboss.moneymanager.data.local.SpendingPref
import com.cuncisboss.moneymanager.viewmodel.SpendingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<SpendingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewpager_main.adapter = MyPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_reset -> {
                val builder = AlertDialog.Builder(this)
                builder.setCancelable(true)
                builder.setTitle("Delete All Data")
                builder.setMessage("Are You Sure Delete All Data ?")
                builder.setPositiveButton("Yes") { dialog, _ ->
                    SpendingPref.clear(this)
                    viewModel.deleteAllData()
                    dialog.dismiss()
                }
                builder.setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show()
            }
            else -> {
                Log.d("_logMain", "onOptionsItemSelected: Something went wrong")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}