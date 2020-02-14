package mychatproject.project

/* This is the  Facepalm Messenger
Created by: Roni Jumpponen */

//Singleton class for storing necessary data
object ClassData {
    //Duplicate ChatClient object created in Startup
    lateinit var realClient: ChatClient
}