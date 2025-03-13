package com.qe170193.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.qe170193.data.model.Land
import com.qe170193.ui.viewmodel.LandViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditLandScreen(
    land: Land?,
    viewModel: LandViewModel,
    navController: NavController
) {
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    savedStateHandle?.get<LatLng>("selected_location")

    var name by rememberSaveable { mutableStateOf(savedStateHandle?.get("name") ?: land?.name ?: "") }
    var price by rememberSaveable { mutableStateOf(savedStateHandle?.get("price") ?: land?.price?.toString() ?: "") }
    var address by rememberSaveable { mutableStateOf(savedStateHandle?.get("address") ?: land?.address ?: "") }
    var detail by rememberSaveable { mutableStateOf(savedStateHandle?.get("detail") ?: land?.details ?: "") }
    var latitude by rememberSaveable { mutableStateOf(savedStateHandle?.get("latitude") ?: land?.latitude?.toString() ?: "") }
    var longitude by rememberSaveable { mutableStateOf(savedStateHandle?.get("longitude") ?: land?.longitude?.toString() ?: "") }

    var nameError by remember { mutableStateOf(false) }
    var priceError by remember { mutableStateOf(false) }
    var addressError by remember { mutableStateOf(false) }
    var detailError by remember { mutableStateOf(false) }
    var latitudeError by remember { mutableStateOf(false) }
    var longitudeError by remember { mutableStateOf(false) }
    var locationError by remember { mutableStateOf(false) }


    LaunchedEffect(navController.currentBackStackEntry?.savedStateHandle?.get<LatLng>("selected_location")) {
        navController.currentBackStackEntry?.savedStateHandle?.get<LatLng>("selected_location")?.let { latLng ->
            latitude = latLng.latitude.toString()
            longitude = latLng.longitude.toString()

            savedStateHandle?.set("latitude", latitude)
            savedStateHandle?.set("longitude", longitude)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (land == null) "Add Land" else "Edit Land") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack("land_list", inclusive = false)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                    nameError = it.isBlank()
                },
                label = { Text("Name") },
                isError = nameError,
                modifier = Modifier.fillMaxWidth()
            )
            if (nameError) Text("Name cannot be empty", color = MaterialTheme.colorScheme.error)

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = price,
                onValueChange = {
                    if (it.isEmpty() || it.all { char -> char.isDigit() || char == '.' }) {
                        price = it
                        priceError = it.isBlank()
                    }
                },
                label = { Text("Price") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = priceError,
                modifier = Modifier.fillMaxWidth()
            )
            if (priceError) Text("Price must be a valid number", color = MaterialTheme.colorScheme.error)

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = address,
                onValueChange = {
                    address = it
                    addressError = it.isBlank()
                },
                label = { Text("Address") },
                isError = addressError,
                modifier = Modifier.fillMaxWidth()
            )
            if (addressError) Text("Address cannot be empty", color = MaterialTheme.colorScheme.error)

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = detail,
                onValueChange = {
                    detail = it
                    detailError = it.isBlank()
                },
                label = { Text("Details") },
                isError = detailError,
                modifier = Modifier.fillMaxWidth()
            )
            if (detailError) Text("Details cannot be empty", color = MaterialTheme.colorScheme.error)

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = latitude,
                onValueChange = {
                    if (it.isEmpty() || it.toDoubleOrNull() != null) {
                        latitude = it
                        latitudeError = it.isBlank() || it.toDoubleOrNull()?.let { lat -> lat !in -90.0..90.0 } ?: true
                    }
                },
                label = { Text("Latitude") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = latitudeError,
                modifier = Modifier.fillMaxWidth()
            )
            if (latitudeError) Text("Latitude should be between -90 and 90", color = MaterialTheme.colorScheme.error)

            Spacer(modifier = Modifier.padding(8.dp))

            OutlinedTextField(
                value = longitude,
                onValueChange = {
                    if (it.isEmpty() || it.toDoubleOrNull() != null) {
                        longitude = it
                        longitudeError = it.isBlank() || it.toDoubleOrNull()?.let { lon -> lon !in -180.0..180.0 } ?: true
                    }
                },
                label = { Text("Longitude") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                isError = longitudeError,
                modifier = Modifier.fillMaxWidth()
            )
            if (longitudeError) Text("Longitude should be between -180 and 180", color = MaterialTheme.colorScheme.error)

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    savedStateHandle?.apply {
                        set("name", name)
                        set("price", price)
                        set("address", address)
                        set("detail", detail)
                        set("latitude", latitude)
                        set("longitude", longitude)
                        if (latitude.isNotBlank() && longitude.isNotBlank()) {
                            set("selected_location", LatLng(latitude.toDouble(), longitude.toDouble()))
                        }
                    }
                    navController.navigate("map_picker?returnTo=add_edit/${land?.id ?: -1}")
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pick Location")
            }

            Spacer(modifier = Modifier.padding(4.dp))

            OutlinedButton(
                onClick = {
                    nameError = name.isBlank()
                    priceError = price.isBlank()
                    addressError = address.isBlank()
                    detailError = detail.isBlank()
                    latitudeError = latitude.isBlank() || latitude.toDoubleOrNull()?.let { it !in -90.0..90.0 } ?: true
                    longitudeError = longitude.isBlank() || longitude.toDoubleOrNull()?.let { it !in -180.0..180.0 } ?: true
                    locationError = latitudeError || longitudeError

                    if (!(nameError || priceError || addressError || detailError || locationError)) {
                        val newLand = Land(
                            id = land?.id ?: 0,
                            name = name,
                            price = price.toDouble(),
                            address = address,
                            latitude = latitude.toDouble(),
                            longitude = longitude.toDouble(),
                            details = detail
                        )
                        if (land == null) viewModel.addLand(newLand) else viewModel.updateLand(newLand)
                        navController.popBackStack("land_list", inclusive = false)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save")
            }

        }
    }
}
