package com.android.settings.deviceinfo.firmwareversion;

import android.content.Context;
import android.os.SystemProperties;

import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class LighthouseBuildStatusPreferenceController extends BasePreferenceController {

    private static final String TAG = "LighthouseBuildStatusPreferenceCtrl";

    private static final String KEY_ROM_BUILD_TYPE_PROP = "ro.lighthouse.build.type";

    public LighthouseBuildStatusPreferenceController(Context context, String key) {
        super(context, key);
    }

    @Override
    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    @Override
    public CharSequence getSummary() {
        return SystemProperties.get(KEY_ROM_BUILD_TYPE_PROP,
                mContext.getString(R.string.device_info_default));
    }
}