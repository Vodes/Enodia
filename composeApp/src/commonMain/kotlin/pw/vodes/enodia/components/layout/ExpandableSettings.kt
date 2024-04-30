package pw.vodes.enodia.components.layout

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pw.vodes.enodia.components.Toggles.settingsContainer
import pw.vodes.enodia.components.buttons.ExpandIconButton

@Composable
fun ExpandableSettings(
    title: String,
    isExpanded: Boolean,
    onExpandClick: () -> Unit,
    withContainer: Boolean = true,
    content: (@Composable ColumnScope.() -> Unit)
) {
    ElevatedCard(onExpandClick, Modifier.padding(5.dp)) {
        Row(Modifier.fillMaxWidth().padding(3.dp)) {
            Text(title, modifier = Modifier.padding(5.dp).weight(1f), style = MaterialTheme.typography.headlineSmall)
            ExpandIconButton(isExpanded = isExpanded, onClick = onExpandClick)
        }
        AnimatedVisibility(isExpanded) {
            if (withContainer) {
                Column(Modifier.settingsContainer()) {
                    content()
                }
            } else {
                Column(Modifier.fillMaxWidth()) {
                    content()
                }
            }
        }
    }
}