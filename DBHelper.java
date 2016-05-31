package ilp.tcs.com.myapplication;

/**
 * Created by 1014085 on 2/9/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1014085 on 2/8/2016.
 */

public class DBHelper {

    Context context = null;
    DataBaseHelper dbhelper = null;

    public DBHelper(Context context) {

        this.context = context;
        dbhelper = new DataBaseHelper(context);
    }


    class DataBaseHelper extends SQLiteOpenHelper {

        private final static String DATABASENAME = "TJA195";
        private final static int DATABASEVERSION = 2;
        private final static String TABLENAME = "NewsTable";
        private final static String id = "_id";
        private final static String newstitle = "NewsTitle";
        private final static String newsdesc = "NewsDesc";
        private final static String newsviews = "NewsViews";
      //  private final static String newsimage = "NewsImage";

        public DataBaseHelper(Context context) {
            super(context, DATABASENAME, null, DATABASEVERSION);
            Toast.makeText(context,"constructor called",Toast.LENGTH_SHORT).show();
            Log.d("tag","Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            Log.d("tag", "On Create is called");

            String sql = "CREATE TABLE " + TABLENAME + "(" + id + " INTEGER PRIMARY KEY AUTOINCREMENT," + newstitle + " VARCHAR(255)," + newsdesc + " VARCHAR(255),"+ newsviews +" INTEGER)";
            Log.d("tag", sql);

            Toast.makeText(context, "table created", Toast.LENGTH_SHORT).show();
            db.execSQL(sql);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d("tag", "On Upgrade is called");
            //DROP TABLE IF EXISTS TABLENAME

            String sql = "DROP TABLE IF EXISTS " + TABLENAME;
            Toast.makeText(context, "upgrage table called", Toast.LENGTH_SHORT).show();
            db.execSQL(sql);
            onCreate(db);

        }
    }

    public long insert(String newstitle,String newsdesc,Integer newsviews){
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBaseHelper.newstitle, newstitle);
        contentValues.put(DataBaseHelper.newsdesc,newsdesc);
        contentValues.put(DataBaseHelper.newsviews,newsviews);
        /*Uri uri = null;
        contentValues.put(DataBaseHelper.newsimage,uri.parse(newsimage));*/

        long result = db.insert(DataBaseHelper.TABLENAME, null, contentValues);
        Log.d("tag3",DataBaseHelper.id+contentValues.toString());
        if(result < 0){
            Log.d("tag","insertion unsuccessful");
        }
        else {
            Log.d("tag","insertion successful");
        }
        db.close();
        return result;
    }


    public List<NewsBean> getAllData(){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        String[] columns = {dbhelper.id,dbhelper.newstitle,dbhelper.newsdesc,dbhelper.newsviews};
        Cursor cursor = sqLiteDatabase.query(dbhelper.TABLENAME, columns, null, null, null, null, null);

        StringBuffer buffer = new StringBuffer();

        //now

        List<NewsBean> newsBeanArray=new ArrayList<>();

        while(cursor.moveToNext()){

            NewsBean newsBean = new NewsBean();
            String newstitle = cursor.getString(1);
            String newsdesc = cursor.getString(2);
            Integer nofviews =cursor.getInt(3);
            newsBean.setNewstitle(newstitle);
            newsBean.setNewsdesc(newsdesc);
            newsBean.setNoofviews(nofviews);

            newsBeanArray.add(newsBean);

            Log.d("id",cursor.getString(0));
        }

      return  newsBeanArray;
    }

    public void deleteData(String newstitle) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        String[] sel={newstitle};
        int a = db.delete(DataBaseHelper.TABLENAME, DataBaseHelper.newstitle + "=?", sel);

        //select columnname from table name where username="android" AND password="sql"

        Log.d("tag","result from delete"+a);
        db.close();


    }

    public int updateData(NewsBean bean1,String oldtitle){
        SQLiteDatabase sqLiteDatabase = dbhelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbhelper.newsdesc,bean1.getNewsdesc());
        values.put(dbhelper.newsviews,bean1.getNoofviews());
        String[] whereArgs = {oldtitle};
        int count = sqLiteDatabase.update(dbhelper.TABLENAME,values,dbhelper.newstitle +" = ?",whereArgs);

        return count;
    }
}

