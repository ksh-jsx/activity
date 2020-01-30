package google.shkim.example.com.mp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Attr;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class index  extends Activity
{
    private ListView m_oListView = null;
    private final long FINISH_INTERVAL_TIME = 2000;
    private long   backPressedTime = 0;
    private ArrayList<ListItem> data = null;
    private int position;
    int currentTab= 1 ;
    Button gotoPlan;
    Button del;
    ArrayList arrayList;
    private int count = 0;
    String[] items;
    String[] items1;
    String[] items2;
    String selectedItem;
    final Context context = this;
    private static final String DEBUG_TAG = "{LOG_ANDROID}";
    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        gotoPlan = (Button)findViewById(R.id.gotoPlan);
        del = (Button)findViewById(R.id.del);
        String dateSave = "a";

        final tempView tempview = new tempView();
        ImageView tabWidget0 = new ImageView(this);
        tabWidget0.setImageResource(R.drawable.tab_00);
        ImageView tabWidget1 = new ImageView(this);
        tabWidget1.setImageResource(R.drawable.tab_01);
        ImageView tabWidget2 = new ImageView(this);
        tabWidget2.setImageResource(R.drawable.tab_02);

        final Database dbHelper = new Database(getApplicationContext(), "travelHelper.db", null, 1);
        final TextView TextTitle = (TextView) findViewById(R.id.textTitle);
        ListView listview = (ListView)findViewById(R.id.List_view);
        TextView noticeText = (TextView)findViewById(R.id.notice);
        TextView noticeText2 = (TextView)findViewById(R.id.notice2);
        TextView textTitle = (TextView)findViewById(R.id.textTitle);
        TextView Attractions = (TextView)findViewById(R.id.AttractionName);
        final EditText setLoad = (EditText)findViewById(R.id.setLoad);
        Button loadBtn = (Button)findViewById(R.id.loadBtn);
        Button subtractBtn = (Button)findViewById(R.id.subtractBtn);
        ListView pakingList = (ListView)findViewById(R.id.pakingList);

        String[] country = {};
        String[] temperature = {};
        String[] loadSum = {};
        ;

        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final Button button1 = (Button) findViewById(R.id.imgBtn1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        final Button button2 = (Button) findViewById(R.id.imgBtn2);
        final Spinner spinner3 = (Spinner)findViewById(R.id.spinner3);
        final Button button3 = (Button)findViewById(R.id.imgBtn3);
        final Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        final Button button4 = (Button) findViewById(R.id.imgBtn4);
        final Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        final Button button5 = (Button) findViewById(R.id.imgBtn5);
        final Spinner spinner6 = (Spinner) findViewById(R.id.spinner6);
        final Button button6 = (Button) findViewById(R.id.imgBtn6);
        final Spinner spinner7 = (Spinner) findViewById(R.id.spinner7);
        final Button button7 = (Button) findViewById(R.id.imgBtn7);
        final Spinner spinner8 = (Spinner) findViewById(R.id.spinner8);
        final Button button8 = (Button) findViewById(R.id.imgBtn8);
        final Spinner spinner9 = (Spinner) findViewById(R.id.spinner9);
        final Button button9 = (Button) findViewById(R.id.imgBtn9);


        final TabHost tabhost = (TabHost)findViewById(R.id.tabHost1);
        tabhost.setup();

        TabHost.TabSpec ts1 = tabhost.newTabSpec("Tab Spec 1") ;
        ts1.setContent(R.id.content1) ;
        ts1.setIndicator(tabWidget0);
        tabhost.addTab(ts1)  ;

        // 두 번째 Tab. (탭 표시 텍스트:"TAB 2"), (페이지 뷰:"content2")
        final TabHost.TabSpec ts2 = tabhost.newTabSpec("Tab Spec 2") ;
        ts2.setContent(R.id.content2) ;
        ts2.setIndicator(tabWidget1);
        tabhost.addTab(ts2) ;

        // 세 번째 Tab. (탭 표시 텍스트:"TAB 3"), (페이지 뷰:"content3")
        TabHost.TabSpec ts3 = tabhost.newTabSpec("Tab Spec 3") ;
        ts3.setContent(R.id.content3) ;
        ts3.setIndicator(tabWidget2);
        tabhost.addTab(ts3) ;

        tabhost.setCurrentTab(currentTab);

        String[] necessary_items = {"여권","현금","신용카드","바우처","볼펜","수첩","목베개","담요",
                "안대","휴대폰 충전기","보조 배터리  --캐리어에 넣으면 안됨!","노트북","카메라","귀중품",
                "자물쇠","속옷","잠옷","양말","여분 신발","슬리퍼","수건","스킨","로션","선크림","렌즈, 세쳑용품","치약",
                "칫솔","클렌징 용품","샴푸","린스","바디 워시","면도기","각종 충전기","멀티어댑터","선글라스",
                "셀카봉","상비약","지퍼백","면봉","비상식량","옷걸이","보조가방","압축팩","손톱깎이","모기향"};

        String[] korea = {"접을 수 있는 대형가방"};
        String[] japan = {"110V 플로그 어댑터","동전지갑","접을 수 있는 대형가방"};
        String[] china = {"vpn(중국은 sns접속 불가!)","황사마스크"};
        String[] south_asia = {"모기 퇴치제","자외선 차단제","휴대용 선풍기"};
        String[] etc = {"전압 변환기"};

        String[] Celsius_30 = {"반팔 상의","샌들","반바지"};
        String[] Celsius_20 = {"얇은 긴팔 상의","셔츠","긴바지","반팔"};
        String[] Celsius_10 = {"긴팔 상의","긴 바지","겉옷","셔츠"};
        String[] Celsius_00 = {"긴팔 상의","긴 바지","두꺼운 겉옷"};

        /*String[] in_cabin = {"여권","현금","신용카드","바우처","볼펜","수첩","목베개","담요","안대","휴대폰 충전기","보조 배터리  --캐리어에 넣으면 안됨!","노트북","카메라","귀중품","자물쇠"};
        String[] clothes = {"옷","속옷","잠옷","양말","신발","수건"};
        String[] cosmetics = {"스킨","로션","선크림","렌즈, 세쳑용품"};

        String[] washing_tools = {"치약","칫솔","클렌징 용품","샴푸","린스","바디 워시","면도기"};
        String[] props = {"각종 충전기","멀티어댑터","선글라스","셀카봉","상비약"};
        String[] helpful = {"지퍼백","면봉","비상식량","옷걸이","보조가방","압축팩","손톱깎이","모기향"};
        */

        double latitude_ave = 0;
        int latitude_count = 0;
        double longitude_ave = 0;
        int longitude_count = 0;
        double month_ave = 0;
        int month_count = 0;

        Cursor latitude = dbHelper.select("select Lat from infos");
        Cursor longitude = dbHelper.select("select Lng from infos");
        Cursor month = dbHelper.select("select Month from infos");
        Cursor load = dbHelper.select("select * from baggage");
        latitude.moveToFirst();
        longitude.moveToFirst();
        month.moveToFirst();
        load.moveToFirst();
        if(latitude.isFirst())
        {
            while (latitude.getCount() != latitude_count) {
                latitude_ave += latitude.getDouble(0);
                latitude.moveToNext();
                latitude_count++;

            }
            while (longitude.getCount() != longitude_count) {
                longitude_ave += longitude.getDouble(0);
                longitude.moveToNext();
                longitude_count++;

            }
            while (month.getCount() != month_count) {
                month_ave += month.getInt(0);
                month.moveToNext();
                month_count++;
            }
        }
        latitude_ave /= latitude_count;
        Log.d(DEBUG_TAG, "latitude_ave : " + latitude_ave);
        longitude_ave /= longitude_count;
        Log.d(DEBUG_TAG, "longitude_ave : " + longitude_ave);
        month_ave /= month_count;
        Log.d(DEBUG_TAG, "month_ave : " + month_ave);

         String countryName = getCountryName(context,latitude_ave,longitude_ave);

            if (countryName.equals("Japan")) // 위도,경도가 일본영역
            {
                Attractions.setText("당신의 여행지는 '일본' 이군요?");
                country = japan;

            } else if (countryName.equals("Korea")) // 위도,경도가 한국영역
            {
                Attractions.setText("당신의 여행지는 '한국' 이군요?");
                country = korea;
            } else if (countryName.equals("China")) // 위도,경도가 중국영역
            {
                Attractions.setText("당신의 여행지는 '중국' 이군요?");
                country = china;
            } else if (countryName.equals("Thailand") || countryName.equals("Philippine") || countryName.equals("Vietnam") || countryName.equals("Singapore") || countryName.equals("Laos") || countryName.equals("India") || countryName.equals("Bangladesh") || countryName.equals("Nepal") || countryName.equals("Myanmar")) {
                Attractions.setText("당신의 여행지는 '동남아' 이군요?");
                country = south_asia;
            } else {
                Attractions.setText("당신의 여행지는 '" + countryName + "' 이군요?");
                country = etc;
            }

            if (latitude_ave >= -10 && latitude_ave <= 10) {
                temperature = Celsius_30;
            } else if (latitude_ave >= 10 && latitude_ave <= 20) {
                if (month_ave == 1 || month_ave == 2 || month_ave == 12)  // month_ave가 1,2,12월
                    temperature = Celsius_20;

                else
                    temperature = Celsius_30;
            } else if (latitude_ave <= -10 && latitude_ave >= -20) {
                if (month_ave == 6 || month_ave == 7 || month_ave == 8)  // month_ave가 1,2,12월
                    temperature = Celsius_20;

                else
                    temperature = Celsius_30;
            } else if (latitude_ave >= 20 && latitude_ave <= 30) {
                if (month_ave == 1 || month_ave == 2 || month_ave == 3 || month_ave == 4 || month_ave == 10 || month_ave == 11 || month_ave == 12)
                    temperature = Celsius_20;
                else
                    temperature = Celsius_30;
            } else if (latitude_ave <= -20 && latitude_ave >= -30) {
                if (month_ave == 1 || month_ave == 2 || month_ave == 3 || month_ave == 4 || month_ave == 10 || month_ave == 11 || month_ave == 12)
                    temperature = Celsius_30;
                else
                    temperature = Celsius_20;
            } else if (latitude_ave >= 30 && latitude_ave <= 40) {
                if (month_ave == 1 || month_ave == 2 || month_ave == 12)  // month_ave가 1,2,12월
                    temperature = Celsius_00;
                else if (month_ave == 3 || month_ave == 4 || month_ave == 10 || month_ave == 11)  // month_ave가 3,4,10,11월
                    temperature = Celsius_10;

                else if (month_ave == 5 || month_ave == 9)  // month_ave가 5,9월
                    temperature = Celsius_20;

                else if (month_ave == 6 || month_ave == 7 || month_ave == 8)  // month_ave가 6,7,8월
                    temperature = Celsius_30;
            } else if (latitude_ave <= -30 && latitude_ave >= -40) {
                if (month_ave == 6 || month_ave == 7 || month_ave == 8)
                    temperature = Celsius_00;
                else if (month_ave == 3 || month_ave == 4 || month_ave == 10 || month_ave == 11)
                    temperature = Celsius_20;

                else if (month_ave == 5 || month_ave == 9)  // month_ave가 5,9월
                    temperature = Celsius_10;

                else if (month_ave == 12 || month_ave == 1 || month_ave == 2)
                    temperature = Celsius_30;
            } else if (latitude_ave >= 40 && latitude_ave <= 50) {
                if (month_ave == 1 || month_ave == 2 || month_ave == 12 || month_ave == 11 || month_ave == 3)
                    temperature = Celsius_20;
                else if (month_ave == 4 || month_ave == 10 || month_ave == 5 || month_ave == 9)
                    temperature = Celsius_10;

                else if (month_ave == 6 || month_ave == 7 || month_ave == 8)
                    temperature = Celsius_00;
            } else if (latitude_ave >= 50 && latitude_ave <= 60) {
                if (month_ave == 6 || month_ave == 7 || month_ave == 8)
                    temperature = Celsius_00;
                else
                    temperature = Celsius_10;

            } else
                temperature = Celsius_00;


            if(load.isFirst())
            {
                loadSum = new String[load.getCount()];
                for(int i=0;i<load.getCount();i++)
                {
                    loadSum[i] = load.getString(0);
                    load.moveToNext();
                }
            }

            items1 = new String[necessary_items.length+country.length];
            items2 = new String[temperature.length+loadSum.length];
            System.arraycopy(necessary_items,0,items1,0,necessary_items.length);
            System.arraycopy(country,0,items1,necessary_items.length,country.length);
            System.arraycopy(temperature,0,items2,0,temperature.length);
            System.arraycopy(loadSum,0,items2,temperature.length,loadSum.length);
            items = new String[items1.length+items2.length];
            System.arraycopy(items1,0,items,0,items1.length);
            System.arraycopy(items2,0,items,items1.length,items2.length);
            ArrayAdapter adapterPackng = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
            pakingList.setAdapter(adapterPackng);



        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(setLoad.getText().toString().length() > 1)
                {
                    Cursor bag = dbHelper.select("select * from baggage");
                    bag.moveToFirst();
                    boolean istComplete = true;
                    if(bag.getCount() != 0)
                    {
                        for (int i = 0; i < bag.getCount(); i++)
                        {
                            if (setLoad.getText().toString().equals(bag.getString(0)))
                            {
                                Toast.makeText(getApplicationContext(),"이미 들어있어요",Toast.LENGTH_SHORT).show();
                                istComplete =false;
                                break;
                            }
                            bag.moveToNext();
                        }
                    }

                    if(istComplete)
                    {
                        String getText = setLoad.getText().toString();
                        dbHelper.insert("insert into baggage values('" + getText + "');");
                        currentTab = 0;
                        onCreate(savedInstanceState);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"저장 할 내용을 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
        subtractBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getText = setLoad.getText().toString();
                boolean delComplete = true;
                Cursor bag = dbHelper.select("select * from baggage");
                bag.moveToFirst();
                if(bag.getCount() != 0)
                {
                    for (int i = 0; i < bag.getCount(); i++)
                    {
                        if (setLoad.getText().toString().equals(bag.getString(0)))
                        {
                            dbHelper.delete("delete from baggage where item = '" + getText + "';");
                            currentTab = 0;
                            delComplete = false;
                            onCreate(savedInstanceState);

                        }
                        bag.moveToNext();
                    }
                }
                if(delComplete)
                {
                    Toast.makeText(getApplicationContext(),"일치하는 짐이 없어요",Toast.LENGTH_SHORT).show();
                }
            }
        });


        // 데이터 생성.
        Cursor cursor1 = dbHelper.select("select * from infos order by Year asc, Month asc, Date asc, Hour asc, Minute asc");
        cursor1.moveToFirst();
        if(cursor1.getCount() != 0)
        {
            listview.setVisibility(View.VISIBLE);
            pakingList.setVisibility(View.VISIBLE);
            noticeText.setVisibility(View.GONE);
            noticeText2.setVisibility(View.GONE);
        }
        data = new ArrayList<>();
        final ArrayList<ItemData> oData = new ArrayList<>();
        //데이터 삽입
        for (int i=0; i<cursor1.getCount(); ++i)
        {
            ItemData oItem = new ItemData();
            oItem.strId = cursor1.getString(0);
            oItem.strTitle = cursor1.getString(1);
            if(cursor1.getInt(5) < 10)
            {
                if(cursor1.getInt(6) < 10)
                    oItem.strTIme = "0"+cursor1.getInt(5)+"시 "+"0"+cursor1.getInt(6)+"분";
                else
                    oItem.strTIme = "0"+cursor1.getInt(5)+"시 "+cursor1.getInt(6)+"분";
            }
            else if (cursor1.getInt(5) > 10 && cursor1.getInt(6) < 10)
                oItem.strTIme = cursor1.getInt(5)+"시 "+"0"+cursor1.getInt(6)+"분";
            else
                oItem.strTIme = cursor1.getInt(5)+"시 "+cursor1.getInt(6)+"분";

            if(cursor1.getInt(3) < 10)
            {
                if(cursor1.getInt(4) < 10)
                    oItem.strDate = cursor1.getInt(2)+"년 "+"0"+cursor1.getInt(3)+"월 "+"0"+cursor1.getInt(4)+"일";
                else
                    oItem.strDate = cursor1.getInt(2)+"년 "+"0"+cursor1.getInt(3)+"월 "+cursor1.getInt(4)+"일";
            }
            else if (cursor1.getInt(3) > 10 && cursor1.getInt(4) < 10)
                oItem.strDate = cursor1.getInt(2)+"년 "+cursor1.getInt(3)+"월 "+"0"+cursor1.getInt(4)+"일";
            else
                oItem.strDate = cursor1.getInt(2)+"년 "+cursor1.getInt(3)+"월 "+cursor1.getInt(4)+"일";

            oItem.strLat = cursor1.getString(7);
            oItem.strLng = cursor1.getString(8);
            oItem.strAdd = cursor1.getString(9);
            oItem.count1 = i+1;
            oItem.count2 = cursor1.getCount();
            if(!dateSave.equals(oItem.strDate))
                i--;
            else
                cursor1.moveToNext();

            dateSave = oItem.strDate;
            oData.add(oItem);

            ListItem item1 = new ListItem(oItem.strId,oItem.strTitle, oItem.strDate,oItem.strTIme,oItem.strLat,oItem.strLng, oItem.strAdd);
            data.add(item1);
        }

        // ListView 생성
        m_oListView = (ListView)findViewById(R.id.List_view);
        final ListAdapter oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);

        // ListView 클릭 이벤트

        m_oListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {

                LinearLayout dateLinear = (LinearLayout)view.findViewById(R.id.dateLinear) ;
                TextView oTextTitle = (TextView) view.findViewById(R.id.textTitle);
                TextView oImgName = (TextView) view.findViewById(R.id.imgName);
                final TextView oLatName = (TextView) view.findViewById(R.id.textLat);
                final TextView oLngName = (TextView) view.findViewById(R.id.textLng);
                ImageView lineImage = (ImageView)view.findViewById(R.id.lineImage);
                LinearLayout btns = (LinearLayout)view.findViewById(R.id.btns);
                Button modifyButton = (Button)view.findViewById(R.id.listButton1);
                Button deleteButton = (Button)view.findViewById(R.id.listButton2);
                Button showButton = (Button)findViewById(R.id.showLocationBtn);
                Button empty = (Button)findViewById(R.id.empty);
                position = i;
                if(count != 0) tempview.applyView();
                if(btns.getVisibility() == View.INVISIBLE) {
                    view.setBackgroundColor(Color.rgb(51, 170, 187));


                    if (oImgName.getText().equals("last"))
                        lineImage.setImageResource(R.drawable.line_sky_non_bottom);
                    else
                        lineImage.setImageResource(R.drawable.line_sky);

                    if(dateLinear.getVisibility() == View.GONE) {
                        btns.setVisibility(View.VISIBLE);
                        showButton.setVisibility(view.VISIBLE);
                        oTextTitle.setTextColor(Color.WHITE);
                        Animation animation = new AlphaAnimation(0, 1);
                        animation.setDuration(400);
                        showButton.setAnimation(animation);
                    }
                   else {
                        btns.setVisibility(View.GONE);
                        showButton.setVisibility(view.GONE);
                    }
                }


                tempview.setView(view);
                count++;
                //Log.d(DEBUG_TAG, "date0 : " + data.get(position).getDate());
                modifyButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View item1) {
                        Intent intent = new Intent(getApplicationContext(),EditPlan.class);
                        intent.putExtra("bool","1");
                        intent.putExtra("id",data.get(position).getId());
                        intent.putExtra("Title",data.get(position).getTitle());
                        intent.putExtra("Date",data.get(position).getDate());
                        intent.putExtra("Time",data.get(position).getTime());
                        intent.putExtra("Lat",data.get(position).getLat());
                        intent.putExtra("Lng",data.get(position).getLng());
                        intent.putExtra("Address",data.get(position).getAddress());
                        dbHelper.delete("delete from markerPoint;");
                        dbHelper.delete("delete from tempSave;");
                        startActivity(intent);

                    }
                });

                showButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dbHelper.insert("delete from markerPoint");
                        String gotoGoogle = "tempPoint";
                        double dLat = Double.parseDouble(oLatName.getText().toString());
                        double dLng = Double.parseDouble(oLngName.getText().toString());
                        Intent intent = new Intent(index.this, google.class);
                        dbHelper.insert("insert into markerPoint values('" + gotoGoogle + "'," + dLat + ", " + dLng + ");");
                        startActivity(intent);
                    }
                });

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View item2) {
                        dbHelper.delete("delete from infos where _id = "+data.get(position).getId()+";");
                        oData.remove(position);
                        items = new String[0];
                        onCreate(savedInstanceState);

                    }
                });
                empty.performClick();
            }
        });


        gotoPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper.delete("delete from markerPoint;");
                dbHelper.delete("delete from tempSave;");
                Intent intent = new Intent(index.this, EditPlan.class);
                intent.putExtra("bool","0");
                startActivity(intent);
            }
        });


        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("정말 삭제하시겠습니까?");
                //builder.setMessage("AlertDialog Content");
                builder.setPositiveButton("삭제",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.delete("delete from infos;");
                                dbHelper.delete("delete from baggage;");
                                onCreate(savedInstanceState);

                            }
                        });
                builder.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.show();
            }
        });


        final boolean[] countClick ={true,true,true,true,true,true,true,true,true};


        //회화 화면 클릭리스터
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner1.performClick();
                if(countClick[0]) {
                    spinner1.setVisibility(View.VISIBLE);
                    spinner2.setVisibility(View.GONE);
                    spinner3.setVisibility(View.GONE);
                    spinner4.setVisibility(View.GONE);
                    spinner5.setVisibility(View.GONE);
                    spinner6.setVisibility(View.GONE);
                    spinner7.setVisibility(View.GONE);
                    spinner8.setVisibility(View.GONE);
                    spinner9.setVisibility(View.GONE);
                    countClick[0] = false;
                    countClick[1] = true;
                    countClick[2] = true;
                    countClick[3] = true;
                    countClick[4] = true;
                    countClick[5] = true;
                    countClick[6] = true;
                    countClick[7] = true;
                    countClick[8] = true;
                    button1.performClick();

                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner2.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                spinner3.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
                spinner5.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
                spinner7.setVisibility(View.GONE);
                spinner8.setVisibility(View.GONE);
                spinner9.setVisibility(View.GONE);
                spinner2.performClick();
                if(countClick[1]) {
                    countClick[1] = false;
                    countClick[0] = true;
                    countClick[2] = true;
                    countClick[3] = true;
                    countClick[4] = true;
                    countClick[5] = true;
                    countClick[6] = true;
                    countClick[7] = true;
                    countClick[8] = true;
                    button2.performClick();

                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner3.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                spinner2.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
                spinner5.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
                spinner7.setVisibility(View.GONE);
                spinner8.setVisibility(View.GONE);
                spinner9.setVisibility(View.GONE);
                spinner3.performClick();
                if(countClick[2]) {
                    countClick[2] = false;
                    countClick[1] = true;
                    countClick[0] = true;
                    countClick[3] = true;
                    countClick[4] = true;
                    countClick[5] = true;
                    countClick[6] = true;
                    countClick[7] = true;
                    countClick[8] = true;
                    button3.performClick();

                }
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner4.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                spinner2.setVisibility(View.GONE);
                spinner3.setVisibility(View.GONE);
                spinner5.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
                spinner7.setVisibility(View.GONE);
                spinner8.setVisibility(View.GONE);
                spinner9.setVisibility(View.GONE);
                spinner4.performClick();
                if(countClick[3]) {
                    countClick[3] = false;
                    countClick[1] = true;
                    countClick[2] = true;
                    countClick[0] = true;
                    countClick[4] = true;
                    countClick[5] = true;
                    countClick[6] = true;
                    countClick[7] = true;
                    countClick[8] = true;
                    button4.performClick();

                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner5.performClick();
                spinner5.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                spinner2.setVisibility(View.GONE);
                spinner3.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
                spinner7.setVisibility(View.GONE);
                spinner8.setVisibility(View.GONE);
                spinner9.setVisibility(View.GONE);
                if(countClick[4]) {
                    countClick[4] = false;
                    countClick[1] = true;
                    countClick[2] = true;
                    countClick[3] = true;
                    countClick[0] = true;
                    countClick[5] = true;
                    countClick[6] = true;
                    countClick[7] = true;
                    countClick[8] = true;
                    button5.performClick();

                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner6.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                spinner2.setVisibility(View.GONE);
                spinner3.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
                spinner5.setVisibility(View.GONE);
                spinner7.setVisibility(View.GONE);
                spinner8.setVisibility(View.GONE);
                spinner9.setVisibility(View.GONE);
                spinner6.performClick();
                if(countClick[5]) {
                    countClick[5] = false;
                    countClick[1] = true;
                    countClick[2] = true;
                    countClick[3] = true;
                    countClick[4] = true;
                    countClick[0] = true;
                    countClick[6] = true;
                    countClick[7] = true;
                    countClick[8] = true;
                    button6.performClick();

                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner7.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                spinner2.setVisibility(View.GONE);
                spinner3.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
                spinner5.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
                spinner8.setVisibility(View.GONE);
                spinner9.setVisibility(View.GONE);
                spinner7.performClick();
                if(countClick[6]) {
                    countClick[6] = false;
                    countClick[1] = true;
                    countClick[2] = true;
                    countClick[3] = true;
                    countClick[4] = true;
                    countClick[5] = true;
                    countClick[0] = true;
                    countClick[7] = true;
                    countClick[8] = true;
                    button7.performClick();

                }
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner8.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                spinner2.setVisibility(View.GONE);
                spinner3.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
                spinner5.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
                spinner7.setVisibility(View.GONE);
                spinner9.setVisibility(View.GONE);
                spinner8.performClick();
                if(countClick[7]) {
                    countClick[7] = false;
                    countClick[1] = true;
                    countClick[2] = true;
                    countClick[3] = true;
                    countClick[4] = true;
                    countClick[5] = true;
                    countClick[6] = true;
                    countClick[0] = true;
                    countClick[8] = true;
                    button8.performClick();

                }
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner9.setVisibility(View.VISIBLE);
                spinner1.setVisibility(View.GONE);
                spinner2.setVisibility(View.GONE);
                spinner3.setVisibility(View.GONE);
                spinner4.setVisibility(View.GONE);
                spinner5.setVisibility(View.GONE);
                spinner6.setVisibility(View.GONE);
                spinner7.setVisibility(View.GONE);
                spinner8.setVisibility(View.GONE);
                spinner9.performClick();
                if(countClick[8]) {
                    countClick[8] = false;
                    countClick[1] = true;
                    countClick[2] = true;
                    countClick[3] = true;
                    countClick[4] = true;
                    countClick[5] = true;
                    countClick[6] = true;
                    countClick[7] = true;
                    countClick[0] = true;
                    button9.performClick();

                }

            }
        });

        //스피너 내용 삽입

            arrayList = new ArrayList();
            arrayList.add("선택하세요");
            arrayList.add("인사");
            arrayList.add("부탁/요청");
            arrayList.add("감사/축하");
            arrayList.add("사과/용서");
            arrayList.add("질문/응답");


            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner1.setAdapter(adapter1);


            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("인사")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "A1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("부탁/요청")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "A2" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("감사/축하")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "A3" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("사과/용서")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "A4" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("질문/응답")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "A5" + "');");
                        gotoLanguageView();

                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });





            arrayList = new ArrayList();
            arrayList.add("선택하세요");
            arrayList.add("주문");
            arrayList.add("예약/출입구");
            arrayList.add("계산");
            arrayList.add("컴플레인");


            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner2.setAdapter(adapter2);

            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("주문")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "B1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("예약/출입구")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "B2" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("계산")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "B3" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("컴플레인")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "B4" + "');");
                        gotoLanguageView();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });




            arrayList = new ArrayList();
            arrayList.add("선택하세요");
            arrayList.add("체크인 카운터");
            arrayList.add("기내");

            ArrayAdapter<String> adapter3 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner3.setAdapter(adapter3);

            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("체크인 카운터")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "C1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("기내")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "C2" + "');");
                        gotoLanguageView();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });




            arrayList = new ArrayList();
            arrayList.add("선택하세요");
            arrayList.add("택시");
            arrayList.add("버스");
            arrayList.add("지하철(모노레일)");
            arrayList.add("기차/배");
            arrayList.add("운전");


            ArrayAdapter<String> adapter4 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner4.setAdapter(adapter4);

            spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("택시")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "D1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("버스")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "D2" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("지하철(모노레일)")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "D3" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("기차/배")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "D4" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("운전")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "D5" + "');");
                        gotoLanguageView();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });



            arrayList = new ArrayList();
            arrayList.add("선택하세요");

            arrayList.add("마트");
            arrayList.add("문구/서점");
            arrayList.add("의류상점");
            arrayList.add("물건 찾기");
            arrayList.add("가격/구매");
            arrayList.add("계산/포장/배달");
            arrayList.add("교환/환불");


            ArrayAdapter<String> adapter5 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner5.setAdapter(adapter5);

            spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("마트")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "E1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("문구/서점")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "E2" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("의류상점")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "E3" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("물건 찾기")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "E4" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("가격/구매")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "E5" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("계산/배달/포장")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "E6" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("교환/환불")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "E7" + "');");
                        gotoLanguageView();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });



            arrayList = new ArrayList();
            arrayList.add("선택하세요");
            arrayList.add("예약");
            arrayList.add("서비스/식사");
            arrayList.add("체크인/체크아웃");



            ArrayAdapter<String> adapter6 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner6.setAdapter(adapter6);

            spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("예약")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "F1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("서비스/식사")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "F2" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("체크인/체크아웃")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "F3" + "');");
                        gotoLanguageView();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });



            arrayList = new ArrayList();
            arrayList.add("선택하세요");
            arrayList.add("응급상황");
            arrayList.add("분실/도난");
            arrayList.add("재난");
            arrayList.add("교통 사고/위반");


            ArrayAdapter<String> adapter7 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner7.setAdapter(adapter7);

            spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("응급상황")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "G1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("분실/도난")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "G2" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("재난")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "G3" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("교통 사고/위반")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "G4" + "');");
                        gotoLanguageView();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });




            arrayList = new ArrayList();
            arrayList.add("선택하세요");
            arrayList.add("예약/진찰");
            arrayList.add("약 구매/설명");


            ArrayAdapter<String> adapter9 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner9.setAdapter(adapter9);

            spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("예약/진찰")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "H1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("문구/서점")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "H2" + "');");
                        gotoLanguageView();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });



            arrayList = new ArrayList();
            arrayList.add("선택하세요");
            arrayList.add("길 묻기");
            arrayList.add("소요시간 묻기");


            ArrayAdapter<String> adapter8 = new ArrayAdapter<String>
                    (this, android.R.layout.simple_spinner_dropdown_item, arrayList);
            spinner8.setAdapter(adapter8);

            spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    dbHelper.delete("delete from spinnerSelect;");
                    selectedItem = (String) parent.getItemAtPosition(position);
                    if (selectedItem.equals("길 묻기")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "I1" + "');");
                        gotoLanguageView();
                    } else if (selectedItem.equals("소요시간 묻기")) {
                        dbHelper.insert("insert into spinnerSelect values('" + "I2" + "');");
                        gotoLanguageView();
                    }

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }

            });

        }



    public static String getCountryName(Context context, double lat, double lng) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            Address result;

            if (addresses != null && !addresses.isEmpty()) {
                return addresses.get(0).getCountryName();
            }

        } catch (IOException ignored) {
            //do something
        }

        return null;
    }

    public void delShow()
    {

    }

    public void onBackPressed()
    {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)
        {
            finishAffinity();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "뒤로가기 한번 더  누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }
    public void gotoLanguageView()
    {
        Intent intent = new Intent(index.this, languageView.class);
        startActivity(intent);
        finish();
    }

}



