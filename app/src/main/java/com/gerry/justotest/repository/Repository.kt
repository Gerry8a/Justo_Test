package com.gerry.justotest.repository

import com.gerry.justotest.api.ApiResponseStatus
import com.gerry.justotest.api.ApiService
import com.gerry.justotest.api.dto.PersonDTOMapper
import com.gerry.justotest.api.makeNetworkCall
import com.gerry.justotest.model.Person
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getPerson(): ApiResponseStatus<List<Person>> = makeNetworkCall {
        val personApiresponse = apiService.getPerson()
        val personDTO = personApiresponse.results
        val personDTOMapper = PersonDTOMapper()
        personDTOMapper.fromPersonListDTOToPersonDomain(personDTO)
    }
}