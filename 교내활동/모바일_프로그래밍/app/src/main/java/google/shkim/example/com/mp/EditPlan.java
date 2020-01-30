package google.shkim.example.com.mp;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class EditPlan extends Activity {

    private Database dbHelper;
    private static final String DEBUG_TAG = "{LOG_ANDROID}";
    private int year= Calendar.getInstance().get(Calendar.YEAR);
    private int month=Calendar.getInstance().get(Calendar.MONTH)+1;
    private int date=Calendar.getInstance().get(Calendar.DATE);

    private DatePickerDialog.OnDateSetListener dateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int Year, int monthOfYear, int dayOfMonth) {
            year=Year;
            month=monthOfYear+1;
            date=dayOfMonth;
            TextView dateShow=(TextView)findViewById(R.id.dateValue);
            if(month<10 && date<10)
                dateShow.setText(year+"년 "+"0"+month+"월 "+"0"+date+"일");
            else if(month<10 && date>=10)
                dateShow.setText(year+"년 "+"0"+month+"월 "+date+"일");
            else if(month>10 && date<10)
                dateShow.setText(year+"년 "+month+"월 "+"0"+date+"일");
            else
                dateShow.setText(year+"년 "+month+"월 "+date+"일");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_plan);
        dbHelper = new Database(getApplicationContext(), "travelHelper.db", null, 1);
        Button setDate=(Button)findViewById(R.id.setDate);
        Button setAddress = (Button)findViewById(R.id.setAddress);
        Button submit=(Button)findViewById(R.id.submit);
        Button back=(Button)findViewById(R.id.back);
        final EditText getName = (EditText)findViewById(R.id.placeNameValue);
        final EditText getDate = (EditText)findViewById(R.id.dateValue);
        final TimePicker getTime = (TimePicker)findViewById(R.id.timeValue);
        final TextView getAddress = (TextView)findViewById(R.id.AddressValue);
        final TextView getlat = (TextView)findViewById(R.id.latValue);
        final TextView getlng = (TextView)findViewById(R.id.lngValue);
        getDate.setEnabled(false);

        final Intent intent = getIntent();
        if(intent.getStringExtra("bool").equals("1"))
        {
            getName.setText(intent.getStringExtra("Title"));
            String intentHour = intent.getStringExtra("Time").substring(0, 2);
            String intentMinute = intent.getStringExtra("Time").substring(4, 6);
            getTime.setHour((int) Double.parseDouble(intentHour));
            getTime.setMinute((int) Double.parseDouble(intentMinute));
            getlat.setText(intent.getStringExtra("Lat"));
            getlng.setText(intent.getStringExtra("Lng"));
            getAddress.setText(intent.getStringExtra("Address"));
            Log.d(DEBUG_TAG, "id0 : " + intent.getStringExtra("id"));
        }
        final Cursor cursor = dbHelper.select("SELECT * FROM tempSave;");
        cursor.moveToFirst();

        if(cursor.getCount()>0) {
            Log.d(DEBUG_TAG, "name : " + cursor.getString(0));
            Log.d(DEBUG_TAG, "Fulldate : " + cursor.getString(1));
            Log.d(DEBUG_TAG, "year : " + cursor.getInt(2));
            Log.d(DEBUG_TAG, "month : " + cursor.getInt(3));
            getName.setText(cursor.getString(0));
            getDate.setText(cursor.getString(1));
            getTime.setHour(cursor.getInt(2));
            getTime.setMinute(cursor.getInt(3));
            dbHelper.delete("delete from tempSave;");
        }
        else {
            if (intent.getStringExtra("bool").equals("1"))
                getDate.setText(intent.getStringExtra("Date"));
            else if (month < 10 && date < 10)
                getDate.setText(year + "년 " + "0" + month + "월 " + "0" + date + "일");
            else if (month < 10 && date >= 10)
                getDate.setText(year + "년 " + "0" + month + "월 " + date + "일");
            else if (month > 10 && date < 10)
                getDate.setText(year + "년 " + month + "월 " + "0" + date + "일");
            else
                getDate.setText(year + "년 " + month + "월 " + date + "일");

        }
        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(EditPlan.this,dateSetListener,year,month-1,date).show();
            }
        });
        setAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = getName.getText().toString();
                String fullDatevalue = getDate.getText().toString();
                String yearValue = fullDatevalue.substring(0,4);
                String monthValue =  fullDatevalue.substring(6,8);
                String dateValue =  fullDatevalue.substring(10,12);
                int hourValue = getTime.getHour();
                int minuteValue = getTime.getMinute();


                dbHelper.insert("insert into tempSave values('" + nameValue + "','" + fullDatevalue + "', " + hourValue + ", " + minuteValue + ");");
                if(!getAddress.getText().equals(""))
                {
                    String dAddr = getAddress.getText().toString();
                    double dLat = Double.parseDouble(getlat.getText().toString());
                    double dLng = Double.parseDouble(getlng.getText().toString());

                }
                Intent intent1 = new Intent(EditPlan.this,google.class);
                intent1.putExtra("bool",intent.getStringExtra("bool"));
                intent1.putExtra("id",intent.getStringExtra("id"));
                startActivity(intent1);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameValue = getName.getText().toString();
                String fullDatevalue = getDate.getText().toString();
                String yearValue = fullDatevalue.substring(0,4);
                String monthValue =  fullDatevalue.substring(6,8);
                String dateValue =  fullDatevalue.substring(10,12);
                int hourValue = getTime.getHour();
                int minuteValue = getTime.getMinute();
                String latString = getlat.getText().toString();
                String lngString = getlng.getText().toString();
                String addValue = getAddress.getText().toString();

                Log.d(DEBUG_TAG, "name : " + nameValue);
                Log.d(DEBUG_TAG, "Fulldate : " + fullDatevalue);
                Log.d(DEBUG_TAG, "year : " + yearValue);
                Log.d(DEBUG_TAG, "month : " + monthValue);
                Log.d(DEBUG_TAG, "date : " + dateValue);
                Log.d(DEBUG_TAG, "hour : " + hourValue);
                Log.d(DEBUG_TAG, "minute : " + minuteValue);

                Log.d(DEBUG_TAG, "location : " + latString + ", " + lngString);
                if(nameValue.length() == 0)
                    Toast.makeText(getApplicationContext(), "장소명을 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(fullDatevalue.length() == 0)
                    Toast.makeText(getApplicationContext(), "날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                else if(latString.length() == 0)
                    Toast.makeText(getApplicationContext(), "위치를 입력해주세요", Toast.LENGTH_SHORT).show();
                else
                {
                    Intent intent2 = getIntent();
                    if(!intent2.getStringExtra("bool").equals("0"))
                    {

                        dbHelper.delete("delete from infos where _id = "+intent2.getStringExtra("id"));
                    }

                    dbHelper.insert("insert into infos(PlaceName,Year,Month,Date,Hour,Minute,Lat,Lng,PlaceAdd) values('" + nameValue + "'," + yearValue + ", " + monthValue + ", " + dateValue + ", " + hourValue + ", " + minuteValue+"," +latString + ", " + lngString +", '"+addValue+"');");
                    Intent intent = new Intent(EditPlan.this,index.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditPlan.this,index.class);
                startActivity(intent);
                finish();
            }
        });



        final Cursor cursor1 = dbHelper.select("SELECT * FROM markerPoint;");
        cursor1.moveToFirst();
        if(cursor1.isFirst()) {
            getAddress.setText(cursor1.getString(0));
            getlat.setText(cursor1.getString(1));
            getlng.setText(cursor1.getString(2));
            dbHelper.select("DELETE FROM markerPoint;");
        }
    }
    public void onBackPressed()
    {
        Intent intent = new Intent(EditPlan.this,index.class);
        startActivity(intent);
        finish();
    }
}

