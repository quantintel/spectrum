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
 */
package org.quantintel.ql.currencies

import org.quantintel.ql.math.Rounding

/**
 * @author Paul Bernard
 */

// Americas


class America {

  /**
   * Description:             Argentinian Peso
   * ISO three-letter code:   ARS
   * Numerical Code:          32
   * Divided by:              100 centavos
   */
  class ARSCurrency extends Currency {

    val arsData = Data("Argentinian peso", "ARS", 32, "", "", 100, Rounding(), "#.###,##")

    data = arsData


  }

  /**
   * Description:           Brazilian real
   * ISO three-letter code: BRL
   * Numerical code:        986
   * Divided by:            100 centavos
   */
  class BRLCurrency extends Currency {

    val brlData = Data("Brazilian real", "BRL", 986, "R$", "",  100, Rounding(),"#.###,##")

    data = brlData
  }


  /**
   * Description:           Canadian Dollar
   * ISO three-letter code: CAD
   * Numerical code:        124
   * Divided by:            100 cents
   */
  class CADCurrency extends Currency {

    val cadData = Data("Canadian dollar", "CAD", 124, "Can$", "", 100, Rounding(), "#,###.##")

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

    val clpData = Data("Chilean peso", "CLP", 152, "Ch$", "",  100, Rounding(), "#.###")

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


    val copData = Data("Columbian peso", "COP", 170, "Col$", "", 100, Rounding(), "#.###,##")

    data = copData
  }

  /**
   * Description:           Mexican peso
   * ISO three-letter code: MXN
   * Numerical code:        484
   * Divided by:            100 centavos
   */
  class MXNCurrency extends Currency {

    val mxnData = Data("Mexico peso", "MXN", 484, "Mex$", "", 100, Rounding(), "#,###.##")

    data = mxnData

  }

  /**
   * Description:           Peruvian nuevo sol
   * ISO three-letter code: PEN
   * Numerical code:        604
   * Divided by:            100 centimos
   */
  class PENCurrency extends Currency {

      val penData = Data("Peruvian nuevo sol", "PEN", 604, "S/.", "", 100, Rounding(), "#,###.##")

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

      val peiData = Data("Peruvian inti", "PEI", 998, "I/.", "", 100, Rounding(), "#,###.##")

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


    val pehData = Data("Peruvian sol", "PEH", 999, "S/.", "",100, Rounding(), "#,###.##")

    data = pehData
  }

  /**
   * Description:           Trinidad & Tobago dollar
   * ISO three-letter code: TTD
   * Numerical Code:        780
   * Divided by:            100 cents
   */
  class TTDCurrency extends Currency {

    val ttdData = Data ("Trinidad & Tobago dollar", "TTD", 780, "TT$", "", 100, Rounding(), "#,###.##" )

    data = ttdData

  }

  /**
   * Description:           U.S. dollar
   * ISO three-letter code: USD
   * Numerical code:        840
   * Divided by:            100 cents
   */
  class USDCurrency extends Currency {

    val usdData = Data("U.S. dollar", "USD", 840, "$", "\u00A2", 100, Rounding(), "#.###,##")

    data = usdData

  }

  /**
   * Description:           Venezuelan bolivar
   * ISO three-letter code: VEB
   * Numerical Code:        862
   * Divided by:            100 centimos
   */
  class VEBCurrency extends Currency {

    val vebData = Data("Venezuelan bolivar", "VEB", 862, "Bs", "", 100, Rounding(), "#.###,##")

    data = vebData

  }

}



