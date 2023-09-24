package com.example.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.math.sqrt
import android.widget.Toast
import android.text.TextWatcher
import android.text.Editable
import android.view.KeyEvent

class MainActivity : AppCompatActivity() {

    private lateinit var num0: Button
    private lateinit var num1: Button
    private lateinit var num2: Button
    private lateinit var num3: Button
    private lateinit var num4: Button
    private lateinit var num5: Button
    private lateinit var num6: Button
    private lateinit var num7: Button
    private lateinit var num8: Button
    private lateinit var num9: Button
    private lateinit var add: Button
    private lateinit var minus: Button
    private lateinit var time: Button
    private lateinit var divide: Button
    private lateinit var sqrt: Button
    private lateinit var equal: Button
    private lateinit var sign: Button
    private lateinit var clear: Button
    //private lateinit var textView: EditText
    private var tex: String = ""
    private var store: String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        num0 = findViewById(R.id.num0)
        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        num3 = findViewById(R.id.num3)
        num4 = findViewById(R.id.num4)
        num5 = findViewById(R.id.num5)
        num6 = findViewById(R.id.num6)
        num7 = findViewById(R.id.num7)
        num8 = findViewById(R.id.num8)
        num9 = findViewById(R.id.num9)
        minus = findViewById(R.id.minus)
        add = findViewById(R.id.add)
        equal = findViewById(R.id.equal)
        sqrt = findViewById(R.id.sqrt)
        time = findViewById(R.id.time)
        divide = findViewById(R.id.divide)
        sign = findViewById(R.id.sign)
        clear = findViewById(R.id.clear)
        val textView = findViewById<EditText>(R.id.text)

       textView.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                if (s.toString().contains("sqrt")) {
                    tex = s.toString()
                    val new = s.toString().replace("sqrt", "s")
                    store = new
                }
                else {
                    tex = s.toString()
                    store = s.toString()
                }
                if (tex == "0.0") {
                    tex = ""
                    store = ""
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })


        clear.setOnClickListener { view: View ->
            tex = ""
            store = ""
            //textView.text = "0.0"
            textView.setText("0.0")
        }
        add.setOnClickListener { view: View ->
            //tex += "+"
            //store += "+"
            val temp = tex + "+"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        minus.setOnClickListener { view: View ->
            //tex += "-"
            //store += "-"
            val temp = tex + "-"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        time.setOnClickListener { view: View ->
            //tex += "*"
            //store += "*"
            val temp = tex + "*"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        divide.setOnClickListener { view: View ->
            //tex += "/"
            //store +="/"
            val temp = tex + "/"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        sqrt.setOnClickListener { view: View ->
            //tex += "sqrt"
            //store+= "s"
            val temp = tex + "sqrt"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        sign.setOnClickListener { view: View ->
            //tex += "."
            //store += "."
            val temp = tex + "."
            //textView.text = "$tex"
            textView.setText("$temp")
        }

        num0.setOnClickListener { view: View ->
            if (tex.length != 0)
            {
                //tex += "0"
                //store += "0"
                val temp = tex + "0"
                //textView.text = "$tex"
                textView.setText("$temp")
            }
        }
        num1.setOnClickListener { view: View ->
            //tex += "1"
            //store += "1"
            val temp = tex + "1"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        num2.setOnClickListener { view: View ->
            //tex += "2"
            //store += "2"
            val temp = tex + "2"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        num3.setOnClickListener { view: View ->
            //tex += "3"
            //store += "3"
            val temp = tex + "3"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        num4.setOnClickListener { view: View ->
            //tex += "4"
            //store += "4"
            val temp = tex + "4"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        num5.setOnClickListener { view: View ->
            //tex += "5"
            //store += "5"
            val temp = tex + "5"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        num6.setOnClickListener { view: View ->
            //tex += "6"
            //store += "6"
            val temp = tex + "6"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        num7.setOnClickListener { view: View ->
            //tex += "7"
            //store += "7"
            val temp = tex + "7"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        num8.setOnClickListener { view: View ->
            //tex += "8"
            //store += "8"
            val temp = tex + "8"
            //textView.text = "$tex"
            textView.setText("$temp")
        }
        num9.setOnClickListener { view: View ->
            //tex += "9"
            //store += "9"
            val temp = tex + "9"
            //textView.text = "$tex"
            textView.setText("$temp")
        }

        fun cal(tex:String): MutableList<Any> {
            var a: MutableList<Any> = mutableListOf()
            var p:Int =0
            var text=""
            if (tex[0]== '+'||tex[0]== '*'||tex[0]== '/'||tex[0]== 's'||tex[p]=='r'||tex[p]=='q'||tex[p]=='t'){
                a.add(""+ tex[p])
            }
            else{
                text+=tex[0]
            }
            p+=1
            while(tex.length != p){
                if (tex[p]=='+'||tex[p]=='-'||tex[p]=='*'||tex[p]=='/'||tex[p]=='s'||tex[p]=='q'||tex[p]=='r'||tex[p]=='t'){
                    if (text != ""){a.add(text.toFloat())}
                    a.add(""+ tex[p])
                    text =""
                    p+=1
                }
                else{
                    text += tex[p]
                    if(tex.length== p+1){
                        a.add(text.toFloat())
                    }
                    p += 1
                }
            }
            return a
        }
        fun culate(a:MutableList<Any>): String{
            while(a.indexOfFirst{it == "s"}!= -1){
                var p = a.indexOfFirst{it == "s"}
                a[p-1] = sqrt(a[p-1] as Float)
                a.removeAt(p)
            }
            while(a.indexOfFirst{it == "/"}!=-1){
                var p = a.indexOfFirst{it == "/"}
                a[p-1] = a[p-1] as Float/a[p+1] as Float
                a.removeAt(p+1)
                a.removeAt(p)
            }
            while(a.indexOfFirst{it == "*"}!=-1){
                var p = a.indexOfFirst{it == "*"}
                a[p-1] = a[p-1] as Float*a[p+1] as Float
                a.removeAt(p+1)
                a.removeAt(p)
            }
            while(a.indexOfFirst{it == "+"}!=-1){
                var p = a.indexOfFirst{it == "+"}
                a[p-1] = a[p-1] as Float+a[p+1] as Float
                a.removeAt(p+1)
                a.removeAt(p)
            }
            while(a.indexOfFirst{it == "-"}!=-1){
                var p = a.indexOfFirst{it == "-"}
                a[p-1] = a[p-1] as Float - a[p+1] as Float
                a.removeAt(p+1)
                a.removeAt(p)
            }
            return a[0].toString()
        }

        fun rpeat(a:MutableList<Any>):Boolean{
            var p = 0
            while (p != a.size -2){
                if (a[p] =="+"||a[p] =="-"||a[p] =="*"||a[p] =="/"||a[p] =="s") {
                    if (a[p+1] =="+"||a[p+1] =="-"||a[p+1] =="*"||a[p+1] =="/"||a[p+1] =="s"){
                        return false
                    }
                }
                p+=1
            }
            return true
        }

        fun sqrcheck(a:MutableList<Any>):Boolean{
            var p = 0
            while (p <= a.size - 4){
                if (a[p] =="q"||a[p] =="r"||a[p] =="t") {
                    return false
                }
                if (a[p] == "s") {
                    if (a[p+1] == "r" || a[p+1] == "t" || a[p+1] == "s") {
                        return false
                    }
                }
                if (a[p] == "s"){
                    if (a[p+1] == "q"){
                        if (a[p+2] == "s" || a[p+2] == "t" || a[p+2] == "q"){
                            return false
                        }
                    }
                }
                if (a[p] == "s"){
                    if (a[p+1] == "q") {
                        if (a[p + 2] == "r") {
                            if (a[p + 3] == "s" || a[p + 3] == "r" || a[p + 3] == "q") {
                                return false
                            }
                        }
                    }
                }
                p+=1
            }


            return true
        }

        fun did0(a:MutableList<Any>):Boolean{
            var p = 0
            while (p != a.size -2){
                if (a[p] =="/") {
                    if (a[p+1] =="0"){
                        return false
                    }
                }
                p+=1
            }
            return true
        }

        fun isNumeric(toCheck: String): Boolean {
            return toCheck.all { char -> char.isDigit() }
        }


        equal.setOnClickListener { view: View ->
            if (store == "") {
                store = ""
                tex = ""
                textView.setText("0.0")
            }
            else if (isNumeric(store)){
                textView.setText(tex)
            }
            else {
                var a = cal(store)
                if (a[0] == "+" || a[0] == "*" || a[0] == "/" || a[0] == "s") {
                    store = ""
                    tex = ""
                    //textView.text = "error:wrong symbol position"
                    textView.setText("error:wrong symbol position")
                } else if (a[a.size - 1] == "+" || a[a.size - 1] == "-" || a[a.size - 1] == "*" || a[a.size - 1] == "/") {
                    store = ""
                    tex = ""
                    //textView.text = "error:wrong symbol position"
                    textView.setText("error:wrong symbol position")
                } else if (!rpeat(a)) {
                    store = ""
                    tex = ""
                    //textView.text = "error:symbols cannot connect"
                    textView.setText("error:symbols cannot connect")
                } else if (!did0(a)) {
                    store = ""
                    tex = ""
                    //textView.text = "error:cannot divide 0"
                    textView.setText("error:cannot divide 0")
                } else if (!sqrcheck(a)) {
                    store = ""
                    tex = ""
                    //textView.text = "error:cannot divide 0"
                    textView.setText("error:wrong symbols or syntax")
                } else {
                    var c = culate(a)
                    tex = c
                    store = c
                    //textView.text = "$c"
                    textView.setText("$c")
                }
            }
        }

        textView.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var a = cal(store)
                if (a[0] =="+"||a[0] =="*"||a[0] =="/"||a[0] =="s"){
                    store = ""
                    tex = ""
                    //textView.text = "error:wrong symbol position"
                    textView.setText("error:wrong symbol position")
                }
                else if (a[a.size-1]=="+"||a[a.size-1] =="-"||a[a.size-1] =="*"||a[a.size-1] =="/"){
                    store = ""
                    tex = ""
                    //textView.text = "error:wrong symbol position"
                    textView.setText("error:wrong symbol position")
                }
                else if (!rpeat(a)){
                    store = ""
                    tex = ""
                    //textView.text = "error:symbols cannot connect"
                    textView.setText("error:symbols cannot connect")
                } else if (!did0(a)){
                    store = ""
                    tex = ""
                    //textView.text = "error:cannot divide 0"
                    textView.setText("error:cannot divide 0")
                }
                else if (!sqrcheck(a)){
                    store = ""
                    tex = ""
                    //textView.text = "error:cannot divide 0"
                    textView.setText("error:wrong symbols or syntax")
                }
                else{
                    var c= culate(a)
                    tex = c
                    store = c
                    //textView.text = "$c"
                    textView.setText("$c")
                }
                return@OnKeyListener true
            }
            false
        })
    }

}