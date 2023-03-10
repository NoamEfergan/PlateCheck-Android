package NoamEfergan.platecheck.Views.HomeView

import NoamEfergan.platecheck.Utils.Network.DVLAResponse
import NoamEfergan.platecheck.Utils.Network.NetworkService
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class HomeViewVM: ViewModel() {
    private val networkService = NetworkService()
    var showDialog by mutableStateOf(false)
    var errorMessege =  MutableSharedFlow<String>()

    fun performApiCall() {
        showDialog = true
        viewModelScope.launch {
            val response = networkService.getDVLAItem("AA19AA")
            response.second?.let {
                errorMessege.emit(it)
                showDialog = false
                return@launch
            }
            response.first?.let {
                Log.i("DVLA", "received")
            }
            showDialog = false
        }
    }
}