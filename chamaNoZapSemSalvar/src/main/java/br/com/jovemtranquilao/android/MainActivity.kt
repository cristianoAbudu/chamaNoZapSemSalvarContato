package br.com.jovemtranquilao.android

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview


//https://kotlinlang.org/docs/multiplatform-mobile-create-first-app.html#examine-the-project-structure
// Material components 3
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                GreetingView( this)
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    GreetingView(MainActivity())
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GreetingView(context: ComponentActivity) {

    var telefone: String by remember { mutableStateOf("") }

    Column(
    ) {
        val focusRequester = remember { FocusRequester() }

        var keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = telefone,
            label = { Text("Telefone") },
            onValueChange = {
                telefone = it.replace(Regex("^(\\d{2})(\\d{2})(\\d)(\\d{4})(\\d{4})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]} ${it.groupValues[4]}-${it.groupValues[5]}"
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        DisposableEffect(Unit) {
            focusRequester.requestFocus()
            keyboardController?.show()

            onDispose {
                focusRequester.freeFocus()
                keyboardController?.hide()
            }
        }

        Row(
        ) {
            Button(onClick = {
                val sendIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hello Swapz")
                    putExtra("jid", telefone + "@s.whatsapp.net")
                    type = "text/plain"
                    setPackage("com.whatsapp")
                }

                try {
                    context.startActivity(sendIntent)
                } catch (ex: ActivityNotFoundException) {
                    sendIntent.setPackage(null)
                    context.startActivity(sendIntent)
                }
            }) { Text("Whatsapp") }

            Button(onClick = {
                val urlString = "http://wa.me/" + telefone
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.android.chrome")

                try {
                    context.startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    // Chrome browser presumably not installed so allow user to choose instead
                    intent.setPackage(null)
                    context.startActivity(intent)
                }
            }) { Text("Crome") }
        }

    }


}




