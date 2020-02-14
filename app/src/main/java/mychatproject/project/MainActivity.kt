package mychatproject.project

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

/* This is the  Facepalm Messenger
Created by: Roni Jumpponen */

//MainActivity for the UI elements and user interaction.
class MainActivity : AppCompatActivity() {
    //The onCreate method that is ran every time orientation changes.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sendMessageTxt = findViewById<EditText>(R.id.editText)
        val sendButton = findViewById<Button>(R.id.send)
        recyclerView.layoutManager = LinearLayoutManager(this)
        //Duplicate adapter used within the ChatClient object that was duplicated in the Startup
        recyclerView.adapter = ClassData.realClient.myAdapterCopy

        //Button for running the duplicate ChatClient object's sendMessage method that was created in Startup Activity's onCreate() and stored in ClassData Singleton.. Simple enough?
        sendButton.setOnClickListener {
            ClassData.realClient.sendMessage(sendMessageTxt.text.toString())
            sendMessageTxt.setText("")
        }
    }
}












