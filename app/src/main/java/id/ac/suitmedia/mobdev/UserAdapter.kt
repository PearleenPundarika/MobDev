package id.ac.suitmedia.mobdev

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import com.bumptech.glide.Glide


class UserAdapter(private val context: Context, private val users: List<User>) :
    ArrayAdapter<User>(context, R.layout.user_list, users) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val user = users[position]
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.user_list, parent, false)

        val userFirstNameTextView = view.findViewById<TextView>(R.id.userFirstName)
        val userLastNameTextView = view.findViewById<TextView>(R.id.userLastName)
        val userEmailTextView = view.findViewById<TextView>(R.id.userEmail)
        val userImageView = view.findViewById<ImageView>(R.id.userImg)

        userFirstNameTextView.text = user.firstName
        userLastNameTextView.text = user.lastName
        userEmailTextView.text = user.email
        Glide.with(context).load(user.avatar.toUri()).into(userImageView)

        return view
    }
}