package ru.punkoff.translator.main.utils

import ru.punkoff.model.AppState
import ru.punkoff.model.Meanings
import ru.punkoff.model.DataModel
import ru.punkoff.translator.main.room.HistoryEntity

fun parseOnlineSearchResults(state: ru.punkoff.model.AppState): ru.punkoff.model.AppState {
    return ru.punkoff.model.AppState.Success(mapResult(state, true))
}

fun parseLocalSearchResults(data: ru.punkoff.model.AppState): ru.punkoff.model.AppState {
    return ru.punkoff.model.AppState.Success(mapResult(data, false))
}

private fun mapResult(
    data: ru.punkoff.model.AppState,
    isOnline: Boolean
): List<ru.punkoff.model.DataModel> {
    val newSearchResults = arrayListOf<ru.punkoff.model.DataModel>()
    when (data) {
        is ru.punkoff.model.AppState.Success -> {
            getSuccessResultData(data, isOnline, newSearchResults)
        }
    }
    return newSearchResults
}

private fun getSuccessResultData(
    data: ru.punkoff.model.AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<ru.punkoff.model.DataModel>
) {
    val dataModels: List<ru.punkoff.model.DataModel> = data.data as List<ru.punkoff.model.DataModel>
    if (dataModels.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in dataModels) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in dataModels) {
                newDataModels.add(ru.punkoff.model.DataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(dataModel: ru.punkoff.model.DataModel, newDataModels: ArrayList<ru.punkoff.model.DataModel>) {
    if (!dataModel.text.isNullOrBlank() && !dataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<ru.punkoff.model.Meanings>()
        for (meaning in dataModel.meanings!!) {
            if (meaning.translation != null && !meaning.translation!!.translation.isNullOrBlank()) {
                newMeanings.add(ru.punkoff.model.Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(ru.punkoff.model.DataModel(dataModel.text, newMeanings))
        }
    }
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<ru.punkoff.model.DataModel> {
    val searchResult = ArrayList<ru.punkoff.model.DataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(ru.punkoff.model.DataModel(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: ru.punkoff.model.AppState): HistoryEntity? {
    return when (appState) {
        is ru.punkoff.model.AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}


fun convertMeaningsToString(meanings: List<ru.punkoff.model.Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}
