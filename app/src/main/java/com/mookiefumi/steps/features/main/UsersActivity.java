package com.mookiefumi.steps.features.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.mookiefumi.steps.R;
import com.mookiefumi.steps.services.pojos.Repo;
import com.mookiefumi.steps.services.pojos.User;
import com.squareup.picasso.Picasso;

public class UsersActivity extends AppCompatActivity implements View.OnClickListener, IUsersView {

    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private IUsersPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        progressBar = findViewById(R.id.progress);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        presenter = new UsersPresenter(this);
        adapter = new UserAdapter(this, presenter);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        presenter.getUsersFromApi();
    }

    @Override
    public void NotifyDataSetChanged() {
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(presenter.getResult().getItems().size());
    }

    @Override
    public void ShowMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Search() {
        presenter.getUsersFromApi();
    }

    @Override
    public void SetBusy(Boolean busy) {
        LinearLayout layout = (LinearLayout) progressBar.getParent();
        if (busy) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {

    }

    public class UserAdapter extends RecyclerView.Adapter<UserAdapter.RepoViewHolder> {

        private IUsersPresenter presenter;
        private Context context;

        public UserAdapter(Context context, IUsersPresenter presenter) {
            this.context = context;
            this.presenter = presenter;
        }

        @Override
        public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RepoViewHolder holder, final int position) {
            final User user = presenter.getResult().getItems().get(position);
            holder.loginTextView.setText(user.getLogin());
            //holder.descriptionTextView.setText(user.getDescription().toString());
            Picasso.with(this.context).load(user.getAvatarUrl()).into(holder.avatarImageView);

            /*holder.tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (view != null) {
                        view.navigateToDetails(position+1);
                    }
                }
            });*/
        }

        @Override
        public int getItemCount() {

            return presenter.getResult().getItems().size();
        }


        public class RepoViewHolder extends RecyclerView.ViewHolder {
            TextView loginTextView;
            ImageView avatarImageView;

            public RepoViewHolder(View itemView) {
                super(itemView);
                loginTextView = (TextView) itemView.findViewById(R.id.login);
                avatarImageView = (ImageView) itemView.findViewById(R.id.avatar);
            }
        }
    }
}

