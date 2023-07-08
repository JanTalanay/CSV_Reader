package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.sql.Time

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        readCSVFile();
    }

    private fun readCSVFile() {
        val textView = findViewById<TextView>(R.id.CSV)
        val bufferReader = BufferedReader(assets.open("com.samsung.shealth.blood_pressure.20230706212807.csv").reader())
        val csvParser = CSVParser.parse(
            bufferReader,
            CSVFormat.DEFAULT
        )
        val list= mutableListOf<BloodPressureSample>()
        csvParser.forEach{
            it?.let{
                val BPS = BloodPressureSample(
                    medication = it.get(0),
                    BPStartTime = it.get(1),
                    BPcustom = it.get(2),
                    BPUpdateTime = it.get(3),
                    BPCreateTime = it.get(4),
                    BPMean = it.get(5),
                    BPPulse = it.get(6),
                    BPTime = it.get(7),
                    BPDevice = it.get(8),
                    BPComment = it.get(9),
                    BPPkg = it.get(10),
                    BPDiastolic = it.get(11),
                    BPDataUID = it.get(12),
                    BPSystolic = it.get(13),
                )
                list.add(BPS)
            }

        }
        list.forEach{
            textView.append(
                "${it.BPPulse} ${it.BPSystolic} ${it.BPDiastolic}\n\n"
            )
        }
    }

}