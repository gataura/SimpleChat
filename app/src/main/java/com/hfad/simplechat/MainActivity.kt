package com.hfad.simplechat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.firebase.database.*
import com.hfad.simplechat.Adapter.ChatAdapter


class MainActivity : AppCompatActivity() {

    var db: FirebaseDatabase = FirebaseDatabase.getInstance()
    var myRef: DatabaseReference = db.getReference("messages")
    lateinit var mSendButton:Button
    lateinit var mEditTextMEssage: EditText
    private var MAX_MESSAGE_LENGTH = 150
    var messages = ArrayList<String>()
    lateinit var mMessagesRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ChatAdapter(this, messages)

        mSendButton = findViewById(R.id.send_message_b)
        mEditTextMEssage = findViewById(R.id.message_input)
        mMessagesRecycler = findViewById(R.id.messages_recycler)

        mMessagesRecycler.layoutManager = LinearLayoutManager(this)
       mMessagesRecycler.adapter = adapter

        mSendButton.setOnClickListener {
            var msg = mEditTextMEssage.text.toString()

            if (msg == "") {

                Toast.makeText(applicationContext, "Введите сообщение", Toast.LENGTH_SHORT).show()

            } else if (msg.length > MAX_MESSAGE_LENGTH) {

                Toast.makeText(applicationContext, "Сообщение слишком длинное", Toast.LENGTH_SHORT).show()

            } else {
                myRef.push().setValue(msg)
                mEditTextMEssage.setText("")
            }
        }

        myRef.addChildEventListener(object: ChildEventListener {

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {

                var msg = p0.value as String
                messages.add(msg)
                adapter.notifyDataSetChanged()
                mMessagesRecycler.smoothScrollToPosition(messages.size)

            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

            override fun onCancelled(p0: DatabaseError) {

            }

        })


    }
}
