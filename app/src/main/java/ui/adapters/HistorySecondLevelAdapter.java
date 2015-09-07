package ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.cheesehole.expencemanager.R;

import java.util.ArrayList;

import ui.activities.ExpenseViewActivity;
import ui.fragments.HistoryFragment;
import ui.helpers.HistorySecondLevel;

/**
 * Created by Жамбыл on 21.06.2015.
 */
public class HistorySecondLevelAdapter extends BaseExpandableListAdapter {

    // Context
    Context context;

    // ExpandableListView itself
    private ExpandableListView listView;

    // Variables
    ArrayList<HistorySecondLevel> secondLevelList;
    private int lastExpandedGroupPosition;

    /*
        Constructor
     */

    public HistorySecondLevelAdapter(Context context, ArrayList<HistorySecondLevel> secondLevelList) {
        this.context = context;
        this.secondLevelList = secondLevelList;
    }

    /*
        GroupView
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View groupView, ViewGroup parent) {
        listView = (ExpandableListView)parent;
//        if(convertView == null) {
//            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.history_second_layer_group,null);
//        }
////        convertView.setMinimumHeight(150);
//
//        TextView group_text = (TextView) convertView.findViewById(R.id.history_second_layer_group_text);
//        group_text.setText((String) secondLevelList.get(groupPosition).secondLevelHeader.get(HistoryActivity.DAYS_NAME));

        TextView textView = new TextView(context);
        textView.setText("second level");
        return textView;
    }

    /*
        ChildView
     */
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View childView, ViewGroup parent) {

        if(childView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            childView = inflater.inflate(R.layout.history_second_layer_child,null);
        }

        // Showing expense data
        childView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ExpenseViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(HistoryFragment.DATE, "10.06.15");
                bundle.putString(HistoryFragment.CATEGORY, "Grocery");
                bundle.putString(HistoryFragment.SUBCATEGORY, "Auchan");
                bundle.putString(HistoryFragment.COMMENT, "Hi");
                bundle.putString(HistoryFragment.MONEY, "82.32");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return childView;
    }

    @Override
    public void onGroupExpanded(int groupPosition){
        // collapse the old expanded group, if not the same
        // as new group to expand
        if(groupPosition != lastExpandedGroupPosition) {
            listView.collapseGroup(lastExpandedGroupPosition);
        }

        super.onGroupExpanded(groupPosition);
        lastExpandedGroupPosition = groupPosition;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return secondLevelList.get(groupPosition).thirdLevelList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return secondLevelList.get(groupPosition).thirdLevelList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return secondLevelList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return secondLevelList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // TODO Auto-generated method stub
        return true;
    }

}
