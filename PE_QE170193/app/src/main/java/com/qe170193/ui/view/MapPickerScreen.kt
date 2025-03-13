package com.qe170193.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun MapPickerScreen(navController: NavController) {
    val savedStateHandle = navController.previousBackStackEntry?.savedStateHandle
    val defaultLocation = LatLng(13.782, 109.219)

    var selectedLocation by remember {
        mutableStateOf(savedStateHandle?.get<LatLng>("selected_location") ?: defaultLocation)
    }
    var tempLocation by remember { mutableStateOf(selectedLocation) }
    var isOkEnabled by remember { mutableStateOf(false) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(selectedLocation, 12f)
    }

    LaunchedEffect(Unit) {
        savedStateHandle?.get<LatLng>("selected_location")?.let { latLng ->
            tempLocation = latLng
            isOkEnabled = true
            cameraPositionState.move(CameraUpdateFactory.newLatLng(latLng))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pick Location") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            selectedLocation = tempLocation
                            savedStateHandle?.set("selected_location", selectedLocation)
                            navController.popBackStack()
                        },
                        enabled = isOkEnabled
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Save Location")
                    }
                }
            )
        }
    ) { paddingValues ->
        GoogleMap(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                tempLocation = latLng
                isOkEnabled = true
                cameraPositionState.move(CameraUpdateFactory.newLatLng(latLng))
            }
        ) {
            Marker(state = MarkerState(position = tempLocation), title = "Selected Location")
        }
    }
}





