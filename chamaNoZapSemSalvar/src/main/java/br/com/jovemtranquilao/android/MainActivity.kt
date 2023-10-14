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

    //TODO: descobrir o estado pela geolocalizacao do dispositivo e colocar automaticamente o DDD
    var telefone: String by remember { mutableStateOf("+55 (11) ") }

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
                var limpo = limpar(it)


                if(limpo.length == 0) {
                    telefone = it
                }

                else if(limpo.length == 1) {
                    telefone = limpo.replace(Regex("^(\\d)$")) {
                        "+${it.groupValues[1]}"
                    }
                }

                else if(limpo.length == 2) {
                    telefone = limpo.replace(Regex("^(\\d{2})$")) {
                        "+${it.groupValues[1]}"
                    }
                }

                else if(limpo.length == 3) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d)$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]})"
                    }
                }

                else if(limpo.length == 4) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]})"
                    }
                }

                else if(limpo.length == 5) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d)$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}"
                    }
                }

                else if(limpo.length == 6) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{2})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}"
                    }
                }

                else if(limpo.length == 7) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{3})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}"
                    }
                }

                else if(limpo.length == 8) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}"
                    }
                }

                else if(limpo.length == 9) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})(\\d)$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}-${it.groupValues[4]}"
                    }
                }

                else if(limpo.length == 10) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})(\\d{2})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}-${it.groupValues[4]}"
                    }
                }

                else if(limpo.length == 11) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})(\\d{3})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}-${it.groupValues[4]}"
                    }
                }

                else if(limpo.length == 12) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d{4})(\\d{4})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]}-${it.groupValues[4]}"
                    }
                }

                else if(limpo.length == 13) {
                    telefone = limpo.replace(Regex("^(\\d{2})(\\d{2})(\\d)(\\d{4})(\\d{4})$")) {
                        "+${it.groupValues[1]} (${it.groupValues[2]}) ${it.groupValues[3]} ${it.groupValues[4]}-${it.groupValues[5]}"
                    }
                }


            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.NumberPassword
            ),
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
                    putExtra("jid", limpar(telefone) + "@s.whatsapp.net")
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
                val urlString = "http://wa.me/" + limpar(telefone)
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

fun limpar(telefone: String): String {
    var retorno =  telefone.replace("+", "");
    retorno =  retorno.replace(" ", "")
    retorno =  retorno.replace("(", "")
    retorno =  retorno.replace(")", "")
    return retorno.replace("-", "");
}




