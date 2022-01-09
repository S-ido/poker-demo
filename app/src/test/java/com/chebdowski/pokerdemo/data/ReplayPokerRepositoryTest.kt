package com.chebdowski.pokerdemo.data

import com.chebdowski.pokerdemo.data.model.*
import com.chebdowski.pokerdemo.domain.Failure
import com.chebdowski.pokerdemo.domain.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReplayPokerRepositoryTest {

    private val rings = Rings(
        0, "", "", 0, "", Blinds(0, 0), Brings(0, 0), closed = false, fast = false,
        listOf(), "", listOf(), false, 0, 0, 0, 0, listOf(), listOf(), "",
        "", 0, Game("", "", "")
    )
    private val ringsResponse = RingsResponse(listOf(rings, rings))
    private val mockRequest: Request = Request.Builder().url("https://example.com").build()
    private val successResponse: Response<RingsResponse> = Response.success(
        ringsResponse, okhttp3.Response.Builder()
            .request(mockRequest)
            .protocol(Protocol.HTTP_2)
            .code(200)
            .message("Response.success()")
            .receivedResponseAtMillis(1619053449513)
            .sentRequestAtMillis(1619053443814)
            .build()
    )

    @Mock
    private lateinit var replayPokerApi: ReplayPokerApi

    @Test
    fun `getRings should call proper methods from Api`() = runTest {
        `when`(replayPokerApi.rings()).thenReturn(successResponse)
        val pokerRepository = ReplayPokerRepository(replayPokerApi)

        pokerRepository.getRings()

        verify(replayPokerApi).rings()
        verifyNoMoreInteractions(replayPokerApi)
    }

    @Test
    fun `getRings should return successful Result when api call is successful`() = runTest {
        `when`(replayPokerApi.rings()).thenReturn(successResponse)
        val pokerRepository = ReplayPokerRepository(replayPokerApi)

        val result = pokerRepository.getRings()

        assertTrue(result is Result.Success)
        result.fold(
            success = { ringsList -> assertEquals(ringsResponse.rings.size, ringsList.size) },
            error = {},
            loading = {}
        )
    }

    @Test
    fun `getRings should return null body when api call is not successful`() = runTest {
        `when`(replayPokerApi.rings()).thenReturn(errorResponse(400))
        val pokerRepository = ReplayPokerRepository(replayPokerApi)

        val result = pokerRepository.getRings()

        assertTrue(result is Result.Error)
        result.fold(
            success = { ringsList -> assertNull(ringsList) },
            error = {},
            loading = {}
        )
    }

    @Test
    fun `getRings should return correct Failure when api call is not successful`() = runTest {
        `when`(replayPokerApi.rings()).thenReturn(errorResponse(408))
        val pokerRepository = ReplayPokerRepository(replayPokerApi)

        val result = pokerRepository.getRings()
        assertTrue(result is Result.Error)
        result.fold(
            success = {},
            error = { failure -> assertEquals(Failure.Http.RequestTimeout, failure) },
            loading = {}
        )
    }

    private fun errorResponse(errorCode: Int): Response<RingsResponse> {
        val errorResponseJson = "{\n" + "  \"type\": \"error\",\n" +
                "  \"message\": \"Error response message\"\n" + "}"
        val errorResponseBody = errorResponseJson.toResponseBody("application/json".toMediaTypeOrNull())
        return Response.error(errorCode, errorResponseBody)
    }
}