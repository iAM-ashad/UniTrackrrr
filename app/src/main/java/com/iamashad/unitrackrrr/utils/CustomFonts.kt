package com.iamashad.unitrackrrr.utils

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.iamashad.unitrackrrr.R

fun customFonts(fontName: GoogleFont): FontFamily {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val myFontFamily = FontFamily(
        Font(googleFont = fontName, fontProvider = provider)
    )
    return myFontFamily

}