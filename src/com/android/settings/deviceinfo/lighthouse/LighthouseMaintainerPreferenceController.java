/*
 * Copyright (C) 2021 The Project Lighthouse
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

package com.android.settings.deviceinfo.lighthouse;
import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.R;
import com.android.settings.core.BasePreferenceController;

public class LighthouseMaintainerPreferenceController extends BasePreferenceController {

    private static final String TAG = "LighthouseMaintainerPreferenceController";

    public LighthouseMaintainerPreferenceController(Context context, String key) {
        super(context, key);
    }

    public int getAvailabilityStatus() {
        return AVAILABLE;
    }

    public CharSequence getSummary() {
        String maintainer = mContext.getResources().getString(R.string.lighthouse_maintainer);
        return maintainer;
    }
}
