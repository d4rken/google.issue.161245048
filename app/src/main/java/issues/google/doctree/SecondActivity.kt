package issues.google.doctree

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestIntent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE)
        requestIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
        requestIntent.putExtra("android.content.extra.SHOW_ADVANCED", true)
        startActivityForResult(requestIntent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            Log.i("tag", "Data: $data")
            val result = Intent()
            contentResolver.takePersistableUriPermission(
                Uri.parse("content://com.android.externalstorage.documents/tree/primary%3ATestDir"),
                Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
            setResult(Activity.RESULT_OK, result)
            finish()
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}