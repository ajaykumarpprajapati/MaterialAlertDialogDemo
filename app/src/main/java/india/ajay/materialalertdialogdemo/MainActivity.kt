package india.ajay.materialalertdialogdemo

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log

class MainActivity : AppCompatActivity() {

    private var alerDialog: AlertDialog? = null
    private var builder: AlertDialog.Builder? = null
    private var TAG: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TAG = getString(R.string.app_name)
        setupView()
    }

    private fun setupView() {
        builder = AlertDialog.Builder(this@MainActivity)
        builder?.setMessage(getString(R.string.discard_draft))
        builder?.setPositiveButton(getString(R.string.discard)) { _, _ ->
            Log.d(TAG, "Clicked Postive button")
        }
        builder?.setNegativeButton(getString(R.string.cancel)) { _, _ ->
            Log.d(TAG, "Clicked Negative button")
        }
        alerDialog = builder?.create()
        alerDialog?.show()
    }
}
