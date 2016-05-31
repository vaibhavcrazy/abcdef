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
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    LayoutInflater layoutInflater;
    List<ListBean> listBeanArray = new ArrayList<>();
     Communicator comm;

    public MyAdapter(Context context, List<ListBean> listBeanArray) {
        layoutInflater = LayoutInflater.from(context);
        this.listBeanArray = listBeanArray;
        comm= (Communicator) context;

    }

    //inflate the  layout
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = layoutInflater.inflate(R.layout.custom_row, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    //this will display the data
    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        ListBean listBean = new ListBean();
        listBean = listBeanArray.get(i);
        viewHolder.image.setImageResource(listBean.getImage());
        viewHolder.text.setText(listBean.getTitle());
        final ListBean finalListBean = listBean;
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comm.respond(finalListBean);
                Log.d("taag", "success");


            }
        });

    }

    @Override
    public int getItemCount() {
        return listBeanArray.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;


        public MyViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.rowimage);
            text = (TextView) itemView.findViewById(R.id.rowtitle);


        }
    }
}
