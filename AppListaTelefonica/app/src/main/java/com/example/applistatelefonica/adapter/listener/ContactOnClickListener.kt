package com.example.applistatelefonica.adapter.listener

import com.example.applistatelefonica.model.ContactModel

class ContactOnClickListener(val clickListener: (contact: ContactModel) -> Unit) {
    fun onCLick(contact: ContactModel) = clickListener
}