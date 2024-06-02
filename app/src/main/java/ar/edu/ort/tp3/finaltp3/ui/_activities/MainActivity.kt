package ar.edu.ort.tp3.finaltp3.ui._activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import ar.edu.ort.tp3.finaltp3.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavView : BottomNavigationView
    private lateinit var navHostFragment : NavHostFragment
    private lateinit var  drawerLayout: DrawerLayout
    private lateinit var  navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        // Retrieve the shared preferences
        val sharedPreferences: SharedPreferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isDarkMode = sharedPreferences.getBoolean("dark_mode", false)


        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.navigation_view)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment


        bottomNavView = findViewById(R.id.bottom_bar)
        val navController = navHostFragment.navController
        bottomNavView.setupWithNavController(navController)
        setupDrawerLayout()

        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.explore -> {
                    navController.navigate(R.id.explore)
                    true
                }

                R.id.search -> {
                    navController.navigate(R.id.search)
                    true
                }

                R.id.offers -> {
                    navController.navigate(R.id.offers)
                    true
                }

                R.id.profile -> {
                    navController.navigate(R.id.profile)
                    true
                }

                else -> false
            }
        }
    }

    private fun setupDrawerLayout(){
        val navController = navHostFragment.navController
        navigationView.setupWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        return false
    }


}
