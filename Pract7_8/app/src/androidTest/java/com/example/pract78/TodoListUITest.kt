package com.example.pract78

import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoListUITest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun displaysAllThreeTodos() {
        composeTestRule.onNodeWithTag("todo_item_1").assertExists()
        composeTestRule.onNodeWithTag("todo_item_2").assertExists()
        composeTestRule.onNodeWithTag("todo_item_3").assertExists()
    }

    @Test
    fun checkboxTogglesStatus() {
        composeTestRule.onNodeWithTag("checkbox_1").assertIsOff()
        composeTestRule.onNodeWithTag("checkbox_1").performClick()
        composeTestRule.onNodeWithTag("checkbox_1").assertIsOn()
    }

    @Test
    fun navigateToListAndBack() {
        composeTestRule.onNodeWithTag("todo_item_1").performClick()
        composeTestRule.onNodeWithTag("detail_screen").assertExists()
        composeTestRule.onNodeWithTag("back_button").performClick()
        composeTestRule.onNodeWithTag("home_screen").assertExists()
    }
}