import androidx.compose.ui.window.ComposeUIViewController
import pw.vodes.enodia.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
