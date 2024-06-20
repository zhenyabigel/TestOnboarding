package by.zhenyabigel.testonboarding.util

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import by.zhenyabigel.testonboarding.R
import by.zhenyabigel.testonboarding.ui.theme.Blue
import by.zhenyabigel.testonboarding.ui.theme.Limon
import by.zhenyabigel.testonboarding.ui.theme.Peach
import by.zhenyabigel.testonboarding.ui.theme.Purple

sealed class OnBoardingPage(
    val title: String,
    val description: String,
    val backgroundColor: Color,
    @DrawableRes
    val mainImage: Int,
) {
    data object First : OnBoardingPage(
        title = "Your first car without\na driver's license",
        description = "Goes to meet people who just got\ntheir license",
        backgroundColor = Limon,
        mainImage = R.drawable.img_car1
    )

    data object Second : OnBoardingPage(
        title = "Always there: more\nthan 1000 cars in Tbilisi",
        description = "Our company is a leader by the\nnumber of cars in the fleet",
        backgroundColor = Purple,
        mainImage = R.drawable.img_car2
    )

    data object Third : OnBoardingPage(
        title = "Do not pay for parking,\nmaintenance and gasoline",
        description = "We will pay for you, all expenses\nrelated to the car",
        backgroundColor = Peach,
        mainImage = R.drawable.img_car3
    )

    data object Fourth : OnBoardingPage(
        title = "29 car models: from Skoda\nOctavia to Porsche 911",
        description = "Choose between regular car models\nor exclusive ones",
        backgroundColor = Blue,
        mainImage = R.drawable.img_car4
    )
}