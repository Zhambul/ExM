package ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import com.cheesehole.expencemanager.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ui.helpers.HistoryFirstLevel;
import ui.helpers.HistorySecondLevel;
import ui.helpers.HistoryThirdLevel;
import ui.helpers.MyDrawer;
import ui.helpers.HistoryFirstLevelAdapter;

/**
 * Created by Жамбыл on 21.06.2015.
 */
public class History extends BaseActivity {

    ExpandableListView listView;
    Toolbar toolbar;
    MyDrawer drawer;
    int colorOfDrawer;

    // Flags
    public final static String DAY_CATEGORY = "DAY_CATEGORY";
    public final static String DAY_COMMENT = "DAY_COMMENT";
    public final static String DAY_MONEY = "DAY_MONEY";

    public final static String DAYS_NAME = "DAYS_NAME";
    public final static String DAYS_MONEY = "DAYS_MONEY";

    public final static String MONTHS_NAME = "MONTHS_NAME";
    public final static String MONTHS_MONEY = "MONTHS_MONEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        startUI();
    }

    @Override
    protected void startUI() {
        getColors();
        initToolbar();
        initDrawer();
        initExpListView();
    }

    private void getColors() {
        colorOfDrawer = getResources().getColor(R.color.HomeColorPrimary);
    }

    private void initDrawer() {
        drawer = new MyDrawer(this, toolbar,colorOfDrawer);
        drawer.create();
    }

    private void initToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbarHistory);
    }

    private void initExpListView() {
        listView = (ExpandableListView)findViewById(R.id.history_list);

        // region content init
        List<String> listOfMoths = new ArrayList<>();

        List<List<String>> listOfAllDays = new ArrayList<>();
        List<List<String>> listOfAllDay = new ArrayList<>();

        List<String> listOfDays1= new ArrayList<>();
        List<String> listOfDays2= new ArrayList<>();
        List<String> listOfDays3= new ArrayList<>();

        List<String> listOfDay1= new ArrayList<>();
        List<String> listOfDay2= new ArrayList<>();
        List<String> listOfDay3= new ArrayList<>();
        List<String> listOfDay4= new ArrayList<>();
        List<String> listOfDay5= new ArrayList<>();
        List<String> listOfDay6= new ArrayList<>();
        List<String> listOfDay7= new ArrayList<>();
        List<String> listOfDay8= new ArrayList<>();
        List<String> listOfDay9= new ArrayList<>();

        listOfMoths.add("November");
        listOfMoths.add("December");
        listOfMoths.add("January");

        listOfDays1.add("09.11.2015");
        listOfDays1.add("10.11.2015");
        listOfDays1.add("11.11.2015");
        listOfDays1.add("12.11.2015");
        listOfAllDays.add(listOfDays1);

        listOfDays2.add("09.12.2015");
        listOfDays2.add("10.12.2015");
        listOfAllDays.add(listOfDays2);

        listOfDays3.add("09.01.2015");
        listOfDays3.add("10.01.2015");
        listOfDays3.add("11.01.2015");
        listOfDays3.add("12.01.2015");
        listOfDays3.add("13.01.2015");
        listOfDays3.add("14.01.2015");
        listOfAllDays.add(listOfDays3);

        // to make triple layer of  day

        listOfDay1.add("23");
        listOfDay2.add("23");
        listOfDay3.add("23");
        listOfDay4.add("23");
        listOfDay5.add("23");
        listOfDay6.add("23");
        listOfDay7.add("23");
        listOfDay8.add("23");
        listOfDay9.add("23");


        listOfAllDay.add(listOfDay1);
        listOfAllDay.add(listOfDay2);
        listOfAllDay.add(listOfDay3);

        // endregion

        // Container
        ArrayList<HistoryFirstLevel> firstLevelList = new ArrayList<>();



        for(int i = 0; i < listOfMoths.size();i++) {
            HistoryFirstLevel firstLevel = new HistoryFirstLevel();
            firstLevel.firstLevelHeader.put(MONTHS_NAME,listOfMoths.get(i));
            firstLevel.firstLevelHeader.put(MONTHS_MONEY,"$8000");

            for(int j =0; j < listOfAllDays.get(i).size(); j++) {
                HistorySecondLevel secondLevel= new HistorySecondLevel();
                secondLevel.secondLevelHeader.put(DAYS_NAME, listOfAllDays.get(i).get(j) );
                secondLevel.secondLevelHeader.put(DAYS_MONEY, "$45");

                for (int k = 0; k < listOfAllDay.size(); k++) {
                    HistoryThirdLevel day = new HistoryThirdLevel();
                    day.thirdLevel.put(DAY_CATEGORY, "Auchan");
                    day.thirdLevel.put(DAY_COMMENT, "Auchan");
                    day.thirdLevel.put(DAY_MONEY, listOfAllDay.get(k).get(0));

                    secondLevel.thirdLevelList.add(day);
                }
                firstLevel.secondLevelList.add(secondLevel);
            }
            firstLevelList.add(firstLevel);
        }

        HistoryFirstLevelAdapter adapter = new HistoryFirstLevelAdapter(this,firstLevelList);
        listView.setAdapter(adapter);
    }
}
