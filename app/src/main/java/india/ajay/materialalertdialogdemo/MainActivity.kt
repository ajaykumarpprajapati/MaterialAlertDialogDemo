package india.ajay.materialalertdialogdemo

import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import java.util.*

class MainActivity : AppCompatActivity() {

    private var alerDialog: AlertDialog? = null
    private var builder: AlertDialog.Builder? = null
    private var TAG: String? = null
    private val difficultyLevelArray = arrayOf("Easy", "Medium", "Hard", "Very Hard")
    private var selectedString: String? = null
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var timer: Timer? = null
    private var counter: Int? = 0

    /*Circular progress dialog*/
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TAG = getString(R.string.app_name)
        setupView()
    }

    private fun setupView() {
        //showAlertDialog()
        //showConfirmationAlertDialog()
        //showCustomizedConfirmationAlertDialog()
        //showProgressDialog()
        showLinearProgressDialog()

    }

    private fun showConfirmationAlertDialog() {

        //Passing style in 2nd paramter will change by default color of dialog action
        builder = AlertDialog.Builder(this@MainActivity)
        builder?.setTitle(getString(R.string.select_difficulty_level))
        builder?.setSingleChoiceItems(difficultyLevelArray, -1) { _, which ->
            selectedString = difficultyLevelArray[which]
            Log.d(TAG, selectedString)
        }
        builder?.setPositiveButton(getString(R.string.ok)) { _, _ ->
            Log.d(TAG, "Clicked Postive button")
        }
        builder?.setNegativeButton(getString(R.string.cancel)) { _, _ ->
            Log.d(TAG, "Clicked Negative button")
        }
        alerDialog = builder?.create()
        alerDialog?.show()

    }

    private fun showAlertDialog(){

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

        /*2nd way to change color of button text by using style*/


    }

    private fun showCustomizedConfirmationAlertDialog() {
        //Passing style in 2nd paramter will change by default color of dialog action
        builder = AlertDialog.Builder(this@MainActivity, R.style.ConfirmationDialogTheme)
        builder?.setTitle(getString(R.string.select_difficulty_level))
        builder?.setSingleChoiceItems(difficultyLevelArray, -1) { _, which ->
            selectedString = difficultyLevelArray[which]
            Log.d(TAG, selectedString)
        }
        builder?.setPositiveButton(getString(R.string.ok)) { _, _ ->
            Log.d(TAG, "Clicked Postive button")
        }
        builder?.setNegativeButton(getString(R.string.cancel)) { _, _ ->
            Log.d(TAG, "Clicked Negative button")
        }
        alerDialog = builder?.create()
        alerDialog?.show()
    }

    private fun showProgressDialog() {
        //This class was deprecated in API level 26. ProgressDialog is a modal dialog,
        // which prevents the user from interacting with the app.
        // Instead of using this class, you should use a progress indicator like ProgressBar,
        // which can be embedded in your app's UI. Alternatively,
        // you can use a notification to inform the user of the task's progress

        progressDialog = ProgressDialog(this)
        progressDialog?.setTitle(R.string.progress_dialog)
        progressDialog?.setMessage(getString(R.string.please_wait))
        progressDialog?.show()
        // To Cancel dialog/
        //progressDialog?.cancel()
    }


    private fun showLinearProgressDialog() {
        //This class was deprecated in API level 26. ProgressDialog is a modal dialog,
        // which prevents the user from interacting with the app.
        // Instead of using this class, you should use a progress indicator like ProgressBar,
        // which can be embedded in your app's UI. Alternatively,
        // you can use a notification to inform the user of the task's progress

        progressDialog = ProgressDialog(this)
        progressDialog?.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        progressDialog?.isIndeterminate = false
        progressDialog?.setTitle(R.string.progress_dialog)
        progressDialog?.setMessage(getString(R.string.please_wait))
        progressDialog?.show()
        progressDialog?.progress = 0
        progressDialog?.max = 100

        handler = Handler()
        runnable = Runnable {
            counter = counter?.plus(5)
            counter?.let {
                if(it <= 100){
                    progressDialog?.progress = it
                } else {
                    timer?.cancel()
                    progressDialog?.cancel()
                    counter = 0
                }
            }
        }

        timer = Timer()
        val timerTask = object: TimerTask(){
            override fun run(){
                handler?.post(runnable)
            }
        }
        timer?.schedule(timerTask, 8000, 500)

        // To Cancel dialog/
        //progressDialog?.cancel()
    }
}
