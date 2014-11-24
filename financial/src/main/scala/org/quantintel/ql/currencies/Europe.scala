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

import org.quantintel.ql.math.{RoundingMethods, Rounding}


object EuropeEnum extends Enumeration {

  type EuropeEnum = Value
  val BGL = Value(1)
  val BYR = Value(2)
  val CHF = Value(3)
  val CYP = Value(4)
  val CZK = Value(5)
  val DKK = Value(6)
  val EEK = Value(7)
  val EUR = Value(8)
  val GBP = Value(9)
  val HUF = Value(10)
  val ISK = Value(11)
  val LTL = Value(12)
  val LVL = Value(13)
  val NOK = Value(14)
  val PLN = Value(15)
  val ROL = Value(16)
  val RON = Value(17)
  val SEK = Value(18)
  val SIT = Value(19)
  val TRL = Value(20)
  val TRY = Value(21)
  val ATS = Value(22)
  val BEF = Value(23)
  val DEM = Value(24)
  val ESP = Value(25)
  val FIM = Value(26)
  val FRF = Value(27)
  val GRD = Value(28)
  val IEP = Value(29)
  val ITL = Value(30)
  val LUF = Value(31)
  val MTL = Value(32)
  val NLG = Value(33)
  val PTE = Value(34)
  val SKK = Value(35)





  def valueOf(currencies: Int) : EuropeEnum   = currencies match {
    case 1 => BGL
    case 2 => BYR
    case 3 => CHF
    case 4 => CYP
    case 5 => CZK
    case 6 => DKK
    case 7 => EEK
    case 8 => EUR
    case 9 => GBP
    case 10 => HUF
    case 11 => ISK
    case 12 => LTL
    case 13 => LVL
    case 14 => NOK
    case 15 => PLN
    case 16 => ROL
    case 17 => RON
    case 18 => SEK
    case 19 => SIT
    case 20 => TRL
    case 21 => TRY
    case 22 => ATS
    case 23 => BEF
    case 24 => DEM
    case 25 => ESP
    case 26 => FIM
    case 27 => FRF
    case 28 => GRD
    case 29 => IEP
    case 30 => ITL
    case 31 => LUF
    case 32 => MTL
    case 33 => NLG
    case 34 => PTE
    case 35 => SKK
    case _ => throw new Exception("Valid units = 1 or 35")
  }

}

// Europe

object Europe {

  import org.quantintel.ql.currencies.EuropeEnum._

  def apply(currency: EuropeEnum) : Currency = {
    currency match {
      case BGL => new BGLCurrency()
      case BYR => new BYRCurrency()
      case CHF => new CHFCurrency()
      case CYP => new CYPCurrency()
      case CZK => new CZKCurrency()
      case DKK => new DKKCurrency()
      case EEK => new EEKCurrency()
      case EUR => new EURCurrency()
      case GBP => new GBPCurrency()
      case HUF => new HUFCurrency()
      case ISK => new ISKCurrency()
      case LTL => new LTLCurrency()
      case LVL => new LVLCurrency()
      case NOK => new NOKCurrency()
      case PLN => new PLNCurrency()
      case ROL => new ROLCurrency()
      case RON => new RONCurrency()
      case SEK => new SEKCurrency()
      case SIT => new SITCurrency()
      case TRL => new TRLCurrency()
      case TRY => new TRYCurrency()
      case ATS => new ATSCurrency()
      case BEF => new BEFCurrency()
      case DEM => new DEMCurrency()
      case ESP => new ESPCurrency()
      case FIM => new FIMCurrency()
      case FRF => new FRFCurrency()
      case GRD => new GRDCurrency()
      case IEP => new IEPCurrency()
      case ITL => new ITLCurrency()
      case LUF => new LUFCurrency()
      case MTL => new MTLCurrency()
      case NLG => new NLGCurrency()
      case PTE => new PTECurrency()
      case SKK => new SKKCurrency()

    }
  }

  /**
   * Description:             Bulgarian lev
   * ISO three-letter code:   BGL
   * Numerical Code:          100
   * Divided by:              100 sotinki
   */
  class BGLCurrency extends Currency {

    val bglData = Data("Bulgarian lev", "BGL", 100, "lv", "", 100, Rounding(), "%1% %2$.3f")

    data = bglData
  }

  /**
   * Description:             Belarussian ruble
   * ISO three-letter code:   BYR
   * Numerical Code:          974
   * Divided by:              1
   */
  class BYRCurrency extends Currency {

    val byrData = Data("Belarussian ruble", "BYR", 974, "BR", "", 1, Rounding(), "%2% %1$.0f")

    data = byrData
  }

  /**
   * Description:             Swiss franc
   * ISO three-letter code:   CHF
   * Numerical Code:          756
   * Divided by:              100 cents
   */
  class CHFCurrency extends Currency {

    val chfData = Data("Swiss franc","CHF", 756, "SwF", "", 100, Rounding(), "%3% %1$.2f")

    data = chfData

  }

  /**
   * Description:             Cypriot pound
   * ISO three-letter code:   CYP
   * Numerical Code:          196
   * Divided by:              100 cents
   */
  class CYPCurrency extends Currency {

    val cypData = Data("Cypriot pound", "CYP", 196, "u00a3 C", "", 100, Rounding(), "%3% %1$.2f")

    data = cypData

  }

  /**
   * Description:             Czech Koruna
   * ISO three-letter code:   CZK
   * Numerical Code:          203
   * Divided by:              100 haleru
   */
  class CZKCurrency extends Currency {

    val czkData = Data("Czech Koruna", "CZK", 203, "Kc", "", 100, Rounding(), "%1% %2$.3f")

    data = czkData

  }

  /**
   * Description:             Danish krone
   * ISO three-letter code:   DKK
   * Numerical Code:          208
   * Divided by:              100 ore
   */
  class DKKCurrency extends Currency {

    val dkkData = Data("Danish krone", "DKK", 208, "Dkr", "", 100, Rounding(), "%3% %1$.2f")

    data = dkkData

  }

  /**
   * Description:             Estonian kroon
   * ISO three-letter code:   EEK
   * Numerical Code:          233
   * Divided by:              100 senti
   */
  class EEKCurrency extends Currency {

    val eekData = Data("Esonian kroon","EEK", 233, "KR", "", 100, Rounding(), "%1% %2$.2f")

    data = eekData

  }

  /**
   * Description:             European Euro
   * ISO three-letter code:   EUR
   * Numerical Code:          978
   * Divided by:              100 cents
   */
  class EURCurrency extends Currency {

    val eurData = Data("European Euro", "EUR", 978, "", "", 100, Rounding(RoundingMethods.CLOSEST, 2), "%2% %1$.2f")

    data = eurData

  }

  class GBPCurrency extends Currency {

    val gbpData = Data("British pound sterling", "GBP", 826, "\u00A3", "p", 100, Rounding(), "%3% %1$.2f")

    data = gbpData

  }

  /**
   * Description:             Hungarian forint
   * ISO three-letter code:   HUF
   * Numerical Code:          348
   * Divided by:              1
   */
  class HUFCurrency extends Currency {

    val hufData = Data("Hungarian forint", "HUF", 348, "Ft", "", 1, Rounding(), "%1% %0$.3f")

    data = hufData

  }

  /**
   * Description:             Iceland Krona
   * ISO three-letter code:   ISK
   * Numerical Code:          352
   * Divided by:              100 aurar
   */
  class ISKCurrency extends Currency {

    val iskData = Data("Iceland krona", "ISK", 352, "Ikr", "", 100, Rounding(), "%1% %2$.3f")

    data = iskData
  }

  /**
   * Description:             Lithuanian litas
   * ISO three-letter code:   LTL
   * Numerical Code:          440
   * Divided by:              100 centu
   */
  class LTLCurrency extends Currency {

    val ltlData = Data("Lithuanian litas", "LTL", 440, "Lt", "", 100, Rounding(), "%1% %2$.3f")

    data = ltlData
  }

  /**
   * Description:             Latvian lat
   * ISO three-letter code:   LVL
   * Numerical Code:          428
   * Divided by:              100 santims
   */
  class LVLCurrency extends Currency {

    val lvlData = Data("Latvian lat", "LVL", 428, "Ls", "", 100, Rounding(), "%3% %1$.2f")

    data = lvlData
  }


  /**
   * Description:             Norwegian krone
   * ISO three-letter code:   NOK
   * Numerical Code:          578
   * Divided by:              100 øre
   */
  class NOKCurrency extends Currency {

    val nokCurrency = Data("Norwegian krone", "NOK", 578, "NKr", "", 100, Rounding(), "%3% %1$.2f")

    data = nokCurrency
  }

  /**
   * Description:             Polish zloty
   * ISO three-letter code:   PLN
   * Numerical Code:          985
   * Divided by:              100 groszy
   */
  class PLNCurrency extends Currency {

    val plnData = Data("Polish zloty","PLN",985, "zl", "", 100, Rounding(), "%1% %2$.3f")

    data = plnData

  }

  /**
   * Description:             Romanian leu
   * ISO three-letter code:   ROL
   * Numerical Code:          642
   * Divided by:              100 bani
   */
  class ROLCurrency extends Currency {

    val rolData = Data("Romanian leu", "ROL", 642, "L", "", 100, Rounding(),"%1% %2$.3f" )

    data = rolData

  }

  /**
   * Description:             Romanian new leu
   * ISO three-letter code:   RON
   * Numerical Code:          946
   * Divided by:              100 bani
   */
  class RONCurrency extends Currency {

    val ronData = Data("Romian new leu", "RON", 946, "L", "", 100, Rounding(), "%1% %2$.3f")

    data = ronData

  }

  /**
   * Description:             Swedish Krona
   * ISO three-letter code:   SEK
   * Numerical Code:          752
   * Divided by:              100 öre
   */
  class SEKCurrency extends Currency {

    val sekData = Data("Swedish Krona", "SEK", 752, "Kr", "", 100, Rounding(), "%1% %2$.3f")

    data = sekData

  }

  /**
   * Description:             Slovenian tolar
   * ISO three-letter code:   SIT
   * Numerical Code:          705
   * Divided by:              100 stotinov
   */
  class SITCurrency extends Currency {

    val sitData = Data("Slovenian tolar", "SIT", 705, "Slt", "", 100, Rounding(), "%1% %2$.3f")

    data = sitData
  }



  /**
   * Description:             Turkish Lira
   * ISO three-letter code:   TRL
   * Numerical Code:          792
   * Divided by:              100 kurus
   */
  class TRLCurrency extends Currency {

    val trlData = Data("Turkish lira", "TRL", 792, "TL", "", 100, new Rounding(), "%1$.0f %3%")

    data = trlData
  }

  /**
   * Description:             New Turkish lira
   * ISO three-letter code:   TRY
   * Numerical Code:          949
   * Divided by:              100 new kurus
   */
  class TRYCurrency extends Currency {

    val tryData = Data("New Turkish lira", "TRY", 949, "YTL", "", 100, new Rounding(), "%1$.2f %3%")

    data = tryData
  }

  /**
   * The following currencies were replaced by the Euro
   */

  /**
   * Description:             Austrian shilling
   * ISO three-letter code:   ATS
   * Numerical Code:          40
   * Divided by:              100 groschen
   */
  class ATSCurrency extends Currency {

    val atsData = Data("Austrian shilling", "ATS", 40, "", "", 100, new Rounding(), "%2% %1$.2f", new EURCurrency())

    data = atsData
  }


  /**
   * Description:             Belgian franc
   * ISO three-letter code:   BEF
   * Numerical Code:          56
   * Divided by:              1
   */
  class BEFCurrency extends Currency {

    val befData = Data("Belgian franc", "BEF", 56, "", "", 1, new Rounding(), "%2% %1$.0f", new EURCurrency())

    data = befData

  }

  /**
   * Description:             Deutsche mark
   * ISO three-letter code:   DEM
   * Numerical Code:          276
   * Divided by:              100 pfennig
   */
  class DEMCurrency extends Currency {

    val demData = Data("Deutsche mark", "DEM", 276, "DM", "", 100, new Rounding(), "%1$.2f %3%", new EURCurrency())

    data = demData
  }

  /**
   * Description:             Spanish peseta
   * ISO three-letter code:   ESP
   * Numerical Code:          724
   * Divided by:              100 centimos
   */
  class ESPCurrency extends Currency {

    val espData = Data("Spanish peseta", "ESP", 724, "Pta", "", 100, new Rounding(), "%1$.0f %3%", new EURCurrency())

    data = espData

  }

  /**
   * Description:             Finnish markka
   * ISO three-letter code:   FIM
   * Numerical Code:          246
   * Divided by:              100 penniä
   */
  class FIMCurrency extends Currency {

    val fimData = Data("Finnish markka", "FIM", 246, "mk", "", 100, new Rounding(), "%1$.2f %3%", new EURCurrency())

    data = fimData

  }

  /**
   * Description:             French franc
   * ISO three-letter code:   FRF
   * Numerical Code:          250
   * Divided by:              100 centimes
   */
  class FRFCurrency extends Currency {

    val frfData = Data("French franc", "FRF", 250, "", "", 100, new Rounding(), "%1$.2f %2%", new EURCurrency())

    data = frfData

  }

  /**
   * Description:             Greek drachma
   * ISO three-letter code:   GRD
   * Numerical Code:          300
   * Divided by:              100 lepta
   */
  class GRDCurrency extends Currency {

    val grdData = Data("Greek drachma", "GRD", 300, "", "", 100, new Rounding(), "%1$.2f %2%", new EURCurrency())

    data = grdData

  }

  /**
   * Description:             Irish punt
   * ISO three-letter code:   IEP
   * Numerical Code:          372
   * Divided by:              100 pence
   */
  class IEPCurrency extends Currency {

    val iepData = Data("Irish punt", "IEP", 372, "", "", 100, new Rounding(), "%2% %1$.2f", new EURCurrency())

    data = iepData

  }

  /**
   * Description:             Italian lira
   * ISO three-letter code:   ITL
   * Numerical Code:          380
   * Divided by:              1
   */
  class ITLCurrency extends Currency {

    val itlData = Data("Italian lira", "ITL", 380, "L", "", 1, new Rounding(), "%3% %1$.0f", new EURCurrency())

    data = itlData

  }

  /**
   * Description:             Luxembourg franc
   * ISO three-letter code:   LUF
   * Numerical Code:          442
   * Divided by:              100 centimes
   */
  class LUFCurrency extends Currency {

    val lufData = Data("Luxembourg franc", "LUF", 442, "F", "", 100, new Rounding(), "%1$.0f %3%", new EURCurrency())

    data = lufData
  }

  /**
   * Description:           Maltese lira
   * ISO three-letter code: MTL
   * Numerical code:        470
   * Divided by:            100 cents
   */
  class MTLCurrency extends Currency {

    val mtlData = Data("Maltese lira", "MTL", 470, "Lm", "", 100, Rounding(), "%3% %1$.2f")

    data = mtlData

  }

  /**
   * Description:             Dutch guilder
   * ISO three-letter code:   NLG
   * Numerical Code:          528
   * Divided by:              100 cents
   */
  class NLGCurrency extends Currency {

    val nlgData = Data("Dutch guilder", "NLG", 528, "f", "", 100, new Rounding(), "%3% %1$.2f", new EURCurrency())

    data = nlgData

  }

  /**
   * Description:             Portuguese escudo
   * ISO three-letter code:   PTE
   * Numerical Code:          620
   * Divided by:              100 centavos
   */
  class PTECurrency extends Currency {

    val pteData = Data("Portuguese escudo", "PTE", 620, "Esc", "", 100, new Rounding(), "%1$.0f %3%", new EURCurrency())

    data = pteData

  }

  /**
   * Description:             Slovak koruna
   * ISO three-letter code:   SKK
   * Numerical Code:          703
   * Divided by:              100 halierov
   */
  class SKKCurrency extends Currency {

    val skkData = Data("Slovak koruna", "SKK", 703, "Sk", "", 100, Rounding(), "%1$.2f %3%")

    data = skkData



  }

}

