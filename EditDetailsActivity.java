package ilp.tcs.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class EditDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    EditText oldnewstitle,newstitle,newsdesc,nofviews;
    String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);
        toolbar= (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        drawer= (DrawerLayout) findViewById(R.id.drawer);

        setUpNavigation();

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        NewsBean newsbean = (NewsBean) b.get("data");
        Log.d("tag", ">>>>>>>>>>>>>>>>>>");
        Log.d("tag", newsbean + "");
        Log.d("tag", newsbean.getNewstitle());


        oldnewstitle = (EditText)findViewById(R.id.text1);
        newsdesc = (EditText)findViewById(R.id.text2);
        nofviews =(EditText)findViewById(R.id.text3);

       title = newsbean.getNewstitle();

        oldnewstitle.setText(title);

        String desc = newsbean.getNewsdesc();
        newsdesc.setText(desc);

        Integer nof = newsbean.getNoofviews();
        nofviews.setText(nof.toString());
    }

    private void setUpNavigation() {
        toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawer.setDrawerListener(toggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save) {

            NewsBean bean1 = new NewsBean();
            newstitle =(EditText)findViewById(R.id.text1);
            newsdesc =(EditText)findViewById(R.id.text2);
            nofviews =(EditText)findViewById(R.id.text3);

            bean1.setNewstitle(newstitle.getText().toString());
            bean1.setNewsdesc(newsdesc.getText().toString());
            bean1.setNoofviews(Integer.parseInt(nofviews.getText().toString()));

            String oldtitle = oldnewstitle.getText().toString();

            final DBHelper dbHelper=new DBHelper(this);
            dbHelper.updateData(bean1,oldtitle );

        }

        return super.onOptionsItemSelected(item);
    }
}
