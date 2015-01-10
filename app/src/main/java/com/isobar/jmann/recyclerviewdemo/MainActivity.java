package com.isobar.jmann.recyclerviewdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;


public class MainActivity extends ActionBarActivity {

    private List<PertinentInfo> infoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        infoList = new ArrayList<PertinentInfo>(createList(20));

        RecyclerViewDemoAdapter adapter = new RecyclerViewDemoAdapter();
        recyclerView.setAdapter(adapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 0) {
                    //recyclerView.scrollToPosition(10);


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private List<PertinentInfo> createList(int size) {
        List<PertinentInfo> result = new ArrayList<PertinentInfo>();
        for (int i = 0; i < size; i++) {
            PertinentInfo pi = new PertinentInfo("Number " + String.valueOf(i), new Date());

            result.add(pi);
        }

        return result;
    }

    public final static class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView item;
        TextView time;

        public ListItemViewHolder(View itemView) {
            super(itemView);

            item = (TextView)itemView.findViewById(R.id.txt_label_item);
            time = (TextView)itemView.findViewById(R.id.txt_label_time);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(v.getContext(), "view clicked", Toast.LENGTH_LONG).show();
        }
    }

    public final class HeaderViewHolder extends RecyclerView.ViewHolder {


        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public final class FooterViewHolder extends RecyclerView.ViewHolder {


        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class RecyclerViewDemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private static final int TYPE_HEADER = 0;
        private static final int TYPE_ITEM = 1;
        private static final int TYPE_FOOTER = 2;


        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

            if (viewType == TYPE_ITEM) {
                View itemView = inflater
                        .inflate(R.layout.card_layout, viewGroup, false);

                return new ListItemViewHolder(itemView);
            } else if (viewType == TYPE_HEADER) {
                View headerView = inflater.inflate(R.layout.header_layout, viewGroup, false);

                return new HeaderViewHolder(headerView);

            } else if (viewType == TYPE_FOOTER) {
                View footerView = inflater.inflate(R.layout.footer_layout, viewGroup, false);

                return new HeaderViewHolder(footerView);
            }

            throw new RuntimeException("there is not type that matches the type " + viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

            if (viewHolder instanceof ListItemViewHolder) {
                PertinentInfo pertinentInfo = infoList.get(position - 1);
                ((ListItemViewHolder)viewHolder).item.setText(pertinentInfo.getItem());
                ((ListItemViewHolder)viewHolder).time.setText(df.format(pertinentInfo.getTime()));
            } else if (viewHolder instanceof HeaderViewHolder) {
                // cast holder to HeaderView and set data for header

            } else if (viewHolder instanceof FooterViewHolder) {
                // cast holder to FooterView and set data for footer

            }
        }

        @Override
        public int getItemCount() {
            return infoList.size() + 2;
        }

        @Override
        public int getItemViewType(int position) {

            if (isPositionHeader(position))
                return TYPE_HEADER;
            else if (isPositionFooter(position))
                return TYPE_FOOTER;

            return TYPE_ITEM;
        }

        private boolean isPositionHeader(int position) {
            return position == 0;
        }

        private boolean isPositionFooter(int position) {

            Log.d("Main", "position is " + position + ", size is " + getItemCount());

            return (position == getItemCount() - 1);
        }
    }
}
