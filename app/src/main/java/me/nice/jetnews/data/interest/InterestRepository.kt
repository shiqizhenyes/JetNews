package me.nice.jetnews.data.interest

import kotlinx.coroutines.flow.Flow
import me.nice.jetnews.data.Result
import me.nice.jetnews.model.Publication

typealias TopicsMap = Map<String, List<String>>

interface InterestRepository {

    suspend fun getTopics(): Result<TopicsMap>

    suspend fun getPeople(): Result<List<String>>

    suspend fun getPublications(): Result<List<String>>

    suspend fun toggleTopicSelection(topic: TopicSelection)

    suspend fun togglePersonSelected(person: String)

    suspend fun togglePublicationSelected(publication: String)

    fun observeTopicsSelected(): Flow<Set<TopicSelection>>

    fun observePeopleSelected(): Flow<Set<String>>

    fun observePublicationSelected(): Flow<Set<String>>

}

data class TopicSelection(val section: String, val topic: String)