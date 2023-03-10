package NoamEfergan.platecheck.Views.HomeView

import NoamEfergan.platecheck.Utils.Network.NetworkService
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class HomeViewVM: ViewModel() {
    private val networkService = NetworkService()

    fun performApiCall() {
        viewModelScope.launch {
            networkService.getDVLAItem("AA19AAA")
        }
    }
}