package pw.vodes.enodia.logic.source

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.russhwolf.settings.get
import pw.vodes.enodia.components.Toggles
import pw.vodes.enodia.logic.settings
import pw.vodes.enodia.theme.AppShapes

abstract class SourceOption(sourceName: String, val optionName: String) {
    val fullOptionName = "$sourceName-$optionName"

    @Composable
    abstract fun OptionComponent()
}

class SourceOptionBool(
    sourceName: String,
    optionName: String,
    private val defaultValue: Boolean,
    private val displayName: String? = null,
    private val description: String? = null
) :
    SourceOption(sourceName, optionName) {
    var value: Boolean
        get() = settings[fullOptionName, defaultValue]
        set(value) = settings.putBoolean(fullOptionName, value)

    @Composable
    override fun OptionComponent() {
        Toggles.ContainerSwitch(displayName ?: optionName, description, value = value) {
            value = it
        }
    }
}

class SourceOptionStr(
    sourceName: String,
    optionName: String,
    private val defaultValue: String = "",
    private val hideValue: Boolean = false,
    private val displayName: String? = null,
    private val description: String? = null,
    private val multiLine: Boolean = false
) :
    SourceOption(sourceName, optionName) {
    var value: String
        get() = settings[fullOptionName, defaultValue]
        set(value) = settings.putString(fullOptionName, value)

    @Composable
    override fun OptionComponent() {
        var currentValue by remember { mutableStateOf(value) }
        Column(
            Modifier.fillMaxWidth().padding(8.dp, 4.dp).clip(AppShapes.large).background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
        ) {
            OutlinedTextField(
                currentValue, { currentValue = it.also { value = it } },
                Modifier.fillMaxWidth().padding(12.dp, 7.dp, 12.dp, 0.dp).clip(AppShapes.medium),
                label = { Text(displayName ?: optionName) },
                supportingText = {
                    if (description != null)
                        Text(description)
                }, singleLine = !multiLine,
                visualTransformation = if (hideValue) PasswordVisualTransformation() else VisualTransformation.None,
                keyboardOptions = if (hideValue) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions.Default
            )
        }
    }
}