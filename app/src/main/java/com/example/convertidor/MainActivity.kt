package com.example.convertidor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var amountCurrency: EditText
    private lateinit var currencyFrom: Spinner
    private lateinit var currencyTo: Spinner
    private lateinit var buttonConvertCurrency: Button
    private lateinit var resultCurrency: TextView

    private lateinit var amountTemperature: EditText
    private lateinit var temperatureFrom: Spinner
    private lateinit var temperatureTo: Spinner
    private lateinit var buttonConvertTemperature: Button
    private lateinit var resultTemperature: TextView

    private lateinit var amountLength: EditText
    private lateinit var lengthFrom: Spinner
    private lateinit var lengthTo: Spinner
    private lateinit var buttonConvertLength: Button
    private lateinit var resultLength: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setupSpinners()
        setupButtonListeners()
    }

    private fun initializeViews() {
        amountCurrency = findViewById(R.id.amountCurrency)
        currencyFrom = findViewById(R.id.currencyFrom)
        currencyTo = findViewById(R.id.currencyTo)
        buttonConvertCurrency = findViewById(R.id.buttonConvertCurrency)
        resultCurrency = findViewById(R.id.resultCurrency)

        amountTemperature = findViewById(R.id.amountTemperature)
        temperatureFrom = findViewById(R.id.temperatureFrom)
        temperatureTo = findViewById(R.id.temperatureTo)
        buttonConvertTemperature = findViewById(R.id.buttonConvertTemperature)
        resultTemperature = findViewById(R.id.resultTemperature)

        amountLength = findViewById(R.id.amountLength)
        lengthFrom = findViewById(R.id.lengthFrom)
        lengthTo = findViewById(R.id.lengthTo)
        buttonConvertLength = findViewById(R.id.buttonConvertLength)
        resultLength = findViewById(R.id.resultLength)
    }

    private fun setupSpinners() {
        val currencyOptions = arrayOf("Soles (S)","Dolar (USD)", "Euro (EUR)", "lIBRA (GBP)", "Yen (JPY)","Franco (CHF)","PesoMx (MXN)","Rupia (INR)" )
        val temperatureOptions = arrayOf("Celsius", "Fahrenheit", "Kelvin")
        val lengthOptions = arrayOf("Metros", "Kilómetros", "Millas", "Pies")

        setupSpinner(currencyFrom, currencyOptions)
        setupSpinner(currencyTo, currencyOptions)
        setupSpinner(temperatureFrom, temperatureOptions)
        setupSpinner(temperatureTo, temperatureOptions)
        setupSpinner(lengthFrom, lengthOptions)
        setupSpinner(lengthTo, lengthOptions)
    }

    private fun setupSpinner(spinner: Spinner, options: Array<String>) {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, options).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun setupButtonListeners() {
        buttonConvertCurrency.setOnClickListener { convertCurrency() }
        buttonConvertTemperature.setOnClickListener { convertTemperature() }
        buttonConvertLength.setOnClickListener { convertLength() }
    }

    private fun convertCurrency() {
        val amount = amountCurrency.text.toString().toDoubleOrNull()
        if (amount == null) {
            resultCurrency.text = "Por favor, ingrese una cantidad válida"
            return
        }

        val from = currencyFrom.selectedItem.toString()
        val to = currencyTo.selectedItem.toString()

        val result = convertCurrencyAmount(amount, from, to)

        resultCurrency.text = "%.2f %s = %.2f %s".format(amount, from, result, to)
    }

    private fun convertCurrencyAmount(amount: Double, from: String, to: String): Double {
        val rates = mapOf(
            "Soles (S)" to 1.0,
            "Dolar (USD)" to 3.70,  // 1 USD = 3.70 Soles
            "Euro (EUR)" to 4.05,   // 1 EUR = 4.05 Soles
            "lIBRA (GBP)" to 4.70,  // 1 GBP = 4.70 Soles
            "Yen (JPY)" to 0.026,   // 1 JPY = 0.026 Soles
            "Franco (CHF)" to 4.20, // 1 CHF = 4.20 Soles
            "PesoMx (MXN)" to 0.22, // 1 MXN = 0.22 Soles
            "Rupia (INR)" to 0.045  // 1 INR = 0.045 Soles
        )

        // Convertir primero a Soles
        val amountInSoles = if (from == "Soles (S)") amount else amount * rates[from]!!

        // Luego convertir de Soles a la moneda destino
        return if (to == "Soles (S)") amountInSoles else amountInSoles / rates[to]!!
    }

    private fun convertTemperature() {
        val amount = amountTemperature.text.toString().toDoubleOrNull()
        if (amount == null) {
            resultTemperature.text = "Por favor, ingrese una cantidad válida"
            return
        }

        val from = temperatureFrom.selectedItem.toString()
        val to = temperatureTo.selectedItem.toString()

        val result = when {
            from == "Celsius" && to == "Fahrenheit" -> (amount * 9/5) + 32
            from == "Fahrenheit" && to == "Celsius" -> (amount - 32) * 5/9
            from == "Celsius" && to == "Kelvin" -> amount + 273.15
            from == "Kelvin" && to == "Celsius" -> amount - 273.15
            from == "Fahrenheit" && to == "Kelvin" -> (amount - 32) * 5/9 + 273.15
            from == "Kelvin" && to == "Fahrenheit" -> (amount - 273.15) * 9/5 + 32
            else -> amount // Si son iguales
        }

        resultTemperature.text = "%.2f %s = %.2f %s".format(amount, from, result, to)
    }

    private fun convertLength() {
        val amount = amountLength.text.toString().toDoubleOrNull()
        if (amount == null) {
            resultLength.text = "Por favor, ingrese una cantidad válida"
            return
        }

        val from = lengthFrom.selectedItem.toString()
        val to = lengthTo.selectedItem.toString()

        val result = when {
            from == "Metros" && to == "Kilómetros" -> amount / 1000
            from == "Kilómetros" && to == "Metros" -> amount * 1000
            from == "Millas" && to == "Kilómetros" -> amount * 1.60934
            from == "Kilómetros" && to == "Millas" -> amount / 1.60934
            from == "Metros" && to == "Pies" -> amount * 3.28084
            from == "Pies" && to == "Metros" -> amount / 3.28084
            from == "Millas" && to == "Pies" -> amount * 5280
            from == "Pies" && to == "Millas" -> amount / 5280
            else -> amount // Si son iguales
        }

        resultLength.text = "%.2f %s = %.2f %s".format(amount, from, result, to)
    }
}