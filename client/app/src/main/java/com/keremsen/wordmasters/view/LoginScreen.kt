package com.keremsen.wordmasters.view


import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.keremsen.wordmasters.R
import com.keremsen.wordmasters.model.LoginRequest
import com.keremsen.wordmasters.model.RegisterRequest
import com.keremsen.wordmasters.viewmodel.UserViewModel


@Composable
fun LoginScreen(navController: NavController){
    val userViewModel : UserViewModel = hiltViewModel()
    val user = userViewModel.user.collectAsState()

    val responseCode = userViewModel.responseCode.collectAsState()
    val responseCodeForLogin = userViewModel.responseCode2.collectAsState()

    var isLoginMode by remember { mutableStateOf(true) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }

    var nickNameIsEmpty by remember { mutableStateOf(false) }
    var firstNameIsEmpty by remember { mutableStateOf(false) }
    var lastNameIsEmpty by remember { mutableStateOf(false) }
    var emailIsEmpty by remember { mutableStateOf(false) }
    var paswordIsEmpty by remember { mutableStateOf(false) }

    var isThereEmail by remember { mutableStateOf(false) }

    var isUniqNickName by remember { mutableStateOf(false) }
    var isUniqEmail by remember { mutableStateOf(false) }
    var isRegisterSuccessful by remember { mutableStateOf(false) }
    var isnotvalidemail by remember { mutableStateOf(false) }
    var passwordError by remember{ mutableStateOf(false) }

    var isNotValidLoginEmail by remember { mutableStateOf(false) }
    var isNotValidLoginEmailOrPassword by remember { mutableStateOf(false) }

    val context = LocalContext.current
    var lastToast : Toast? = null

    var loginTrigger by remember { mutableIntStateOf(0) }
    val isLoginSuccessful = remember { mutableStateOf(false) }

    val regexNickName = Regex("^[a-zA-Z0-9]*$")
    val regexName = Regex("^[a-zA-Z]+$")

    when (responseCode.value) {
        403 -> {
            isUniqNickName = true
            isUniqEmail = false  // Diğer hataları resetle
            passwordError = false
        }
        409 -> {
            isUniqEmail = true
            isUniqNickName = false
            passwordError = false
        }
        401 -> {
            passwordError = true
            isUniqEmail = false
            isUniqNickName = false
        }
        201 -> {
            isRegisterSuccessful = true
            // Tüm hataları temizle
            isUniqEmail = false
            isUniqNickName = false
            passwordError = false
        }
    }
    when (responseCodeForLogin.value) {
        400 -> {
            isNotValidLoginEmail = true
        }
        409 -> {
            isNotValidLoginEmailOrPassword = true
        }

    }


    fun clearAllErrors() {
        nickNameIsEmpty = false
        firstNameIsEmpty = false
        lastNameIsEmpty = false
        emailIsEmpty = false
        paswordIsEmpty = false
        isUniqNickName = false
        isUniqEmail = false
        isnotvalidemail = false
        passwordError = false
        isNotValidLoginEmail = false
        isNotValidLoginEmailOrPassword = false
    }
    // Animasyon için infinite pulse effect
    val infiniteTransition = rememberInfiniteTransition(label="animation")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),label="animation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Uygulama Başlığı - Animasyonlu
            Text(
                text = "Kelime Ustası",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.TextColor),
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .scale(scale),
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black,
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                )
            )


            // Form alanı
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 300,
                            easing = EaseOutCubic

                        )
                    ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(id = R.color.ButtonColor) // XML'den renk
                ),


            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Sekmeler - Giriş / Kayıt
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 24.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TabButton(
                            title = "Giriş",

                            isSelected = isLoginMode,
                            onClick = {
                                nickname=""
                                firstname=""
                                lastname=""
                                password=""
                                clearAllErrors()
                                isLoginMode = true }
                        )
                        TabButton(
                            title = "Kayıt Ol",
                            isSelected = !isLoginMode,
                            onClick = {
                                email=""
                                password=""
                                clearAllErrors()
                                isLoginMode = false }
                        )
                    }
                    //

                    // Form alanları

                    AnimatedVisibility(
                        visible = !isLoginMode,
                        enter = fadeIn(animationSpec = tween(durationMillis = 1000)) + slideInVertically(),
                        exit = fadeOut(),
                        modifier = Modifier
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            value = nickname,
                            onValueChange = {input ->
                                if (regexNickName.matches(input)) {
                                    nickname = input
                                } },
                            label = { Text("Kullanıcı Adı") },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                                    contentDescription = "Kullanıcı", modifier = Modifier
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),supportingText = {
                                when {
                                    nickNameIsEmpty -> Text("Zorunlu alan", color = Color.Red)
                                    isUniqNickName -> Text("Kullanıcı adınız benzersiz olmalı!", color = Color.Red)
                                }
                            }
                        )
                    }


                    AnimatedVisibility(
                        visible = !isLoginMode,
                        enter = fadeIn(animationSpec = tween(durationMillis = 1000)) + slideInVertically(),
                        exit = fadeOut()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            value = firstname,
                            onValueChange = { input ->
                                if (regexName.matches(input)) {
                                    firstname = input
                                }  },
                            label = { Text("Adı") },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                                    contentDescription = "Kullanıcı"
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),supportingText = {
                                if (firstNameIsEmpty) {
                                    Text("Zorunlu alan", color = Color.Red)
                                }
                            }
                        )

                    }
                    AnimatedVisibility(
                        visible = !isLoginMode,
                        enter = fadeIn(animationSpec = tween(durationMillis = 1000)) + slideInVertically(),
                        exit = fadeOut()
                    ) {
                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            value = lastname,
                            onValueChange = {  input ->
                                if (regexName.matches(input)) {
                                    lastname = input
                                }  },
                            label = { Text("Soyadı") },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                                    contentDescription = "Kullanıcı"
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),supportingText = {
                                if (lastNameIsEmpty) {
                                    Text("Zorunlu alan", color = Color.Red)
                                }
                            }
                        )

                    }



                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            value = email,
                            onValueChange = { if (!it.contains(" ")) {
                                email = it
                            }},
                            label = { Text("E-posta") },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = android.R.drawable.ic_dialog_email),
                                    contentDescription = "Email"
                                )
                            },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),supportingText = {
                                when {
                                    emailIsEmpty -> Text("Zorunlu alan", color = Color.Red)
                                    isnotvalidemail -> Text("Geçersiz E-posta formatı!", color = Color.Red)
                                    isUniqEmail -> Text("E-postanız benzersiz olmalı!", color = Color.Red)
                                    isNotValidLoginEmail -> Text("Kayıtlı kullanıcı bulunamadı!", color = Color.Red)
                                }
                            }
                        )


                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        value = password,
                        onValueChange = { if (!it.contains(" ")) {
                            password = it
                        }},
                        label = { Text("Şifre") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_lock_idle_lock),
                                contentDescription = "Şifre"
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    painter = painterResource(
                                        id = if (passwordVisible)
                                            android.R.drawable.ic_menu_close_clear_cancel
                                        else
                                            android.R.drawable.ic_menu_view
                                    ),
                                    contentDescription = if (passwordVisible) "Şifreyi Gizle" else "Şifreyi Göster"
                                )
                            }
                        },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),supportingText = {
                            when {
                                paswordIsEmpty -> Text("Zorunlu alan", color = Color.Red)
                                isNotValidLoginEmailOrPassword -> Text("Geçersiz E-posta veya şifre girdiniz!", color = Color.Red)
                                passwordError -> Text("Şifreniz En az 7 karakter olmalı!", color = Color.Red)
                            }
                        }

                    )

                    if(isRegisterSuccessful){
                        LaunchedEffect(Unit) {
                            Toast.makeText(
                                context,
                                "Kayıt Başarılı",
                                Toast.LENGTH_SHORT
                            ).show()
                            nickname=""
                            firstname=""
                            lastname=""
                            email=""
                            password=""
                            isRegisterSuccessful = false // State'i resetle
                        }
                        isRegisterSuccessful = false
                    }
                    // Buton - Giriş / Kayıt
                    Button(
                        onClick = {
                            // Login/Register işlemi burada yapılacak
                            val loginRequest = LoginRequest(email = email, password = password)

                            val registerRequest = RegisterRequest(
                                firstName = firstname,
                                lastName = lastname,
                                nickName = nickname,
                                email = email,
                                password = password
                            )
                            if(isLoginMode){
                                loginTrigger++

                                userViewModel.login(loginRequest)
                                isNotValidLoginEmail= false
                                isNotValidLoginEmailOrPassword = false
                            }else{
                                nickNameIsEmpty = nickname.isBlank()
                                firstNameIsEmpty = firstname.isBlank()
                                lastNameIsEmpty = lastname.isBlank()
                                emailIsEmpty = email.isBlank()
                                paswordIsEmpty = password.isBlank()

                                isnotvalidemail = !isValidEmail(email)

                                isUniqEmail = false
                                isUniqNickName = false
                                passwordError =false

                            if(!nickNameIsEmpty && !firstNameIsEmpty && !lastNameIsEmpty && !emailIsEmpty && !paswordIsEmpty && !isnotvalidemail && isThereEmail){
                                userViewModel.register(registerRequest)
                                }
                            }

                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.ButtonColor2), // XML'den renk
                            contentColor = colorResource(id = R.color.ButtonColor2) // Yazı rengi
                        )


                    ) {
                        Text(
                            text = if (isLoginMode) "Giriş Yap" else "Kayıt Ol",
                            color = colorResource(id = R.color.TextColor),
                            fontSize = 16.sp
                        )
                    }

                    if (isLoginMode) {
                        TextButton(
                            onClick = { /* Şifremi unuttum işlemi */ },
                            modifier = Modifier.padding(top = 8.dp)
                        ) {
                            Text(
                                text = "Şifremi Unuttum",
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
    LaunchedEffect(user.value,loginTrigger) {
        user.value?.let { currentUser ->
            val id = currentUser.id.toIntOrNull() ?: -1
            println(id)
            if(id != -1){
                isLoginSuccessful.value = true
                navController.navigate("main/${id}") {
                    popUpTo("login_screen") { inclusive = true }
                }
            }else if (!isLoginSuccessful.value){
                lastToast?.cancel() // önceki varsa iptal et
                lastToast = Toast.makeText(context, "Kullanıcı Bulunamadı !", Toast.LENGTH_SHORT)
                lastToast?.show()
            }
        }
    }

}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Composable
fun TabButton(title: String, isSelected: Boolean, onClick: () -> Unit ) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected)
                colorResource(id = R.color.ButtonColor2)
            else
                MaterialTheme.colorScheme.surface,
            contentColor = if (isSelected)
                colorResource(id = R.color.TextColor)
            else
                MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.width(120.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center
        )
    }
}

