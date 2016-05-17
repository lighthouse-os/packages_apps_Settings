/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.dashboard;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.settings.InstrumentedFragment;
import com.android.settings.R;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.overlay.SupportFeatureProvider;
import com.android.settings.widget.SlidingTabLayout;
import com.android.settingslib.drawer.SettingsDrawerActivity;
import com.android.settingslib.HelpUtils;

/**
 * Container for Dashboard fragments.
 */
public final class DashboardContainerFragment extends InstrumentedFragment {

    private static final int INDEX_SUMMARY_FRAGMENT = 0;
    private static final int INDEX_SUPPORT_FRAGMENT = 1;

    private ViewPager mViewPager;
    private View mHeaderView;
    private DashboardViewPagerAdapter mPagerAdapter;

    @Override
    protected int getMetricsCategory() {
        return DASHBOARD_CONTAINER;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        final View content = inflater.inflate(R.layout.dashboard_container, parent, false);
        mViewPager = (ViewPager) content.findViewById(R.id.pager);
        mPagerAdapter = new DashboardViewPagerAdapter(getContext(), getChildFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mHeaderView = inflater.inflate(R.layout.dashboard_container_header, parent, false);
        ((SlidingTabLayout) mHeaderView).setViewPager(mViewPager);
        return content;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPagerAdapter.getCount() > 1) {
            final Activity activity = getActivity();
            if (activity instanceof SettingsDrawerActivity) {
                ((SettingsDrawerActivity) getActivity()).setContentHeaderView(mHeaderView);
            }
        }
    }

    private static final class DashboardViewPagerAdapter extends FragmentPagerAdapter {

        private final Context mContext;
        private final SupportFeatureProvider mSupportFeatureProvider;

        public DashboardViewPagerAdapter(Context context, FragmentManager fragmentManager) {
            super(fragmentManager);
            mContext = context;
            mSupportFeatureProvider =
                    FeatureFactory.getFactory(context).getSupportFeatureProvider(context);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case INDEX_SUMMARY_FRAGMENT:
                    return mContext.getString(R.string.page_tab_title_summary);
                case INDEX_SUPPORT_FRAGMENT:
                    return mContext.getString(R.string.page_tab_title_support);
            }
            return super.getPageTitle(position);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case INDEX_SUMMARY_FRAGMENT:
                    return new DashboardSummary();
                case INDEX_SUPPORT_FRAGMENT:
                    return new SupportFragment();
                default:
                    throw new IllegalArgumentException(
                            String.format(
                                    "Position %d does not map to a valid dashboard fragment",
                                    position));
            }
        }

        @Override
        public int getCount() {
            return mSupportFeatureProvider == null ? 1 : 2;
        }
    }
}
