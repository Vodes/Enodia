package pw.vodes.enodia.logic.source.sources

import pw.vodes.enodia.logic.source.ASource
import pw.vodes.enodia.logic.source.SourceOptionBool
import pw.vodes.enodia.logic.source.SourceOptionStr
import pw.vodes.enodia.logic.types.Series
import pw.vodes.enodia.logic.types.SeriesEntry

class KavitaSource : ASource("Kavita") {

    init {
        options.apply {
            add(SourceOptionBool("kavita", "enabled", false, displayName = "Enabled"))
            add(SourceOptionStr("kavita", "baseURL", displayName = "Instance URL"))
            add(SourceOptionStr("kavita", "apiKey", displayName = "API Key", hideValue = true))
        }
    }

    override fun isReady(): Boolean {
        return !getOptionString("baseURL").isNullOrBlank() && !getOptionString("apiKey").isNullOrBlank()
    }

    override fun search(search: String): List<Series> {
        return emptyList()
    }

    override fun listAll(): List<Series> {
        return emptyList()
    }

    override fun getEntries(series: Series): List<SeriesEntry> {
        return emptyList()
    }
}