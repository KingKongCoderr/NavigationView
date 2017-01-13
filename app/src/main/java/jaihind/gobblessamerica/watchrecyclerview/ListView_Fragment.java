package jaihind.gobblessamerica.watchrecyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

/**
 * Created by nande on 1/11/2017.
 */

public class ListView_Fragment extends Fragment {
    SectionedRecyclerViewAdapter sectionadapter;

    Map<String,List<Watch>> watches;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment1,container,false);
        sectionadapter=new SectionedRecyclerViewAdapter();
        watches=new HashMap<>();
        List<Watch> Samsungwatchset=new ArrayList<>();
        Samsungwatchset.add(new Watch("S2 classic",200,"Tizen OS",R.drawable.stwofrontier));
        Samsungwatchset.add(new Watch("S2 frontier",250,"Tizen OS",R.drawable.stwofrontier));
        Samsungwatchset.add(new Watch("S3 classic",300,"Tizen OS",R.drawable.sthreeclassic));
        Samsungwatchset.add(new Watch("S3 frontier",350,"Tizen OS",R.drawable.sthreefrontier));
        watches.put("Samsung",Samsungwatchset);

        List<Watch> applewatchset=new ArrayList<>();
        applewatchset.add(new Watch("Apple series 1",200,"Watch OS",R.drawable.appleseriesone));
        applewatchset.add(new Watch("Apple series 2",300,"Watch OS",R.drawable.appleseriestwo));
        applewatchset.add(new Watch("Apple Nike+",200,"Watch OS",R.drawable.applenike));
        applewatchset.add(new Watch("Apple Hermes",250,"Watch OS",R.drawable.applehermes));
        applewatchset.add(new Watch("Apple Edition",250,"Watch OS",R.drawable.appleedition));
        watches.put("Apple",applewatchset);

        List<Watch> Motowatchset=new ArrayList<>();
        Motowatchset.add(new Watch("Moto 1st Gen",300,"Android Wear",R.drawable.motoonegen));
        Motowatchset.add(new Watch("Moto 2nd Gen",350,"Android Wear",R.drawable.motosecondgen));
        Motowatchset.add(new Watch("Moto Sport",300,"Android Wear",R.drawable.motosport));
        watches.put("Moto",Motowatchset);

        List<Watch> otherwatchset=new ArrayList<>();
        otherwatchset.add(new Watch("Huawei",300,"Android Wear",R.drawable.huawei));
        otherwatchset.add(new Watch("fit bit",100,"Proprietary OS",R.drawable.fitbit));
        otherwatchset.add(new Watch("Tag Heuer",2000,"Custom OS",R.drawable.tagheuer));
        otherwatchset.add(new Watch("Pebbel",300,"Proprietary OS",R.drawable.pebbel));
        watches.put("other",otherwatchset);



sectionadapter.addSection(new Watchsection("Samsung",watches.get("Samsung")));
        sectionadapter.addSection(new Watchsection("Apple",watches.get("Apple")));
        sectionadapter.addSection(new Watchsection("Moto",watches.get("Moto")));
        sectionadapter.addSection(new Watchsection("Other",watches.get("other")));





        Log.d("list","Inside listfrag");
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list_rcview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionadapter);


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = ((AppCompatActivity) getActivity());
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle("Expandable Section");
        }
    }
    class Watchsection extends StatelessSection{
     String title;
        List<Watch> list;
        boolean expanded= false;

        public Watchsection( String title, List<Watch> list) {
            super(R.layout.listheader, R.layout.listitem);
            this.title = title;
            this.list = list;
        }

        @Override
        public int getContentItemsTotal() {
            return expanded?list.size():0;
        }

        @Override
        public RecyclerView.ViewHolder getItemViewHolder(View view) {
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

            final ItemViewHolder itemHolder = (ItemViewHolder) holder;

            itemHolder.image.setImageResource(list.get(position).getImg_id());

            itemHolder.image.
                    setImageBitmap
                    (ImageGenie.decodeSampledBitmapFromResource(getResources(),list.get(position).getImg_id(),150,150));

            itemHolder.name.setText(list.get(position).getName());
            itemHolder.os.setText(list.get(position).getOs());



        }

        @Override
        public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
            return new HeaderViewHolder(view);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
            final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            headerHolder.sectiontitle.setText(title);


            headerHolder.rootview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   expanded = !expanded;
                    headerHolder.arrow.setImageResource(
                            expanded ? R.drawable.up_arrow : R.drawable.down_arrow
                    );
                    sectionadapter.notifyDataSetChanged();
                }
            });

        }
    }
    public class HeaderViewHolder extends RecyclerView.ViewHolder{
TextView sectiontitle;
        ImageView arrow;
        View rootview;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            rootview= itemView;
            arrow= (ImageView)itemView.findViewById(R.id.listheader_iv);
            sectiontitle=(TextView)itemView.findViewById(R.id.listheader_tv);
        }
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
ImageView image;
        TextView name,os;
        View rootview;

        public ItemViewHolder(View itemView) {
            super(itemView);
            rootview=itemView;
            name=(TextView)itemView.findViewById(R.id.listitem_tv);
            os=(TextView)itemView.findViewById(R.id.os_tv);
            image=(ImageView)itemView.findViewById(R.id.listitem_iv);

        }
    }
}
