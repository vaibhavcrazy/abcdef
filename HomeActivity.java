package ilp.tcs.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements Communicator{

    Toolbar toolbar;
    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    TextView username1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar= (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        drawer= (DrawerLayout) findViewById(R.id.drawer);

        setUpNavigation();
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
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void respond(ListBean listBean) {
        Log.d("tag", listBean + "");
        if(listBean.getTitle().toString().equals("Add News")){
            Log.d("taag", "2222");
            AddNewsFragment frag1=new AddNewsFragment();

            getSupportFragmentManager().beginTransaction().replace(R.id.emptyLayout,frag1).addToBackStack("tag").commit();


            drawer.closeDrawer(GravityCompat.START);

        }
        else if(listBean.getTitle().toString().equals("View News")){
            Log.d("tag","inside view news respond");
            getSupportFragmentManager().beginTransaction().replace(R.id.emptyLayout,new ViewNewsFragment()).addToBackStack("tag").commit();
            drawer.closeDrawer(GravityCompat.START);
        }
        else if(listBean.getTitle().toString().equals("Logout")){

            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            this.finish();

        }
    }

    public void respond(NewsBean newsBean) {
        Log.d("tagg", "inside respond");
        Bundle b = new Bundle();
        b.putString("title",newsBean.getNewstitle());
        b.putString("desc",newsBean.getNewsdesc());
        b.putInt("nof",newsBean.getNoofviews());
//        b.putString("image",newsBean.getImageUri().toString());
        ViewNewsDetailsFragment new1 = new ViewNewsDetailsFragment();
        new1.setArguments(b);
        getSupportFragmentManager().beginTransaction().replace(R.id.emptyLayout,new1).addToBackStack("tag").commit();
        drawer.closeDrawer(GravityCompat.START);
    }
}
