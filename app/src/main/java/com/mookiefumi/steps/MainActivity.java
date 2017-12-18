package com.mookiefumi.steps;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mookiefumi.steps.services.pojos.Repo;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity implements IMainView {

    private RecyclerView recyclerView;
    private MainAdapter adapter;
    private IMainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);
        adapter = new MainAdapter(this, presenter);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        presenter.getRepos();
    }

    @Override
    public void NotifyDataSetChanged() {
        adapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(presenter.getItems().size());
    }

    public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RepoViewHolder> {

        private IMainPresenter presenter;
        private Context context;

        public MainAdapter(Context context, IMainPresenter presenter) {
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
            holder.descriptionTextView.setText(repo.getDescription().toString());
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

