package com.tadhg.moodchart.activity;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tadhg.moodchart.R;
import com.tadhg.moodchart.database.DataBaseHelper;
import com.tadhg.moodchart.database.MoodDAO;
import com.tadhg.moodchart.model.Mood;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Tadhg on 22/09/2015.
 */
public class HomeFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup radioGroup;
    private RadioButton great, good, average, bad,terrible;
    private Button button;
    private TextView textView, dateText;
    Calendar dateCalendar;
    Mood mood;
    private AddMoodTask task;
    private MoodDAO moodDAO;

    Calendar c = Calendar.getInstance();
    SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
    //Or use only MMM if you want only 3 characters in the month
    String formattedDate = df.format(c.getTime());

    public HomeFragment() {
        // Required empty public constructor
    }

    DataBaseHelper sqliteHelper;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqliteHelper = new DataBaseHelper((getActivity()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        dateText = (TextView) rootView.findViewById(R.id.date);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        //Or use only MMM if you want only 3 characters in the month
        String formattedDate = df.format(c.getTime());
        dateText.setText(formattedDate);

        radioGroup = (RadioGroup) rootView.findViewById(R.id.myRadioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected


                if(checkedId == R.id.great){
                   Toast.makeText(getActivity(), "choice: Great",
                            Toast.LENGTH_SHORT).show();
                } else if(checkedId == R.id.good) {
                    Toast.makeText(getActivity(), "choice: Good", Toast.LENGTH_SHORT).show();

                }else if(checkedId == R.id.average) {
                    Toast.makeText(getActivity(), "choice: Average",
                            Toast.LENGTH_SHORT).show();
                } else if(checkedId == R.id.bad) {
                    Toast.makeText(getActivity(), "choice: Bad",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getActivity(), "choice: Terrible",
                            Toast.LENGTH_SHORT).show();
                }
            }

        });




        great = (RadioButton) rootView.findViewById(R.id.great);
        good = (RadioButton) rootView.findViewById(R.id.good);
        average = (RadioButton) rootView.findViewById(R.id.average);
        bad = (RadioButton) rootView.findViewById(R.id.bad);
        terrible = (RadioButton) rootView.findViewById(R.id.terrible);
        textView = (TextView) rootView.findViewById(R.id.text);

        button = (Button)rootView.findViewById(R.id.chooseBtn);
        button.setOnClickListener(new View.OnClickListener() {


            //@Override
            public void onClick(View v) {
                //int checkedId = radioGroup.getCheckedRadioButtonId();

                boolean checked = ((RadioButton) v).isChecked();


                // find which radioButton is checked by id
                switch(v.getId()) {
                    case R.id.great:
                        if (checked)
                            textView.setText("You chose 'Great'");
                        break;


                }
                    switch(v.getId()) {
                        case R.id.good:
                            if (checked)
                                textView.setText("You chose 'Good'");
                            break;

                    }

                        switch(v.getId()) {
                            case R.id.average:
                                if (checked)
                                    textView.setText("You chose 'Average'");
                                break;
                        }

                            switch(v.getId()) {
                                case R.id.bad:
                                    if (checked)
                                        textView.setText("You chose 'Bad'");
                                    break;
                            }

                                switch (v.getId()) {
                                    case R.id.terrible:
                                        if (checked)
                                            textView.setText("You chose 'Terrible'");
                                        break;
                                }


           setMood();
                task = new AddMoodTask(getActivity());
                task.execute((Void) null);
            }


        });


        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }


    public class AddMoodTask extends AsyncTask<Void, Void, Long> {

        private final WeakReference<Activity> activityWeakRef;

        public AddMoodTask(Activity context) {
            this.activityWeakRef = new WeakReference<Activity>(context);
        }

        @Override
        protected Long doInBackground(Void... arg0) {

            long result = moodDAO.save(mood);
            return result;
        }

        @Override
        protected void onPostExecute(Long result) {
            if (activityWeakRef.get() != null
                    && !activityWeakRef.get().isFinishing()) {
                if (result != -1)
                    Toast.makeText(activityWeakRef.get(), "Mood Saved",
                            Toast.LENGTH_LONG).show();
            }
        }
    }









    private void setMood() {
        mood = new Mood();

        String date = df.format(dateCalendar.getTime());

            mood.setDate(date);
            mood.setGreat(radioGroup.getCheckedRadioButtonId());
            mood.setGood(radioGroup.getCheckedRadioButtonId());//)
            mood.setAverage(radioGroup.getCheckedRadioButtonId());
            mood.setBad(radioGroup.getCheckedRadioButtonId());
            mood.setTerrible(radioGroup.getCheckedRadioButtonId());

        }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
