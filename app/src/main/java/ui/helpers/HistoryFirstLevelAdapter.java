package ui.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.cheesehole.expencemanager.R;

import java.util.ArrayList;

import ui.activities.History;

/**
 * Created by Жамбыл on 21.06.2015.
 */

public class HistoryFirstLevelAdapter extends BaseExpandableListAdapter {
    Context context;
    ArrayList<HistoryFirstLevel> firstLevelList;
    ExpandableListView listView;
    private int lastExpandedGroupPosition;

    public HistoryFirstLevelAdapter(Context context, ArrayList<HistoryFirstLevel> firstLevel) {
            this.context = context;
            this.firstLevelList = firstLevel;
        }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        listView = (ExpandableListView)parent;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.history_first_layer,null);
        }


        TextView monthName = (TextView)convertView.findViewById(R.id.history_first_layer_month);
        TextView monthMoney = (TextView)convertView.findViewById(R.id.history_first_layer_money);

        monthName.setText((String) firstLevelList.get(groupPosition).firstLevelHeader.get(History.MONTHS_NAME));
        monthMoney.setText((String) firstLevelList.get(groupPosition).firstLevelHeader.get(History.MONTHS_MONEY));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        CustExpListview SecondLevelexplv = new CustExpListview(context);
        SecondLevelexplv.setAdapter(new HistorySecondLevelAdapter(context,
                firstLevelList.get(groupPosition).secondLevelList));
        SecondLevelexplv.setGroupIndicator(null);
        return SecondLevelexplv;
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
        return firstLevelList.get(groupPosition).secondLevelList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return firstLevelList.get(groupPosition).firstLevelHeader;
    }

    @Override
    public int getGroupCount() {
        return firstLevelList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

