package com.example.convertidor

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

/**
 * Actividad principal de la aplicación de conversión de unidades.
 */
class MainActivity : AppCompatActivity() {

    // Declaración de variables para los elementos de la interfaz de usuario
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

    private lateinit var amountCrypto: EditText
    private lateinit var cryptoFrom: Spinner
    private lateinit var cryptoTo: Spinner
    private lateinit var buttonConvertCrypto: Button
    private lateinit var resultCrypto: TextView

    private lateinit var amountData: EditText
    private lateinit var dataFrom: Spinner
    private lateinit var dataTo: Spinner
    private lateinit var buttonConvertData: Button
    private lateinit var resultData: TextView

    private lateinit var amountWeight: EditText
    private lateinit var weightFrom: Spinner
    private lateinit var weightTo: Spinner
    private lateinit var buttonConvertWeight: Button
    private lateinit var resultWeight: TextView

    private lateinit var amountSpeed: EditText
    private lateinit var speedFrom: Spinner
    private lateinit var speedTo: Spinner
    private lateinit var buttonConvertSpeed: Button
    private lateinit var resultSpeed: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de los elementos de la interfaz de usuario
        initializeViews()
        setupSpinners()
        setupButtonListeners()
    }

    /**
     * Inicializa los elementos de la interfaz de usuario.
     */
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

        amountCrypto = findViewById(R.id.amountCrypto)
        cryptoFrom = findViewById(R.id.cryptoFrom)
        cryptoTo = findViewById(R.id.cryptoTo)
        buttonConvertCrypto = findViewById(R.id.buttonConvertCrypto)
        resultCrypto = findViewById(R.id.resultCrypto)

        amountData = findViewById(R.id.amountData)
        dataFrom = findViewById(R.id.dataFrom)
        dataTo = findViewById(R.id.dataTo)
        buttonConvertData = findViewById(R.id.buttonConvertData)
        resultData = findViewById(R.id.resultData)

        amountWeight = findViewById(R.id.amountWeight)
        weightFrom = findViewById(R.id.weightFrom)
        weightTo = findViewById(R.id.weightTo)
        buttonConvertWeight = findViewById(R.id.buttonConvertWeight)
        resultWeight = findViewById(R.id.resultWeight)

        amountSpeed = findViewById(R.id.amountSpeed)
        speedFrom = findViewById(R.id.speedFrom)
        speedTo = findViewById(R.id.speedTo)
        buttonConvertSpeed = findViewById(R.id.buttonConvertSpeed)
        resultSpeed = findViewById(R.id.resultSpeed)
    }

    /**
     * Configura los Spinners con las opciones de conversión.
     */
    private fun setupSpinners() {
        val currencyOptions = arrayOf("Soles (S)","Dolar (USD)", "Euro (EUR)", "lIBRA (GBP)", "Yen (JPY)","Franco (CHF)","PesoMx (MXN)","Rupia (INR)" )
        val temperatureOptions = arrayOf("Celsius", "Fahrenheit", "Kelvin")
        val lengthOptions = arrayOf("Metros", "Kilómetros", "Millas", "Pies")
        val cryptoOptions = arrayOf("Bitcoin (BTC)", "Ethereum (ETH)", "XRP (XRP)", "Soles (S)", "Dolar (USD)")
        val dataOptions = arrayOf("Bytes (B)", "Kilobytes (KB)", "Megabytes (MB)", "Gigabytes (GB)")
        val weightOptions = arrayOf("Kilogramos (kg)", "Gramos (g)", "Libras (lb)", "Onzas (oz)")
        val speedOptions = arrayOf("km/h", "m/s", "mph")

        setupSpinner(currencyFrom, currencyOptions)
        setupSpinner(currencyTo, currencyOptions)
        setupSpinner(temperatureFrom, temperatureOptions)
        setupSpinner(temperatureTo, temperatureOptions)
        setupSpinner(lengthFrom, lengthOptions)
        setupSpinner(lengthTo, lengthOptions)
        setupSpinner(cryptoFrom, cryptoOptions)
        setupSpinner(cryptoTo, cryptoOptions)
        setupSpinner(dataFrom, dataOptions)
        setupSpinner(dataTo, dataOptions)
        setupSpinner(weightFrom, weightOptions)
        setupSpinner(weightTo, weightOptions)
        setupSpinner(speedFrom, speedOptions)
        setupSpinner(speedTo, speedOptions)
    }

    /**
     * Configura un Spinner específico con las opciones de conversión.
     *(Spinner a configurar y Opciones de conversión)
     */
    private fun setupSpinner(spinner: Spinner, options: Array<String>) {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, options).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    /**
     * Configura los listeners de los botones de conversión.
     */
    private fun setupButtonListeners() {
        buttonConvertCurrency.setOnClickListener { convertCurrency() }
        buttonConvertTemperature.setOnClickListener { convertTemperature() }
        buttonConvertLength.setOnClickListener { convertLength() }
        buttonConvertCrypto.setOnClickListener { convertCrypto() }
        buttonConvertData.setOnClickListener { convertData() }
        buttonConvertWeight.setOnClickListener { convertWeight() }
        buttonConvertSpeed.setOnClickListener { convertSpeed() }
    }

    /**
     * Realiza la conversión de divisas.
     */
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

    /**
     * Realiza el cálculo de la conversión de divisas.
     * donde (amount es para la Cantidad a convertir,
     * from indica la Moneda de origen
     * y  to indica la Moneda de destino)
     */
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

    /**
     * Realiza la conversión de temperatura.
     */
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

    /**
     * Realiza la conversión de longitud.
     */
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

    /**
     * Realiza la conversión de criptomonedas.
     */
    private fun convertCrypto() {
        val amount = amountCrypto.text.toString().toDoubleOrNull()
        if (amount == null) {
            resultCrypto.text = "Por favor, ingrese una cantidad válida"
            return
        }

        val from = cryptoFrom.selectedItem.toString()
        val to = cryptoTo.selectedItem.toString()

        val result = when {
            from == "Bitcoin (BTC)" && to == "Ethereum (ETH)" -> amount * (95483.5 / 2800.03)
            from == "Ethereum (ETH)" && to == "Bitcoin (BTC)" -> amount / (95483.5 / 2800.03)
            from == "Bitcoin (BTC)" && to == "XRP (XRP)" -> amount * (95483.5 / 2.5307)
            from == "XRP (XRP)" && to == "Bitcoin (BTC)" -> amount / (95483.5 / 2.5307)
            from == "Ethereum (ETH)" && to == "XRP (XRP)" -> amount * (2800.03 / 2.5307)
            from == "XRP (XRP)" && to == "Ethereum (ETH)" -> amount / (2800.03 / 2.5307)
            from == "Bitcoin (BTC)" && to == "Soles (S)" -> amount * (95483.5 * 3.7)
            from == "Soles (S)" && to == "Bitcoin (BTC)" -> amount / (95483.5 * 3.7)
            from == "Bitcoin (BTC)" && to == "Dolar (USD)" -> amount * 95483.5
            from == "Dolar (USD)" && to == "Bitcoin (BTC)" -> amount / 95483.5
            from == "Ethereum (ETH)" && to == "Soles (S)" -> amount * (2800.03 * 3.7)
            from == "Soles (S)" && to == "Ethereum (ETH)" -> amount / (2800.03 * 3.7)
            from == "Ethereum (ETH)" && to == "Dolar (USD)" -> amount * 2800.03
            from == "Dolar (USD)" && to == "Ethereum (ETH)" -> amount / 2800.03
            from == "XRP (XRP)" && to == "Soles (S)" -> amount * (2.5307 * 3.7)
            from == "Soles (S)" && to == "XRP (XRP)" -> amount / (2.5307 * 3.7)
            from == "XRP (XRP)" && to == "Dolar (USD)" -> amount * 2.5307
            from == "Dolar (USD)" && to == "XRP (XRP)" -> amount / 2.5307
            from == "Soles (S)" && to == "Dolar (USD)" -> amount / 3.7
            from == "Dolar (USD)" && to == "Soles (S)" -> amount * 3.7
            else -> amount // Si son iguales
        }

        resultCrypto.text = "%.2f %s = %.2f %s".format(amount, from, result, to)
    }

    /**
     * Realiza la conversión de datos.
     */
    private fun convertData() {
        val amount = amountData.text.toString().toDoubleOrNull()
        if (amount == null) {
            resultData.text = "Por favor, ingrese una cantidad válida"
            return
        }

        val from = dataFrom.selectedItem.toString()
        val to = dataTo.selectedItem.toString()

        val result = when {
            from == "Bytes (B)" && to == "Kilobytes (KB)" -> amount / 1024
            from == "Kilobytes (KB)" && to == "Bytes (B)" -> amount * 1024
            from == "Bytes (B)" && to == "Megabytes (MB)" -> amount / (1024 * 1024)
            from == "Megabytes (MB)" && to == "Bytes (B)" -> amount * (1024 * 1024)
            from == "Bytes (B)" && to == "Gigabytes (GB)" -> amount / (1024 * 1024 * 1024)
            from == "Gigabytes (GB)" && to == "Bytes (B)" -> amount * (1024 * 1024 * 1024)
            from == "Kilobytes (KB)" && to == "Megabytes (MB)" -> amount / 1024
            from == "Megabytes (MB)" && to == "Kilobytes (KB)" -> amount * 1024
            from == "Kilobytes (KB)" && to == "Gigabytes (GB)" -> amount / (1024 * 1024)
            from == "Gigabytes (GB)" && to == "Kilobytes (KB)" -> amount * (1024 * 1024)
            from == "Megabytes (MB)" && to == "Gigabytes (GB)" -> amount / 1024
            from == "Gigabytes (GB)" && to == "Megabytes (MB)" -> amount * 1024
            else -> amount // Si son iguales
        }

        resultData.text = "%.2f %s = %.2f %s".format(amount, from, result, to)
    }

    /**
     * Realiza la conversión de peso.
     */
    private fun convertWeight() {
        val amount = amountWeight.text.toString().toDoubleOrNull()
        if (amount == null) {
            resultWeight.text = "Por favor, ingrese una cantidad válida"
            return
        }

        val from = weightFrom.selectedItem.toString()
        val to = weightTo.selectedItem.toString()

        val result = when {
            from == "Kilogramos (kg)" && to == "Gramos (g)" -> amount * 1000
            from == "Gramos (g)" && to == "Kilogramos (kg)" -> amount / 1000
            from == "Kilogramos (kg)" && to == "Libras (lb)" -> amount * 2.20462
            from == "Libras (lb)" && to == "Kilogramos (kg)" -> amount / 2.20462
            from == "Kilogramos (kg)" && to == "Onzas (oz)" -> amount * 35.274
            from == "Onzas (oz)" && to == "Kilogramos (kg)" -> amount / 35.274
            from == "Gramos (g)" && to == "Libras (lb)" -> amount * 0.00220462
            from == "Libras (lb)" && to == "Gramos (g)" -> amount / 0.00220462
            from == "Gramos (g)" && to == "Onzas (oz)" -> amount * 0.035274
            from == "Onzas (oz)" && to == "Gramos (g)" -> amount / 0.035274
            from == "Libras (lb)" && to == "Onzas (oz)" -> amount * 16
            from == "Onzas (oz)" && to == "Libras (lb)" -> amount / 16
            else -> amount // Si son iguales
        }

        resultWeight.text = "%.2f %s = %.2f %s".format(amount, from, result, to)
    }

    /**
     * Realiza la conversión de velocidad.
     */
    private fun convertSpeed() {
        val amount = amountSpeed.text.toString().toDoubleOrNull()
        if (amount == null) {
            resultSpeed.text = "Por favor, ingrese una cantidad válida"
            return
        }

        val from = speedFrom.selectedItem.toString()
        val to = speedTo.selectedItem.toString()

        val result = when {
            from == "km/h" && to == "m/s" -> amount / 3.6
            from == "m/s" && to == "km/h" -> amount * 3.6
            from == "km/h" && to == "mph" -> amount / 1.60934
            from == "mph" && to == "km/h" -> amount * 1.60934
            from == "m/s" && to == "mph" -> amount * 2.23694
            from == "mph" && to == "m/s" -> amount / 2.23694
            else -> amount // Si son iguales
        }

        resultSpeed.text = "%.2f %s = %.2f %s".format(amount, from, result, to)
    }
}