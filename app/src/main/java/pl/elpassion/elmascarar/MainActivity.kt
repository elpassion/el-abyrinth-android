package pl.elpassion.elmascarar

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.phoenixframework.channels.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val socket = Socket("").connect()
    }
}
