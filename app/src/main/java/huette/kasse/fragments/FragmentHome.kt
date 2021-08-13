package huette.kasse.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
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

    lateinit var namesAdapter: NamesAdapter

    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        setHasOptionsMenu(true)

        val view = inflater.inflate(R.layout.home, container, false)

        val btnAddUser: ImageButton = view.findViewById(R.id.btnAdd)
        val btnDeleteUser: ImageButton = view.findViewById(R.id.btnRemove)
        val searchView: SearchView = view.findViewById(R.id.searchView)

        val recyclerViewNames: RecyclerView = view.findViewById(R.id.recyclerViewNames)

        // Zu Testzwecken

        namesAdapter = NamesAdapter(view.context, this)

        recyclerViewNames.adapter = namesAdapter
        recyclerViewNames.layoutManager =
            GridLayoutManager(view.context, Variables.cols)
        //recyclerViewNames.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // UserViewModel
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getAllUsers.observe(viewLifecycleOwner, { users ->
            namesAdapter.setData(users)
        })

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                namesAdapter.filter.filter(newText)
                return true
            }
        })

        //namesAdapter.notifyDataSetChanged()

        btnAddUser.setOnClickListener {
            startActivity(Intent(view.context, AddUser::class.java))
        }

        btnDeleteUser.setOnClickListener {
            Variables.function = 2
            startActivity(Intent(view.context, Password::class.java))
        }

        return view
    }

    override fun OnItemClick(position: Int, users: List<User>) {
        Variables.position = position
        Variables.user = users[position]
        startActivity(Intent(this.view?.context, AddDrinkToUser::class.java))
    }


    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView
        //searchItem.expandActionView()

        searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                namesAdapter.filter.filter(newText)
                return true
            }
        })

        //searchItem.collapseActionView()

        return super.onCreateOptionsMenu(menu, inflater)
    }

    fun refreshSearch() {
        namesAdapter.filter.filter("")
    }*/
}