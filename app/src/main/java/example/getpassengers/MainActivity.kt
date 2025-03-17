package example.getpassengers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat




class MainActivity : AppCompatActivity() {

    private val listText: TextView by lazy { findViewById(R.id.show_list) }
    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val count = ((data?.getStringExtra("COUNT") ?: "")).toInt()
            for(i in 0 until count){
                val passengerInfo = data?.getStringExtra("PASS$i") ?: ""
                listText.append("\n$passengerInfo")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val listText = findViewById<TextView>(R.id.show_list)


    }
    fun getList(v: View){
        findViewById<TextView>(R.id.show_list).setText("Returned Passenger List:")
        startForResult.launch(Intent(this, GetPassengers::class.java))

    }

}

class Passenger(var fName: String, var lName: String, var phone: String){
    override fun toString(): String {
        return "$fName $lName, $phone"
    }
}