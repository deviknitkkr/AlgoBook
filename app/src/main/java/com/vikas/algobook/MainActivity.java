package com.vikas.algobook;

import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import com.vikas.algobook.Adapters.*;
import android.support.v4.app.*;
import com.vikas.algobook.Fragments.*;

public class MainActivity extends AppCompatActivity {
	
	private ViewPagerAdapter mViewPagerAdapter;
    private ViewPager mViewPager;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
		addPagesInViewPager();
		
		mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mViewPagerAdapter);
		mViewPager.setOffscreenPageLimit(3);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
      
    }
	
	private void addPagesInViewPager()
	{
		addFragmentForCategory("Algorithms", "https://iq.opengenus.org/tag/algorithm/");
		addFragmentForCategory("Data-Structure", "https://iq.opengenus.org/tag/data-structure/");
		addFragmentForCategory("Machine-Learning", "https://iq.opengenus.org/tag/machine-learning/");
	}
	
    private void addFragmentForCategory(String tabTitle,String baseUrl)
	{
		Fragment f=new PageFragment();
		Bundle bundle=new Bundle();
		bundle.putString("url",baseUrl);
		f.setArguments(bundle);
		mViewPagerAdapter.addFragment(f,tabTitle);
	}
}
