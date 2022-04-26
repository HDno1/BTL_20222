package dz.phamtuanvan.btl_2022.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import dz.phamtuanvan.btl_2022.Fragment.Change_PassFragment;
import dz.phamtuanvan.btl_2022.Fragment.FavouriteFragment;
import dz.phamtuanvan.btl_2022.Fragment.HistoryFragment;
import dz.phamtuanvan.btl_2022.Fragment.HomeFragment;
import dz.phamtuanvan.btl_2022.R;
import dz.phamtuanvan.btl_2022.SQLite.SqlLogin;
import dz.phamtuanvan.btl_2022.model.Student;

public class MainActivity_Test extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_HISTORY = 2;
    private static final int FRAGMENT_FAVOURITE = 1;
    private static final int FRAGMENT_CHANGE_PASS = 4;
    private int CurrentFragment = FRAGMENT_HOME;
    private DrawerLayout drawerLayout;
    private ImageView imgAvatar;
    private TextView tvName,tvEmail;
    SqlLogin sqlLogin;
    ArrayList<Student> arrayList;
    Student student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgAvatar = findViewById(R.id.img_avatar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        Intent intent = getIntent();

        /*arrayList = sqlLogin.getName();
                        student = arrayList.get(1);
                        String name =  student.getName();*/
        View hView = navigationView.getHeaderView(0);
        tvName = hView.findViewById(R.id.tv_name);
        tvName.setText(intent.getStringExtra("MSV"));
        String s = tvName.getText().toString();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            if (CurrentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                CurrentFragment = FRAGMENT_HOME;
            }
        }else if (id == R.id.nav_favourite){
            if (CurrentFragment != FRAGMENT_FAVOURITE){
                replaceFragment(new FavouriteFragment());
                CurrentFragment = FRAGMENT_FAVOURITE;
            }
        }else if (id == R.id.nav_history){
            if (CurrentFragment != FRAGMENT_HISTORY){
                replaceFragment(new HistoryFragment());
                CurrentFragment = FRAGMENT_HISTORY;
            }
        }else if (id == R.id.nav_change_password){
            if (CurrentFragment != FRAGMENT_CHANGE_PASS){
                replaceFragment(new Change_PassFragment());
                CurrentFragment = FRAGMENT_CHANGE_PASS;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}