package com.example.apicollaboration.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.apicollaboration.ArraySavingClass.array_saving_class.alTMapPoint;

import com.example.apicollaboration.ArraySavingClass.array_saving_class;
import com.example.apicollaboration.R;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.util.logging.LogManager;

public class CenterActivity extends AppCompatActivity {
    TMapView tMapView;
    TextView address_textView;
    TextView name_textView;
    Bitmap bitmap;
    Bitmap temp;
    TMapPoint centerP;
    TMapData tmapdata;


    //
    String addressOfCenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        LinearLayout linearLayoutTmap = (LinearLayout) findViewById(R.id.mapviewCenter);
        tMapView = new TMapView(this);
        name_textView = (TextView) findViewById(R.id.nameOfLocation);
        address_textView = (TextView) findViewById(R.id.nameOfAddress);

        linearLayoutTmap.addView(tMapView);

        bitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.group6);

        temp = BitmapFactory.decodeResource(this.getResources(), R.drawable.group6);

            tMapView.setCenterPoint(array_saving_class.center_point.getLatitude(), array_saving_class.center_point.getLongitude());

            TMapMarkerItem center = new TMapMarkerItem();
            centerP = new TMapPoint(array_saving_class.center_point.getLongitude(), array_saving_class.center_point.getLatitude());

            center.setIcon(bitmap); // 마커 아이콘 지정
            center.setCanShowCallout(true); // 풍선뷰의 사용여부를 설정한다.
            center.setCalloutSubTitle("중간지점 입니다."); // 풍선뷰 보조메시지 설정
            center.setCalloutTitle("중간지점 "); // 풍선뷰 제목 설정
            center.setCalloutRightButtonImage(temp); // 풍선뷰 오른쪽 이미지 설정
            center.setAutoCalloutVisible(true); // 풍선뷰 자동 활성화

            // 마커의 좌표 지정
            center.setTMapPoint(centerP); // 마커 위,경도 설정
            center.setVisible(TMapMarkerItem.VISIBLE); // 아이콘 보이게
            //지도에 마커 추가
            tMapView.addMarkerItem("markerItem", center);

            // 중간지점에 자동차경로 추가
            drawline();
    }



    // 자동차 경로 호출시 외부에서 Thread를 통해서 호출해줘야 정상적으로 실행

    public void drawline() {

        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    for(int i=0; i< alTMapPoint.size(); i++) {

                        TMapPolyLine tMapPolyLine = new TMapData().findPathData(alTMapPoint.get(i), centerP);

                        if(i ==0)
                            tMapPolyLine.setLineColor(Color.BLUE);

                        else if (i ==1)
                            tMapPolyLine.setLineColor(Color.RED);

                        else if (i ==2)
                            tMapPolyLine.setLineColor(Color.GREEN);

                        else if (i ==3)
                            tMapPolyLine.setLineColor(Color.CYAN);

                        else if (i ==4)
                            tMapPolyLine.setLineColor(Color.BLACK);

                            tMapPolyLine.setLineWidth(2);
                        tMapView.addTMapPolyLine("Line1" + i, tMapPolyLine);
                    }

                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void markReturn() {

        for (int i = 0; i < alTMapPoint.size(); i++) {

            TMapMarkerItem markerItem1 = new TMapMarkerItem();
            // 마커 아이콘 지정
            markerItem1.setIcon(bitmap);
            markerItem1.setCanShowCallout(true);
            markerItem1.setCalloutSubTitle("위치:" + array_saving_class.nameOfIt.get(array_saving_class.nameOfIt.size() - 1)); // 위치 자동으로 찍히게 수정해야함
            markerItem1.setCalloutTitle("좌표 " + Integer.toString(i + 1) + "번");
            markerItem1.setCalloutRightButtonImage(temp);

            markerItem1.setAutoCalloutVisible(true);
            // 마커의 좌표 지정
            markerItem1.setTMapPoint(alTMapPoint.get(i));
            //지도에 마커 추가
            tMapView.addMarkerItem("markerItem" + i, markerItem1);
            tMapView.setCenterPoint(alTMapPoint.get(i).getLatitude(), alTMapPoint.get(i).getLatitude());

        }
    }

    @Override
    public void onBackPressed() {
        Intent backMainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backMainIntent);
    }
}