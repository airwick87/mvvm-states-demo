package com.eric.domain.usecases

import com.eric.domain.shared.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class GeneralUseCase<PARAMS : RequestBodyParam, RESULT>() {

    //TODO: Allow multiple types of dispatchers!
    suspend fun execute(
        param: PARAMS,
    ): Flow<ResultResponse<RESULT>> {
        return buildFlow(param)
            .flowOn(Dispatchers.IO)
    }

    protected abstract suspend fun buildFlow(
        param: PARAMS
    ): Flow<ResultResponse<RESULT>>
}


abstract class RequestBodyParam