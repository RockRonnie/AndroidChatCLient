package mychatproject.project

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/* This is the  Facepalm Messenger
Created by: Roni Jumpponen */

//Startup Activity is for the initial onCreate() method.
class Startup : AppCompatActivity() {
    private val ip = "192.168.43.200"
    private val port = 60000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Creating the ChatClient Object Instance with MainActivity reference in the constructor. Im not 100% sure this was needed but i ran into so problems when i didn't do it.
        val client = ChatClient(MainActivity())
        //Creating a duplicate object inside the ClassData Singleton
        ClassData.realClient = client
        val myAdapter = MyRecyclerViewAdapter(this, client.myMessages)
        //Creating a duplicate adapter within the ChatClient object that can be later used in MainActivity.
        client.myAdapterCopy = myAdapter
        //Making the connection to the server
        client.connect(ip, port)
        //Intent used to move to a new activity at the end of the Startup process. (MainActivity)
        val main = Intent(this, MainActivity::class.java).apply {}
        startActivity(main)
    }
}
