package co.dapi.wusample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import co.dapi.connect.core.base.Dapi
import co.dapi.connect.core.callbacks.OnDapiConnectListener
import co.dapi.connect.data.models.DapiConnection
import co.dapi.connect.data.models.DapiError
import co.dapi.wusample.databinding.ActivityBanksBinding

class BanksActivity : AppCompatActivity(), BanksAdapter.OnBankClickListener {
    private lateinit var binding: ActivityBanksBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBanksBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setConnectListener()
        initBanksRv()
    }

    override fun onBankClicked(bankID: String) {
        presentConnect(bankID)
    }

    //Set connectListener to get callbacks for connection attempts
    private fun setConnectListener() {
        Dapi.connectListener = object : OnDapiConnectListener {
            override fun onConnectionSuccessful(connection: DapiConnection) {
                finish()
                Log.i("Dapi", "Successfully connected: ${connection.toString()}")
            }

            override fun onConnectionFailure(error: DapiError, bankID: String) {
                finish()
                Log.e("DapiError", "Connection failed $bankID with error: ${error.message}")
            }

            override fun onBankRequest(bankName: String, iban: String) {

            }

            override fun onDismissed() {
                Log.i("Dapi", "Connect UI is dismissed")
            }

        }
    }

    private fun initBanksRv() {
        val banks = hashMapOf(
            Pair("ADCB", "DAPIBANK_AE_ADCB"),
            Pair("ENBD", "DAPIBANK_AE_ENBD"),
            Pair("LIV", "DAPIBANK_AE_LIV"),
            Pair("ADIB", "DAPIBANK_AE_ADIB"),
            Pair("EIB", "DAPIBANK_AE_EIB"),
            Pair("HSBC", "DAPIBANK_AE_HSBC"),
            Pair("Standard Chartered Bank", "DAPIBANK_AE_SCHRTD"),
        )
        val adapter = BanksAdapter(banks)
        adapter.onBankClickListener = this
        binding.rvBanks.layoutManager = LinearLayoutManager(this)
        binding.rvBanks.adapter = adapter
    }

    //Display Dapi login screen for bankID
    private fun presentConnect(bankID: String) {
        if (Dapi.isStarted) {
            Dapi.presentConnect(bankID)
        } else {
            Log.e("DapiError", "Must start the SDK before doing any operation")
        }
    }
}