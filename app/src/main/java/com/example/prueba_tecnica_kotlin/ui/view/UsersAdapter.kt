package com.example.prueba_tecnica_kotlin.ui.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_tecnica_kotlin.R
import com.example.prueba_tecnica_kotlin.utils.Utils
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.util.*

class UsersAdapter (var context: Context, var users: JsonArray) :  RecyclerView.Adapter<UsersAdapter.UsersViewHolder>(){
    var auxData = mutableListOf<JsonElement>();

    init {
        if(auxData.isEmpty()){
            auxData = users.toMutableList()
        }
    }

    class UsersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val userName: TextView = itemView.findViewById(R.id.user_name)
        val email: TextView = itemView.findViewById(R.id.email)
        val phone: TextView = itemView.findViewById(R.id.phone)
        val webSite: TextView = itemView.findViewById(R.id.website)
        val initials: TextView = itemView.findViewById(R.id.initials)
        val userID: TextView = itemView.findViewById(R.id.user_id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.UsersViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_users, null)
        return UsersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersAdapter.UsersViewHolder, position: Int) {
        var current = auxData[position]
        var user = current.asJsonObject
        var name = "${user.get("name")}".replace("\"","")
        holder.name.text = name
        holder.userName.text = "${user.get("username")}".replace("\"","")
        holder.email.text = "${user.get("email")}".replace("\"","")
        holder.phone.text = "${user.get("phone")}".replace("\"","")
        holder.webSite.text = "${user.get("website")}".replace("\"","")
        holder.userID.text = "${user.get("id")}".replace("\"","")
        holder.initials.text = Utils.getInitals(name)
    }

    override fun getItemCount(): Int {
        return auxData.size
    }

    fun filter(str: String) {
        if (str.length != 0) {
            auxData.clear()
            for (userlist in users) {
                if (userlist.asJsonObject.get("name").toString()
                        .lowercase().contains(str.lowercase())) {
                    auxData.add(userlist)
                }
            }
        } else {
            auxData.clear()
            auxData.addAll(users)
        }
        notifyDataSetChanged()
    }

}