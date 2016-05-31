package ilp.tcs.com.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewNewsFragment extends Fragment {

    DrawerLayout drawer;
    public ViewNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_view_news, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int d =NewsDataBase.getNewsbean().size();

        final DBHelper dbHelper=new DBHelper(this.getActivity());

        List<NewsBean> data ;
        data = dbHelper.getAllData();


        Log.d("tag", "result of view all data" );
        for(int i =0;i<data.size();i++){
            Log.d("tag1",data.get(i).getNewstitle()+" "+data.get(i).getNoofviews());
        }

        ViewNewsAdapter adap=new ViewNewsAdapter(getActivity(),data);
        RecyclerView recycler= (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setAdapter(adap);//the adapter to which the recycler view is linked
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));

     }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.view_news, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.sort){

            Log.d("tag11", "sort Menu Selected");
            sortFragment fragment2 = new sortFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment1, fragment2).addToBackStack("tag");
            fragmentTransaction.commit();


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
