package pw.vodes.enodia

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pw.vodes.enodia.components.layout.ExpandableSettings
import pw.vodes.enodia.components.layout.MainScaffold
import pw.vodes.enodia.logic.source.sources
import pw.vodes.enodia.theme.AppTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun App() = AppTheme {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        var enabledSources by remember { mutableStateOf(sources.filter { it.getOptionBoolean("enabled") == true && it.isReady() }) }
        val scrollState = rememberScrollState()
        MainScaffold(Modifier.fillMaxSize(), title = "Enodia") {
            Column(Modifier.fillMaxSize().verticalScroll(scrollState), horizontalAlignment = Alignment.Start) {
                if (enabledSources.isEmpty()) {
                    Text("You don't have any sources enabled or properly configured.", Modifier.align(Alignment.CenterHorizontally))
                } else {
                    FlowColumn(Modifier.fillMaxWidth()) {
                        enabledSources.forEach {
                            ElevatedButton({}) {
                                Text(it.name)
                            }
                        }
                    }
                }
                Spacer(Modifier.weight(1f))
                Text("Source Configuration", Modifier.padding(7.dp), style = MaterialTheme.typography.titleLarge)
                sources.forEach { source ->
                    var expanded by remember { mutableStateOf(false) }
                    ExpandableSettings(source.name, expanded, {
                        enabledSources = sources.filter { it.getOptionBoolean("enabled") == true && it.isReady() }
                        expanded = !expanded
                    }) {
                        Column(Modifier.fillMaxWidth()) {
                            source.options.forEach {
                                it.OptionComponent()
                            }
                        }
                    }
                }
            }
        }
    }
}

internal expect fun openUrl(url: String?)