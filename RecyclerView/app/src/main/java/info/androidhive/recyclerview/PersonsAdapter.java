package info.androidhive.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.MyViewHolder> {

    private List<PersonsName> personsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }


    public PersonsAdapter(List<PersonsName> personsList) {
        this.personsList = personsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.person_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PersonsName names = personsList.get(position);


        StringBuilder sbLastName = new StringBuilder(names.getLastName().toLowerCase());
        sbLastName.setCharAt(0, Character.toUpperCase(sbLastName.charAt(0)));
        String lastName = sbLastName.toString();

        StringBuilder sbFirstName = new StringBuilder(names.getFirstName().toLowerCase());
        sbFirstName.setCharAt(0, Character.toUpperCase(sbFirstName.charAt(0)));
        String firstName = sbFirstName.toString();


        holder.title.setText(lastName + ", " + firstName);
    }

    @Override
    public int getItemCount() {
        return personsList.size();
    }
}
