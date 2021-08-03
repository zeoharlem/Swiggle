package ng.com.zeoharlem.swopit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ng.com.zeoharlem.swopit.databinding.ActivityMainBinding
import ng.com.zeoharlem.swopit.dialog.LoginDialogBottomSheet

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView: BottomNavigationView  = findViewById(R.id.bottomNavigationView)
        navController                                   = findNavController(R.id.fragmentContainerView)
        val appBarConfiguration: AppBarConfiguration    = AppBarConfiguration(
            setOf(
                R.id.moviesFragment,
                R.id.tvSeasonsFragment,
                R.id.tvShowsFragment,
                R.id.peopleFragment,
                R.id.trendingFragment,
            )
        )

        bottomNavigationView.setupWithNavController(navController)
        //bottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}