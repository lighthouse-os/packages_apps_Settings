package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.SystemProperties;
import android.text.TextUtils;

import androidx.preference.Preference;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class LighthouseMaintainerPreferenceController extends BasePreferenceController {

    private static final String KEY_BUILD_MAINTAINER_URL = "ro.lighthouse.build.donate_url";

    private final PackageManager mPackageManager;

    private String mDeviceMaintainer;

    public LighthouseMaintainerPreferenceController(Context context, String key) {
        super(context, key);
        mDeviceMaintainer = SystemProperties.get("ro.lighthouse.build.maintainer");
        mPackageManager = mContext.getPackageManager();
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

    @Override
    public boolean handlePreferenceTreeClick(Preference preference) {
        String maintainerUrl = SystemProperties.get(KEY_BUILD_MAINTAINER_URL,
                mContext.getString(R.string.unknown));
        if (!TextUtils.equals(preference.getKey(), getPreferenceKey())) {
            return false;
        }

        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(maintainerUrl));
        mContext.startActivity(intent);
        return true;
    }
}