package ilp.tcs.com.myapplication;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 1014085 on 2/2/2016.
 */
public class NewsBean implements Parcelable,Comparable<NewsBean> {

    String newstitle;
    String newsdesc;
    int noofviews;
    Uri imageUri;


    public static final Creator<NewsBean> CREATOR = new Creator<NewsBean>() {
        @Override
        public NewsBean createFromParcel(Parcel in) {
            return new NewsBean(in);
        }

        @Override
        public NewsBean[] newArray(int size) {
            return new NewsBean[size];
        }
    };

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public NewsBean() {
    }



    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewsdesc() {
        return newsdesc;
    }

    public void setNewsdesc(String newsdesc) {
        this.newsdesc = newsdesc;
    }

    public int getNoofviews() {
        return noofviews;
    }

    public void setNoofviews(int noofviews) {
        this.noofviews = noofviews;
    }

    protected NewsBean(Parcel in) {
        newstitle = in.readString();
        newsdesc = in.readString();
        noofviews = in.readInt();
        imageUri = in.readParcelable(Uri.class.getClassLoader());
    }




    public int compareTo(NewsBean another) {
        int compare=((NewsBean)another).getNoofviews();
        /* For Ascending order*/
        return compare-this.getNoofviews();
    }

    @Override
    public String toString() {
        return "NewsBean{" +
                "newstitle='" + newstitle + '\'' +
                ", newsdesc='" + newsdesc + '\'' +
                ", noofviews=" + noofviews +
                ", imageUri=" + imageUri +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(newstitle);
        dest.writeString(newsdesc);
        dest.writeInt(noofviews);
        dest.writeParcelable(imageUri, flags);
    }
}
