/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License.
 *
 */

package com.microsoft.device.display.samples.masterdetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.microsoft.device.display.samples.masterdetail.Item
import com.microsoft.device.display.samples.masterdetail.R
import kotlinx.android.synthetic.main.fragment_items_list.view.*

class ItemsListFragment : Fragment(), AdapterView.OnItemClickListener {
    private lateinit var adapterItems: ArrayAdapter<Item>
    private lateinit var listView: ListView
    private lateinit var items: ArrayList<Item>
    private lateinit var listener: OnItemSelectedListener

    interface OnItemSelectedListener {
        fun onItemSelected(i: Item, position: Int)
    }

    fun registerOnItemSelectedListener(l: OnItemSelectedListener) {
        listener = l
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.apply {
            adapterItems = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_activated_1,
                    items
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_items_list, container, false)
        listView = view.list_view.also {
            it.adapter = adapterItems
            it.choiceMode = ListView.CHOICE_MODE_SINGLE
            it.onItemClickListener = this
        }
        return view
    }

    fun setSelectedItem(position: Int) {
        listView.setItemChecked(position, true)
        listener.onItemSelected(items[position], position)
    }

    override fun onItemClick(adapterView: AdapterView<*>, item: View, position: Int, rowId: Long) {
        listener.onItemSelected(adapterItems.getItem(position)!!, position)
    }

    companion object {
        @JvmStatic
        fun newInstance(items: ArrayList<Item>): ItemsListFragment {
            return ItemsListFragment().apply {
                this.items = items
            }
        }
    }
}
