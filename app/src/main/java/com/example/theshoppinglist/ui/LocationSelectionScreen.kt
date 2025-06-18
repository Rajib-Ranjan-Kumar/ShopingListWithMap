package com.example.theshoppinglist.ui

import android.location.Location
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.theshoppinglist.data.LocationData
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocatiionSelectionScreen(
    Location: LocationData,
    onLocationSelect:(LocationData)->Unit
) {
    val userlocation =remember{
        mutableStateOf(LatLng(Location.lat,Location.lng))
    }
    var CameraPositionState = rememberCameraPositionState {
      position= CameraPosition.fromLatLngZoom(userlocation.value,10f)
    }


    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()){

        GoogleMap(
            modifier = Modifier.weight(1f).padding(top=16.dp),
            cameraPositionState = CameraPositionState,
            onMapClick = {
                userlocation.value=it
            }
        ){
            Marker(state = MarkerState(userlocation.value))
        }


                   var newLocation:LocationData
        Button(onClick = {
            newLocation=LocationData(userlocation.value.latitude,userlocation.value.longitude)
            onLocationSelect(newLocation)
        }) {
            Text("Select Location")

        }
    }

}