package com.example.applicationfitmate.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.applicationfitmate.R

class WorkoutAdapter(private val appContext: Context, private val nameList: ArrayList<String>,
                    private val descList: ArrayList<String>) : BaseAdapter() {

    private val inflater: LayoutInflater
            = appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return nameList.size
    }

    override fun getItem(i: Int): Any {
        return i
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {

        var view: View? = view
        view = inflater.inflate(R.layout.activity_list_vu, parent, false)

        val name = view.findViewById<TextView>(R.id.txtWorName)
        val desc = view.findViewById<TextView>(R.id.txtWorDesc)

        name.text = nameList[position]
        desc.text = descList[position]

        return view

    }

}