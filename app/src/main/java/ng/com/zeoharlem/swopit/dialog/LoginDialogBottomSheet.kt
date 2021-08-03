package ng.com.zeoharlem.swopit.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ng.com.zeoharlem.swopit.R

class LoginDialogBottomSheet: BottomSheetDialogFragment() {
    val TAG = "MainActivity"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        Log.d("MainActivity", "Empty at first")

        val initDayOfWeek: Int  = 5;
        val dayOfWeek           = when(initDayOfWeek){
            1 -> "monday"
            2 -> "tuesday"
            3 -> "wednesday"
            4 -> "thursday"
            5 -> "friday"
            6 -> "saturday"
            7 -> "sunday"
            else -> "day not found"
        }

        Log.e("MainActivity", "onCreate: $dayOfWeek")

        val numberTypCheck: Any = 5.8f
        val checkedTypeState    = when(numberTypCheck){
            is Float -> "Float"
            is String -> "String"
            is Int -> "Integer"
            is Boolean -> "Boolean"
            is Double -> "Double"
            is Array<*> -> "Array"
            else -> "Nothing found in data type"
        }
        Log.e("MainActivity", "DataType: $checkedTypeState")

        val schoolPeriod    = 4
        val timeForSchool   = when(schoolPeriod){
            in (1..5) -> "School Time"
            in (6..7) -> "Weekends"
            else -> "Day not included"
        }
        Log.e("MainActivity", "TimeForSchool: $timeForSchool")

        var myNewNumber: Int    = 1;
        while(myNewNumber < 10){
            myNewNumber += 1
            Log.e("WhileLoop", "onCreate: $myNewNumber")
        }

        for(myRangeNumber in (1..5)){
            Log.e("MainActivity", "ForLoop: $myRangeNumber", )
        }

        for(i in 5 downTo 1){
            Log.e(TAG, "DownTo: $i", )
        }

        for(myStepNumber in 1..20 step 2){
            Log.e(TAG, "Step by 2: $myStepNumber", )
        }

        val myArrayString: Array<String>    = arrayOf("Study","Power","Knowlefge","Understanding")
        for (myElement in myArrayString){
            Log.e(TAG, "ArrayElement: $myElement")
        }
        for (index in myArrayString.indices){
            Log.e(TAG, "index[$index]: = ${myArrayString[index]}", )
        }
        for ((index, element) in myArrayString.withIndex()){
            Log.e(TAG, "index:[$index] = Value: $element", )
        }
        val myString: String?   = null
        Log.e(TAG, "MyStringLength: ${myString?.length}", )

        myString?.let {
            Log.e(TAG, "Expecting No Null: ${it.length}", )
        }

        val getUsingElvis   = myString?.length ?: -1
        Log.e(TAG, "Elvis Operator: $getUsingElvis", )

        return inflater.inflate(R.layout.login_bottomsheet_dialog, container, false)

    }
}