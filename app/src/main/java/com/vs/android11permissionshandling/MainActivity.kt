package com.vs.android11permissionshandling

/**
 * Created by Sachin
 * https://medium.com/@iAmSachinRajput
 */
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnCamera).setOnClickListener { _ ->
            cameraAction()
        }
    }

    private fun cameraAction() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            findViewById<TextView>(R.id.tvStatus).text = getString(R.string.permission_granted)

        } else {
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                //Permission is denied can show some alert here
                showPermissionDeniedDialog(Manifest.permission.CAMERA, REQUEST_CODE_CAMERA)
            } else {
                //ask permission
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_CAMERA)
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    findViewById<TextView>(R.id.tvStatus).text = getString(R.string.permission_granted)
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                        setCameraDenied()
                        showPermissionDeniedDialog(Manifest.permission.CAMERA, REQUEST_CODE_CAMERA)
                    } else {
                        showMandatoryPermissionsNeedDialog()
                    }
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_CAMERA = 1001
    }

    private fun setCameraDenied() {
        AppPreferences.cameraPermissionDeniedOnce = true
    }

    /**
     * We show this custom dialog to alert user denied camera permission
     */
    private fun showPermissionDeniedDialog(permissions: String, permissionRequestCode: Int) {
        AlertDialog.Builder(this).apply {
            setCancelable(true)
            setMessage(getString(R.string.permission_camera_access_required))
            setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permissions), permissionRequestCode)
            }
        }.show()
    }

    /**
     * We show this custom dialog to alert user that please go to settings to enable camera permission
     */
    private fun showMandatoryPermissionsNeedDialog() {
        AlertDialog.Builder(this).apply {
            setCancelable(true)
            setMessage(getString(R.string.mandatory_permission_access_required))
            setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivity(intent)
            }
        }.show()
    }
}