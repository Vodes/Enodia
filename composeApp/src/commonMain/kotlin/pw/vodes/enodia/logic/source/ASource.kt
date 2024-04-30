package pw.vodes.enodia.logic.source

import pw.vodes.enodia.logic.source.sources.KavitaSource
import pw.vodes.enodia.logic.types.Series
import pw.vodes.enodia.logic.types.SeriesEntry

abstract class ASource(val name: String) {
    val options = mutableListOf<SourceOption>()

    fun getOptionString(name: String): String? {
        return options.find { it.optionName.equals(name, true) && it is SourceOptionStr }?.let { (it as SourceOptionStr).value }
    }

    fun getOptionBoolean(name: String): Boolean? {
        return options.find { it.optionName.equals(name, true) && it is SourceOptionBool }?.let { (it as SourceOptionBool).value }
    }

    fun setOptionValue(name: String, value: String) {
        val option = options.find { it.optionName.equals(name, true) && it is SourceOptionStr }
        if (option != null) {
            (option as SourceOptionStr).value = value
        } else {
            throw Exception("Option $name not found")
        }
    }

    fun setOptionValue(name: String, value: Boolean) {
        val option = options.find { it.optionName.equals(name, true) && it is SourceOptionBool }
        if (option != null) {
            (option as SourceOptionBool).value = value
        } else {
            throw Exception("Option $name not found")
        }
    }

    open fun isReady(): Boolean = true

    abstract fun search(search: String): List<Series>
    abstract fun listAll(): List<Series>
    abstract fun getEntries(series: Series): List<SeriesEntry>
}

val sources = listOf(KavitaSource())