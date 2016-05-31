package ilp.tcs.com.myapplication;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment {

   // EditText newstitle,newsdesc,nofviews;
    TextView username1;
    ImageView image;
    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username1 = (TextView) view.findViewById(R.id.text3);
        username1.setText("Hello " + Username.name);
        image=(ImageView)view.findViewById(R.id.newsicon);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                Log.d("sree", "1212");
                Intent chooser = Intent.createChooser(intent, "Select Picture");
                startActivityForResult(chooser, 1);


            }
        });

        int[] images={R.drawable.addnews,R.drawable.view_news,R.drawable.logout};
        String[] title={"Add News","View News","Logout"};
        List<ListBean> listBeanArray=new ArrayList<>();
        for (int i=0;i<images.length;i++){


            ListBean bean=new ListBean();

            bean.setImage(images[i]);
            bean.setTitle(title[i]);
            listBeanArray.add(bean);
        }



        MyAdapter adap=new MyAdapter(getActivity(),listBeanArray);
        RecyclerView recycler= (RecyclerView) view.findViewById(R.id.recycler);
        recycler.setAdapter(adap);//the adapter to which the recycler view is linked
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("sree", "resultcode" + resultCode + " actual" + getActivity().RESULT_OK);
        if(data!=null&&requestCode==1&&resultCode==getActivity().RESULT_OK){
            Uri uri = data.getData();


            Log.d("sree", "URI path is " + uri.getPath());
            Uri.parse(uri + ".png");


            try {
                Bitmap bmp= BitmapFactory.decodeStream(getActivity().getContentResolver().openInputStream(uri));
                image.setImageBitmap(bmp);
               // image.setImageURI(uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }


}
