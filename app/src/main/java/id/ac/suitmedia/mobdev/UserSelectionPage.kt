package id.ac.suitmedia.mobdev

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class UserSelectionPage : AppCompatActivity() {
    private lateinit var userListView: ListView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val users = ArrayList<User>()
    private lateinit var adapter: UserAdapter
    private var currentPage = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view_user_page)

        userListView = findViewById(R.id.listViewUsername)

        adapter = UserAdapter(this, users)
        userListView.adapter = adapter


        swipeRefreshLayout.setOnRefreshListener {
            users.clear()
            currentPage = 1
            fetchUsers()
        }


        userListView.setOnItemClickListener { _, _, position, _ ->
            val selectedUser = users[position]
            val resultIntent = intent
            resultIntent.putExtra("SELECTED_USER_NAME", "${selectedUser.firstName} ${selectedUser.lastName}")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        fetchUsers()
    }

    private fun fetchUsers() {
        val url = "https://reqres.in/api/users?page=$currentPage"
        val request = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val dataArray = response.getJSONArray("data")
                for (i in 0 until dataArray.length()) {
                    val userJson = dataArray.getJSONObject(i)
                    val user = User(
                        userJson.getInt("id"),
                        userJson.getString("first_name"),
                        userJson.getString("last_name"),
                        userJson.getString("email"),
                        userJson.getString("avatar")
                    )
                    users.add(user)
                }
                adapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false
            },
            { error ->
                error.printStackTrace()
                swipeRefreshLayout.isRefreshing = false
            }
        )
        Volley.newRequestQueue(this).add(request)
    }
}