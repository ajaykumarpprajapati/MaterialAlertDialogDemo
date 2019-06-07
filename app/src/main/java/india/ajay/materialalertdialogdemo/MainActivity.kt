package india.ajay.materialalertdialogdemo

import android.os.Bundle
import android.support.v4.content.ContextCompat
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
        //Passing style in 2nd paramter will change by default color of dialog action
        builder = AlertDialog.Builder(this@MainActivity, R.style.AlertDialogTheme)
        builder?.setMessage(getString(R.string.discard_draft))
        builder?.setPositiveButton(getString(R.string.discard)) { _, _ ->
            Log.d(TAG, "Clicked Postive button")
        }
        builder?.setNegativeButton(getString(R.string.cancel)) { _, _ ->
            Log.d(TAG, "Clicked Negative button")
        }
        alerDialog = builder?.create()
        alerDialog?.show()

        /*First way to change color of button text*/
        //Changing color of positive button
        /*alerDialog?.getButton(AlertDialog.BUTTON_POSITIVE)
            ?.setTextColor(ContextCompat.getColor(this, R.color.colorAlertDialog))

        //Changing color of negative button
        alerDialog?.getButton(AlertDialog.BUTTON_NEGATIVE)
            ?.setTextColor(ContextCompat.getColor(this, R.color.colorAlertDialog))*/

        /*2nd way to change color of button text*/

    }
}
