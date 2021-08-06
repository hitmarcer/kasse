package huette.kasse.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import huette.kasse.NamesAdapter
import huette.kasse.R
import huette.kasse.Variables
import huette.kasse.activities.AddDrinkToUser
import huette.kasse.activities.AddUser
import huette.kasse.activities.Password
import huette.kasse.data.entities.User
import huette.kasse.data.viewmodels.UserViewModel

class FragmentHome: Fragment(R.layout.home), NamesAdapter.OnItemClickListener {

    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.home, container, false)

        val btnAddUser: ImageButton = view.findViewById(R.id.btnAdd)
        val btnDeleteUser: ImageButton = view.findViewById(R.id.btnRemove)

        val recyclerViewNames: RecyclerView = view.findViewById(R.id.recyclerViewNames)

        // Zu Testzwecken

        val namesAdapter = NamesAdapter(view.context, this)

        recyclerViewNames.adapter = namesAdapter
        recyclerViewNames.layoutManager =
            GridLayoutManager(view.context, Variables.cols)
        //recyclerViewNames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // UserViewModel
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getAllUsers.observe(viewLifecycleOwner, { users ->
            namesAdapter.setData(users)
        })

        //namesAdapter.notifyDataSetChanged()

        btnAddUser.setOnClickListener {
            startActivity(Intent(view.context, AddUser::class.java))
        }

        btnDeleteUser.setOnClickListener {
            Variables.function = 2
            //Toast.makeText(this, "Wird noch nicht unterstützt", Toast.LENGTH_SHORT).show()
            startActivity(Intent(view.context, Password::class.java))
        }

        return view
    }

    override fun OnItemClick(position: Int, users: List<User>) {
        Variables.position = position
        Variables.user = users[position]
        startActivity(Intent(this.view?.context, AddDrinkToUser::class.java))
    }
}