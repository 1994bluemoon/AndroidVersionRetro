package vinova.henry.com.androidversionretro.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_android_version.*
import vinova.henry.com.androidversionretro.R
import vinova.henry.com.androidversionretro.model.AndroidVersion
import vinova.henry.com.androidversionretro.retro.ApiService
import vinova.henry.com.androidversionretro.retro.Repository

class AndroidVersionActivity : AppCompatActivity() {

    var mAdapter: AndroidVersionAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_version)

        initRecyclerView()
        requestAndroidVersion()
    }

    // khởi tao recyclerview
    private fun initRecyclerView() {
        rv_android_list.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rv_android_list.layoutManager = layoutManager
    }

    // request data tu server
    private fun requestAndroidVersion() {
        Repository.createService(ApiService::class.java).getAndroidVersion()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        //cú pháp của rxjava trong kotlin
                        { result ->
                            //request thành công
                            handleSuccessAndroidVersion(result)
                        },
                        { error ->
                            //request thất bai
                            handlerErrorAndroidVersion(error)
                        }
                )
    }

    //Xử lí dữ liệu khi request thành công
    private fun handleSuccessAndroidVersion(result: List<AndroidVersion>) {
        mAdapter = AndroidVersionAdapter(this, result)
        rv_android_list.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()
    }

    //Xử lí dữ l request thất bại
    private fun handlerErrorAndroidVersion(error: Throwable) {
        Log.e("get server's data", "handlerErrorAndroidVersion: ${error.localizedMessage}")
        Toast.makeText(this, "Error ${error.localizedMessage}", Toast.LENGTH_SHORT).show()
    }
}
