package maven_spark.co.samsung.spark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math.util.OpenIntToDoubleHashMap.Iterator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.RelationalGroupedDataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SparkSession;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Person p1 = new Person(1, "CA", "Bloggs");
    	Person p2 = new Person(2, "MA", "Cals");
    	Person p3 = new Person(3, "CA", "amper");
    	Person p4 = new Person(4, "MA", "peru");
    	List<Person> persons = new ArrayList<Person>();
    	persons.add(p1);
    	persons.add(p2);
    	persons.add(p3);
    	persons.add(p4);
    	SparkSession spark = SparkSession.builder()
    			  .master("local[*]")
    			  .appName("Example")
    			  .getOrCreate();
    			  // Java still requires of the JavaSparkContext 
    	JavaSparkContext sc = new JavaSparkContext(spark.sparkContext());  	    	
    	Dataset<Person> ds = spark.createDataset(
    			persons,
    		    Encoders.bean(Person.class)
    		);
    	List<String> cityNames = new ArrayList<String>();
    	for(Row roe :ds.groupBy(ds.col("city")).count().collectAsList())
    	{
    		System.out.println(roe.get(0));
    		cityNames.add((String)roe.get(0));
    	}
    	for(String city : cityNames)
    	{
    		Dataset<Person> ds_filtered = ds.filter(ds.col("city").equalTo(city));
    		System.out.println(city+ " = "+ds_filtered.count());
    		for(Person ps : ds_filtered.collectAsList())
    		{
    			System.out.println("City = "+ city +" Name = " +ps.getName());
    		}
    	}
    	//System.out.println(datasets.count().collect());
    }
  
}
