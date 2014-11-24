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

/**
 * @author Paul Bernard
 */

// Americas


object AmericaEnum extends Enumeration {

  type AmericaEnum = Value
  val ARS = Value(1)
  val BRL = Value(2)
  val CAD = Value(3)
  val CLP = Value(4)
  val COP = Value(5)
  val MXN = Value(6)
  val PEN = Value(7)
  val PEI = Value(8)
  val PEH = Value(9)
  val TTD = Value(10)
  val USD = Value(11)
  val VEB = Value(12)

  def valueOf(currencies: Int) : AmericaEnum  = currencies match {
    case 1 => ARS
    case 2 => BRL
    case 3 => CAD
    case 4 => CLP
    case 5 => COP
    case 6 => MXN
    case 7 => PEN
    case 8 => PEI
    case 9 => PEH
    case 10 => TTD
    case 11 => USD
    case 12 => VEB
    case _ => throw new Exception("Valid units = 1 or 12")
  }

}

object America {

  import org.quantintel.ql.currencies.AmericaEnum._


  def apply(currency: AmericaEnum) : Currency = {
    currency match {
      case ARS => new ARSCurrency()
      case BRL => new BRLCurrency()
      case CAD => new CADCurrency()
      case CLP => new CLPCurrency()
      case COP => new COPCurrency()
      case MXN => new MXNCurrency()
      case PEN => new PENCurrency()
      case PEI => new PEICurrency()
      case PEH => new PEHCurrency()
      case TTD => new TTDCurrency()
      case USD => new USDCurrency()
      case VEB => new VEBCurrency()
    }
  }

  /**
   * Description:             Argentinian Peso
   * ISO three-letter code:   ARS
   * Numerical Code:          32
   * Divided by:              100 centavos
   */
  class ARSCurrency extends Currency {

    val arsData = Data("Argentinian peso", "ARS", 32, "", "", 100, Rounding(), "%2% %1$.2f")

    data = arsData


  }

  /**
   * Description:           Brazilian real
   * ISO three-letter code: BRL
   * Numerical code:        986
   * Divided by:            100 centavos
   */
  class BRLCurrency extends Currency {

    val brlData = Data("Brazilian real", "BRL", 986, "R$", "",  100, Rounding(),"%3% %1$.2f")

    data = brlData
  }


  /**
   * Description:           Canadian Dollar
   * ISO three-letter code: CAD
   * Numerical code:        124
   * Divided by:            100 cents
   */
  class CADCurrency extends Currency {

    val cadData = Data("Canadian dollar", "CAD", 124, "Can$", "", 100, Rounding(), "%3% %1$.2f")

    data = cadData

  }

  /**
   * Description:           Chilean peso
   * ISO three-letter code: CLP
   * Numerical code:        152
   * Divided by:            100 centavos
   *
   */
  class CLPCurrency extends Currency {

    val clpData = Data("Chilean peso", "CLP", 152, "Ch$", "",  100, Rounding(), "%3% %1$.0f")

    data = clpData
  }

  /**
   * Description:           Columbian peso
   * ISO three-letter code: COP
   * Numerical code:        170
   * Divided by:            100 centavos
   *
   */
  class COPCurrency extends Currency {


    val copData = Data("Columbian peso", "COP", 170, "Col$", "", 100, Rounding(), "%3% %1$.2f")

    data = copData
  }

  /**
   * Description:           Mexican peso
   * ISO three-letter code: MXN
   * Numerical code:        484
   * Divided by:            100 centavos
   */
  class MXNCurrency extends Currency {

    val mxnData = Data("Mexico peso", "MXN", 484, "Mex$", "", 100, Rounding(), "%3% %1$.2f")

    data = mxnData

  }

  /**
   * Description:           Peruvian nuevo sol
   * ISO three-letter code: PEN
   * Numerical code:        604
   * Divided by:            100 centimos
   */
  class PENCurrency extends Currency {

      val penData = Data("Peruvian nuevo sol", "PEN", 604, "S/.", "", 100, Rounding(), "%3% %1$.2f")

      data = penData
  }

  /**
   * Description:           Peruvian inti
   * ISO three-letter code: PEI
   * Numerical code:        998
   * Divided by:            100 centimos
   *
   * Note:
   * Numerical code not available.
   * As per ISO 3166-1 and to be consistent
   * with QuantLib the code 998 is used.
   */
  class PEICurrency extends Currency {

      val peiData = Data("Peruvian inti", "PEI", 998, "I/.", "", 100, Rounding(), "%3% %1$.2f")

      data = peiData
  }

  /**
   * Description:           Peruvian sol
   * ISO three-letter code: PEH
   * Numerical code:        999
   * Divided by:            100 centavos
   *
   * Note:
   * Numerical code not available.
   * As per ISO 3166-1 and to be consistent with
   * QuantLib the code 999 is used.
   *
   */
  class PEHCurrency extends Currency {


    val pehData = Data("Peruvian sol", "PEH", 999, "S/.", "",100, Rounding(), "%3% %1$.2f")

    data = pehData
  }

  /**
   * Description:           Trinidad & Tobago dollar
   * ISO three-letter code: TTD
   * Numerical Code:        780
   * Divided by:            100 cents
   */
  class TTDCurrency extends Currency {

    val ttdData = Data ("Trinidad & Tobago dollar", "TTD", 780, "TT$", "", 100, Rounding(), "%3% %1$.2f" )

    data = ttdData

  }

  /**
   * Description:           U.S. dollar
   * ISO three-letter code: USD
   * Numerical code:        840
   * Divided by:            100 cents
   */
  class USDCurrency extends Currency {

    val usdData = Data("U.S. dollar", "USD", 840, "$", "\u00A2", 100, Rounding(), "%3% %1$.2f")

    data = usdData

  }

  /**
   * Description:           Venezuelan bolivar
   * ISO three-letter code: VEB
   * Numerical Code:        862
   * Divided by:            100 centimos
   */
  class VEBCurrency extends Currency {

    val vebData = Data("Venezuelan bolivar", "VEB", 862, "Bs", "", 100, Rounding(), "%3% %1$.2f")

    data = vebData

  }

}



