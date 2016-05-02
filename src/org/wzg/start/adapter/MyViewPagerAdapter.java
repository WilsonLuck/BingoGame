package org.wzg.start.adapter;

import org.wzg.activity.PlayerActivity;
import org.wzg.player.fragment.ViewPagerFragmentOne;
import org.wzg.player.fragment.ViewPagerFragmentThree;
import org.wzg.player.fragment.ViewPagerFragmentTwo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

	private  int PAGER_COUNT ;
	private ViewPagerFragmentOne mViewPagerFragmentOne = null;
	private ViewPagerFragmentTwo mViewPagerFragmentTwo = null;
	private ViewPagerFragmentThree mViewPagerFragmentThree = null;
	private PlayerActivity playerActivity;
    
	//这里后来传了activity
	public MyViewPagerAdapter(FragmentManager fm, PlayerActivity playerActivity) {
		super(fm);

		this.playerActivity = playerActivity;
		mViewPagerFragmentOne = new ViewPagerFragmentOne();
		mViewPagerFragmentTwo = new ViewPagerFragmentTwo();
		mViewPagerFragmentThree = new ViewPagerFragmentThree();
	}

	@Override
	public Object instantiateItem(ViewGroup vg, int position) {
		return super.instantiateItem(vg, position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		System.out.println("position Destory" + position);
		super.destroyItem(container, position, object);
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		switch (position) {
		case PlayerActivity.PAGE_ONE:
			fragment = mViewPagerFragmentOne;
			break;
		case PlayerActivity.PAGE_TWO:
			fragment = mViewPagerFragmentTwo; 
			break;
		case PlayerActivity.PAGE_THREE:
			fragment = mViewPagerFragmentThree;
			break;

		}
		return fragment;
	}

	@Override
	public int getCount() {
		PAGER_COUNT = playerActivity.cardcount;
		return PAGER_COUNT;
	}

	public boolean isAward() {
		return mViewPagerFragmentOne.isAward();
	}
	
}
