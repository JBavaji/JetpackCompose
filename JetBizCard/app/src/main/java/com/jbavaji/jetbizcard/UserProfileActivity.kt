package com.jbavaji.jetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import coil.size.Size
import com.jbavaji.jetbizcard.ui.theme.MyTheme
import com.jbavaji.jetbizcard.ui.theme.lightGreen

class UserProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                UsersApplication()
            }
        }
    }

    @Composable
    fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "users_list") {
            composable(route = "users_list") {
                UserProfileListScreen(userProfiles = userProfiles, navController)
            }

            composable(
                route = "user_details/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.IntType })
            ) { navBackStackEntry ->
                UserProfileDetailsScreen(
                    userId = navBackStackEntry.arguments!!.getInt("userId"),
                    navController
                )
            }
        }
    }

    @Composable
    private fun UserProfileListScreen(
        userProfiles: List<UserProfile>,
        navController: NavHostController?
    ) {
        Scaffold(topBar = {
            AppBar(title = "User List", icon = Icons.Default.Home) {}
        }) {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                LazyColumn {
                    items(userProfiles) { userProfile ->
                        ProfileCard(userProfile = userProfile) {
                            navController?.navigate("user_details/${userProfile.id}")
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {
        Card(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(align = Alignment.Top)
                .clickable(onClick = clickAction),
            elevation = 8.dp,
            backgroundColor = Color.White
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ProfilePicture(userProfile.drawableUrl, userProfile.status, 72.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
            }
        }
    }

    @Composable
    private fun ProfileContent(
        userName: String,
        onlineStatus: Boolean,
        alignment: Alignment.Horizontal
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = alignment
        ) {
            CompositionLocalProvider(
                LocalContentAlpha provides (
                        if (onlineStatus)
                            1f else ContentAlpha.medium)
            ) {
                Text(
                    text = userName,
                    style = MaterialTheme.typography.h5
                )
            }
            CompositionLocalProvider(LocalContentAlpha provides (ContentAlpha.medium)) {
                Text(
                    text = if (onlineStatus)
                        "Active now"
                    else "Offline",
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }

    @Composable
    private fun ProfilePicture(drawableId: String, onlineStatus: Boolean, imageSize: Dp) {
        Card(
            shape = CircleShape,
            border = BorderStroke(
                width = 2.dp,
                color = if (onlineStatus)
                    MaterialTheme.colors.lightGreen
                else Color.Red
            ),
            modifier = Modifier.padding(16.dp),
            elevation = 4.dp
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(drawableId)
                    .crossfade(true)
                    .size(Size.ORIGINAL)
                    .build(),
                modifier = Modifier.size(imageSize),
                contentScale = ContentScale.Crop,
                contentDescription = ""
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
        }
    }

    @Composable
    private fun AppBar(title: String, icon: ImageVector, iconClickAction: () -> Unit) {
        TopAppBar(
            navigationIcon = {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { iconClickAction() },
                    )
                }
            },
            title = { Text(text = title) }
        )
    }

    @Composable
    private fun UserProfileDetailsScreen(userId: Int, navController: NavHostController?) {
        val userProfile: UserProfile =
            userProfileList.first { userProfile -> userId == userProfile.id }
        Scaffold(topBar = {
            AppBar(title = "User Details", icon = Icons.Default.ArrowBack) {
                navController!!.navigateUp()
            }
        }) {
            Surface(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    ProfilePicture(userProfile.drawableUrl, userProfile.status, 240.dp)
                    ProfileContent(
                        userProfile.name,
                        userProfile.status,
                        Alignment.CenterHorizontally
                    )
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun UserProfileListPreview() {
        MyTheme {
            UserProfileListScreen(userProfiles = userProfileList, null)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun UserProfileDetailPreview() {
        MyTheme {
            UserProfileDetailsScreen(userId = 4, null)
        }
    }
}
