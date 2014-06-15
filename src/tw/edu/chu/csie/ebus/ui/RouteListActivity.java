package tw.edu.chu.csie.ebus.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import tw.edu.chu.csie.ebus.R;
import tw.edu.chu.csie.ebus.provider.DBProvider;
import tw.edu.chu.csie.format.BusService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * An activity representing a list of Routes. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link RouteDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link RouteListFragment} and the item details (if present) is a
 * {@link RouteDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link RouteListFragment.Callbacks} interface to listen for item selections.
 */
public class RouteListActivity extends FragmentActivity implements
        RouteListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_list);

        if (findViewById(R.id.route_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((RouteListFragment) getSupportFragmentManager().findFragmentById(
                    R.id.route_list)).setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
        //DBProvider db = new DBProvider(this);
        /*try {
            BusService bus = new BusService("5", false, "12:00", 3, "2014/3/10", "2014/7/7", "豪泰");
            BusService bus2 = new BusService("5", false, "13:00", 3, "2014/3/10", "2014/7/7", "豪泰");
            BusService bus3 = new BusService("5", false, "14:00", 3, "2014/3/10", "2014/7/7", "豪泰");
            BusService bus4 = new BusService("5", false, "15:00", 3, "2014/3/10", "2014/7/7", "豪泰");
            BusService bus5 = new BusService("5", false, "16:00", 3, "2014/3/10", "2014/7/7", "豪泰");
            
            db.insertBusService(bus);
            db.insertBusService(bus2);
            db.insertBusService(bus3);
            db.insertBusService(bus4);
            db.insertBusService(bus5);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        /*String[] route = db.getRouteToStringArray();
        for(int i=0; i<route.length; i++) {
            Toast.makeText(this, ""+route[i], 0).show();
        }*/

    }

    /**
     * Callback method from {@link RouteListFragment.Callbacks} indicating that
     * the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(RouteDetailFragment.ARG_ITEM_ID, id);
            RouteDetailFragment fragment = new RouteDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.route_detail_container, fragment).commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, RouteDetailActivity.class);
            detailIntent.putExtra(RouteDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
