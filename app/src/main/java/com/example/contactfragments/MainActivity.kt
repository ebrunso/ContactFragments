package com.example.contactfragments

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_recycler.*


class MainActivity : AppCompatActivity(), ContactInfoEntryFragment.OnFragmentInteractionListener,
    RecyclerFragment.OnFragmentInteractionListener {

    val recyclerViewFragment by lazy {RecyclerFragment()}
    val contactList by lazy { populateContactList()}

    lateinit var contactValue: String

    val adapter by lazy {ContactRVAdapter(contactList)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
        rvContacts.layoutManager = layoutManager
        rvContacts.adapter = adapter
    }

    fun displayAllValues() {
        if(this::contactValue.isInitialized) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frgRecyclerView, recyclerViewFragment)
                .commit()
        }
    }

    private fun populateContactList(): ArrayList<Contact>{
        val returnList = ArrayList<Contact>()
        returnList.add(Contact("Eric Brunon", "epiceric@gmail.com", "8375555434"))
        returnList.add(Contact("John Doe", "johnarbuckledoe@gmail.com", "4567773423"))
        returnList.add(Contact("Alice Moonlight", "epiceric@gmail.com", "8375555434"))
        return returnList
    }


    override fun onFragmentInteraction(string: String) {
        contactValue = string
        displayAllValues()
    }
}
