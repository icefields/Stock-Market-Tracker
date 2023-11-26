package org.hungrytessy.stockmarkettracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import org.hungrytessy.stockmarkettracker.presentation.NavGraphs
import org.hungrytessy.stockmarkettracker.ui.theme.StockMarketAppTheme

/**
 * In Clean Architecture we organize the packages in 3 layers:
 * data : data related logic. Everything related to data, retrofit, database (room), csv parsing,
 *      everything related to data. Also implementation of how we get data
 * domain : business rules, how would we like to filter search, entities, app models. This is the
 *      inner most layer, other layers can access this, but domain shouldn't be able to access
 *      other layer.
 * presentation : show something to the user. contains ui, screens, states, viewModels
 *
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // one-line setup navigation library
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}
