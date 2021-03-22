package com.example.socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.socket.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import java.io.DataOutputStream
import java.io.PrintWriter
import java.net.Socket
import java.util.*

class MainActivity : AppCompatActivity() {

    private var active = true
    private var data = ""
    private var text = ""
    var input = ""
    private val connect = "connect"
    private val disconnect = "disconnect"
    lateinit var socket: Socket
    lateinit var dos: DataOutputStream
    lateinit var writer: PrintWriter
    lateinit var reader : Scanner

    val adress = "192.168.4.2"
    val port = 5000

    private lateinit var bn: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bn = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bn.root)


        connectSocket()
        bn.button.setOnClickListener {
            try {
                CoroutineScope(IO).launch {
                    text = bn.editText.text.toString()
                    if(text.isNotEmpty()) writer.write(text)
                }
            }catch (e:Exception){
                e.printStackTrace()
                Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }

        }

//        CoroutineScope(IO).launch {
//            client(adress,port)
//        }

//        bn.button.setOnClickListener {
//            if(bn.button.text == connect){
//                bn.button.text = disconnect
//                active = true
//                CoroutineScope(IO).launch{
//                    client(adress,port)
//                }
//
//            }else{
//                bn.button.text = connect
//                active = false
//            }
//        }
    }

    private fun connectSocket() {
        try {
            CoroutineScope(IO).launch {
                socket = Socket(adress, port)

                writer = PrintWriter(socket.getOutputStream())
                reader = Scanner(socket.getInputStream())

                CoroutineScope(Main).launch {
                    bn.textView.text = reader.nextLine()
                }
            }
        }catch (e:java.lang.Exception){
            e.printStackTrace()
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun send() {
//        val sender = MessageSender()
//        sender.execute(bn.textView.text.toString())
        try {



            //receive

//            while (active){
//                input = reader.nextLine()
//                if(data.length  < 300){
//                    data += "\n$input"
//                }else{
//                    data = input
//                }
//                CoroutineScope(Main).launch{
//                    bn.textView.text = data
//                }
//            }
//            pw.flush()
//            pw.close()
//            s.close()

        }catch (e: Exception){
            e.printStackTrace()
        }
    }

//    private suspend fun client(address:String, port:Int){
//        val connection = Socket(address,port)
//        val writer = connection.getOutputStream()
//        writer.write(1)
//        val reader = Scanner(connection.getInputStream())
//        while (active){
//            input = reader.nextLine()
//            if(data.length  < 300){
//                data += "\n$input"
//            }else{
//                data = input
//            }
//            CoroutineScope(Main).launch{
//                bn.textView.text = data
//            }
//        }
//
//    }

    override fun onDestroy() {
        super.onDestroy()
            writer.flush()
            writer.close()
            socket.close()
    }
}