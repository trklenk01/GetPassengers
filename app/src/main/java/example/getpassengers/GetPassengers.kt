package example.getpassengers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GetPassengers : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_get_passengers)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    val passList: MutableList<Passenger> = ArrayList<Passenger>()

    fun enterPassenger(v: View){

        val textPut = findViewById<TextView>(R.id.accum_list)
        val textFirst = findViewById<EditText>(R.id.first_name)
        val textLast =  findViewById<EditText>(R.id.last_name)
        val phone = findViewById<EditText>(R.id.phone_number)
        val firstName = textFirst.getText().toString()
        val lastName = textLast.getText().toString()
        val phoneNumber = phone.getText().toString()
        val passenger = Passenger(firstName, lastName, phoneNumber)
        passList.add(passenger)
        textPut.append("\n${passenger.toString()}")
        textFirst.text.clear()
        textLast.text.clear()
        phone.text.clear()
    }

    fun backToMain(v: View){
        val goBack = Intent(this,MainActivity::class.java)
        goBack.putExtra("COUNT",passList.size.toString())
        for(i in 0 until passList.size){
            val string = "PASS" + i.toString()
            goBack.putExtra(string, passList[i].toString())
        }
        setResult(Activity.RESULT_OK,goBack)

        finish()


    }



}