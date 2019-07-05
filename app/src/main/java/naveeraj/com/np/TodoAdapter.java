package naveeraj.com.np;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends BaseAdapter {

    private List<Todo> todos = new ArrayList<>();


    Context context;

    public TodoAdapter(Context context) {
        this.context = context;
    }

    @Override public int getCount() {
        return todos.size();
    }

    @Override public Todo getItem(int position) {
        if (position < 0 || position >= todos.size()) {
            return null;
        } else {
            return todos.get(position);
        }
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        final View view = (convertView != null ? convertView : createView(parent));
        final GitHubRepoViewHolder viewHolder = (GitHubRepoViewHolder) view.getTag();
        Todo obj=getItem(position);

        viewHolder.setResturant(obj,context);
        return view;
    }

    public void setTodos(@Nullable List<Todo> repos) {
        if (repos == null) {
            return;
        }
        todos.clear();
        todos.addAll(repos);
        notifyDataSetChanged();
    }

    private View createView(ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.item_restaurant, parent, false);
        final GitHubRepoViewHolder viewHolder = new GitHubRepoViewHolder(view);
        view.setTag(viewHolder);
        return view;
    }

    private static class GitHubRepoViewHolder {

        private TextView title;
        private TextView descrption;
        private TextView dates;


        public GitHubRepoViewHolder(View view) {
            title = (TextView) view.findViewById(R.id.title);
            descrption = (TextView) view.findViewById(R.id.description);
            dates = (TextView) view.findViewById(R.id.dates);

        }

        public void setResturant(Todo todo,Context context) {
            title.setText(todo.getTitle());
            descrption.setText(todo.getDescription());
            dates.setText(todo.getStartDate()+" to "+todo.getEndDate());


        }
    }
}
