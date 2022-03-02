object Parameters {
  def instance(args: Array[String]) = new Parameters(args)
}

class Parameters(args: Array[String]) extends Serializable {
  private val paramMap: Map[String, String] = args
    .map(param => {
      val pair = param.split("=", -1)
      (pair(0), pair(1))
    }).toMap
  def src_invoce : String = paramMap.getOrElse("src_invoce", "SRC_INVOCE")
  def tgt_invoce : String = paramMap.getOrElse("tgt_invoice", "TGT_INVOCE")
}
