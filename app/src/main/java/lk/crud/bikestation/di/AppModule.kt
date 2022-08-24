package lk.crud.bikestation.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import lk.crud.bikestation.common.Constants
import lk.crud.bikestation.data.remote.BikeStationApi
import lk.crud.bikestation.data.remote.mapper.*
import lk.crud.bikestation.data.repository.BikeRepositoryImpl
import lk.crud.bikestation.domain.repository.BikeRepository
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideBikeStationApi(): BikeStationApi {

        class LogJsonInterceptor : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val request: Request = chain.request()
                val response: Response = chain.proceed(request)
                val rawJson: String? = response.body?.string()
                Log.d(
                    "BIKE_STATION_APP",
                    String.format("raw JSON response is: %s", rawJson)
                )

                // Re-create the response before returning it because body can be read only once
                return response.newBuilder()
                    .body(ResponseBody.create(response.body?.contentType(), rawJson.toString()))
                    .build()
            }
        }

        val client = OkHttpClient.Builder()

        client.connectTimeout(30, TimeUnit.SECONDS)
        client.writeTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.interceptors().add(LogJsonInterceptor())

        client.addInterceptor { chain ->
            val requestBuilder =
                chain.request().newBuilder().header("Authorization", "")
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
            .create(BikeStationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBikeStationRepository(
        api: BikeStationApi,
        bikeStationMapper: BikeStationMapper
    ): BikeRepository {
        return BikeRepositoryImpl(api, bikeStationMapper)
    }

    @Provides
    @Singleton
    fun provideBikeStationMapper(
        crsMapper: CrsMapper,
        featureMapper: FeatureMapper
    ): BikeStationMapper {
        return BikeStationMapper(crsMapper, featureMapper)
    }

    @Provides
    @Singleton
    fun provideCrsMapper(crsPropertyMapper: CrsPropertyMapper): CrsMapper {
        return CrsMapper(crsPropertyMapper)
    }

    @Provides
    @Singleton
    fun provideCrsPropertyMapper(): CrsPropertyMapper {
        return CrsPropertyMapper()
    }

    @Provides
    @Singleton
    fun provideFeatureMapper(
        geometryMapper: GeometryMapper,
        propertiesMapper: PropertiesMapper
    ): FeatureMapper {
        return FeatureMapper(geometryMapper, propertiesMapper)
    }

    @Provides
    @Singleton
    fun provideGeometryMapper(): GeometryMapper {
        return GeometryMapper()
    }

    @Provides
    @Singleton
    fun providePropertiesMapper(): PropertiesMapper {
        return PropertiesMapper()
    }

}