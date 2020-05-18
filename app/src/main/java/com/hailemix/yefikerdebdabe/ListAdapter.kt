    package com.hailemix.yefikerdebdabe

    import android.app.Activity
    import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.BaseExpandableListAdapter
    import android.widget.TextView

    /**
     * Created by Haile on 3/8/2016.
     */
    class ListAdapter(private val context: Activity, private val Items: List<String>,
                      private val ParentListItems: Map<String, List<String>>) : BaseExpandableListAdapter() {



        override fun getChild(groupPosition: Int, childPosition: Int): Any =
                ParentListItems[Items[groupPosition]]!![childPosition]

        override fun getChildId(groupPosition: Int, childPosition: Int): Long = childPosition.toLong()

        override fun getChildView(groupPosition: Int, childPosition: Int,
                                  isLastChild: Boolean, ListView: View?, parent: ViewGroup): View {
            var myListView = ListView
            val contentPosition = getChild(groupPosition, childPosition) as String
            val inflater = context.layoutInflater
            if (myListView == null) myListView = inflater.inflate(R.layout.child_list_item, null)
            val item = myListView!!.findViewById<TextView>(R.id.textView1)
            item.text = contentPosition
            return myListView
        }

        override fun getChildrenCount(groupPosition: Int): Int =
            ParentListItems[Items[groupPosition]]!!.size



        override fun getGroup(groupPosition: Int): Any = Items[groupPosition]

        override fun getGroupCount(): Int = Items.size

        override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

        override fun getGroupView(groupPosition: Int, isExpanded: Boolean,
                                  ListView: View?, parent: ViewGroup): View {
            var myListView= ListView
            val allContents = getGroup(groupPosition) as String
            if (myListView == null) {
                val textInflater = context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                myListView = textInflater.inflate(R.layout.parent_list_item, null)
            }
            val item = myListView!!.findViewById<TextView>(R.id.textView1)
            item.text = allContents
            return myListView
        }

        override fun hasStableIds(): Boolean = true

        override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true
    }