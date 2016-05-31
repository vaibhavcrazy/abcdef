package ilp.tcs.com.myapplication;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileNotFoundException;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNewsFragment extends Fragment {

    EditText newstitle,newsdesc,nofviews;
    ImageView image;
    ImageView image1;
    Uri uri;

    public AddNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_news, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.save){
            Log.d("tag", "Save Menu Selected");
            NewsBean newsBean=new NewsBean();
            newsBean.setNewstitle(newstitle.getText().toString());
            newsBean.setNewsdesc(newsdesc.getText().toString());
            newsBean.setNoofviews(Integer.parseInt(nofviews.getText().toString()));
            newsBean.setImageUri(uri);
            NewsDataBase.setNewsbean(newsBean);
            Log.d("tag1", ">>>" + NewsDataBase.getNewsbean());

            final DBHelper dbHelper=new DBHelper(this.getActivity());

            Log.d("tag2",nofviews.getText().toString());

            long result = dbHelper.insert(newstitle.getText().toString(), newsdesc.getText().toString(), Integer.parseInt(nofviews.getText().toString()));
            Log.d("tag", "result of insertion" + result);


            Intent intent = new Intent(getActivity(),HomeActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.addnewsmenu, menu);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        image=(ImageView)view.findViewById(R.id.newsicon);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                Intent chooser = Intent.createChooser(intent, "Select Picture");
                startActivityForResult(chooser, 1);
            }
        });
        newstitle= (EditText) view.findViewById(R.id.newsTitle);
        newsdesc= (EditText) view.findViewById(R.id.desc);
        nofviews= (EditText) view.findViewById(R.id.nofviews);
        image1 = (ImageView) view.findViewById(R.id.newsicon);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("sree", "resultcode" + resultCode + " actual" + getActivity().RESULT_OK);
        if(data!=null&&requestCode==1&&resultCode==getActivity().RESULT_OK){
            uri = data.getData();


            Log.d("sree", "URI path is " + uri.getPath());
            Uri.parse(uri + ".png");


            try {
                Bitmap bmp= BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                image.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }


}


