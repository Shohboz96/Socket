package com.example.socket

import android.os.AsyncTask
import java.io.DataOutputStream
import java.io.PrintWriter
import java.net.Socket

class MessageSender : AsyncTask<String,Unit,Unit>() {

    lateinit var s: Socket
    lateinit var dos: DataOutputStream
    lateinit var pw: PrintWriter

    override fun doInBackground(vararg params: String) {
        val message = params[0]


        try {
            s = Socket("192.168.43.120",24)
            pw = PrintWriter(s.getOutputStream())
            pw.write(message)
            pw.flush()
            pw.close()
            s.close()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}