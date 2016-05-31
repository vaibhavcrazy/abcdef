package ilp.tcs.com.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1014085 on 2/2/2016.
 */
public class NewsDataBase  {
    static List<NewsBean> newsbean=new ArrayList<>();

    public static List<NewsBean> getNewsbean() {
        return newsbean;
    }

    public static void setNewsbean(NewsBean userbean1) {
        newsbean.add(userbean1);
    }
}
