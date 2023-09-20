package com.example.udemy_stateflow_sharedflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.*
import com.example.udemy_stateflow_sharedflow.ui.theme.Udemy_StateFlow_SharedFlowTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var mainActivityViewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewModelFactory = MainActivityViewModelFactory(125)
        mainActivityViewModel =
            ViewModelProvider(this, mainActivityViewModelFactory).get(MainActivityViewModel::class.java)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {

            }
        }

        setContent {
            Udemy_StateFlow_SharedFlowTheme {
                DisplayTotal(mainActivityViewModel)
            }
        }
    }
}

@Composable
fun DisplayTotal(mainActivityViewModel: MainActivityViewModel) {

    val startingTotal by mainActivityViewModel.flowTotal.collectAsStateWithLifecycleRemember(initial = 0)
    var text by remember { mutableStateOf(TextFieldValue("")) }


    Column() {
        val context = LocalContext.current
        Text("${startingTotal}")
        TextField(
            value = text,
            onValueChange = { newValue ->
                text = newValue
            }
        )
        Button(
            onClick = {
                text.text.toIntOrNull()?.let { mainActivityViewModel.setTotal(it) }
            }
        ) {
            Text("Add")

        }
    }


}

@Composable
fun <T> Flow<T>.collectAsStateWithLifecycleRemember(
    initial: T,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
): State<T> {
    val lifecycleOwner = LocalLifecycleOwner.current
    val flowLifecycleAware = remember(this, lifecycleOwner) {
        flowWithLifecycle(lifecycleOwner.lifecycle, minActiveState)
    }
    return flowLifecycleAware.collectAsState(initial)
}

//
//@Composable
//fun DisplayTotal(mainActivityViewModel: MainActivityViewModel) {
//    val total by mainActivityViewModel.totalLiveData.observeAsState(0)
//    var text by remember { mutableStateOf(TextFieldValue("")) }
//
//
//    Column() {
//        Text("${total}")
//        TextField(
//            value = text,
//            onValueChange = { newValue ->
//                text = newValue
//            }
//        )
//        Button(
//            onClick = {
//                text.text.toIntOrNull()?.let { mainActivityViewModel.setTotal(it) }
//            }
//        ) {
//            Text("Add")
//        }
//    }
//
//}