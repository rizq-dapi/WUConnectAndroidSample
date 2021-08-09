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
            appKey = "c4005ee6f03e90d0c1a82d602f425cbcd8a058a1f2879fa2216f1ef33ce99f93", //On the dashboard
            clientUserID = "currentLoggedInWUUser.id", //Used to distinguish between different users on the same device
            configurations = DapiConfigurations(
                environment = DapiEnvironment.SANDBOX,
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