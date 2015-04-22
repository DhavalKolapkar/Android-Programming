    /*
     * Copyright (C) 2014  PlumCalculator
     * Author: Dhaval Kolapkar
     * SJSU ID: 010011625
     * Course: CMPE 277
     */

    package dhaval.kolapkar.sjsu.edu.plumcalculator;

    import android.app.Activity;
    import android.app.Dialog;
    import android.content.Context;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.TextView;
    import android.widget.Toast;

    public class MainActivity extends Activity {

        /*
        Declaration of variables used in the entire program.
        */
        final Context context = this;
        int inNum=0;
        EditText text;
        int result = 0;
        Button one,two,add,calc;
        String s= "0";
        char operator = ' ';

        /*
        Called when the application is starting, before any activity, service, or receiver objects (excluding content providers) have been created.
         */
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            //text gives the display of all the numbers and calculations
            text = (EditText)findViewById(R.id.editText);

            Log.d("MainActivity", "Activity started");

            final int idList[] = {R.id.button1,
                    R.id.button2,
                    R.id.button3,
                    R.id.button4,
                    R.id.button5,
                    R.id.button6,
                    R.id.button7,
                    R.id.button8,
                    R.id.button9,
                    R.id.button0,
                    R.id.buttonSubtract,
                    R.id.buttonClear,
                    R.id.buttonAdd,
                    R.id.buttonEquals};

            for(int id:idList){
                Button btn = (Button) findViewById(id);

                //performs the below functions of computing or storing the digits based on the buttons clicked by the user.
                btn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case R.id.button1:
                            case R.id.button2:
                            case R.id.button3:
                            case R.id.button4:
                            case R.id.button5:
                            case R.id.button6:
                            case R.id.button7:
                            case R.id.button8:
                            case R.id.button9:
                            case R.id.button0:
                                String inDigit =   ((Button) v).getText().toString();
                                if (s.equals("0"))
                                {
                                    if(operator=='-' && result==0 && inNum==0){
                                        s=operator+inDigit;
                                        operator=' ';
                                                      }
                                    else

                                    s= inDigit;

                                } else if (s.length()>6){

                                    Toast msg = Toast.makeText(getApplicationContext(),"Please enter number up to 7 digits",Toast.LENGTH_LONG);
                                    msg.show();
                                }

                                else
                                {
                                    s+=inDigit;
                                }
                                text.setText(s);
                                if(operator == '=')
                                {
                                    result=0;
                                    operator =' ';
                                }
                                break;

                            case R.id.buttonAdd:
                                if (!(operator=='-' || operator=='+'))
                                    inNum = Integer.parseInt(s);
                                if(result!=0)     {
                                    compute();
                                }
                                else
                                result = inNum;
                                s="0";
                                operator = '+';
                                break;

                            case R.id.buttonSubtract:
                                if (!(operator=='-' || operator=='+'))
                                    inNum = Integer.parseInt(s);
                                 if(result!=0)     {
                                          compute();
                                 } else
                                result = inNum;

                                s="0";
                                operator = '-';
                                break;

                            case R.id.buttonClear:
                               //resetting the calculator
                                text.setText("");
                                s="0";
                                operator=' ';
                                result=0;
                                break;
                            case R.id.buttonEquals:
                                Log.d("MainActivity","Result is: "+result);
                                compute();
                                operator=' ';
                                s= ""+result;
                        }

                    }
                });
            }
        }

        /*
            Performs the computation based on the user inputs and returns the result.
         */
        private void compute()
        {
            // TODO Auto-generated method stub
            int inNum = Integer.parseInt(s);
            Log.d("Compute","Starting the computation");
            s = "0";
            if (operator == ' ')
            {
                result = inNum;
            }
            else if (operator == '+')
            {
                result += inNum;
            }

            else if (operator == '-')
            {
                result -= inNum;
            }

            text.setText(String.valueOf(result));
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }
            return super.onOptionsItemSelected(item);
        }

    }