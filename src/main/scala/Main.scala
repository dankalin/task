import org.apache.spark.sql._
import org.apache.spark.sql.functions.{col, explode, when}



object Main extends App{
  implicit val spark: SparkSession = SparkSession.builder()
   .enableHiveSupport()
    .getOrCreate()
  val param = Parameters.instance(args)
  //val df=spark.read.option("multiline","true").json("data.json").write.parquet("testData") //создаем parquet с помощью json
val df=spark.read.table(param.src_invoce)                                          //.parquet("testData")
  .withColumn("col1", explode(col("inv_doc")))
  .withColumn("col2", col("col1.key"))
  .withColumn("col3", col("col1.value"))
  .withColumn("inv_value",when(col("col2")
    .isin(11,21,31,41),col("col3"))
  .otherwise("NULL"))
  .write
  .format("hive")
  .saveAsTable(param.tgt_invoce)



}