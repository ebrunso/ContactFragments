package com.example.contactfragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_contacts.view.*

//class ContactRVAdapter(var contactList: ArrayList<Contact>, val callback: ContactCallback)
class ContactRVAdapter(var contactList: ArrayList<Contact>)
    : RecyclerView.Adapter<ContactRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) :
            ContactRVAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_contacts,
            parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = contactList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populateContactItem(contactList[position])
    }

    fun updateList(passedList : ArrayList<Contact>) {
        contactList = passedList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateContactItem(contact : Contact) {
            itemView.tvName.text = contact.name
            itemView.tvEmail.text = contact.email
            itemView.tvPhone.text = contact.phone
            itemView.setOnClickListener{
                Log.v("TAG", contact.name)
            }
        }
    }

}