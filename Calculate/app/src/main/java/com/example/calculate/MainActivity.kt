package com.example.calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.sqrt
import android.widget.Toast

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
    private lateinit var textView: TextView
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
        textView = findViewById(R.id.text)

        add.setOnClickListener { view: View ->
            tex += "+"
            store += "+"
            textView.text = "$tex"
        }
        minus.setOnClickListener { view: View ->
            tex += "-"
            store += "-"
            textView.text = "$tex"
        }
        time.setOnClickListener { view: View ->
            tex += "*"
            store += "*"
            textView.text = "$tex"
        }
        divide.setOnClickListener { view: View ->
            tex += "/"
            store +="/"
            textView.text = "$tex"
        }
        sqrt.setOnClickListener { view: View ->
            tex += "sqrt"
            store+= "s"
            textView.text = "$tex"
        }
        sign.setOnClickListener { view: View ->
            tex += "."
            store += "."
            textView.text = "$tex"
        }

        num0.setOnClickListener { view: View ->
            if (tex.length != 0)
            {
                tex += "0"
                store += "0"
                textView.text = "$tex"
            }
        }
        num1.setOnClickListener { view: View ->
            tex += "1"
            store += "1"
            textView.text = "$tex"
        }
        num2.setOnClickListener { view: View ->
            tex += "2"
            store += "2"
            textView.text = "$tex"
        }
        num3.setOnClickListener { view: View ->
            tex += "3"
            store += "3"
            textView.text = "$tex"
        }
        num4.setOnClickListener { view: View ->
            tex += "4"
            store += "4"
            textView.text = "$tex"
        }
        num5.setOnClickListener { view: View ->
            tex += "5"
            store += "5"
            textView.text = "$tex"
        }
        num6.setOnClickListener { view: View ->
            tex += "6"
            store += "6"
            textView.text = "$tex"
        }
        num7.setOnClickListener { view: View ->
            tex += "7"
            store += "7"
            textView.text = "$tex"
        }
        num8.setOnClickListener { view: View ->
            tex += "8"
            store += "8"
            textView.text = "$tex"
        }
        num9.setOnClickListener { view: View ->
            tex += "9"
            store += "9"
            textView.text = "$tex"
        }

        fun cal(tex:String): MutableList<Any> {
            var a: MutableList<Any> = mutableListOf()
            var p:Int =0
            var text=""
            while(tex.length != p){
                if (tex[p]=='+'||tex[p]=='-'||tex[p]=='*'||tex[p]=='/'||tex[p]=='s'){
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
        fun culate(a:MutableList<Any>): Float{
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
            return a[0] as Float
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


        equal.setOnClickListener { view: View ->
            var a = cal(store)
            if (a[0] =="+"||a[0] =="-"||a[0] =="*"||a[0] =="/"||a[0] =="s"){
                store = ""
                tex = ""
                textView.text = "error:wrong symbol position"
            }
            else if (a[a.size-1]=="+"||a[a.size-1] =="-"||a[a.size-1] =="*"||a[a.size-1] =="/"){
                store = ""
                tex = ""
                textView.text = "error:wrong symbol position"
            }
            else if (!rpeat(a)){
                store = ""
                tex = ""
                textView.text = "error:symbols cannot connect"
            } else if (!did0(a)){
                store = ""
                tex = ""
                textView.text = "error:cannot divide 0"
            }else{
                var c= culate(a)
                tex = c.toString()
                textView.text = "$c"
            }
        }
    }

}