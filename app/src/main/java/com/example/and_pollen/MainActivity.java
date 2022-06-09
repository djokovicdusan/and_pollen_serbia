package com.example.and_pollen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_pollen.responses.AllergensResponse;
import com.example.and_pollen.responses.ConcentrationsResponse;
import com.example.and_pollen.model.Locations;
import com.example.and_pollen.responses.PollenResponse;
import com.example.and_pollen.network.AllergensAPI;
import com.example.and_pollen.network.ConcentrationsAPI;
import com.example.and_pollen.network.LocationsAPI;
import com.example.and_pollen.network.PollensAPI;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private EditText etDateAfter, etDateBefore;
    private Spinner spinner;
    private ArrayList<String> pomocnaLista = new ArrayList<>();
    private Button button;
    private ArrayList<DisplayClass> displayClassArrayList = new ArrayList<>();
    private DisplayClass granica = new DisplayClass("-----------------------", 0);
    private ArrayList<DisplaySuperclass> displaySuperclassArrayList = new ArrayList<>();
    private ArrayList<String> datesList = new ArrayList<>();
    PollensAPI pollensAPI;
    ConcentrationsAPI concentrationsAPI;
    AllergensAPI allergensAPI;
    int pomocniID;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    private int day, month, year;
    private ListView pollenReport;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    int brojacKonekcija = 0;

    RelativeLayout rlProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = findViewById(R.id.LocationSpinner);
        etDateAfter = findViewById(R.id.editTxtDateAfter);
        etDateBefore = findViewById(R.id.editTxtDateBefore);
        recyclerView = findViewById(R.id.recyclerViewPollenReport);
        button = findViewById(R.id.btnDisplayData);
        rlProgressBar = findViewById(R.id.rlProgressBar);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://polen.sepa.gov.rs")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LocationsAPI locApi = retrofit.create(LocationsAPI.class);
        Call<List<Locations>> call = locApi.getLocations();

        pollensAPI = retrofit.create(PollensAPI.class);
        concentrationsAPI = retrofit.create(ConcentrationsAPI.class);
        allergensAPI = retrofit.create(AllergensAPI.class);


        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,this,displayClassArrayList,datesList);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                displayClassArrayList.clear();
                datesList.clear();

                if (spinner.getSelectedItemId() != 0) {
                    getPollens(new PollenCallback() {
                        @Override
                        public void onError(String message) {
                            System.out.println("Nesto nije u redu sa polenom" + message);
                        }

                        @Override
                        public void onResponse(ArrayList<PollenResponse> pollenResponseArrayList) {

                            System.out.println("Prvi poziv je uspesan!");
                            //ovde zoves dalje
                            for (PollenResponse p : pollenResponseArrayList) {
                                DisplaySuperclass displaySuperclass = new DisplaySuperclass();
                                displaySuperclass.setDate(p.getDate());
                                System.out.println("Date: " + p.getDate());
                                datesList.add(p.getDate());
                                for (int i : p.getConcentrations()) {
                                    getConcentrations(i, new ConcentrationsCallback() {
                                        @Override
                                        public void onError(String message) {
                                            System.out.println("Nesto nije u redu sa koncentracijama" + message);
                                        }

                                        @Override
                                        public void onResponse(int id, int value) {
                                            // sada imamo id alergena, ovde moramo uhvatiti value
                                            DisplayClass displayClass = new DisplayClass();
                                            displayClass.setValue(value);
                                            // idemo po ime alergena

                                            getAllergens(id, new AllergensCallback() {
                                                @Override
                                                public void onError(String message) {
                                                    System.out.println("Nesto nije u redu sa alergenima" + message);
                                                }

                                                @Override
                                                public void onResponse(String allergen_name) {

                                                    displayClass.setAllergen_name(allergen_name);
                                                    displayClass.setDate(p.getDate());

                                                    displayClassArrayList.add(displayClass);
//                                                    arrayAdapter.notifyDataSetChanged();
//                                                    customAdapter = new CustomAdapter(MainActivity.this,MainActivity.this,displayClassArrayList,datesList);

                                                    customAdapter.notifyDataSetChanged();

                                                    //progress bar start

                                                }
                                            });


                                        }
                                    });
                                    //displayClassArrayList.add(granica);

                                }
                                //superklasa
                                displaySuperclass.setData(displayClassArrayList);
                                displaySuperclassArrayList.add(displaySuperclass);
                            }

                            //ispis dispplay superclasse

                            for (DisplaySuperclass d : displaySuperclassArrayList) {
                                System.out.println(d.getDate());
                                System.out.println(d.getData().size() + "velicina");
                                for (DisplayClass c : d.getData()) {
                                    System.out.println(c.toString());
                                }
                            }
//                            System.out.println(displayClassArrayList.toString());

                        }


                    });
                    for (DisplaySuperclass d : displaySuperclassArrayList) {
                        System.out.println(d.getDate());
                        System.out.println(d.getData().size() + " velicina liste");
                        for (DisplayClass c : d.getData()) {
                            System.out.println(c.toString());
                        }
                    }


                } else
                    Toast.makeText(MainActivity.this, "Niste odabrali sva potrebna polja!", Toast.LENGTH_SHORT).show();

//                Toast.makeText(MainActivity.this, "Dugme je kliknuto !", Toast.LENGTH_SHORT).show();



            }

        });

        etDateAfter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate(etDateAfter);

            }
        });
        etDateBefore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDate(etDateBefore);
            }
        });

        ArrayAdapter<String> locationsArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, pomocnaLista);
        spinner.setAdapter(locationsArrayAdapter);

        call.enqueue(new Callback<List<Locations>>() {
            @Override
            public void onResponse(Call<List<Locations>> call, Response<List<Locations>> response) {


                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    //txtView.setText("Code:" + response.code());
                    return;
                }
                List<Locations> posts = response.body();


                //prikaz sadrzaja
                pomocnaLista.add("Одаберите град");

                for (Locations p : posts) {
                    pomocnaLista.add(p.getName().toString());
                    locationsArrayAdapter.notifyDataSetChanged();
                }


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        for (Locations p : posts) {
                            if (pomocnaLista.get(i).equals(p.getName().toString())) {
                                pomocniID = p.getId();
                                break;
                            }
                        }
                        //Toast.makeText(MainActivity.this, pomocnaLista.get(i) + " selected", Toast.LENGTH_SHORT).show();
                        System.out.println(pomocniID);


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onFailure(Call<List<Locations>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //getConcentrations(490453);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        switch (id) {

            case R.id.action_info: {
                Intent intent = new Intent(getBaseContext(), InfoActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                return true;

            }
            case R.id.action_quiz: {
                Intent intent = new Intent(getBaseContext(), QuestionnaireActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

                return true;
            }
            default:
                return true;
        }

    }

    public interface PollenCallback {
        void onError(String message);

        void onResponse(ArrayList<PollenResponse> pollenResponseArrayList);
    }

    public void getPollens(PollenCallback pollenCallback) {
        String dateAfter = etDateAfter.getText().toString();
        String dateBefore = etDateBefore.getText().toString();
        if (dateAfter.isEmpty()) {
            etDateAfter.setError("Unesite datum");

        }
        if (dateBefore.isEmpty()) {
            etDateBefore.setError("Unesite datum");
        } else {

            Call<PollenSuperclass> call_pollen = pollensAPI.getPollen(dateAfter, dateBefore, pomocniID);

            dodajKonekciju();
            call_pollen.enqueue(new Callback<PollenSuperclass>() {

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onResponse(Call<PollenSuperclass> call, Response<PollenSuperclass> response) {
                    izbaciKonekciju();
//                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    PollenSuperclass proba = response.body();
                    ArrayList<PollenResponse> pomocnaLista = proba.getResults();
                    pollenCallback.onResponse(pomocnaLista);

                }

                @Override
                public void onFailure(Call<PollenSuperclass> call, Throwable t) {
                    pollenCallback.onError(t.getMessage());

                }
            });
        }


    }

    public interface ConcentrationsCallback {
        void onError(String message);

        void onResponse(int id, int value);
    }

    public void getConcentrations(int id, ConcentrationsCallback concentrationsCallback) {

        Call<ConcentrationsSuperclass> call = concentrationsAPI.getConcentration(id);
        dodajKonekciju();
        call.enqueue(new Callback<ConcentrationsSuperclass>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<ConcentrationsSuperclass> call, Response<ConcentrationsSuperclass> response) {
                izbaciKonekciju();
                ConcentrationsSuperclass concentrationsSuperclass = response.body();
                ConcentrationsResponse concentrationsResponse = concentrationsSuperclass.getResults().get(0);
                concentrationsCallback.onResponse(concentrationsResponse.getAllergen(), concentrationsResponse.getValue());

            }

            @Override
            public void onFailure(Call<ConcentrationsSuperclass> call, Throwable t) {
                concentrationsCallback.onError(t.getMessage());

            }

        });


    }

    public interface AllergensCallback {
        void onError(String message);

        void onResponse(String allergen_name);
    }


    public void getAllergens(int id, AllergensCallback allergensCallback) {
        Call<List<AllergensResponse>> call = allergensAPI.getAllergen(id);
        dodajKonekciju();
        call.enqueue(new Callback<List<AllergensResponse>>() {
            @Override
            public void onResponse(Call<List<AllergensResponse>> call, Response<List<AllergensResponse>> response) {

                izbaciKonekciju();
                List<AllergensResponse> pomocnaLista = response.body();
                String ime = pomocnaLista.get(0).getLocalized_name();
                allergensCallback.onResponse(ime);

            }

            @Override
            public void onFailure(Call<List<AllergensResponse>> call, Throwable t) {
                allergensCallback.onError(t.getMessage());


            }
        });

    }

    public interface DisplayDataCallback {
        void onError(String message);

        void onResponse(ArrayList<DisplayClass> displayClassArrayList);

    }



    public void chooseDate(EditText et) {

        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);
        // date picker dialog
        datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String monthDisplay;
                        String dayDisplay;
                        if (monthOfYear < 9) {
                            monthDisplay = "0" + String.valueOf(++monthOfYear);
                        } else {
                            monthDisplay = String.valueOf(++monthOfYear);
                        }
                        if (dayOfMonth < 10) {
                            dayDisplay = "0" + String.valueOf(dayOfMonth);
                        } else {
                            dayDisplay = String.valueOf(dayOfMonth);
                        }

                        et.setText(year + "-" + monthDisplay + "-" + dayDisplay);
                        et.setError(null);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void dodajKonekciju() {
        brojacKonekcija++;
        System.out.println(brojacKonekcija);
        rlProgressBar.setVisibility(View.VISIBLE);

    }

    public void izbaciKonekciju() {
        brojacKonekcija--;
        System.out.println(brojacKonekcija);
        if (brojacKonekcija == 0){
            rlProgressBar.setVisibility(View.GONE);
        }
    }
}