/*
 * Copyright (c) 2014  Paul Bernard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */


/*
 *  Data from http://fx.sauder.ubc.ca/currency_table.html
 *  and http://www.thefinancials.com/vortex/CurrencyFormats.html
*/

package org.quantintel.ql.currencies

// Asia

/**
 * Description:             Bangladesh taka
 * ISO three-letter code:   BDT
 * Numerical Code:          50
 * Divided by:              100 paisa
 */
object BDT {
  val iso = "BDT"
  val abbrev = "Bt"
  val desc = "Bangladesh taka"
  val rounding = "%3% %1$.2f"
  val numCode = 50
  val divisor = 100
}

/**
 * Description:             Chinese yuan
 * ISO three-letter code:   CNY
 * Numerical Code:          156
 * Divided by:              100 fend
 */
object CNY {
  val iso = "CNY"
  val symbol = "Y"
  val desc = "Chinese yuan"
  val rounding = "%3% %1$.2f"
  val numCode = 156
  val divisor = 100
}

/**
 * Description:             Hong Kong Dollar
 * ISO three-letter code:   HKD
 * Numerical Code:          344
 * Divided by:              100 cents
 */
object HKD {
  val iso = "HKD"
  val symbol = "HK$"
  val desc = "Hong Kong dollar"
  val rounding = "%3% %1$.2f"
  val numCode = 344
  val divisor = 100
}

/**
 * Description:             Israeli shekel
 * ISO three-letter code:   ILS
 * Numerical Code:          375
 * Divided by:              100 agorot
 */
object ILS {
  val iso = "ILS"
  val symbol = "NIS"
  val desc = "Israeli shekel"
  val rounding = "%1% %2$.3f"
  val numCode = 376
  val divisor = 100
}

/**
 * Description:             Indian rupee
 * ISO three-letter code:   INR
 * Numerical Code:          356
 * Divided by:              100 paise
 */
object INR {
  val iso = "INR"
  val symbol = "Rs"
  val desc = "Indian rupee"
  val rounding = "%3% %1$.2f"
  val numCode = 356
  val divisor = 100
}

/**
 * Description:             Iraqi dinar
 * ISO three-letter code:   IQD
 * Numerical Code:          368
 * Divided by:              100 fils
 */
object IQD {
  val iso = "IQD"
  val symbol = "ID"
  val desc = "Iraqi dinar"
  val rounding = "%2% %1$.3f"
  val numCode = 368
  val divisor = 1000
}

/**
 * Description:             Iranian rial
 * ISO three-letter code:   IRR
 * Numerical Code:          364
 * Divided by:              1 (not otherwise subdividable)
 */
object IRR {
  val iso = "IQD"
  val symbol = "IRR"
  val desc = "Iranian rail"
  val rounding = "%2% %1$.2f"
  val numCode = 368
  val divisor = 1
}

/**
 * Description:             Japanese yet
 * ISO three-letter code:   JPY
 * Numerical Code:          392
 * Divided by:              100
 */
object YPY {
  val iso = "JPY"
  val symbol = "u00a5"
  val desc = "Japanese yen"
  val rounding = "%2% %1$.2f"
  val numCode = 368
  val divisor = 1
}

/**
 * Description:             South-Korean won
 * ISO three-letter code:   KRW
 * Numerical Code:          410
 * Divided by:              100 chon
 */
object KRW {
  val iso = "KRW"
  val symbol = "W"
  val desc = "South-Korean won"
  val rounding = "%3% %1$.0f"
  val numCode = 410
  val divisor = 100
}

/**
 * Description:             Kuwaiti dinar
 * ISO three-letter code:   KWD
 * Numerical Code:          414
 * Divided by:              1000 fils
 */
object KWD {
  val iso = "KWD"
  val symbol = "KD"
  val desc = "Kuwait dinar"
  val rounding = "%3% %1$.3f"
  val numCode = 410
  val divisor = 1000
}

/**
 * Description:             Nepal rupee
 * ISO three-letter code:   NPR
 * Numerical Code:          524
 * Divided by:              100 paise
 */
object NPR {
  val iso = "NPR"
  val symbol = "NRs"
  val desc = "Nepal rupee"
  val rounding = "%3% %1$.2f"
  val numCode = 524
  val divisor = 100
}

/**
 * Description:             Pakistani rupee
 * ISO three-letter code:   PKR
 * Numerical Code:          586
 * Divided by:              100 paisa
 */
object PKR {
  val iso = "PKR"
  val symbol = "Rs"
  val desc = "Pakistani rupee"
  val rounding = "%3% %1$.2f"
  val numCode = 586
  val divisor = 100
}

/**
 * Description:             Saudi riyal
 * ISO three-letter code:   SAR
 * Numerical Code:          682
 * Divided by:              100 halalat
 */
object SAR {
  val iso = "SAR"
  val symbol = "SRls"
  val desc = "Saudi riyal"
  val rounding = "%3% %1$.2f"
  val numCode = 682
  val divisor = 100
}

/**
 * Description:             Singapore Dollar
 * ISO three-letter code:   SGD
 * Numerical Code:          702
 * Divided by:              100 cents
 */
object SGD {
  val iso = "SGD"
  val symbol = "S$"
  val desc = "Singapore dollar"
  val rounding = "%3% %1$.2f"
  val numCode =  702
  val divisor = 100
}

/**
 * Description:             Thai baht
 * ISO three-letter code:   THB
 * Numerical Code:          764
 * Divided by:              100 stang
 */
object THB {
  val iso = "THB"
  val symbol = "Bht"
  val desc = "Thai bhat"
  val rounding = "%1% %2$.3f"
  val numCode =  764
  val divisor = 100
}

/**
 * Description:             Taiwan dollar
 * ISO three-letter code:   TWD
 * Numerical Code:          901
 * Divided by:              100 cents
 */
object TWD{
  val iso = "TWD"
  val symbol = "NT$"
  val desc = "Taiwan dollar"
  val rounding = "%3% %1$.2f"
  val numCode =  901
  val divisor = 100
}
