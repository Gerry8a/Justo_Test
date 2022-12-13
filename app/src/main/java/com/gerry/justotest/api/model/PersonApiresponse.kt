package com.gerry.justotest.api.model

import com.gerry.justotest.api.dto.PersonDTO

data class PersonApiresponse(
    val info: Info,
    val results: List<PersonDTO>
)