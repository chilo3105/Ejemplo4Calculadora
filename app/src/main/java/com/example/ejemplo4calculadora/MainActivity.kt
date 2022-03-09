package com.example.ejemplo4calculadora

import android.icu.number.Scale
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.ejemplo4calculadora.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var firstValue: Double = 0.0
    var operator: Char = '0'
    var putOperador: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btn0.setOnClickListener{
            setValorPantalla("0")
        }
        binding.btn1.setOnClickListener{
            setValorPantalla("1")
        }
        binding.btn2.setOnClickListener{
            setValorPantalla("2")
        }
        binding.btn3.setOnClickListener{
            setValorPantalla("3")
        }
        binding.btn4.setOnClickListener{
            setValorPantalla("4")
        }
        binding.btn5.setOnClickListener{
            setValorPantalla("5")
        }
        binding.btn6.setOnClickListener{
            setValorPantalla("6")
        }
        binding.btn7.setOnClickListener{
            setValorPantalla("7")
        }
        binding.btn8.setOnClickListener{
            setValorPantalla("8")
        }
        binding.btn9.setOnClickListener{
            setValorPantalla("9")
        }
        binding.btnMas.setOnClickListener{
            if(!putOperador){
                setValorPantalla("+")
            } else {
                if(binding.txtResultado.text.isNotEmpty()) {
                    var secondValue: Double = binding.txtResultado.getText().toString().toDouble()
                    var resultado: Double = makeOperation(firstValue, secondValue)
                    binding.txtResultado.text.clear()

                    firstValue = resultado
                    binding.txtPantalla.setText("$firstValue+")
                } else {

                    binding.txtPantalla.setText(binding.txtPantalla.text.dropLast(1))
                    binding.txtPantalla.text.append("+")
                }
            }

            operator = '+'
        }

        binding.btnMenos.setOnClickListener{
            if(!putOperador){
                setValorPantalla("-")
            } else {
                if(!binding.txtResultado.text.isEmpty()) {
                    var secondValue: Double = binding.txtResultado.getText().toString().toDouble()
                    var resultado: Double = makeOperation(firstValue, secondValue)
                    binding.txtResultado.text.clear()

                    firstValue = resultado
                    binding.txtPantalla.setText("$firstValue-")
                } else {
                    binding.txtPantalla.setText(binding.txtPantalla.text.dropLast(1))
                    binding.txtPantalla.text.append("-")
                }
            }

                operator = '-'
        }
        binding.btnMultiplicar.setOnClickListener{
            if(!putOperador){
                setValorPantalla("*")
            } else {
                if(!binding.txtResultado.text.isEmpty()) {
                    var secondValue: Double = binding.txtResultado.getText().toString().toDouble()
                    var resultado: Double = makeOperation(firstValue, secondValue)
                    binding.txtResultado.text.clear()

                    firstValue = resultado
                    binding.txtPantalla.setText("$firstValue*")
                } else {
                    binding.txtPantalla.setText(binding.txtPantalla.text.dropLast(1))
                    binding.txtPantalla.text.append("*")
                }
            }

            operator = '*'
        }
        binding.btnDividir.setOnClickListener{
            if(!putOperador){
                setValorPantalla("÷")
            } else {
                if(binding.txtResultado.text.isNotEmpty()) {
                    var secondValue: Double = binding.txtResultado.getText().toString().toDouble()
                    var resultado: Double = makeOperation(firstValue, secondValue)
                    binding.txtResultado.text.clear()

                    firstValue = resultado
                    binding.txtPantalla.setText("$firstValue÷")
                } else {

                    binding.txtPantalla.setText(binding.txtPantalla.text.dropLast(1))
                    binding.txtPantalla.text.append("÷")
                }
            }

            operator = '÷'
        }
        binding.btnPorcentaje.setOnClickListener{

        }
        binding.btnPunto.setOnClickListener{
            setValorPantalla(".")
        }
        binding.btnIgual.setOnClickListener {
            if (binding.txtPantalla.text.isNotEmpty()){
                if (!putOperador) {
                    firstValue = binding.txtPantalla.text.toString().toDouble()
                    binding.txtResultado.setText("$firstValue")
                    binding.txtPantalla.text.clear()
                } else {
                    if (binding.txtResultado.text.isNotEmpty()) {
                        var secondValue: Double =
                            binding.txtResultado.getText().toString().toDouble()
                        var resultado: Double = makeOperation(firstValue, secondValue)


                        firstValue = resultado
                        binding.txtPantalla.text.clear()
                        binding.txtResultado.setText("$firstValue")
                    } else {
                        firstValue = binding.txtPantalla.text.dropLast(1).toString().toDouble()
                        binding.txtResultado.setText("$firstValue")
                        binding.txtPantalla.text.clear()
                    }
                }
                operator = '0'
                putOperador = false
            }
        }

        binding.btnAc.setOnClickListener{
            binding.txtPantalla.text.clear()
            binding.txtResultado.text.clear()
            putOperador = false
            firstValue = 0.0
            operator = '0'
        }
        binding.btnDel.setOnClickListener{
            var cadena:String = binding.txtResultado.getText().toString()
            if(cadena.length >=1){
                var cad:String = cadena.substring(0, cadena.length-1)
                binding.txtResultado.setText(cad)
            }
        }

    }

    fun setValorPantalla(caracter:String){
        var cadena : String = binding.txtPantalla.getText().toString()
        var prueba : Char = caracter[0]
        when (caracter[0]) {
            '+' -> {
                if(cadena.isEmpty()){
                    //firstValue = 0.0
                    binding.txtPantalla.append("$firstValue+")
                    putOperador = true
                }else {
                    var ultimo: Char = cadena.last()
                    if(ultimo == '+' || ultimo == '*' || ultimo == '÷' || ultimo == '-'){
                        binding.txtPantalla.setText(binding.txtPantalla.text.dropLast(1))
                        binding.txtPantalla.text.append("+")
                    } else {
                        binding.txtPantalla.append("+")
                    }
                }
                if(!putOperador){
                    firstValue = binding.txtResultado.text.toString().toDouble()
                }
                putOperador = true
                binding.txtResultado.text.clear()
            }
            '-' -> {
                if(cadena.isEmpty()){
                    //firstValue = 0.0
                    binding.txtPantalla.append("$firstValue-")
                    putOperador = true
                }else {
                    var ultimo: Char = cadena.last()
                    if(ultimo == '+' || ultimo == '*' || ultimo == '÷' || ultimo == '-'){
                        binding.txtPantalla.setText(binding.txtPantalla.text.dropLast(1))
                        binding.txtPantalla.text.append("-")
                    } else {
                        binding.txtPantalla.append("-")
                    }
                }

                if(!putOperador){
                    firstValue = binding.txtResultado.text.toString().toDouble()
                }


                putOperador = true
                binding.txtResultado.text.clear()
            }
            '*' -> {
                if(cadena.isEmpty()){
                    //firstValue = 0.0
                    binding.txtPantalla.append("$firstValue*")
                    putOperador = true
                }else {
                    var ultimo: Char = cadena.last()
                    if(ultimo == '+' || ultimo == '*' || ultimo == '÷' || ultimo == '-'){
                        binding.txtPantalla.setText(binding.txtPantalla.text.dropLast(1))
                        binding.txtPantalla.text.append("*")
                    } else {
                        binding.txtPantalla.append("*")
                    }
                }
                if(!putOperador){
                    firstValue = binding.txtResultado.text.toString().toDouble()
                }
                binding.txtResultado.text.clear()
                putOperador = true
            }
            '÷' -> {

                if(cadena.isEmpty()){
                    //firstValue = 0.0
                    binding.txtPantalla.append("$firstValue÷")
                    putOperador = true
                }else {
                    var ultimo: Char = cadena.last()
                    if(ultimo == '+' || ultimo == '*' || ultimo == '÷' || ultimo == '-'){
                        binding.txtPantalla.setText(binding.txtPantalla.text.dropLast(1))
                        binding.txtPantalla.text.append("÷")
                    } else {
                        binding.txtPantalla.append("÷")
                    }
                }
                if(!putOperador){
                    firstValue = binding.txtResultado.text.toString().toDouble()
                }
                binding.txtResultado.text.clear()
                putOperador = true
            }
            else -> {
                binding.txtPantalla.append(caracter)
                binding.txtResultado.append(caracter)
            }
        }
    }

    fun makeOperation(first: Double, second: Double):Double{
        when(operator){
            '-' -> {
                return first - second
            }
            '+' -> {
                return first + second
            }
            '*' -> {
                return first * second
            }
            '÷' -> {
                return first / second
            }
        }
        return 0.0
    }

    fun setValorResultado(resultado: String ){
        binding.txtResultado.append(resultado)
    }

    fun showToast(mensaje: String){
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG)
    }

}