package com.example.navdrawerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.NameValueTable
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle:ActionBarDrawerToggle//it will create 3 lines which will give the list
    lateinit var drawerlayout:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         drawerlayout=findViewById(R.id.drawerlayout)
        toggle=ActionBarDrawerToggle(
           this@MainActivity,drawerlayout,
            R.string.open,R.string.close
        )
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView:NavigationView=findViewById(R.id.navigation_view)
        navView.setNavigationItemSelectedListener {
           it.isChecked= true
            when(it.itemId)
            {
                R.id.home->{
                    replacefragment(homeFragment(),it.title.toString())
                }
                R.id.message->
                {
                    replacefragment(Messagefragment(),it.title.toString())
                }
                R.id.settings->
                {
                    replacefragment(Settingfragment(),it.title.toString())
                }
                R.id.login->
                {
                    replacefragment(Loginfragment(),it.title.toString())
                }

            }
            true
        }
    }

   private fun replacefragment(fragment:Fragment,title:String)
    {
        val fragtran=supportFragmentManager.beginTransaction()
        fragtran.replace(R.id.frame1,fragment)
        fragtran.commit()
        drawerlayout.closeDrawers()
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)

    }
}