package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.TextUtils;

import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class LighthouseMaintainerPreferenceController extends BasePreferenceController {

    private String mDeviceMaintainer;

    public LighthouseMaintainerPreferenceController(Context context, String key) {
        super(context, key);
        mDeviceMaintainer = mContext.getResources().getString(R.string.build_maintainer_summary);
    }

    @Override
    public int getAvailabilityStatus() {
        return !TextUtils.isEmpty(mDeviceMaintainer)
                ? AVAILABLE : CONDITIONALLY_UNAVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        return mDeviceMaintainer;
    }
}
