package co.dapi.wusample

import android.app.Application
import android.util.Log
import co.dapi.connect.core.base.Dapi
import co.dapi.connect.data.models.DapiConfigurations
import co.dapi.connect.data.models.DapiEnvironment

class WUApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Dapi.start(
            application = this,
            appKey = "1d4592c4a8dd6ff75261e57eb3f80c518d7857d6617769af3f8f04b0590baceb", //On the dashboard
            clientUserID = "currentLoggedInWUUser.id", //Used to distinguish between different users on the same device
            configurations = DapiConfigurations(
                environment = DapiEnvironment.SANDBOX,
                showAddButton = false,
                countries = arrayOf("AE", "EG"),
                extraHeaderFields = hashMapOf(Pair("key", "value")), //Added to all requests
                extraQueryParameters = hashMapOf(Pair("key", "value")), //Added to all requests
                extraBody = hashMapOf(Pair("key", "value")) //Added to all requests
            ),
            onSuccess = {
                Log.i("Dapi", "Started successfully")
            },
            onFailure = {
                Log.e("DapiError", it.message!!)
            }
        )

    }
}