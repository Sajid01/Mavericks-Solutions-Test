package info.androidhive.recyclerview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import info.androidhive.recyclerview.AddPersonDialog.onSubmitListener;


public class MainActivity extends AppCompatActivity implements onSubmitListener{
    private List<PersonsName> personsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PersonsAdapter mAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new PersonsAdapter(personsList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PersonsName names = personsList.get(position);
                Toast.makeText(getApplicationContext(), names.getFirstName() + ", " + names.getLastName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPersonDialog fragment1 = new AddPersonDialog();
                fragment1.mListener = MainActivity.this;
                //fragment1.text = mTextView.getText().toString();
                fragment1.show(getFragmentManager(), "");
            }
        });

        new AsyncTaskParseJson().execute();
    }



    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("names_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    // you can make this class as another java file so it will be separated from your main activity.
    public class AsyncTaskParseJson extends AsyncTask<String, String, String> {

        // contacts JSONArray
        JSONArray dataJsonArr = null;

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {

            try {

                JSONObject obj = new JSONObject(loadJSONFromAsset());

                // get the array of users
                dataJsonArr = obj.getJSONArray("names");

                // loop through all users
                for (int i = 0; i < dataJsonArr.length(); i++) {

                    JSONObject c = dataJsonArr.getJSONObject(i);

                    // Storing each json item in variable
                    String firstname = c.getString("firstname");
                    String lastname = c.getString("lastname");

                    preparePersonsData(firstname, lastname);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String strFromDoInBg) {}
    }

    private void preparePersonsData(String firstName, String lastName) {
        PersonsName persons = new PersonsName(firstName, lastName);
        personsList.add(persons);

        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setOnSubmitListener(String firstName, String lastName) {
        PersonsName persons = new PersonsName(firstName, lastName);
        personsList.add(persons);

        mAdapter.notifyDataSetChanged();
    }
}
