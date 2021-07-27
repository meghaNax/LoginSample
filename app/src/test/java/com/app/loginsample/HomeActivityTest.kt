package com.app.loginsample

import android.widget.TextView
import com.app.loginsample.ui.home.HomeActivity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class HomeActivityTest {

    private lateinit var homeActivity: HomeActivity
    private lateinit var helloTxt: TextView

    @Before
    @Throws(Exception::class)
    fun setUp() {
        homeActivity = Robolectric.buildActivity(HomeActivity::class.java)
            .create()
            .resume()
            .get()
        helloTxt =
            homeActivity.findViewById(R.id.tvHello)
    }


    @Test
    @Throws(Exception::class)
    fun launchLogin() {
        assertNotNull(homeActivity)
    }

    @Test
    @Throws(Exception::class)
    fun shouldHaveCorrectAppName() {
        val hello: String = homeActivity.resources.getString(R.string.app_name)
        assertThat(hello, equalTo("LoginSample"))
    }

    @Test
    @Throws(Exception::class)
    fun checkHelloActivityText() {
        shadowOf(homeActivity.mainLooper).idle()
        helloTxt?.text = "Hello"
        val textView: TextView = homeActivity.findViewById(R.id.tvHello)
        assertEquals(textView.text.toString(), homeActivity.getString(R.string.msg_hello))
    }
}