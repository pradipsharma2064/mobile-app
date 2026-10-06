package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.sample.SampleData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read app name string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Hamro Sapana", appName)
  }

  @Test
  fun `verify sample courses and mock exam questions loaded`() {
    assertTrue(SampleData.courses.isNotEmpty())
    assertTrue(SampleData.mockExamQuestions.isNotEmpty())
    assertTrue(SampleData.liveClasses.isNotEmpty())
    assertEquals(8, SampleData.mockExamQuestions.size)
  }

  @Test
  fun `verify primary exam categories for structured entry point`() {
    val categories = SampleData.examCategoryEntries
    assertEquals(5, categories.size)
    val names = categories.map { it.categoryName }
    assertTrue(names.contains("Loksewa Aayog"))
    assertTrue(names.contains("Banking & Finance"))
    assertTrue(names.contains("Shikshak Sewa (TSC)"))
    assertTrue(names.contains("Medical (CEE/MECEE)"))
    assertTrue(names.contains("Engineering (IOE)"))
  }

  @Test
  fun `verify AI doubt solver offline knowledge base`() {
    val replies = SampleData.askGuruSampleReplies
    assertTrue(replies.containsKey("negative_marking"))
    assertTrue(replies.containsKey("constitution"))
    assertTrue(replies.containsKey("nrb_banking"))
    assertTrue(replies.containsKey("iq_clock"))
  }

  @Test
  fun `verify upcoming live classes and timing metadata`() {
    val classes = SampleData.liveClasses
    assertTrue(classes.size >= 5)
    val hasLive = classes.any { it.isLiveNow }
    val hasUpcoming = classes.any { !it.isLiveNow }
    assertTrue(hasLive)
    assertTrue(hasUpcoming)
    assertTrue(classes.any { it.timeLabel == "Today" })
    assertTrue(classes.any { it.timeLabel == "Tomorrow" })
  }
}
