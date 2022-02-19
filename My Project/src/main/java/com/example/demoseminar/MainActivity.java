package com.example.demoseminar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final int MY_REQUEST_CODE = 10;

    private static final int FRAGMENT_HOME = 110;
    private static final int FRAGMENT_LIST_CLASS = 1;
    private static final int FRAGMENT_CLASS_TEST = 2;
    private static final int FRAGMENT_CHAT = 3;
    private static final int FRAGMENT_ABOUT = 4;
    private static final int FRAGMENT_MY_PROFLIE = 5;
    private static final int FRAGMENT_CHANGE_PASSWORD = 6;

    private ImageView imageViewUser;
    TextView tvUserName,tvEmail;



    private MyProfileActivity myProfileActivity;
    private NavigationView mNavigationView;
    private TabLayout mTabLayoutHome;
    private int mCurentFragment = FRAGMENT_HOME;
    private ViewPager2 mViewPagerMain;
    private  DrawerLayout mDrawerLayout ;
    private BottomNavigationView bottomNavigationView;
    private ViewPagerMainAdapter viewPagerMainAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Anhxa();


        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);


        bottomNavigationView = findViewById(R.id.bottom_nav_main);
        mViewPagerMain = findViewById(R.id.view_pager_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,
                R.string.nav_drawer_open,R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewPagerMainAdapter = new ViewPagerMainAdapter(this);
        mViewPagerMain.setAdapter(viewPagerMainAdapter);

        mNavigationView.setNavigationItemSelectedListener(this);

        //replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);

        showUserInformation();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id == R.id.bottom_home){
                    openHomeFragmet();
                   //navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                } else if(id == R.id.bottom_list_class){
                   openListClassFragmet();
                    //navigationView.getMenu().findItem(R.id.nav_list_class).setChecked(true);
                }else if(id == R.id.bottom_class_test){
                    openClassTestFragmet();
                    //navigationView.getMenu().findItem(R.id.nav_class_test).setChecked(true);
                }else if(id == R.id.bottom_chat){
                    openChatFragmet();
                    //navigationView.getMenu().findItem(R.id.nav_chat).setChecked(true);
                }else if(id == R.id.bottom_about){
                    openAboutFragmet();
                   //navigationView.getMenu().findItem(R.id.nav_about).setChecked(true);
                }
                return false;
            }
        });
        mViewPagerMain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){

                    case 0:
                        mCurentFragment = FRAGMENT_HOME;
                        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;

                    case 1:
                        mCurentFragment = FRAGMENT_LIST_CLASS;
                        mNavigationView.getMenu().findItem(R.id.nav_list_class).setChecked(true);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_list_class).setChecked(true);
                        break;

                    case 2:
                        mCurentFragment = FRAGMENT_CLASS_TEST;
                        mNavigationView.getMenu().findItem(R.id.nav_class_test).setChecked(true);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_class_test).setChecked(true);
                        break;

                    case 3:
                        mCurentFragment = FRAGMENT_CHAT;
                        mNavigationView.getMenu().findItem(R.id.nav_chat).setChecked(true);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_chat).setChecked(true);
                        break;

                    case 4:
                        mCurentFragment = FRAGMENT_ABOUT;
                        mNavigationView.getMenu().findItem(R.id.nav_about).setChecked(true);
                        bottomNavigationView.getMenu().findItem(R.id.bottom_about).setChecked(true);
                        break;

                }
            }
        });
    }



    private void Anhxa() {
        mNavigationView=findViewById(R.id.navgation_main);
        tvEmail = mNavigationView.getHeaderView(0).findViewById(R.id.tv_name_email_avatar);
        tvUserName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_name_avatar);
        imageViewUser = mNavigationView.getHeaderView(0).findViewById(R.id.image_avatar);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            //if(mCurentFragment != FRAGMENT_HOME){
                openHomeFragmet();
                //bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                //replaceFragment(new HomeFragment());
              // mCurentFragment = FRAGMENT_HOME;
            //}
        } else  if(id == R.id.nav_list_class){
            //if(mCurentFragment != FRAGMENT_LIST_CLASS){
                openListClassFragmet();
                //bottomNavigationView.getMenu().findItem(R.id.bottom_list_class).setChecked(true);
                //replaceFragment(new ListClassFragment());
                //mCurentFragment = FRAGMENT_LIST_CLASS;
           // }
        }else  if(id == R.id.nav_class_test){
            //if(mCurentFragment != FRAGMENT_CLASS_TEST){
                openClassTestFragmet();
                //bottomNavigationView.getMenu().findItem(R.id.bottom_class_test).setChecked(true);
                //replaceFragment(new ClassTestFragment());
                //mCurentFragment = FRAGMENT_HOME;
           // }
        }else  if(id == R.id.nav_chat){
           // if(mCurentFragment != FRAGMENT_CHAT){
                openChatFragmet();
                //bottomNavigationView.getMenu().findItem(R.id.bottom_chat).setChecked(true);
                //replaceFragment(new ChatFragment());
                //mCurentFragment = FRAGMENT_CHAT;
            //}
        }else  if(id == R.id.nav_about){
            //if(mCurentFragment != FRAGMENT_ABOUT){
                openAboutFragmet();
                //bottomNavigationView.getMenu().findItem(R.id.bottom_about).setChecked(true);
                //replaceFragment(new AboutFragment());
                //mCurentFragment = FRAGMENT_HOME;
           // }
        }else  if(id == R.id.nav_my_profile){
            //if(mCurentFragment != FRAGMENT_ABOUT){
            //openAboutFragmet();
            //bottomNavigationView.getMenu().findItem(R.id.bottom_about).setChecked(true);
                Intent intent = new Intent(this,MyProfileActivity.class);
                startActivity(intent);
            //mCurentFragment = FRAGMENT_HOME;

            // }
        }else  if(id == R.id.nav_change_password){
            //if(mCurentFragment != FRAGMENT_ABOUT){
            //openAboutFragmet();
            Intent intent = new Intent(this,ChangePasswordActivity.class);
            startActivity(intent);
            //bottomNavigationView.getMenu().findItem(R.id.bottom_about).setChecked(true);

            //mCurentFragment = FRAGMENT_HOME;
            // }
        }else if(id==R.id.nav_sign_out){
            FirebaseAuth.getInstance().signOut();
            Intent intent =new Intent(this,SigninActivity.class);
            startActivity(intent);
            finish();
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else
            super.onBackPressed();
    }




    private void openHomeFragmet(){
            if(mCurentFragment != FRAGMENT_HOME){
                mViewPagerMain.setCurrentItem(0);
                //replaceFragment(new HomeFragment());
                mCurentFragment = FRAGMENT_HOME;
            }
    }

    private void openListClassFragmet(){
        if(mCurentFragment != FRAGMENT_LIST_CLASS){
            mViewPagerMain.setCurrentItem(1);
            //replaceFragment(new HomeFragment());
            mCurentFragment = FRAGMENT_LIST_CLASS;
        }
    }

    private void openClassTestFragmet(){
        if(mCurentFragment != FRAGMENT_CLASS_TEST){
            mViewPagerMain.setCurrentItem(2);
            //replaceFragment(new HomeFragment());
            mCurentFragment = FRAGMENT_CLASS_TEST;
        }
    }

    private void openChatFragmet(){
        if(mCurentFragment != FRAGMENT_CHAT){
            mViewPagerMain.setCurrentItem(3);
            //replaceFragment(new HomeFragment());
            mCurentFragment = FRAGMENT_CHAT;
        }
    }

    private void openAboutFragmet(){
        if(mCurentFragment != FRAGMENT_ABOUT){
            mViewPagerMain.setCurrentItem(4);
            //replaceFragment(new HomeFragment());
            mCurentFragment = FRAGMENT_ABOUT;
        }
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction =getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.view_pager_main,fragment);
        fragmentTransaction.commit();
    }

    public void showUserInformation(){
        FirebaseUser user  = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            return;
        } else {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUri = user.getPhotoUrl();

            if(name == null){
                tvUserName.setVisibility(View.GONE);
            }else{
                tvUserName.setVisibility(View.VISIBLE);
                tvUserName.setText(name);
            }


            tvEmail.setText(email);

            Glide.with(this).load(photoUri).error(R.drawable.ic_launcher_background).into(imageViewUser);
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == MY_REQUEST_CODE){
//            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                openGallery();
//            }else{
//
//            }
//        }
//    }
//
//    public void openGallery() {
//
//    }
}