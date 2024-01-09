package com.example.cucisepatu.ui.screen

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import android.widget.Toast
import com.example.cucisepatu.R
import com.example.cucisepatu.ui.viewmodel.HomeScreenViewModel

class HomeScreen : ComponentActivity() {

    private val viewModel: HomeScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val verticalLayout = LinearLayout(this)
        verticalLayout.orientation = LinearLayout.VERTICAL
        verticalLayout.setPadding(
            resources.getDimensionPixelSize(R.dimen.margin_16),
            resources.getDimensionPixelSize(R.dimen.margin_16),
            resources.getDimensionPixelSize(R.dimen.margin_16),
            resources.getDimensionPixelSize(R.dimen.margin_16)
        )

        // Bagian atas
        val textView = androidx.appcompat.widget.AppCompatTextView(this)
        textView.text = "Selamat Datang"
        textView.textSize = 24f
        val topMargin = resources.getDimensionPixelSize(R.dimen.margin_top)
        val textLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        textLayoutParams.topMargin = topMargin
        textView.layoutParams = textLayoutParams
        verticalLayout.addView(textView)

        // Bagian tengah
        val buttonPemesanan = Button(this)
        buttonPemesanan.text = "Pemesanan"
        val buttonLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        buttonLayoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.margin_top)
        buttonPemesanan.layoutParams = buttonLayoutParams
        buttonPemesanan.setOnClickListener {
            showPasswordDialog()
        }
        verticalLayout.addView(buttonPemesanan)

        // Bagian bawah
        val relativeLayout = RelativeLayout(this)
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.margin_top)
        relativeLayout.layoutParams = layoutParams

        val cardView = CardView(this)
        cardView.cardElevation = resources.getDimension(R.dimen.card_elevation)
        cardView.radius = resources.getDimension(R.dimen.card_radius)
        val cardLayoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        cardLayoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.margin_end)
        cardLayoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.margin_top)
        cardView.layoutParams = cardLayoutParams

        val plusButton = Button(this)
        plusButton.text = "+"
        plusButton.setOnClickListener {
            showPasswordDialog()
        }
        cardView.addView(plusButton)

        relativeLayout.addView(cardView)
        verticalLayout.addView(relativeLayout)

        setContentView(verticalLayout)
    }

    private fun showPasswordDialog() {
        val passwordDialogView = layoutInflater.inflate(R.layout.dialog_password, null)

        val passwordEditText = passwordDialogView.findViewById<EditText>(R.id.passwordEditText)
        val okButton = passwordDialogView.findViewById<Button>(R.id.okButton)

        val passwordDialog = AlertDialog.Builder(this)
            .setView(passwordDialogView)
            .create()

        passwordDialog.show()

        okButton?.setOnClickListener {
            val enteredPassword = passwordEditText?.text.toString()
            if (viewModel.isPasswordCorrect(enteredPassword)) {
                // Password benar, redirect ke halaman jenis_sepatu
                Toast.makeText(this, "Password benar", Toast.LENGTH_SHORT).show()
                // Uncomment the following line when you have the JenisSepatuActivity
                // startActivity<JenisSepatuActivity>()
            } else {
                // Password salah, tampilkan pesan error
                Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show()
            }
            passwordDialog.dismiss()
        }
    }
}
