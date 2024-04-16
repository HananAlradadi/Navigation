package com.example.nav

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.nav.ui.theme.NavTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
          Surface (){
              val navController = rememberNavController()

              NavHost(navController = navController , startDestination = "ui1/{userId}" ){
                  composable("ui1/{userId}",
                      arguments = listOf(navArgument("userId") { type = NavType.StringType })){
                      backStackEntry ->
                      Ui1(navController, backStackEntry.arguments?.getString("userId"))
                  }
                  composable("ui2/{userId}",
                      arguments = listOf(navArgument("userId") { type = NavType.StringType })){
                      backStackEntry -> Ui2(navController, backStackEntry.arguments?.getString("userId"))
                  }
                  composable(route = "detail",
                      deepLinks = listOf(
                          navDeepLink {
                              uriPattern = "https://sample.com/{id}"
                              action = Intent.ACTION_VIEW
                          }
                      ),
                      arguments = listOf(
                          navArgument("id"){
                              type= NavType.IntType
                              defaultValue = -1 

                          }
                      )
                  ){
                          entry ->
                      val id = entry.arguments?.getInt("id")
                      Box(modifier = Modifier.fillMaxWidth(),
                          contentAlignment = Alignment.Center
                      ){
                          Text(text = "The id is $id")
                      }
                  }
              }
          }
          //  Ui1(navController)
        }
    }

@Composable
fun Ui1(navController: NavHostController, userId: String?) {

    Surface(color = Color.Red) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
            ) {

                Text(text = "hi ui1 user id is $userId")
            }
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red,
            ) {
                Button(onClick = { navController.navigate("ui2/user1111") }) {
                    Text(text = "go to ui2")
                }
            }
        }
    }
}
    @Composable
    fun Ui2(navController: NavHostController, userId: String?) {
        Surface(color = Color.Blue, modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Blue,
                ) {

                    Text(text = "hi ui2 user id is $userId")
                }
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Blue,
                ) {


                    Button(onClick = { navController.navigate("detail") }) {
                        Text(text = "go to ui3")
                    }
                }

            }
        }

    }
    @Composable
    fun Ui3(navController: NavHostController, userId: String?) {
        Surface(color = Color.Cyan , modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Cyan,
                ) {

                    Text(text = "hi ui3 user id is $userId")
                }
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Cyan,
                ) {


                    Button(onClick = { navController.navigate("ui1/user3333") }) {
                        Text(text = "go to ui1")
                    }
                }
            }
        }
    }
        @Composable
        fun Greeting(name: String, modifier: Modifier = Modifier) {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
        }

        @Preview(showBackground = true)
        @Composable
        fun GreetingPreview() {
            NavTheme {
                Greeting("Android")
            }
        }
    }