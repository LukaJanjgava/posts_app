package com.luukitoo.myapplication.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.luukitoo.myapplication.databinding.ItemPostBinding;
import com.luukitoo.myapplication.model.remote.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<Post> postList = new ArrayList<>();

    public void updateList(List<Post> newList) {
        PostDiffCallback callback = new PostDiffCallback(postList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback);
        postList = newList;
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(
                ItemPostBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.bind(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        private ItemPostBinding binding;

        public PostViewHolder(ItemPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Post post) {
            binding.idTextView.setText(String.valueOf(post.id));
            binding.titleTextView.setText(post.title);
            binding.bodyTextView.setText(post.body);
        }
    }

    private class PostDiffCallback extends DiffUtil.Callback {

        public List<Post> oldList;
        public List<Post> newList;

        public PostDiffCallback(List<Post> oldList, List<Post> newList) {
            this.oldList = oldList;
            this.newList = newList;
        }

        @Override
        public int getOldListSize() {
            return oldList.size();
        }

        @Override
        public int getNewListSize() {
            return newList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            Post oldPost = oldList.get(oldItemPosition);
            Post newPost = newList.get(newItemPosition);
            return oldPost.id == newPost.id;
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            Post oldPost = oldList.get(oldItemPosition);
            Post newPost = newList.get(newItemPosition);
            return oldPost.userId == newPost.userId &&
                    oldPost.id == newPost.id &&
                    oldPost.title.equals(newPost.title) &&
                    oldPost.body.equals(newPost.body);
        }
    }
}
