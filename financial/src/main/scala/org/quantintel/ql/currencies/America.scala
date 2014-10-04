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

/**
 * @author Paul Bernard
 */

// Americas

/**
 * Description:             Argentinian Peso
 * ISO three-letter code:   ARS
 * Numerical Code:          32
 * Divided by:              100 centavos
 */
object ARS {
  val iso = "ARS"
  val abbrev = ""
  val desc = "Argentinian peso"
  val format = "#.###,##"
  val numCode = 32
  val divisor = 100
}

/**
 * Description:           Brazilian real
 * ISO three-letter code: BRL
 * Numerical code:        986
 * Divided by:            100 centavos
 */
object BRL {
  val iso = "BRL"
  val symbol = "R$"
  val desc = "Brazilian real"
  val format = "#.###,##"
  val numCode = 986
  val divisor = 100
}

/**
 * Description:           Canadian Dollar
 * ISO three-letter code: CAD
 * Numerical code:        124
 * Divided by:            100 cents
 */
object CAD {
  val iso = "CAD"
  val symbol = "Can$"
  val desc = "Canadian dollar"
  val format = "#,###.##"
  val numCode = 124
  val divisor = 100
}

/**
 * Description:           Chilean peso
 * ISO three-letter code: CLP
 * Numerical code:        152
 * Divided by:            100 centavos
 *
 */
object CLP {
  val iso = "CLP"
  val symbol = "Ch$"
  val desc = "Chilean peso"
  val format = "#.###"
  val numCode = 152
  val divisor = 100
}

/**
 * Description:           Columbian peso
 * ISO three-letter code: COP
 * Numerical code:        170
 * Divided by:            100 centavos
 *
 */
object COP {
  val iso = "COP"
  val symbol = "Col$"
  val desc = "Columbian peso"
  val format = "#.###,##"
  val numCode = 170
  val divisor = 100
}

/**
 * Description:           Mexican peso
 * ISO three-letter code: MXN
 * Numerical code:        484
 * Divided by:            100 centavos
 */
object MXN {
  val iso = "MXN"
  val symbol = "Mex$"
  val desc = "Mexico peso"
  val format = "#,###.##"
  val numCode = 484
  val divisor = 100
}

/**
 * Description:           Peruvian nuevo sol
 * ISO three-letter code: PEN
 * Numerical code:        604
 * Divided by:            100 centimos
 */
object PEN {
  val iso = "PEN"
  val symbol = "S/."
  val desc = "Peruvian nuevo sol"
  val format = "#,###.##"
  val numCode = 604
  val divisor = 100
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
object PEI {
  val iso = "PEI"
  val symbol = "I/."
  val desc = "Peruvian inti"
  val format = "#,###.##"
  val numCode = 998
  val divisor = 100
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
object PEH {
  val iso = "PEH"
  val symbol = "S/."
  val desc = "Peruvian sol"
  val format = "#,###.##"
  val numCode = 999
  val divisor = 100
}

/**
 * Description:           Trinidad & Tobago dollar
 * ISO three-letter code: TTD
 * Numerical Code:        780
 * Divided by:            100 cents
 */
object TTD{
  val iso = "TTD"
  val symbol = "TT/."
  val desc = "Trinidad & Tobago dollar"
  val format= "#,###.##"
  val numCode = 780
  val divisor = 100
}

/**
 * Description:           U.S. dollar
 * ISO three-letter code: USD
 * Numerical code:        840
 * Divided by:            100 cents
 */
object USD {
  val iso = "USD"
  val symbol = "$"
  val desc = "U.S. dollar"
  val format = "#.###,##"
  val numCode = 840
  val divisor = 100
}

/**
 * Description:           Venezuelan bolivar
 * ISO three-letter code: VEB
 * Numerical Code:        862
 * Divided by:            100 centimos
 */
object VEB {
  val iso = "VEB"
  val symbol = "Bs"
  val desc = "Venezuelan bolivar"
  val format = "#.###,##"
  val numCode = 862
  val divisor = 100
}


