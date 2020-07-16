package issues.google.doctree

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlin.concurrent.thread

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            Log.i("taggg", "Data: $data")
            Log.i(
                "taggg",
                "Before Persisted: ${requireContext().contentResolver.persistedUriPermissions}"
            )
            thread {
                Log.i("taggg", "Sleeping 3 seconds so the other activity is GCed.")
                Thread.sleep(3000)
                try {
                    requireContext().contentResolver.takePersistableUriPermission(
                        Uri.parse("content://com.android.externalstorage.documents/tree/primary%3ATestDir"),
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                } catch (e: Exception) {
                    Log.e("taggg", "Error", e)
                }
                Log.i(
                    "taggg",
                    "After Persisted: ${requireContext().contentResolver.persistedUriPermissions}"
                )
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val intent = Intent(context, SecondActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }
}