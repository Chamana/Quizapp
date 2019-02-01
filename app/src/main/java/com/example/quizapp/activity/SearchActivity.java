package com.example.quizapp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quizapp.R;
import com.example.quizapp.adapter.UserSearchAdapter;
import com.example.quizapp.api.AppController;
import com.example.quizapp.api.IConnectAPI;
import com.example.quizapp.models.Response.SearchResponse;
import com.example.quizapp.models.Response.UserListItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView users_rv;
    IConnectAPI iConnectAPI;
    String oldQuery="";
    List<UserListItem> userListItems;
    UserSearchAdapter userSearchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userListItems=new ArrayList<>();
        users_rv=findViewById(R.id.users_rv);
        iConnectAPI= AppController.user_profile_retrofit.create(IConnectAPI.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        MenuItem back=menu.findItem(R.id.back);
        back.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                finish();
                return false;
            }
        });
        MenuItem search=menu.findItem(R.id.action_search);
        SearchView searchView=(SearchView)search.getActionView();
        searchView.setQueryHint("Search anything.");
        searchView.onActionViewExpanded();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(SearchActivity.this, ""+s, Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                if(!oldQuery.equals(s) && !s.equals("")){
                            iConnectAPI.searchByName(s).enqueue(new Callback<SearchResponse>() {
                                @Override
                                public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                                    if(null!=response.body()) {
                                        if (!response.body().getUserList().isEmpty()) {
                                            oldQuery=s;
                                            clearContents();
                                            userListItems.addAll(response.body().getUserList());
                                            userSearchAdapter=new UserSearchAdapter(userListItems);
                                            users_rv.setHasFixedSize(true);
                                            users_rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                            users_rv.setAdapter(userSearchAdapter);
                                        } else {
                                            oldQuery=s;
                                            clearContents();
                                            userSearchAdapter.notifyDataSetChanged();
                                            Toast.makeText(SearchActivity.this, "No match found", Toast.LENGTH_SHORT).show();
                                        }
                                    }else{
                                        Toast.makeText(SearchActivity.this, "Error fetching user.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<SearchResponse> call, Throwable t) {
                                    Toast.makeText(SearchActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }else{
                    clearContents();
                    userSearchAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void clearContents() {
        userListItems.clear();
    }

}
