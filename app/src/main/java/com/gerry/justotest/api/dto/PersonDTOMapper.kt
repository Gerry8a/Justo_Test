package com.gerry.justotest.api.dto

import com.gerry.justotest.model.Person


class PersonDTOMapper {

    private fun fromPersonDTOToPersonDomain(personDTO: PersonDTO): Person {
        return Person(
            personDTO.gender, personDTO.name, personDTO.location, personDTO.email, personDTO.login,
            personDTO.dob, personDTO.registered, personDTO.phone, personDTO.cell, personDTO.id, personDTO.picture,
            personDTO.nat
        )
    }

    fun fromPersonListDTOToPersonDomain(personDTOList: List<PersonDTO>): List<Person>{
        return personDTOList.map { fromPersonDTOToPersonDomain(it) }
    }
}