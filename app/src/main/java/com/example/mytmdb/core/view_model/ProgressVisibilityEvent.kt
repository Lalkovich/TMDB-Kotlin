package com.example.mytmdb.core.view_model

import android.view.View

/**
 *  Created by Android Studio on 12/7/2020 11:19 AM
 *  Developer: Sergey Zorych
 */

enum class ProgressVisibilityEvent(val visibility: Int) {
    Show(View.VISIBLE),
    Hide(View.GONE)
}