package vinova.henry.com.androidversionretro.retro

import io.reactivex.Observable
import retrofit2.http.GET
import vinova.henry.com.androidversionretro.model.AndroidVersion

interface ApiService {
    @GET("android/jsonarray/")
    fun getAndroidVersion(): Observable<List<AndroidVersion>>
}