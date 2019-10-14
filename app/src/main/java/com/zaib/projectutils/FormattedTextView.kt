package com.zaib.projectutils

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class FormattedTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr)
{


    fun getDayFromEpochDate(epochTime: Long)
    {

        val text = ProjectConstants.dayStringFormat(epochTime)
        this.text = text

    }



}