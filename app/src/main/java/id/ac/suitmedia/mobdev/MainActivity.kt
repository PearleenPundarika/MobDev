package id.ac.suitmedia.mobdev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var palindromeEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var nextButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.login_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        nameEditText = findViewById(R.id.nameEdtTxt)
        palindromeEditText = findViewById(R.id.palindromeEdtTxt)
        checkButton = findViewById(R.id.checkBtn)
        nextButton = findViewById(R.id.nextBtn)

        checkButton.setOnClickListener {
            val inputText = palindromeEditText.text.toString()
            if (isPalindrome(inputText)) {
                showDialog("Palindrome", "The text is a palindrome")
            } else {
                showDialog("Not Palindrome", "The text isn't a palindrome")
            }
        }
        
        nextButton.setOnClickListener {
            val name = nameEditText.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, WelcomePage::class.java)
                intent.putExtra("USER_NAME", name)
                startActivity(intent)
            }
        }
    }


    private fun isPalindrome(input: String): Boolean {
        val cleanInput = input.replace("\\s".toRegex(), "").lowercase()
        return cleanInput == cleanInput.reversed()
    }


    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(this).setTitle(title).setMessage(message).setPositiveButton("OK", null)
            .show()
    }
}