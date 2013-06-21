package com.andbase.activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ab.activity.AbActivity;
import com.ab.global.AbConstant;
import com.ab.util.AbDateUtil;
import com.ab.util.AbStrUtil;
import com.ab.view.wheel.AbNumericWheelAdapter;
import com.ab.view.wheel.AbOnWheelChangedListener;
import com.ab.view.wheel.AbStringWheelAdapter;
import com.ab.view.wheel.AbWheelView;
import com.andbase.R;
import com.andbase.global.MyApplication;

public class WheelActivity extends AbActivity {
	private MyApplication application;
	private View mTimeView1 = null;
	private View mTimeView2 = null;
	private View mDataView1 = null;
	
	private TextView timeTextView1 = null;
	private TextView timeTextView2 = null;
	private TextView mDataTextView1= null;
    
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAbContentView(R.layout.wheel);
        this.setTitleText(R.string.wheel_name);
        this.setLogo(R.drawable.button_selector_back);
        this.setTitleLayoutBackground(R.drawable.top_bg);
		this.setTitleTextMargin(10, 0, 0, 0);
	    this.setLogoLine(R.drawable.line);
	    
        application = (MyApplication)abApplication;
        logoView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        
        mTimeView1 = mInflater.inflate(R.layout.choose_three, null);
        mTimeView2 = mInflater.inflate(R.layout.choose_three, null);
        mDataView1 = mInflater.inflate(R.layout.choose_one, null);
        
        timeTextView1 = (TextView)findViewById(R.id.measureTimeText1);
        timeTextView2 = (TextView)findViewById(R.id.measureTimeText2);
        mDataTextView1 = (TextView)findViewById(R.id.dataTextView1);
        
        timeTextView1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				showDialog(AbConstant.DIALOGBOTTOM,mTimeView1,40);
			}
			
		});
        
        timeTextView2.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				showDialog(AbConstant.DIALOGBOTTOM,mTimeView2,40);
			}
			
		});
        
        mDataTextView1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				showDialog(AbConstant.DIALOGBOTTOM,mDataView1,40);
			}
			
		});
        
        initWheelTime1(mTimeView1);
        initWheelTime2(mTimeView2);
        initWheelData1(mDataView1);
    }
    
    
    public void initWheelData1(View mDataView1){
    	final AbWheelView mWheelView1 = (AbWheelView)mDataView1.findViewById(R.id.wheelView1);
		mWheelView1.setAdapter(new AbNumericWheelAdapter(40, 190));
		// 可循环滚动
		mWheelView1.setCyclic(true);
		// 添加文字
		mWheelView1.setLabel(getResources().getString(R.string.data1_unit));
		// 初始化时显示的数据
		mWheelView1.setCurrentItem(40);
		mWheelView1.setValueTextSize(35);
		mWheelView1.setLabelTextSize(35);
		mWheelView1.setLabelTextColor(0x80000000);
		mWheelView1.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		
		Button okBtn = (Button)mDataView1.findViewById(R.id.okBtn);
		Button cancelBtn = (Button)mDataView1.findViewById(R.id.cancelBtn);
		okBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				removeDialog(1);
				int index = mWheelView1.getCurrentItem();
				String val = mWheelView1.getAdapter().getItem(index);
				mDataTextView1.setText(val);
			}
			
		});
		
		cancelBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				removeDialog(1);
			}
			
		});
    }
    
    
    public void initWheelTime1(View mTimeView1){
    	//
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		String val = AbStrUtil.dateTimeFormat(year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second) ;
		timeTextView1.setText(val);
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };
		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);
		//
		final List<String> textDMList = new ArrayList<String>();
		final List<String> textDMDateList = new ArrayList<String>();
		for(int i=1;i<13;i++){
			if(list_big.contains(String.valueOf(i))){
				for(int j=1;j<32;j++){
					textDMList.add(i+"月"+" "+j+"日");
					textDMDateList.add(year+"-"+i+"-"+j);
				}
			}else{
				if(i==2){
					if(AbDateUtil.isLeapYear(year)){
						for(int j=1;j<28;j++){
							textDMList.add(i+"月"+" "+j+"日");
							textDMDateList.add(year+"-"+i+"-"+j);
						}
					}else{
						for(int j=1;j<29;j++){
							textDMList.add(i+"月"+" "+j+"日");
							textDMDateList.add(year+"-"+i+"-"+j);
						}
					}
				}else{
					for(int j=1;j<29;j++){
						textDMList.add(i+"月"+" "+j+"日");
						textDMDateList.add(year+"-"+i+"-"+j);
					}
				}
			}
			
		}
		String currentDay = month+"月"+" "+day+"日";
		int currentDayIndex = textDMList.indexOf(currentDay);
		
		
		final AbWheelView mWheelView41 = (AbWheelView)mTimeView1.findViewById(R.id.wheelView1);
		final AbWheelView mWheelView42 = (AbWheelView)mTimeView1.findViewById(R.id.wheelView2);
		final AbWheelView mWheelView43 = (AbWheelView)mTimeView1.findViewById(R.id.wheelView3);
		
		// 月日
		mWheelView41.setAdapter(new AbStringWheelAdapter(textDMList,AbStrUtil.strLength("12月"+" "+"12日")));
		mWheelView41.setCyclic(true);
		mWheelView41.setLabel(""); 
		mWheelView41.setCurrentItem(currentDayIndex);
		mWheelView41.setValueTextSize(32);
		mWheelView41.setLabelTextSize(30);
		mWheelView41.setLabelTextColor(0x80000000);
		mWheelView41.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		
		// 时
		mWheelView42.setAdapter(new AbNumericWheelAdapter(1, 24));
		mWheelView42.setCyclic(true);
		mWheelView42.setLabel("点");
		mWheelView42.setCurrentItem(hour-1);
		mWheelView42.setValueTextSize(32);
		mWheelView42.setLabelTextSize(30);
		mWheelView42.setLabelTextColor(0x80000000);
		mWheelView42.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		
		// 分
		mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 60));
		mWheelView43.setCyclic(true);
		mWheelView43.setLabel("分");
		mWheelView43.setCurrentItem(minute-1);
		mWheelView43.setValueTextSize(32);
		mWheelView43.setLabelTextSize(30);
		mWheelView43.setLabelTextColor(0x80000000);
		mWheelView43.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		
		
		
		
		Button okBtn4 = (Button)mTimeView1.findViewById(R.id.okBtn);
		Button cancelBtn4 = (Button)mTimeView1.findViewById(R.id.cancelBtn);
		okBtn4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				removeDialog(1);
				int index1 = mWheelView41.getCurrentItem();
				int index2 = mWheelView42.getCurrentItem()+1;
				int index3 = mWheelView43.getCurrentItem()+1;
				
				String dmStr =  textDMDateList.get(index1);
				Calendar calendar = Calendar.getInstance();
				int second = calendar.get(Calendar.SECOND);
				String val = AbStrUtil.dateTimeFormat(dmStr+" "+index2+":"+index3+":"+second) ;
				timeTextView1.setText(val);
			}
			
		});
		
		cancelBtn4.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				removeDialog(1);
			}
			
		});
    }
    
    public void initWheelTime2(View mTimeView2){
    	//时间选择可以这样实现
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH)+1;
		int day = calendar.get(Calendar.DATE);
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };
		final int START_YEAR = 1990, END_YEAR = 2100;

		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);
		
		
		final AbWheelView mWheelView41 = (AbWheelView)mTimeView2.findViewById(R.id.wheelView1);
		final AbWheelView mWheelView42 = (AbWheelView)mTimeView2.findViewById(R.id.wheelView2);
		final AbWheelView mWheelView43 = (AbWheelView)mTimeView2.findViewById(R.id.wheelView3);
		
		// 年
		mWheelView41.setAdapter(new AbNumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		mWheelView41.setCyclic(true);// 可循环滚动
		mWheelView41.setLabel("年");  // 添加文字
		mWheelView41.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
		mWheelView41.setValueTextSize(32);
		mWheelView41.setLabelTextSize(30);
		mWheelView41.setLabelTextColor(0x80000000);
		mWheelView41.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		
		// 月
		mWheelView42.setAdapter(new AbNumericWheelAdapter(1, 12));
		mWheelView42.setCyclic(true);
		mWheelView42.setLabel("月");
		mWheelView42.setCurrentItem(month-1);
		mWheelView42.setValueTextSize(32);
		mWheelView42.setLabelTextSize(30);
		mWheelView42.setLabelTextColor(0x80000000);
		mWheelView42.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		
		// 日
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month + 1))) {
			mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month + 1))) {
			mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if (AbDateUtil.isLeapYear(year)){
				mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 29));
			}else{
				mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 28));
			}
		}
		mWheelView43.setCyclic(true);
		mWheelView43.setLabel("日");
		mWheelView43.setCurrentItem(day - 1);
		mWheelView43.setValueTextSize(32);
		mWheelView43.setLabelTextSize(30);
		mWheelView43.setLabelTextColor(0x80000000);
		mWheelView43.setCenterSelectDrawable(this.getResources().getDrawable(R.drawable.wheel_select));
		
		// 添加"年"监听
		AbOnWheelChangedListener wheelListener_year = new AbOnWheelChangedListener() {

			public void onChanged(AbWheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(mWheelView42.getCurrentItem() + 1))) {
					mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(mWheelView42.getCurrentItem() + 1))) {
					mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 30));
				} else {
					if (AbDateUtil.isLeapYear(year_num))
						mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 29));
					else
						mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 28));
				}
			}
		};
		// 添加"月"监听
		AbOnWheelChangedListener wheelListener_month = new AbOnWheelChangedListener() {

			public void onChanged(AbWheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(month_num))) {
					mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 30));
				} else {
					int year_num = mWheelView41.getCurrentItem() + START_YEAR;
					if (AbDateUtil.isLeapYear(year_num))
						mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 29));
					else
						mWheelView43.setAdapter(new AbNumericWheelAdapter(1, 28));
				}
			}
		};
		mWheelView41.addChangingListener(wheelListener_year);
		mWheelView42.addChangingListener(wheelListener_month);
		
		Button okBtn = (Button)mTimeView2.findViewById(R.id.okBtn);
		Button cancelBtn = (Button)mTimeView2.findViewById(R.id.cancelBtn);
		okBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				removeDialog(1);
				int indexYear = mWheelView41.getCurrentItem();
				String year = mWheelView41.getAdapter().getItem(indexYear);
				
				int indexMonth = mWheelView42.getCurrentItem();
				String month = mWheelView42.getAdapter().getItem(indexMonth);
				
				int indexDay = mWheelView43.getCurrentItem();
				String day = mWheelView43.getAdapter().getItem(indexDay);
				
				timeTextView2.setText(year+"-"+month+"-"+day);
			}
			
		});
		
		cancelBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				removeDialog(1);
			}
			
		});
    }
    
}


