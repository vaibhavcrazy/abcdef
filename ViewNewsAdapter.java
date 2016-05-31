package ilp.tcs.com.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1014085 on 2/2/2016.
 */
public class ViewNewsAdapter extends RecyclerView.Adapter<ViewNewsAdapter.MyViewHolder> {

        LayoutInflater layoutInflater;
        List<NewsBean> userBeanArray=new ArrayList<>();
        List<ListBean> listBeanArray = new ArrayList<>();
        Communicator comm;

        public ViewNewsAdapter(Context context, List<NewsBean> userBeanArray) {
        layoutInflater=LayoutInflater.from(context);
        this.userBeanArray=userBeanArray;
        comm= (Communicator) context;

        }

//inflate the  layout
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = layoutInflater.inflate(R.layout.custom_row1, viewGroup, false);
        MyViewHolder vh=new MyViewHolder(v);
        return vh;
        }

//this will display the data
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        NewsBean newsBean=new NewsBean();
        newsBean=userBeanArray.get(i);
        Log.d("tag", newsBean.toString());

        viewHolder.text.setText(newsBean.getNewstitle());
        int d = newsBean.getNoofviews();
        Log.d("taag", d+" ");
        viewHolder.text1.setText("V:" + d + " ");
        if(newsBean.getImageUri()!=null) {
            viewHolder.image.setImageURI(null);
            viewHolder.image.setImageURI(newsBean.getImageUri());
        }
        final NewsBean finalNewsBean = newsBean;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.respond(finalNewsBean);
                Log.d("tag", "news item clicked");

            }
        });



        }

@Override
public int getItemCount() {
        return userBeanArray.size();
        }

public class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView image;
    TextView text,text1;


    public MyViewHolder(View itemView) {
        super(itemView);

        image = (ImageView) itemView.findViewById(R.id.rowimage);
        text = (TextView) itemView.findViewById(R.id.rowtitle);
        text1= (TextView) itemView.findViewById(R.id.rownofviews);


    }
}

}
