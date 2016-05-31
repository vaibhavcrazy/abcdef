package ilp.tcs.com.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class sortFragment extends Fragment {


    public sortFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Log.d("tag1","inside sort fragment");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sort, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("tag", "1");
        final DBHelper dbHelper=new DBHelper(this.getActivity());

        List<NewsBean> data ;
        data = dbHelper.getAllData();
        List<NewsBean> newsBeanArray1=data;
        Log.d("array",newsBeanArray1.toString());
         Collections.sort(newsBeanArray1);
        Log.d("tag", "2");
        Log.d("array",newsBeanArray1.toString());
        ViewNewsAdapter adap=new ViewNewsAdapter(getActivity(),newsBeanArray1);
        RecyclerView recycler= (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setAdapter(adap);//the adapter to which the recycler view is linked
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.view_news_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.sort){
            Log.d("tag", "sort Menu Selected");

        }

        if(id==R.id.logout){
            Log.d("tag", "log out Menu Selected");

            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            getActivity().finish();
        }

        return super.onOptionsItemSelected(item);
    }


}


