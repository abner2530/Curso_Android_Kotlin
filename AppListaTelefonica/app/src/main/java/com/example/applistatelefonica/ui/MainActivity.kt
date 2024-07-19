package com.example.applistatelefonica.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applistatelefonica.R
import com.example.applistatelefonica.adapter.ContactListAdapter
import com.example.applistatelefonica.adapter.listener.ContactOnClickListener
import com.example.applistatelefonica.database.DBHelper
import com.example.applistatelefonica.databinding.ActivityMainBinding
import com.example.applistatelefonica.model.ContactModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contactList: List<ContactModel>
    private lateinit var adapter: ContactListAdapter
    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var dbHelper: DBHelper
    private var ascDesc: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)

        val sharedPreferences = application.getSharedPreferences("login", Context.MODE_PRIVATE)

        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username", "")
            editor.apply()
            finish()
        }

        binding.recyclerViewContacts.layoutManager = LinearLayoutManager(applicationContext)

        loadList()

        binding.buttonOrder.setOnClickListener {
            if (ascDesc) {
                binding.buttonOrder.setImageResource(R.drawable.baseline_arrow_upward_24)
            } else {
                binding.buttonOrder.setImageResource(R.drawable.baseline_arrow_downward_24)
            }
            ascDesc = !ascDesc
            contactList = contactList.reversed()
            placeAdapter()
        }

        binding.buttonAdd.setOnClickListener {
            result.launch(Intent(this, NewContactActivity::class.java))
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                loadList()
            } else if (it.data != null && it.resultCode == 0) {
                Toast.makeText(applicationContext, "Operation Canceled", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun placeAdapter() {
        adapter = ContactListAdapter(contactList, ContactOnClickListener { contact ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("id", contact.id)
            result.launch(intent)
        })
        binding.recyclerViewContacts.adapter = adapter
    }

    private fun loadList() {
        contactList = dbHelper.getAllContact().sortedWith(compareBy { it.name })
        placeAdapter()
    }
}

/*binding.listViewContacts.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ContactDetailActivity::class.java)
            intent.putExtra("id", contactList[position].id)
            result.launch(intent)
        }*/

/*contactList = dbHelper.getAllContact()

        adapter = ArrayAdapter<ContactModel>(
            applicationContext,
            android.R.layout.simple_list_item_1,
            contactList
        )

        binding.listViewContacts.adapter = adapter*/

//private lateinit var adapter: ArrayAdapter<ContactModel>