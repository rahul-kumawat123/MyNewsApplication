package com.example.mynewsapplication.fragment

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mynewsapplication.viewmodel.NewsViewModel
import com.example.mynewsapplication.R
import com.example.mynewsapplication.showToast
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    lateinit var viewModel: NewsViewModel
    lateinit  var languageValue : String
    lateinit var countryValue : String


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)

        inflateLanguageDropDownData()

        inflateCountriesDropDownData()
        searchNewsBTN.setOnClickListener {

            val action = SettingsFragmentDirections.actionSettingsFragmentToLiveNewsFragment("general",languageValue,countryValue)
            findNavController().navigate(action)
            //context?.showToast(countryValue)
        }
    }

    private fun inflateLanguageDropDownData() {

        //inflating autoCompleteTextView with string array
        val languages = resources.getStringArray(R.array.languages)
        val arrayAdapter = context?.let { ArrayAdapter(it, R.layout.dropdown_item, languages) }
        autoCompleteTextView_Languages.setAdapter(arrayAdapter)

        autoCompleteTextView_Languages.setOnItemClickListener { parent, view, position, id ->
            when (parent.getItemAtPosition(position)) {
                    //applying respective code to selected language
                    "Arabic" -> languageValue = "ar"
                    "English" -> languageValue = "en"
                    "German" -> languageValue = "de"
                    "Spanish" -> languageValue = "es"
                    "French" -> languageValue = "fr"
                    "Hebrew" -> languageValue = "he"
                    "Italian" -> languageValue = "it"
                    "Dutch" -> languageValue = "nl"
                    "Norwegian" -> languageValue = "no"
                    "Portuguese" -> languageValue = "pt"
                    "Russian" -> languageValue = "ru"
                    "Swedish" -> languageValue = "se"
                    "Chinese" -> languageValue = "zh"
                }
            }
        }

    private fun inflateCountriesDropDownData() {
        //inflating autoCompleteTextView with string array
        val countries = resources.getStringArray(R.array.countries)
        val arrayAdapter = context?.let { ArrayAdapter(it, R.layout.dropdown_item, countries) }
        autoCompleteTextView_Countries.setAdapter(arrayAdapter)

        autoCompleteTextView_Countries.setOnItemClickListener { parent, view, position, id ->

            //applying respective code to selected country
            when (parent.getItemAtPosition(position)) {
                "Argentina" -> countryValue = "ar"
                "Australia" -> countryValue = "au"
                "Austria" -> countryValue = "at"
                "Belgium" -> countryValue = "be"
                "Brazil" -> countryValue = "br"
                "Bulgaria" -> countryValue = "bg"
                "Canada" -> countryValue = "ca"
                "China" -> countryValue = "cn"
                "Colombia" -> countryValue = "co"
                "Czech Republic" -> countryValue = "cz"
                "Egypt" -> countryValue = "eg"
                "France" -> countryValue = "fr"
                "Germany" -> countryValue = "de"
                "Greece" -> countryValue = "gr"
                "Hong Kong" -> countryValue = "hk"
                "India" -> countryValue = "in"
                "Indonesia" -> countryValue = "id"
                "Ireland" -> countryValue = "ie"
                "Israel" -> countryValue = "il"
                "Italy" -> countryValue = "it"
                "Japan" -> countryValue = "jp"
                "Latvia" -> countryValue = "lv"
                "Lithuania" -> countryValue = "lt"
                "Malaysia" -> countryValue = "my"
                "Mexico" -> countryValue = "mx"
                "Morocco" -> countryValue = "ma"
                "Netherlands" -> countryValue = "nl"
                "New Zealand" -> countryValue = "nz"
                "Nigeria" -> countryValue = "ng"
                "Norway" -> countryValue = "no"
                "Philippines" -> countryValue = "ph"
                "Poland" -> countryValue = "pl"
                "Portugal" -> countryValue = "pt"
                "Romania" -> countryValue = "ro"
                "Saudi Arabia" -> countryValue = "sa"
                "Serbia" -> countryValue = "rs"
                "Singapore" -> countryValue = "sg"
                "Slovakia" -> countryValue = "sk"
                "Slovenia" -> countryValue = "si"
                "South Africa" -> countryValue = "za"
                "South Korea" -> countryValue = "kr"
                "Sweden" -> countryValue = "se"
                "Switzerland" -> countryValue = "ch"
                "Taiwan" -> countryValue = "tw"
                "Thailand" -> countryValue = "th"
                "Turkey" -> countryValue = "tr"
                "UAE" -> countryValue = "ae"
                "Ukraine" -> countryValue = "ua"
                "United Kingdom" -> countryValue = "gb"
                "United States" -> countryValue = "us"
                "Venuzuela" -> countryValue = "ve"
            }
        }
    }


}