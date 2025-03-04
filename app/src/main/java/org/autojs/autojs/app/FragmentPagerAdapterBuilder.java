package org.autojs.autojs.app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stardust on 2017/3/24.
 */
public class FragmentPagerAdapterBuilder {

    public interface OnFragmentInstantiateListener {
        void OnInstantiate(int pos, Fragment fragment);
    }

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mTitles = new ArrayList<>();
    private final FragmentActivity mActivity;

    public FragmentPagerAdapterBuilder(FragmentActivity activity) {
        mActivity = activity;
    }

    public FragmentPagerAdapterBuilder add(Fragment fragment, String title) {
        mFragments.add(fragment);
        mTitles.add(title);
        return this;
    }

    public FragmentPagerAdapterBuilder add(Fragment fragment, int titleResId) {
        return add(fragment, mActivity.getString(titleResId));
    }

    public StoredFragmentPagerAdapter build() {
        return new StoredFragmentPagerAdapter(mActivity.getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTitles.get(position);
            }
        };
    }

    public abstract static class StoredFragmentPagerAdapter extends FragmentPagerAdapter {

        private final SparseArray<Fragment> mStoredFragments = new SparseArray<>();
        private OnFragmentInstantiateListener mOnFragmentInstantiateListener;

        public StoredFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            mStoredFragments.put(position, fragment);
            if(mOnFragmentInstantiateListener != null){
                mOnFragmentInstantiateListener.OnInstantiate(position, fragment);
            }
            return fragment;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            mStoredFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        public Fragment getStoredFragment(int position) {
            return mStoredFragments.get(position);
        }

        public void setOnFragmentInstantiateListener(OnFragmentInstantiateListener onFragmentInstantiateListener) {
            mOnFragmentInstantiateListener = onFragmentInstantiateListener;
        }
    }
}
