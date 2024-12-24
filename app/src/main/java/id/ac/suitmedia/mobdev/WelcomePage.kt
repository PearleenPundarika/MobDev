package id.ac.suitmedia.mobdev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class WelcomePage : AppCompatActivity() {

    private lateinit var welcomeTextView: TextView
    private lateinit var selectedUserNameTextView: TextView
    private lateinit var chooseUserButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.username_selection_page)

        welcomeTextView = findViewById(R.id.userNameTxt)
        selectedUserNameTextView = findViewById(R.id.selectedUserNameTxt)
        chooseUserButton = findViewById(R.id.chooseUserBtn)

        val userName = intent.getStringExtra("USER_NAME")
        welcomeTextView.text = "$userName"

        chooseUserButton.setOnClickListener {
            val intent = Intent(this, UserSelectionPage::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val selectedUserName = data?.getStringExtra("SELECTED_USER_NAME")
            selectedUserNameTextView.text = selectedUserName
        }
    }

}