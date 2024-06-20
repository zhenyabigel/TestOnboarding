package by.zhenyabigel.testonboarding.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import by.zhenyabigel.testonboarding.R
import by.zhenyabigel.testonboarding.navigation.Screen
import by.zhenyabigel.testonboarding.ui.theme.PurpleGrey40
import by.zhenyabigel.testonboarding.ui.theme.robotoFontFamily
import by.zhenyabigel.testonboarding.util.OnBoardingPage
import by.zhenyabigel.testonboarding.viewmodel.WelcomeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WelcomeScreen(
    navController: NavHostController,
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third,
        OnBoardingPage.Fourth
    )
    val pagerState = rememberPagerState(pageCount = {
        4
    })
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxSize()
        ) { pozition ->
            PagerScreen(onBoardingPage = pages[pozition])
        }
        Footer(
            pagerState = pagerState,
            navController = navController
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Footer(
    pagerState: PagerState,
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Row(
                Modifier
                    .wrapContentHeight()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) Color.White else PurpleGrey40
                    val width = if (pagerState.currentPage == iteration) 24.dp else 8.dp
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(color)
                            .height(8.dp)
                            .width(width)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .padding(start = 4.dp)
                    .clickable {
                        navController.popBackStack()
                        navController.navigate(Screen.Home.route)
                    }
            ) {
                Text(
                    text = "Skip",
                    fontWeight = FontWeight.Thin,
                    textAlign = TextAlign.Start,
                    fontFamily = robotoFontFamily,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

        }
        Box(
            modifier = Modifier
                .height(58.dp)
                .width(58.dp)
                .clickable {
                    when (pagerState.currentPage) {
                        3 -> {
                            navController.popBackStack()
                            navController.navigate(Screen.Home.route)
                        }
                        else -> {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    }
                }
        ) {
            val image = when (pagerState.currentPage) {
                0 -> painterResource(id = R.drawable.loader_screen1)
                1 -> painterResource(id = R.drawable.loader_screen2)
                2 -> painterResource(id = R.drawable.loader_screen3)
                3 -> painterResource(id = R.drawable.loader_screen4)
                else -> {
                    painterResource(id = R.drawable.loader_screen4)
                }
            }
            Image(
                painter = image,
                contentDescription = "nextBtn",
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}


@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(onBoardingPage.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .padding(top = 64.dp),
            text = onBoardingPage.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start,
            fontFamily = robotoFontFamily,
            fontSize = 24.sp,
            color = Color.White
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 12.dp),
            text = onBoardingPage.description,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start,
            fontFamily = robotoFontFamily,
            fontSize = 19.sp,
            color = Color.White
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(top = 24.dp),
            painter = painterResource(id = onBoardingPage.mainImage),
            contentDescription = "Pager Image"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FirstOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.First)
    }
}

@Composable
@Preview(showBackground = true)
fun SecondOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Second)
    }
}

@Composable
@Preview(showBackground = true)
fun ThirdOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Third)
    }
}

@Composable
@Preview(showBackground = true)
fun FourthOnBoardingScreenPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PagerScreen(onBoardingPage = OnBoardingPage.Fourth)
    }
}