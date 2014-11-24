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
 * Spectrum Finance is based in part on:
 *        QuantLib. http://quantlib.org/
 *
 */




package org.quantintel.ql.currencies

import org.quantintel.ql.math.Rounding

/*
 *  Data from http://fx.sauder.ubc.ca/currency_table.html
 *  and http://www.thefinancials.com/vortex/CurrencyFormats.html
*/
object AsiaEnum extends Enumeration {

  type AsiaEnum = Value
  val BDT = Value(1)
  val CNY = Value(2)
  val HKD = Value(3)
  val ILS = Value(4)
  val INR = Value(5)
  val IQD = Value(6)
  val IRR = Value(7)
  val JPY = Value(8)
  val KRW = Value(9)
  val KWD = Value(10)
  val NPR = Value(11)
  val PKR = Value(12)
  val SAR = Value(13)
  val SGD = Value(14)
  val THB = Value(15)
  val TWD = Value(16)


  def valueOf(currencies: Int) : AsiaEnum  = currencies match {
    case 1 => BDT
    case 2 => CNY
    case 3 => HKD
    case 4 => ILS
    case 5 => INR
    case 6 => IQD
    case 7 => IRR
    case 8 => JPY
    case 9 => KRW
    case 10 => KWD
    case 11 => NPR
    case 12 => PKR
    case 13 => SAR
    case 14 => SGD
    case 15 => THB
    case 16 => TWD
    case _ => throw new Exception("Valid units = 1 or 16")
  }

}

// Asia

object Asia {

  import org.quantintel.ql.currencies.AsiaEnum._

  def apply(currency: AsiaEnum) : Currency = {
    currency match {
      case BDT => new BDTCurrency()
      case CNY => new CNYCurrency()
      case HKD => new HKDCurrency()
      case ILS => new ILSCurrency()
      case INR => new INRCurrency()
      case IQD => new IQDCurrency()
      case IRR => new IRRCurrency()
      case JPY => new JPYCurrency()
      case KRW => new KRWCurrency()
      case KWD => new KWDCurrency()
      case NPR => new NPRCurrency()
      case PKR => new PKRCurrency()
      case SAR => new SARCurrrency()
      case SGD => new SGDCurrency()
      case THB => new THBCurrency()
      case TWD => new TWDCurrency()
    }
  }

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

    val hdkData = Data("Hong Kong dollar", "HKD", 344, "HK$", "", 100, Rounding(), "%3% %1$.2f")

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
  class JPYCurrency extends Currency {

    val jpyData = Data("Japanese yen", "JPY", 392, "u00a5", "", 100, Rounding(), "%3% %1$.0f")

    data = jpyData

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

    val nprData = Data("Nepal rupee", "NPR", 524, "NRs", "", 100, Rounding(), "%3% %1$.2f")

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

    val sarData = Data("Saudi riyal", "SAR", 682, "SRls", "", 100, Rounding(), "%3% %1$.2f")

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

    val thbData = Data("Thai bhat", "THB", 764, "Bht", "", 100, Rounding(), "%1% %2$.3f")

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

}
