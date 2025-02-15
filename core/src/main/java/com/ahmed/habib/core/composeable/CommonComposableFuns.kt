package com.ahmed.habib.core.composeable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner

@Composable
fun CommonTextView(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit
) {
    Text(
        text = text,
        fontSize = fontSize,
        modifier = modifier,
        textAlign = TextAlign.Center,
        color = Color.Gray
    )
}

@Composable
fun CircleProgressBar(
    modifier: Modifier = Modifier,
    color: Int
) {
    CircularProgressIndicator(
        modifier = modifier,
        color = colorResource(id = color),
        strokeWidth = Dp(value = 4F)
    )
}

@Composable
fun CommonRoundedButton(
    modifier: Modifier = Modifier,
    name: String,
    fontSize: TextUnit,
    mainColor: Int,
    textColor: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(18.dp),
        modifier = modifier.clip(RoundedCornerShape(16.dp)),
        border = BorderStroke(1.dp, colorResource(id = mainColor)),
        colors = ButtonDefaults.buttonColors(
            contentColor = colorResource(id = textColor),
            backgroundColor = colorResource(id = mainColor)
        )
    ) {
        Text(
            text = name,
            fontSize = fontSize,
            modifier = Modifier
                .padding(vertical = 4.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@ExperimentalMaterialApi
fun CommonOutLineTextField(
    lifecycleOwner: LifecycleOwner,
    hint: String,
    textColor: Int,
    cursorColor: Int,
    errorBorderColor: Int,
    fontSize: TextUnit,
    focusBorderColor: Int,
    unFocusBorderColor: Int,
    placeHolder: String,
    inputType: KeyboardType,
    wroteData: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    OutlinedTextField(
        value = input,
        label = { Text(text = hint, fontSize = fontSize) },
        placeholder = { Text(text = placeHolder) },
        onValueChange = {
            input = it
            wroteData.invoke(input)
        },
        keyboardOptions = KeyboardOptions(keyboardType = inputType),
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = outlinedTextFieldColors(
            textColor = colorResource(id = textColor),
            cursorColor = colorResource(id = cursorColor),
            errorBorderColor = colorResource(id = errorBorderColor),
            focusedBorderColor = colorResource(id = focusBorderColor),
            unfocusedBorderColor = colorResource(id = unFocusBorderColor)
        )
    )
}

@Composable
@ExperimentalMaterialApi
fun CommonTextFieldWithIcons(
    lifecycleOwner: LifecycleOwner,
    hint: String,
    textColor: Int,
    cursorColor: Int,
    errorBorderColor: Int,
    focusBorderColor: Int,
    unFocusBorderColor: Int,
    fontSize: TextUnit,
    placeHolder: String,
    inputType: KeyboardType,
    icon: ImageVector,
    wroteData: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    OutlinedTextField(
        value = input,
        keyboardOptions = KeyboardOptions(keyboardType = inputType),
        leadingIcon = { Icon(imageVector = icon, contentDescription = "") },
        onValueChange = {
            input = it
            wroteData.invoke(input)
        },
        placeholder = { Text(text = placeHolder) },
        label = { Text(text = hint, fontSize = fontSize) },
        modifier = modifier.fillMaxWidth(),
        colors = outlinedTextFieldColors(
            textColor = colorResource(id = textColor),
            cursorColor = colorResource(id = cursorColor),
            errorBorderColor = colorResource(id = errorBorderColor),
            focusedBorderColor = colorResource(id = focusBorderColor),
            unfocusedBorderColor = colorResource(id = unFocusBorderColor)
        )
    )
}