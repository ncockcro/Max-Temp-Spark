// Gets the mex temperature for all of the months in the text file
val file = sc.textFile("/home/nick/input/temp.txt") // Open the file and create rdd
val token = file.map(x=>x.split(","))
val keyval = token.map(x=>(x(0),x(1))) // Create key value pair x(0) is the key (month) and x(1) is the value(temp)
val maxtemp = keyval.reduceByKey{case(a,b)=> if (a>b) {a} else {b} } // Compares each temp for a specific month and gets the max temp
val maxval = maxtemp.values.max

val keyvalfilter = keyval.filter{case(a,b)=> b==maxval} // Case(a,b) is the key value pair

keyvalfilter.collect.foreach(println)
