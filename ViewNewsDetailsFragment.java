package ilp.tcs.com.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewNewsDetailsFragment extends Fragment {

    String title,desc,im1;
    int nof;
    TextView t1,t2,t3;
    ImageView im;
    private View myFragmentView;

    public ViewNewsDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        title = getArguments().getString("title");
        desc = getArguments().getString("desc");
        nof = getArguments().getInt("nof");
        im1=getArguments().getString("image");
        Log.d("torn",title);
        Log.d("torn",desc);
        Log.d("torn",nof+" ");

        myFragmentView = inflater.inflate(R.layout.fragment_view_news_details, container, false);

       t1 = (TextView)myFragmentView.findViewById(R.id.text1);
        t2 = (TextView)myFragmentView.findViewById(R.id.text2);
        t3 = (TextView)myFragmentView.findViewById(R.id.text3);
        im = (ImageView)myFragmentView.findViewById(R.id.image1);

        t1.setText(title);
        t2.setText(desc);
        t3.setText(nof + " ");
//        im.setImageURI(Uri.parse(im1));


        return myFragmentView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.view_news_details, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id == R.id.edit){
            NewsBean newsBean = new NewsBean();
            newsBean.setNewstitle(title);
            newsBean.setNewsdesc(desc);
            newsBean.setNoofviews(nof);

            Log.d("tagg", "inside edit");
            Bundle b = new Bundle();
            b.putString("title", newsBean.getNewstitle());
            b.putString("desc", newsBean.getNewsdesc());
            b.putInt("nof", newsBean.getNoofviews());
            Log.d("log", newsBean.getNewstitle());

            Intent intent = new Intent(getActivity(),EditDetailsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            b.putParcelable("data", newsBean);
            Log.d("log1", "******"+newsBean);
            Log.d("log1", newsBean.getNewstitle());
            intent.putExtras(b);
            startActivity(intent);
           // getActivity().finish();

           /* EditNewsDetailsFragment fragment2 = new EditNewsDetailsFragment();
            fragment2.setArguments(b);
            Log.d("log",b.toString());
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment3, fragment2).addToBackStack("tag1").commit();
            //fragmentTransaction.commit();
//            getFragmentManager().beginTransaction().replace(R.id.emptyLayout,new EditNewsDetailsFragment()).addToBackStack("tag").commit();*/
        }

        if(id == R.id.delete){
            final DBHelper dbHelper=new DBHelper(this.getActivity());
            NewsBean newsBean = new NewsBean();
            newsBean.setNewstitle(title);
            newsBean.setNewsdesc(desc);
            newsBean.setNoofviews(nof);
            Log.d("tagg","inside delete");

            String newstitle =title.toString();
            dbHelper.deleteData(newstitle);
            Log.d("tag","data deleted");
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
