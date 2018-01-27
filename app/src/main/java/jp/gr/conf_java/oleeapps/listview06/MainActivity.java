package jp.gr.conf_java.oleeapps.listview06;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    MyListAdapter adapter = null;
    ArrayList<ListItem> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // prepare data
        String[] dates = {"2018/01/26", "2018/01/24", "2018/01/22", "2018/01/21", "2018/01/19"};

        // array -> ArrayList
        data = new ArrayList<>();
        for (int i = 0; i < dates.length; i++) {
            // create ListItem
            ListItem item = new ListItem();
            item.setDate(dates[i]);
            item.setId((new Random()).nextLong());
            // put ListItem to ArrayList
            data.add(item);
        }

        // create adapter
        adapter = new MyListAdapter(MainActivity.this, data, R.layout.list_item);

        // set adapter to ListView
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // item click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**
                // get text from item
                LinearLayout linearLayout = (LinearLayout)view;
                TextView date = (TextView)linearLayout.findViewById(R.id.date);
                Toast.makeText(MainActivity.this, date.getText().toString(), Toast.LENGTH_SHORT).show();
                 **/

                // get item at current position & next position
                ListItem item1 = (ListItem)adapter.getItem(position);
                ListItem item2 = (ListItem)adapter.getItem(++position);

                Log.d("myLog", item1.getDate());
                Log.d("myLog", item2.getDate());
                // Toast.makeText(MainActivity.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // item long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("削除しますか？")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // remove item from ArrayList
                                data.remove(position);
                                // update ListView
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "削除しました", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("キャンセル", null)
                        .setCancelable(true);
                // show dialog
                builder.show();
                 return false;
            }
        });
    }
}
