package com.app.loginsample

import android.widget.Button
import com.app.loginsample.ui.home.HomeActivity
import com.app.loginsample.ui.login.LoginActivity
import com.google.android.material.textfield.TextInputLayout
import junit.framework.TestCase.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.Shadows.shadowOf
import org.robolectric.shadows.ShadowActivity
import org.robolectric.shadows.ShadowIntent
import android.content.Intent
import android.widget.TextView
import org.robolectric.Robolectric.buildActivity


@RunWith(RobolectricTestRunner::class)
class LoginActivityTest {

    private lateinit var loginActivity: LoginActivity
    private lateinit var btnLogin: Button
    private lateinit var userName: TextInputLayout
    private lateinit var password: TextInputLayout


    @Before
    @Throws(Exception::class)
    fun setUp() {

        loginActivity = Robolectric.buildActivity(LoginActivity::class.java)
            .create()
            .resume()
            .get()
        userName =
            loginActivity.findViewById(R.id.tilUserName)
        password =
            loginActivity.findViewById(R.id.tilPassword)
        btnLogin = loginActivity.findViewById(R.id.btnLogin) as Button
    }


    @Test
    @Throws(Exception::class)
    fun launchLogin() {
        assertNotNull(loginActivity)
    }

    @Test
    @Throws(Exception::class)
    fun shouldHaveCorrectAppName() {
        val hello: String = loginActivity.resources.getString(R.string.app_name)
        assertThat(hello, equalTo("LoginSample"))
    }

    @Test
    @Throws(Exception::class)
    fun checkUIComponents() {
        shadowOf(loginActivity.mainLooper).idle()
        assertNotNull(Shadows.shadowOf(userName))
        assertEquals(userName.hint, loginActivity.getString(R.string.hint_user_name))
        assertEquals(password.hint, loginActivity.getString(R.string.hint_password))
        assertNotNull(Shadows.shadowOf(password))
        assertNotNull(Shadows.shadowOf(btnLogin))
        assertEquals(btnLogin.text, loginActivity.getString(R.string.action_login))
    }

    @Test
    @Throws(Exception::class)
    fun checkLoginDisabledAtBegining() {
        shadowOf(loginActivity.mainLooper).idle()
        assertTrue(!btnLogin.isEnabled)
    }

    @Test
    @Throws(Exception::class)
    fun checkLoginDisabledUserNameFilled() {
        shadowOf(loginActivity.mainLooper).idle()
        userName.editText?.setText("Hello")
        assertTrue(!btnLogin.isEnabled)
    }


    @Test
    @Throws(Exception::class)
    fun checkLoginDisabledPasswordFilled() {
        shadowOf(loginActivity.mainLooper).idle()
        password.editText?.setText("Hello")
        assertTrue(!btnLogin.isEnabled)
    }


    @Test
    @Throws(Exception::class)
    fun checkLoginMoveNextScreen() {
        shadowOf(loginActivity.mainLooper).idle()
        userName.editText?.setText("Hello")
        password.editText?.setText("Hello")
        btnLogin.callOnClick()
        val shadowActivity: ShadowActivity = shadowOf(loginActivity)
        val startedIntent = shadowActivity.nextStartedActivity
        val shadowIntent: ShadowIntent = shadowOf(startedIntent)
        assertThat(shadowIntent.intentClass.name, equalTo(HomeActivity::class.java.name))

    }

    @Test
    @Throws(Exception::class)
    fun checkHelloActivityText() {
        shadowOf(loginActivity.mainLooper).idle()
        userName.editText?.setText("Hello")
        password.editText?.setText("Hello")
        btnLogin.callOnClick()
        val shadowActivity: ShadowActivity = shadowOf(loginActivity)
        val startedIntent: Intent = shadowActivity.peekNextStartedActivity()
        val homeActivity: HomeActivity = buildActivity(HomeActivity::class.java, startedIntent)
            .create().get()
        val textView: TextView = homeActivity.findViewById(R.id.tvHello)
        assertEquals(textView.text.toString(), homeActivity.getString(R.string.msg_hello))

    }

}