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

import org.quantintel.ql.math.Rounding



// Asia

/**
 * Description:             Bangladesh taka
 * ISO three-letter code:   BDT
 * Numerical Code:          50
 * Divided by:              100 paisa
 */

class BDTCurrency extends Currency {

  val bdtData = Data("Bangladesh taka", "BDT", 50, "Bt", "", 100, Rounding(), "%3% %1$.2f")

  data = bdtData

}

/**
 * Description:             Chinese yuan
 * ISO three-letter code:   CNY
 * Numerical Code:          156
 * Divided by:              100 fend
 */
class CNYCurrency extends Currency {

  val cnyData = Data("Chinese yuan", "CNY", 156, "Y", "", 100, Rounding(), "%3% %1$.2f")

  data = cnyData

}

/**
 * Description:             Hong Kong Dollar
 * ISO three-letter code:   HKD
 * Numerical Code:          344
 * Divided by:              100 cents
 */
class HKDCurrency extends Currency {

  val hdkData = Data("Hong Kong dollar", "HKD", 344, "HK$", "", 100, Rounding(), "%3% %1$.2f" )

  data = hdkData

}

/**
 * Description:             Israeli shekel
 * ISO three-letter code:   ILS
 * Numerical Code:          375
 * Divided by:              100 agorot
 */
class ILSCurrency extends Currency {

  val ilsData = Data("Israeli shekel", "ILS", 376, "NIS", "", 100, Rounding(), "%1% %2$.3f")

  data = ilsData
}

/**
 * Description:             Indian rupee
 * ISO three-letter code:   INR
 * Numerical Code:          356
 * Divided by:              100 paise
 */
class INRCurrency extends Currency {

  val inrData = Data("Indian rupee", "INR", 356, "Rs", "", 100, Rounding(), "%3% %1$.2f")

  data = inrData

}

/**
 * Description:             Iraqi dinar
 * ISO three-letter code:   IQD
 * Numerical Code:          368
 * Divided by:              100 fils
 */
class IQDCurrency extends Currency {

  val iqdData = Data("Iraqi dinar", "IQD", 368, "ID", "", 1000, Rounding(), "%2% %1$.3f")

  data = iqdData

}

/**
 * Description:             Iranian rial
 * ISO three-letter code:   IRR
 * Numerical Code:          364
 * Divided by:              1 (not otherwise sub-dividable)
 */
class IRRCurrency extends Currency {

  val irrData = Data("Iranian rail", "IRR", 364, "Rls", "", 1, Rounding(), "%3% %1$.2f")

  data = irrData

}

/**
 * Description:             Japanese yet
 * ISO three-letter code:   JPY
 * Numerical Code:          392
 * Divided by:              100
 */
class  YPYCurrency extends Currency {

  val ypyData = Data("Japanese yen", "JPY", 392, "u00a5", "", 100, Rounding(), "%3% %1$.0f")

  data = ypyData

}

/**
 * Description:             South-Korean won
 * ISO three-letter code:   KRW
 * Numerical Code:          410
 * Divided by:              100 chon
 */
class KRWCurrency extends Currency {

  val krwData = Data("South-Korean won", "KRW", 410, "W", "", 100, Rounding(), "%3% %1$.0f")

  data = krwData

}

/**
 * Description:             Kuwaiti dinar
 * ISO three-letter code:   KWD
 * Numerical Code:          414
 * Divided by:              1000 fils
 */
class KWDCurrency extends Currency {

  val kwdData = Data("Kuwait dinar", "KWD", 414, "KD", "", 1000, Rounding(), "%3% %1$.3f")

  data = kwdData

}

/**
 * Description:             Nepal rupee
 * ISO three-letter code:   NPR
 * Numerical Code:          524
 * Divided by:              100 paise
 */
class NPRCurrency extends Currency {

  val nprData = Data ("Nepal rupee", "NPR", 524, "NRs", "", 100, Rounding(), "%3% %1$.2f")

  data = nprData

}

/**
 * Description:             Pakistani rupee
 * ISO three-letter code:   PKR
 * Numerical Code:          586
 * Divided by:              100 paisa
 */
class PKRCurrency extends Currency {

  val pkrData = Data("Pakistani rupee", "PKR", 586, "Rs", "", 100, Rounding(), "%3% %1$.2f")

  data = pkrData

}

/**
 * Description:             Saudi riyal
 * ISO three-letter code:   SAR
 * Numerical Code:          682
 * Divided by:              100 halalat
 */
class SARCurrrency extends Currency {

  val sarData = Data("Saudi riyal", "SAR",682,"SRls", "", 100, Rounding(), "%3% %1$.2f")

  data = sarData

}

/**
 * Description:             Singapore Dollar
 * ISO three-letter code:   SGD
 * Numerical Code:          702
 * Divided by:              100 cents
 */
class SGDCurrency extends Currency {

  val sgdData = Data("Singapore dollar", "SGD", 702, "S$", "", 100, Rounding(), "%3% %1$.2f")

  data = sgdData

}

/**
 * Description:             Thai baht
 * ISO three-letter code:   THB
 * Numerical Code:          764
 * Divided by:              100 stang
 */
class THBCurrency extends Currency {

  val thbData = Data("Thai bhat", "THB", 764,"Bht", "", 100, Rounding(), "%1% %2$.3f")

  data = thbData

}

/**
 * Description:             Taiwan dollar
 * ISO three-letter code:   TWD
 * Numerical Code:          901
 * Divided by:              100 cents
 */
class TWDCurrency extends Currency {

  val twdData = Data("Taiwan dollar", "TWD", 901, "NT$", "", 100, Rounding(), "%3% %1$.2f")

  data = twdData

}
