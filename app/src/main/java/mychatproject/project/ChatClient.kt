package mychatproject.project

import android.util.Log
import java.io.PrintStream
import java.net.InetAddress
import java.net.Socket
import java.util.*

/* This is the  Facepalm Messenger
Created by: Roni Jumpponen */

// Chat Client class is for creating the connection. Storing all the Methods needed for the app to function.
class ChatClient(private val mainActivity: MainActivity) {
    lateinit var socket: Socket
    lateinit var output: PrintStream
    lateinit var input: Scanner
    //Duplicate of the adapter created in Startup so it can be utilized within the class and then used in MainActivity.
    lateinit var myAdapterCopy: MyRecyclerViewAdapter
    val myMessages = mutableListOf<String>()
    val tag = "fail"

    //Function for connecting to the server. Starts a new thread when used. (Ran on Startup)
    fun connect(ip: String, port: Int) {
        val c = Connection(ip, port)
        val ct = Thread(c)
        ct.start()
    }

    //Function for sending the message, starts a new thread every time a message is sent.
    fun sendMessage(m: String) {
        Thread(Runnable {
            try {
                output.println(m)
            } catch (e: Exception) {
                Log.d(tag, "oopsiedaisy, no connection or something")
            }
        }).start()
    }

    /* Unused disconnect function i was planning to use for closing the connection and the app originally via a menu.
    fun disconnect() {
            try {
                input.close()
                output.close()
                socket.close()

            } catch (e: Exception) {
                Log.e(tag, e.message)
            }
    }*/
    //function used for receiving messages. runOnUiThread is needed for updating the UI (RecyclerView)
    fun receiveMessage(input: Scanner) {
        while (true) {
            val inc = input.nextLine()
            myMessages.add(inc)
            mainActivity.runOnUiThread {
                myAdapterCopy.notifyDataSetChanged()

            }

        }
    }

    //inner class makes initializing the lateinit properties possible. And it also being runnable I can create a connection thread within the ChatClient object created in the Startup.
    inner class Connection(private val ip: String, private val po: Int) : Runnable {
        override fun run() {
            try {
                val host = ip
                val port = po
                Log.d(tag, port.toString())
                socket = Socket(InetAddress.getByName(host), port)
                output = PrintStream(socket.getOutputStream(), true)
                input = Scanner(socket.getInputStream())
                receiveMessage(input)
            } catch (e: Exception) {
                Log.e(tag, e.message)
            }
        }
    }
}




