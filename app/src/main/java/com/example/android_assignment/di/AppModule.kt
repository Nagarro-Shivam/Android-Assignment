package com.example.android_assignment.di

import android.content.Context
import android.os.Build
import com.example.android_assignment.BuildConfig
import com.example.android_assignment.data.remote.NewsService
import com.example.android_assignment.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideHTTPLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BASIC else HttpLoggingInterceptor.Level.NONE
        )
        return interceptor
    }

    @Provides
    fun provideNetworkInterceptor() : Interceptor {

        val interceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()

            val originalURL = chain.request().url
            val url = originalURL.newBuilder().addQueryParameter("api-key", BuildConfig.NEWS_API_ACCESS_KEY)
                .build()

            request.url(url)
            chain.proceed(request.build())
        }

        return interceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor, networkIntercept : Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkIntercept)
            .build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(@ApplicationContext context: Context, okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsAPI(retrofit: Retrofit) : NewsService{
        return retrofit.create(NewsService::class.java)
    }

}