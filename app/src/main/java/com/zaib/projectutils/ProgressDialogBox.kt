package com.zaib.projectutils

import android.content.Context
import com.zaib.views.R


class ProgressDialogBox {

    companion object {

         var progressDialog: android.app.Dialog? = null

         fun setProgressBar(context: Context)
         {

             if (progressDialog != null && progressDialog!!.isShowing) {
                 return
             }

             progressDialog = android.app.Dialog(context,android.R.style.TextAppearance_Theme_Dialog
             )
             progressDialog?.setCancelable(false)
             progressDialog?.show()
             progressDialog?.setContentView(R.layout.custom_progress_dialog)
         }

          fun dismissDialog() {
             if (progressDialog != null && progressDialog!!.isShowing)
             {
                 progressDialog!!.dismiss()
             }
         }
     }


}
