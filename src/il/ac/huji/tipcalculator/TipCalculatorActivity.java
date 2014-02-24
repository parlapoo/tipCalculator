package il.ac.huji.tipcalculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculatorActivity extends Activity {
	private final static double TIP_PERCENTAGE = (double)12 /100;
	private final static String TIP_PREFIX = "Tip: $";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		final EditText billAmountText = (EditText)findViewById(R.id.editBillAmount);
		final CheckBox checkRound = (CheckBox)findViewById(R.id.chkround);
		final TextView tipResult = (TextView)findViewById(R.id.txtTipResult);
		final Button calculate = (Button)findViewById(R.id.btnCalculate);
		
		calculate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				double result = 0.0;
				int resultInt = 0;
				try{
					result = Double.parseDouble(billAmountText.getText().toString());
				}catch(NumberFormatException e) {
					return;
				}
				result *= TIP_PERCENTAGE;
				if (result == Math.floor(result)){
					resultInt = (int)Math.floor(result);
					tipResult.setText(TIP_PREFIX+resultInt);
				}
				else if (checkRound.isChecked()){
					double difference = result - Math.floor(result);
					
					if (difference > 0.5)
						resultInt = (int)Math.ceil(result);
					else
						resultInt = (int)Math.floor(result);
					
					tipResult.setText(TIP_PREFIX+resultInt);
				}
				else
					tipResult.setText(TIP_PREFIX+result);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tip_calculator, menu);
		return true;
	}

}
