package dev.yellowhatpro.branchin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dev.yellowhatpro.branchin.data.repo.ApiRepository
import dev.yellowhatpro.branchin.data.repo.ApiRepositoryImpl

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ApiRepositoryModule {
    @Binds
    abstract fun bindsApiRepository(repository: ApiRepositoryImpl) : ApiRepository
}