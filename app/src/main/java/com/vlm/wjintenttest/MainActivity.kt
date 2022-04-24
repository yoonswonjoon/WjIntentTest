package com.vlm.wjintenttest

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vlm.wjintenttest.ui.theme.AActivity
import com.vlm.wjintenttest.ui.theme.WjIntentTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WjIntentTestTheme {
                // A surface container using the 'background' color from the theme

                val (text, changed) = remember {
                    mutableStateOf("")
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column() {
                        Greeting("메일 보내기 기능 할 사람(Text전달)"){
                            val intent =  Intent().apply {
                                action = Intent.ACTION_SEND
                                type = "text/plain"
                                putExtra(Intent.EXTRA_TEXT, text)
                                putExtra(Intent.EXTRA_EMAIL, "doojoons@naver.com")
                            }
                            startActivity(intent)
                        }

                        Greeting("이 어플(app1)의 A_Activity 켜자"){
                            val intent =  Intent(
                                applicationContext,
                                AActivity::class.java
                            )

                            startActivity(intent)
                        }

                        Greeting("다른 어플(App2)의 B_액티비티 켜죠(Text 전달)"){
                            val intent =  Intent().apply {
                                action = "WJ_INTENT_TEST"
                                putExtra("MY_NAME", text)
                            }
                            startActivity(intent)
                        }

                        Greeting("B에게 팬딩 인텐트 전달"){
                            val intent =  Intent(
                                applicationContext,
                                AActivity::class.java
                            )

                            val pendingIntent = PendingIntent.getActivity(
                                applicationContext,
                                0,
                                intent,
                                0
                            )

                            val passing = Intent().apply {
                                action = "WJ_INTENT_TEST"
                                putExtra("Intent", pendingIntent)
                            }
                            startActivity(passing)
                        }


                        Greeting("B에게 그냥 인텐트 전달"){
                            val intent =  Intent(
                                applicationContext,
                                AActivity::class.java
                            )

                            val passing = Intent().apply {
                                action = "WJ_INTENT_TEST"
                                putExtra("Intent", intent)
                            }
                            startActivity(passing)
                        }
                        TextInput(text,changed)

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String,onClick : ()->Unit) {
    Row(modifier= Modifier.padding(10.dp)) {
        Button(onClick = { onClick() }, ) {
            Text(text = "Hello $name!")
        }
    }
}

@Composable
fun TextInput(input : String, onChanged: (String)-> Unit){
    Row(modifier= Modifier.padding(10.dp)) {
        TextField(value = input, onValueChange = onChanged )
    }
}
