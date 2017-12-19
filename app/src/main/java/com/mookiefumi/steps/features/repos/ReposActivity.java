package com.mookiefumi.steps.features.repos;

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
import com.squareup.picasso.Picasso;

public class ReposActivity extends AppCompatActivity implements View.OnClickListener, IReposView {

    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private IReposPresenter presenter;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        progressBar = findViewById(R.id.progress);
        DoubleBounce doubleBounce = new DoubleBounce();
        progressBar.setIndeterminateDrawable(doubleBounce);

        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatEditText searchText = findViewById(R.id.search_text);
                Search(searchText.getText().toString());
            }
        });

        presenter = new ReposPresenter(this);
        adapter = new MainAdapter(this, presenter);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        presenter.getReposFromApi("MookieFumi");
    }

    @Override
    public void NotifyDataSetChanged() {
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(presenter.getItems().size());
    }

    @Override
    public void ShowMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void Search(String searchText) {
        presenter.getReposFromApi(searchText);
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

    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RepoViewHolder> {

        private IReposPresenter presenter;
        private Context context;

        public MainAdapter(Context context, IReposPresenter presenter) {
            this.context = context;
            this.presenter = presenter;
        }

        @Override
        public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RepoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_row_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RepoViewHolder holder, final int position) {
            final Repo repo = presenter.getItems().get(position);
            holder.nameTextView.setText(repo.getName());
            //holder.descriptionTextView.setText(repo.getDescription().toString());
            Picasso.with(this.context).load(repo.getOwner().getAvatarUrl()).into(holder.profileImageView);

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

            return presenter.getItems().size();
        }


        public class RepoViewHolder extends RecyclerView.ViewHolder {
            TextView nameTextView;
            TextView descriptionTextView;
            ImageView profileImageView;

            public RepoViewHolder(View itemView) {
                super(itemView);
                nameTextView = (TextView) itemView.findViewById(R.id.name);
                descriptionTextView = (TextView) itemView.findViewById(R.id.description);
                profileImageView = (ImageView) itemView.findViewById(R.id.image);
            }
        }
    }
}

