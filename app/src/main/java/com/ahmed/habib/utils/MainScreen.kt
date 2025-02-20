package com.ahmed.habib.utils


import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ahmed.habib.core.composeable.CommonRoundedButton

@Composable
fun MainScreen(
    navController: NavController,
    navigateToTimerListActivity: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(22.dp)
    ) {
        Text(
            text = "Examples of [ Services & Broadcast receiver ]",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Black),
            modifier = Modifier.padding(top = 16.dp)
        )

        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CommonRoundedButton(
                name = "Services",
                fontSize = 14.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                navController.navigate(Screens.ServicesScreen.route)
            }

            CommonRoundedButton(
                name = "BroadCast Receiver",
                fontSize = 14.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                navController.navigate(Screens.BroadCastReceiverScreen.route)
            }
        }

        Row(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CommonRoundedButton(
                name = "Location Tracking",
                fontSize = 14.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                navController.navigate(Screens.LocationTrackingScreen.route)
            }

            CommonRoundedButton(
                name = "Content Provider",
                fontSize = 14.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                navController.navigate(Screens.ContentProviderScreen.route)
            }
        }

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CommonRoundedButton(
                name = "Alarm Manager",
                fontSize = 14.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                navController.navigate(Screens.AlarmManagerScreen.route)
            }

            CommonRoundedButton(
                name = "Work Manager",
                fontSize = 14.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                navController.navigate(Screens.WorkManagerScreen.route)
            }
        }

        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
        )

        Text(
            text = "Examples of [ Pic Img from camera & Gallery ]",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Black),
            modifier = Modifier.padding(top = 16.dp)
        )

        Row(
            modifier = Modifier.padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CommonRoundedButton(
                name = "Pick Images",
                fontSize = 14.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                navController.navigate(Screens.PickImageScreen.route)
            }

            CommonRoundedButton(
                name = "Pick Camera Images",
                fontSize = 14.sp,
                mainColor = R.color.purple_700,
                textColor = R.color.white,
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp)
            ) {
                navController.navigate(Screens.PickCameraImageScreen.route)
            }
        }

        Divider(
            thickness = 1.dp,
            color = Color.LightGray,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(1.dp)
        )

        Text(
            text = "Separated Tasks",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Black),
            modifier = Modifier.padding(top = 16.dp)
        )

        CommonRoundedButton(
            name = "Timer List",
            fontSize = 14.sp,
            mainColor = R.color.purple_700,
            textColor = R.color.white,
            modifier = Modifier
                .weight(1f)
                .height(70.dp)
        ) {
            navigateToTimerListActivity()
        }
    }
}