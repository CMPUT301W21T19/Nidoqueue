package com.example.nidoqueue;

import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SignInActivity extends AppCompatActivity implements ExperienceCreateFragment.OnFragmentInteractionListener {

    ImageButton add, options, search;
    RecyclerView created, subscribed;
    DatabaseManager dbManager;
    FirebaseFirestore db;
    User user;
    String android_id;

    ArrayList<Experiment> createdExps;
    ArrayList<String> createdExpsName;

    ExpListAdapter adapter;

    boolean doubleBackToExitPressedOnce = false;

public class SignInActivity extends AbstractActivity {
    ImageButton options;

    static RequestManager requestManager = RequestManager.getInstance();
    static ContextManager contextManager = ContextManager.getInstance();

    private View.OnClickListener Options = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            requestManager.signInOptions();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_user);
        contextManager.setContext(this);


        dbManager = (DatabaseManager) getApplicationContext();
        db = dbManager.getDb();
        user = dbManager.getUser();
        android_id = dbManager.getAndroid_id();

        createdExps = new ArrayList<>();
        createdExpsName = new ArrayList<>();

        add = findViewById(R.id.create_exp_button);
        options = findViewById(R.id.options_button);
        search = findViewById(R.id.search_button1);
        add.setOnClickListener(v -> {
            new ExperienceCreateFragment(null, null, null, null, null, null, dbManager).show(getSupportFragmentManager(), "Create Exp");
        });
        options.setOnClickListener(v -> options());
        search.setOnClickListener(v -> search());

        db.collection("users")
                .document(android_id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Object object = document.getData();
                            String json = new Gson().toJson(object);
                            Log.v("json", json);
                            try {
                                JSONArray expArray = new JSONObject(json).getJSONArray("createdExp");
                                Log.v("json", expArray.toString());
                                Log.d("Length", String.valueOf(expArray.length()));
                                int i = 0;
                                while (i < expArray.length()) {
                                    Log.d("Update", "Progress");
                                    //String key = iterator.next();
                                    //SONObject objArray = expArray.getJSONObject(key);
                                    String expType = expArray.getJSONObject(i).getString("type");
                                    Log.d("Type", expType);
                                    Experiment experiment = null;
                                    switch (expType) {
                                        case "count":
                                            experiment = new ExpCount(user, expArray.getJSONObject(i).getString("name"), expArray.getJSONObject(i).getString("description"), expArray.getJSONObject(i).getBoolean("geoLocation"));
                                            break;
                                        case "binomial":
                                            experiment = new ExpBinomial(user, expArray.getJSONObject(i).getString("name"), expArray.getJSONObject(i).getString("description"), expArray.getJSONObject(i).getBoolean("geoLocation"));
                                            break;
                                        case "nonNegative":
                                            experiment = new ExpNonNegative(user, expArray.getJSONObject(i).getString("name"), expArray.getJSONObject(i).getString("description"), expArray.getJSONObject(i).getBoolean("geoLocation"));
                                            break;
                                        case "measurement":
                                            experiment = new ExpMeasurement(user, expArray.getJSONObject(i).getString("name"), expArray.getJSONObject(i).getString("description"), expArray.getJSONObject(i).getString("unit"), expArray.getJSONObject(i).getBoolean("geoLocation"));
                                            break;
                                    }
                                    createdExps.add(experiment);
                                    i++;
                                }

                                createdExpsName = new ArrayList<>();
                                for (Experiment exps : createdExps) {
                                    createdExpsName.add(exps.getName());
                                    Log.d("Name", exps.getName());
                                }

                                adapter = new ExpListAdapter(createdExpsName);

                                created = findViewById(R.id.created_exps_list);
                                created.setLayoutManager(new LinearLayoutManager(this));
                                created.setAdapter(adapter);

                                subscribed = findViewById(R.id.sub_exps_list);
                                subscribed.setLayoutManager(new LinearLayoutManager(this));
                                subscribed.setAdapter(adapter);

//                                DividerItemDecoration dividerItemDecoration =
//                                        new DividerItemDecoration(created.getContext(),new LinearLayoutManager(this).getOrientation());
//                                created.addItemDecoration(dividerItemDecoration);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //createdExps = document.toObject(ExpDocument.class).experiments;
                        }
                    }
                });

//        ExpListAdapter adapter = new ExpListAdapter(createdExpsName);
//
//
//
//        created.setLayoutManager(new LinearLayoutManager(this));
//        created.setAdapter(adapter);

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    public void options() {
        Intent intent = new Intent(this, UserProfileActivity.class);
        startActivity(intent);
    }

    public void search() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void onOkPressed(Experiment exp, String type) {
        Log.d("Test", "Clicked");

        db.collection("experiments")
                .document(exp.getName().toLowerCase())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            Log.d("FireStore", "Document exists!");
                            Toast.makeText(getApplicationContext(), "Name already exists!\nTry use other name", Toast.LENGTH_LONG).show();
                        } else {
                            user.createExp(exp);
                            db.collection("experiments")
                                    .document(exp.getName().toLowerCase())
                                    .set(exp)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Map<String, String> ownerId = new HashMap<>();
                                            ownerId.put("owner", android_id);
                                            db.collection("experiments")
                                                    .document(exp.getName().toLowerCase())
                                                    .set(ownerId, SetOptions.merge());

                                            db.collection("users")
                                                    .document(android_id)
                                                    .update("createdExp", FieldValue.arrayUnion(exp));
                                        }
                                    });
                        }
                    } else {
                        Log.d("FireStore", "Failed with: ", task.getException());
                    }
                });
    }
}
